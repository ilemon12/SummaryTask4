package ua.nure.jurkov.SummaryTask4.domain.dao.mysql;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.domain.course.Course;
import ua.nure.jurkov.SummaryTask4.domain.course.CourseCreateInfo;
import ua.nure.jurkov.SummaryTask4.domain.course.DateOfCourse;
import ua.nure.jurkov.SummaryTask4.domain.course.EvaluationStudentOfCourse;
import ua.nure.jurkov.SummaryTask4.domain.course.KindOfSortCourses;
import ua.nure.jurkov.SummaryTask4.domain.course.NumberParticipants;
import ua.nure.jurkov.SummaryTask4.domain.dao.ConnectionPool;
import ua.nure.jurkov.SummaryTask4.domain.dao.CourseDao;
import ua.nure.jurkov.SummaryTask4.domain.topic.Topic;

/**
 * Class implements CourseDao interface. 
 * 
 * @author Eugene Jurkov
 *
 */
public class CourseDaoMySqlImpl implements CourseDao{
	private static final Logger LOG = Logger.getLogger(CourseDaoMySqlImpl.class);
	
	@Override
	public List<Course> getAllCourses(KindOfSortCourses kind) {
		
		String sql = getSqlForSort(kind);
		
		List<Course> courses = new ArrayList<>();
		
		try(Connection connection = ConnectionPool.getConnection();
			Statement statement = connection.createStatement())
		{
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				Date startDate = resultSet.getDate(3);
				Date endDate = resultSet.getDate(4);
				int durationDays = resultSet.getInt(5);
				int numberParticipants = resultSet.getInt(6);
				int numberRegistered = resultSet.getInt(7);
				int idTopic = resultSet.getInt(8);
				String nameTopic = resultSet.getString(9);
				
				DateOfCourse dateOfCourse = new DateOfCourse(startDate, 
						endDate, durationDays);
				
				NumberParticipants numberOfParticipants = new NumberParticipants(
						numberParticipants, numberRegistered);
				
				Topic topic = new Topic(idTopic, nameTopic);
				
				Course course = new Course(id, name, dateOfCourse, 
						numberOfParticipants, topic);
				
				courses.add(course);
			}
			
			return courses;
			
		} catch(SQLException e){
			LOG.error("Can not get all courses ", e);
			
			return courses;
		}
		
	}
	
	private String getSqlForSort(KindOfSortCourses kind){
		
		String sql = "SELECT * " +
					 "FROM courses " +
					 "INNER JOIN(SELECT topics.topic_id, topics.name as topic_name, topics_of_courses.course_id " +
					 			"FROM topics " +
					 			"INNER JOIN topics_of_courses " +
					 			"ON topics.topic_id = topics_of_courses.topic_id) as topic " +
					 "ON courses.id_course = topic.course_id  ORDER BY ";
		
		if(kind == KindOfSortCourses.BY_NAME_A_Z){
			sql = sql + "name";
			
			return sql;
		}
		
		if(kind == KindOfSortCourses.BY_NAME_Z_A){
			sql = sql + "name DESC";
			
			return sql;
		}
		
		if(kind == KindOfSortCourses.BY_DURATION){
			sql = sql + "duration_day";
			
			return sql;
		}
		
		if(kind == KindOfSortCourses.BY_REGISTERED_STUDENTS){
			sql = sql + "number_registered";
			
			return sql;
		}
		
		return null;
	}

	@Override
	public List<Course> getCoursesByTopic(String topic) {
		
		String sql = "SELECT courses.id_course, courses.name, courses.start_date, " +
					 		 "courses.end_date, courses.duration_day, courses.number_participants, " +
					 		 "courses.number_registered, topics_courses.topic_id " +
					 "FROM courses " +
					 "INNER JOIN (SELECT topics_of_courses.course_id, topics.topic_id, topics.name as name_of_topic " +
					 			 "FROM topics " +
					 			 "INNER JOIN topics_of_courses " +
					 			 "ON topics.topic_id = topics_of_courses.topic_id) as topics_courses " +
					 "ON courses.id_course = topics_courses.course_id " +   
					 "WHERE topics_courses.name_of_topic = ?;";	
		
		List<Course> courses = new ArrayList<>();
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{	
			statement.setString(1, topic);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				Date startDate = resultSet.getDate(3);
				Date endDate = resultSet.getDate(4);
				int durationDays = resultSet.getInt(5);
				int numberParticipants = resultSet.getInt(6);
				int numberRegistered = resultSet.getInt(7);
				int topicId = resultSet.getInt(8);
				
				DateOfCourse dateOfCourse = new DateOfCourse(startDate, 
						endDate, durationDays);
				
				NumberParticipants numberOfParticipants = new NumberParticipants(
						numberParticipants, numberRegistered);
				
				Topic topicOfCourse = new Topic(topicId, topic);
				
				Course course = new Course(id, name, dateOfCourse, numberOfParticipants, topicOfCourse);
				
				courses.add(course);
			}
			
			return courses;
			
		} catch(SQLException e){
			LOG.error("Can not get courses by topic ", e);
			
			return courses;
		}
	}

	@Override
	public int createCourse(CourseCreateInfo info) {
		
		String sql = "INSERT INTO courses " +
					 "(name, start_date, end_date, duration_day, number_participants, number_registered) " +
					 "VALUES(?, ?, ?, DATEDIFF(end_date, start_date), ?, ?)";
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = 
					connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
		{
			statement.setString(1, info.getName());
			statement.setDate(2, info.getStartDate());
			statement.setDate(3, info.getEndDate());
			statement.setInt(4, info.getNumberOfParticipants());
			statement.setInt(5, 0);
			
			int updateRow = statement.executeUpdate();
			
			if(updateRow == 1){
				
				ResultSet resultSetId = statement.getGeneratedKeys();
				
				if(resultSetId.next()){
					int id = resultSetId.getInt(1);
					
					return id;
				}
				
			}
			
			return -1;
			
		} catch(SQLException e){
			LOG.error("Can not create course", e);
			
			return -1;
		}
	}

	@Override
	public boolean addTopicToCourse(int idCourse, int idTopic) {
		
		String sql = "INSERT INTO topics_of_courses " +
				 	 "(topic_id, course_id) " +
				 	 "VALUES(?, ?)";
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{
			statement.setInt(1, idTopic);
			statement.setInt(2, idCourse);
			
			int updateRow = statement.executeUpdate();
			
			if(updateRow == 1){
				return true;
			}
			
			return false;
			
		} catch(SQLException e){
			LOG.error("Can not add topic to course ", e);
			
			return false;
		}
	}

	@Override
	public Course getCourseById(int id) {
		String sql = "SELECT * " +
				 	 "FROM courses " +
				 	 "INNER JOIN(SELECT topics.topic_id, topics.name as topic_name, topics_of_courses.course_id " +
				 				"FROM topics " +
				 				"INNER JOIN topics_of_courses " +
				 				"ON topics.topic_id = topics_of_courses.topic_id) as topic " +
				 	 "ON courses.id_course = topic.course_id " + 
				 	 "WHERE courses.id_course = ?";
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(!resultSet.next()){
				return null;
			}
			
			String name = resultSet.getString(2);
			Date startDate = resultSet.getDate(3);
			Date endDate = resultSet.getDate(4);
			int durationDays = resultSet.getInt(5);
			int numberParticipants = resultSet.getInt(6);
			int numberRegistered = resultSet.getInt(7);
			int idTopic = resultSet.getInt(8);
			String nameTopic = resultSet.getString(9);
			
			DateOfCourse dateOfCourse = new DateOfCourse(startDate, 
					endDate, durationDays);
			
			NumberParticipants numberOfParticipants = new NumberParticipants(
					numberParticipants, numberRegistered);
			
			Topic topic = new Topic(idTopic, nameTopic);
			
			Course course = new Course(id, name, dateOfCourse, 
					numberOfParticipants, topic);
			
			return course;
			
		} catch(SQLException e){
			LOG.error("Can not get course by id ", e);
			System.out.println("Failed get course by id " + e);
			
			return null;
		}
	}

	@Override
	public void replaceCurrentTopicToNewTopic(int courseId, int oldTopicId, int newTopicId) {
		String sql = "UPDATE topics_of_courses " + 
					 "SET topic_id = ? " +
					 "WHERE topic_id = ? AND course_id = ?";
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{	
			statement.setInt(1, newTopicId);
			statement.setInt(2, oldTopicId);
			statement.setInt(3, courseId);
			
			statement.executeUpdate();
			
		} catch(SQLException e){
			LOG.error("Can not replace current topic to new topic ", e);
		}
	}

	@Override
	public void updateNameOfCourse(String name, int idCourse) {
		String sql = "UPDATE courses " +
					 "SET courses.name = ? " +
					 "WHERE courses.id_course = ?";
		
		try(Connection connection = ConnectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql))
			{	
				statement.setString(1, name);
				statement.setInt(2, idCourse);
				
				statement.executeUpdate();
				
			} catch(SQLException e){
				LOG.error("Can not update name of course ", e);
			}
	}

	@Override
	public void updateStartDate(Date date, int idCourse) {
		String sql = "UPDATE courses " +
				 	 "SET courses.start_date = ?, courses.duration_day = DATEDIFF(end_date, start_date) " +
				 	 "WHERE courses.id_course = ?";
	
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{	
			statement.setDate(1, date);
			statement.setInt(2, idCourse);
			
			statement.executeUpdate();
			
		} catch(SQLException e){
			LOG.error("Failed update start date of course ", e);
		}
		
	}

	@Override
	public void updateEndDate(Date date, int idCourse) {
		String sql = "UPDATE courses " +
				 	 "SET courses.end_date = ?, courses.duration_day = DATEDIFF(end_date, start_date) " +
				 	 "WHERE courses.id_course = ?";
	
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{	
			statement.setDate(1, date);
			statement.setInt(2, idCourse);
			
			statement.executeUpdate();
			
		} catch(SQLException e){
			LOG.error("Failed update end date of course ", e);
		}
	}

	@Override
	public void updateNumberParticipants(int number, int idCourse) {
		String sql = "UPDATE courses " +
				 	 "SET courses.number_participants = ? " +
				 	 "WHERE courses.id_course = ?";
	
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{	
			statement.setInt(1, number);
			statement.setInt(2, idCourse);
			
			statement.executeUpdate();
			
		} catch(SQLException e){
			LOG.error("Can not update number participants of course ", e);
		}
	}
	
	/**
	 * Delete course from topics, journal, lecturers, courses.
	 */
	@Override
	public void deleteCourse(int idCourse) {
		String sqlDeleteFromTopicsCourses = "DELETE FROM topics_of_courses " +
											"WHERE topics_of_courses.course_id = ?";
		
		String sqlDeleteFromJournal = "DELETE FROM journal " +
									  "WHERE journal.course_id = ?";
		
		String sqlDeleteFromCoursesLecturers = "DELETE FROM courses_of_lecturer " +
											   "WHERE courses_of_lecturer.id_course = ?";
		
		String sqlDeleteFromCourses = "DELETE FROM courses " +
									  "WHERE courses.id_course = ?";
		
		try(Connection connection = ConnectionPool.getConnection();
				
			PreparedStatement statementDeleteTopicsCourses = 
					connection.prepareStatement(sqlDeleteFromTopicsCourses);
				
			PreparedStatement statementDeleteFromJournal = 
					connection.prepareStatement(sqlDeleteFromJournal);
			
			PreparedStatement statementDeleteFromCoursesLecturers =
					connection.prepareStatement(sqlDeleteFromCoursesLecturers);
				
			PreparedStatement statementDeleteFromCourse = 
					connection.prepareStatement(sqlDeleteFromCourses);)
		{
			connection.setAutoCommit(false);
			
			statementDeleteTopicsCourses.setInt(1, idCourse);
			statementDeleteFromJournal.setInt(1, idCourse);
			statementDeleteFromCoursesLecturers.setInt(1, idCourse);
			statementDeleteFromCourse.setInt(1, idCourse);
			
			statementDeleteTopicsCourses.executeUpdate();
			statementDeleteFromJournal.executeUpdate();
			statementDeleteFromCoursesLecturers.executeUpdate();
			statementDeleteFromCourse.executeUpdate();
			
			connection.commit();
			
		} catch(SQLException e){
			LOG.error("Can not delete course ", e);
		}
	}

	@Override
	public void addCourseToLecturer(int idLecturer, int idCourse) {
		String sql = "INSERT INTO courses_of_lecturer " +
					 "(id_course, lecturer_id) " +
					 "VALUES(?, ?)";
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{	
			statement.setInt(1, idCourse);
			statement.setInt(2, idLecturer);
			
			statement.executeUpdate();
			
		} catch(SQLException e){
			LOG.error("Can not add course to lecturer ", e);
		}
	}

	@Override
	public List<Course> getCoursesHaventLecturer() {
		String sql = "SELECT topics_id_with_courses.id_course, topics_id_with_courses.name as name_of_course, " + 
	   						"topics_id_with_courses.start_date, topics_id_with_courses.end_date, " +
	   						"topics_id_with_courses.duration_day, topics_id_with_courses.number_participants, " +
	   						"topics_id_with_courses.number_registered, " +
	   						"topics.topic_id, topics.name as name_of_topic " +
	   				 "FROM topics " +
	   				 "INNER JOIN (SELECT * " +
	   			 	 			 "FROM topics_of_courses " +
	   			 	 			 "INNER JOIN(SELECT id_course, name, start_date, end_date, " +
	   			 			 		   				"duration_day, number_participants, number_registered " +
	   			 			 		   		 "FROM courses " +
	   			 			 		   		 "LEFT JOIN (SELECT id_course as course_id, courses_of_lecturer.lecturer_id " +
	   			 			 		   		 		    "FROM courses_of_lecturer ) as courses_id " +
	   			 			 		   		 "ON courses.id_course = courses_id.course_id " + 
	   			 			 		   		 "WHERE courses_id.lecturer_id IS NULL ) as all_courses " + 			 
	   			 			 	"ON topics_of_courses.course_id = all_courses.id_course ) as topics_id_with_courses " +           
	   			 	"ON topics.topic_id = topics_id_with_courses.topic_id ";
		
		List<Course> courses = new ArrayList<>();
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{		
			ResultSet resultSet = statement.executeQuery();
				
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				Date startDate = resultSet.getDate(3);
				Date endDate = resultSet.getDate(4);
				int durationDays = resultSet.getInt(5);
				int numberParticipants = resultSet.getInt(6);
				int numberRegistered = resultSet.getInt(7);
				int idTopic = resultSet.getInt(8);
				String nameTopic = resultSet.getString(9);
				
				DateOfCourse dateOfCourse = new DateOfCourse(startDate, 
						endDate, durationDays);
				
				NumberParticipants numberOfParticipants = new NumberParticipants(
						numberParticipants, numberRegistered);
				
				Topic topic = new Topic(idTopic, nameTopic);
				
				Course course = new Course(id, name, dateOfCourse, 
						numberOfParticipants, topic);
				
				courses.add(course);
			}	
				
		} catch(SQLException e){
			LOG.error("Can not get course have not lecturer ", e);
		}
		
		return courses;
	}

	@Override
	public List<Course> getCoursesByLecturer(int idLecturer) {
		String sql = "SELECT topics_id_with_courses.id_course, topics_id_with_courses.name as name_of_course, " + 
							"topics_id_with_courses.start_date, topics_id_with_courses.end_date, " +
							"topics_id_with_courses.duration_day, topics_id_with_courses.number_participants, " +
							"topics_id_with_courses.number_registered, " +
							"topics.topic_id, topics.name as name_of_topic " +
					 "FROM topics " +
					 "INNER JOIN (SELECT * " +
		 	 			 		 "FROM topics_of_courses " +
		 	 			 		 "INNER JOIN(SELECT id_course, name, start_date, end_date, " +
		 			 		   					   "duration_day, number_participants, number_registered " +
		 			 		   				"FROM courses " +
		 			 		   				"INNER JOIN (SELECT id_course as course_id FROM courses_of_lecturer " +
		 			 		   		 			 		"WHERE lecturer_id = ? ) as courses_id " +
		 			 		   		 		"ON courses.id_course = courses_id.course_id) as all_courses " +
		 			 		   	 "ON topics_of_courses.course_id = all_courses.id_course ) as topics_id_with_courses " +           
		 			 "ON topics.topic_id = topics_id_with_courses.topic_id ";
	
		List<Course> courses = new ArrayList<>();
	
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{
			statement.setInt(1, idLecturer);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				Date startDate = resultSet.getDate(3);
				Date endDate = resultSet.getDate(4);
				int durationDays = resultSet.getInt(5);
				int numberParticipants = resultSet.getInt(6);
				int numberRegistered = resultSet.getInt(7);
				int idTopic = resultSet.getInt(8);
				String nameTopic = resultSet.getString(9);
			
				DateOfCourse dateOfCourse = new DateOfCourse(startDate, 
						endDate, durationDays);
			
				NumberParticipants numberOfParticipants = new NumberParticipants(
						numberParticipants, numberRegistered);
				
				Topic topic = new Topic(idTopic, nameTopic);
			
				Course course = new Course(id, name, dateOfCourse, 
						numberOfParticipants, topic);
			
				courses.add(course);
			}	
			
		} catch(SQLException e){
			LOG.error("Can not get course by id this lecturer ", e);
		}
	
		return courses;
	}

	@Override
	public void enrollStudentToCourse(int idStudent, int idCourse) {
		String sqlForCoursesStudents = "INSERT INTO journal " +
					 				   "(student_id, course_id) " +
					 				   "VALUES(?, ?); ";
		
		String sqlUpdateRegistered = "UPDATE courses " +
									 "SET number_registered = number_registered + 1 " +
									 "WHERE id_course  = ?;";
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statementCoursesStudents = 
					connection.prepareStatement(sqlForCoursesStudents);
			PreparedStatement statementUpdateRegistered = 
					connection.prepareStatement(sqlUpdateRegistered))
		{	
			connection.setAutoCommit(false);
			
			statementCoursesStudents.setInt(1, idStudent);
			statementCoursesStudents.setInt(2, idCourse);
			statementCoursesStudents.executeUpdate();
			
			statementUpdateRegistered.setInt(1, idCourse);
			statementUpdateRegistered.executeUpdate();
			
			connection.commit();
			
		} catch(SQLException e){
			LOG.error("Can not enroll student to course ", e);
		}
	}

	@Override
	public boolean isEnrollStudentAtThisCourse(int idStudent, int idCourse) {
		String sql = "SELECT student_id " +
					 "FROM journal " +
					 "WHERE student_id = ? AND course_id = ?";
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{
			statement.setInt(1, idStudent);
			statement.setInt(2, idCourse);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(!resultSet.next()){
				return false;
			}
			
			return true;
		} catch(SQLException e){
			LOG.error("Can not is enroll student at this course ", e);
			
			return false;
		}
	}

	@Override
	public List<Course> getCoursesStudentRegisteredButHaventStart(int idStudent, Date now) {
		String sql = "SELECT topics_id_with_courses.id_course, topics_id_with_courses.name as name_of_course,  " + 
	   						"topics_id_with_courses.start_date, topics_id_with_courses.end_date, " +
	   						"topics_id_with_courses.duration_day, topics_id_with_courses.number_participants, " + 
	   						"topics_id_with_courses.number_registered, " +
	   						"topics.topic_id, topics.name as name_of_topic " + 
	   				 "FROM topics " +
	   				 "INNER JOIN (SELECT * " + 
	   			 	 			 "FROM topics_of_courses " + 
	   			 	 			 "INNER JOIN(SELECT id_course, name, start_date, end_date, " + 
	   			 			 		   				"duration_day, number_participants, number_registered " + 
	   			 			 		   		"FROM courses " +
	   			 			 		   		"INNER JOIN (SELECT course_id, student_id " +
	   			 			 		   		 		    "FROM journal " +
	   			 			 		   		 		    "WHERE student_id = ?) as courses_of_student " + 
	   			 			 		   		 "ON courses.id_course = courses_of_student.course_id ) as all_courses " +
	   			 			 	 "ON topics_of_courses.course_id = all_courses.id_course " + 
                                 "WHERE start_date >= ?) as topics_id_with_courses " +
	   			 	"ON topics.topic_id = topics_id_with_courses.topic_id ;";
		
		List<Course> courses = new ArrayList<>();
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{
			statement.setInt(1, idStudent);
			statement.setDate(2, now);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				Date startDate = resultSet.getDate(3);
				Date endDate = resultSet.getDate(4);
				int durationDays = resultSet.getInt(5);
				int numberParticipants = resultSet.getInt(6);
				int numberRegistered = resultSet.getInt(7);
				int idTopic = resultSet.getInt(8);
				String nameTopic = resultSet.getString(9);
			
				DateOfCourse dateOfCourse = new DateOfCourse(startDate, 
						endDate, durationDays);
			
				NumberParticipants numberOfParticipants = new NumberParticipants(
						numberParticipants, numberRegistered);
				
				Topic topic = new Topic(idTopic, nameTopic);
			
				Course course = new Course(id, name, dateOfCourse, 
						numberOfParticipants, topic);
			
				courses.add(course);
			}	
			
			return courses;
			
		} catch(SQLException e){
			LOG.error("Failed get courses student registered but do not start ", e);
			
			return courses;
		}
	}

	@Override
	public List<Course> getCoursesInProgress(int idStudent, Date now) {
		String sql = "SELECT topics_id_with_courses.id_course, topics_id_with_courses.name as name_of_course,  " + 
							"topics_id_with_courses.start_date, topics_id_with_courses.end_date, " +
							"topics_id_with_courses.duration_day, topics_id_with_courses.number_participants, " + 
							"topics_id_with_courses.number_registered, " +
							"topics.topic_id, topics.name as name_of_topic " + 
					"FROM topics " +
					"INNER JOIN (SELECT * " + 
								"FROM topics_of_courses " + 
								"INNER JOIN(SELECT id_course, name, start_date, end_date, " + 
		 			 		   					  "duration_day, number_participants, number_registered " + 
		 			 		   			   "FROM courses " +
		 			 		   			   "INNER JOIN (SELECT course_id, student_id " +
		 			 		   			   			   "FROM journal " +
		 			 		   			   			   "WHERE student_id = ?) as courses_of_student " + 
		 			 		   			   "ON courses.id_course = courses_of_student.course_id ) as all_courses " +
		 			 		   	"ON topics_of_courses.course_id = all_courses.id_course " + 
		 			 		   	"WHERE start_date <= ? AND end_date >= ?) as topics_id_with_courses " +
		 			 "ON topics.topic_id = topics_id_with_courses.topic_id ;";

		List<Course> courses = new ArrayList<>();

		try(Connection connection = ConnectionPool.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql))
		{
		statement.setInt(1, idStudent);
		statement.setDate(2, now);
		statement.setDate(3, now);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()){
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			Date startDate = resultSet.getDate(3);
			Date endDate = resultSet.getDate(4);
			int durationDays = resultSet.getInt(5);
			int numberParticipants = resultSet.getInt(6);
			int numberRegistered = resultSet.getInt(7);
			int idTopic = resultSet.getInt(8);
			String nameTopic = resultSet.getString(9);
		
			DateOfCourse dateOfCourse = new DateOfCourse(startDate, 
					endDate, durationDays);
		
			NumberParticipants numberOfParticipants = new NumberParticipants(
					numberParticipants, numberRegistered);
			
			Topic topic = new Topic(idTopic, nameTopic);
		
			Course course = new Course(id, name, dateOfCourse, 
					numberOfParticipants, topic);
		
			courses.add(course);
		}	
		
		return courses;
		
		} catch(SQLException e){
			LOG.error("Can not get courses student in progress ", e);
		
			return courses;
		}
	}

	@Override
	public List<EvaluationStudentOfCourse> getCoursesThatOver(int idStudent, Date now) {
		String sql = "SELECT topics_id_with_courses.id_course, topics_id_with_courses.name as name_of_course,  " + 
							"topics_id_with_courses.start_date, topics_id_with_courses.end_date, " +
							"topics_id_with_courses.duration_day, topics_id_with_courses.number_participants, " + 
							"topics_id_with_courses.number_registered, " +
							"topics.topic_id, topics.name as name_of_topic, topics_id_with_courses.evaluation " + 
					 "FROM topics " +
					 "INNER JOIN (SELECT * " + 
					 			 "FROM topics_of_courses " + 
					 			 "INNER JOIN(SELECT id_course, name, start_date, end_date, " + 
					 			 				   "duration_day, number_participants, " +
					 			 				   "number_registered, evaluation " + 
					 			 		    "FROM courses " +
					 			 		    "INNER JOIN (SELECT * " +
					 			 		    			"FROM journal " +
					 			 		    			"WHERE student_id = ?) as courses_of_student " + 
					 			 		    "ON courses.id_course = courses_of_student.course_id ) as all_courses " +
					 			 "ON topics_of_courses.course_id = all_courses.id_course " + 
					 			 "WHERE end_date <= ?) as topics_id_with_courses " +
					 "ON topics.topic_id = topics_id_with_courses.topic_id ;";

		List<EvaluationStudentOfCourse> courses = new ArrayList<>();
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{
		statement.setInt(1, idStudent);
		statement.setDate(2, now);
		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()){
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			Date startDate = resultSet.getDate(3);
			Date endDate = resultSet.getDate(4);
			int durationDays = resultSet.getInt(5);
			int numberParticipants = resultSet.getInt(6);
			int numberRegistered = resultSet.getInt(7);
			int idTopic = resultSet.getInt(8);
			String nameTopic = resultSet.getString(9);
			int evaluation = resultSet.getInt(10);
			
			DateOfCourse dateOfCourse = new DateOfCourse(startDate, 
					endDate, durationDays);
			
			NumberParticipants numberOfParticipants = new NumberParticipants(
					numberParticipants, numberRegistered);
			
			Topic topic = new Topic(idTopic, nameTopic);
			
			Course course = new Course(id, name, dateOfCourse, 
					numberOfParticipants, topic);
			
			EvaluationStudentOfCourse evaluationOfCourse = 
					new EvaluationStudentOfCourse(course, evaluation);
			
			courses.add(evaluationOfCourse);
		}	
		
		return courses;
		
		} catch(SQLException e){
			LOG.error("Can not get courses that over of student ", e);
		
			return courses;
		}
	}
}
