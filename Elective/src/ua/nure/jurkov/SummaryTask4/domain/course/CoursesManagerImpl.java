
package ua.nure.jurkov.SummaryTask4.domain.course;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import ua.nure.jurkov.SummaryTask4.domain.dao.CourseDao;
import ua.nure.jurkov.SummaryTask4.domain.dao.DaoFactory;

/**
 * Class implements interface CourseManager.
 * 
 * @author Eugene Jurkov
 *
 */
public class CoursesManagerImpl implements CoursesManager{
	private CourseDao courseDao; 
	
	public CoursesManagerImpl(DaoFactory daoFactory){
		courseDao = daoFactory.getCourseDao();
	}
	
	/**
	 * Returned list of courses by kind, if kind is null 
	 * returned empty list.
	 */
	@Override
	public List<Course> getAllCourses(KindOfSortCourses kind) {
		if(kind == null){ 
			return new ArrayList<>();
		}
		
		List<Course> courses = courseDao.getAllCourses(kind);
		
		return courses;
	}

	@Override
	public List<Course> getAllCoursesByTopic(String topic) {
		List<Course> courses = courseDao.getCoursesByTopic(topic);
		
		return courses;
	}
	
	/**
	 * Returned created course. if course creation is not success  
	 */
	@Override
	public Course createNewCourse(CourseCreateInfo info) {
		int idCourse = courseDao.createCourse(info);
		
		int idOfTopic = info.getIdOfTopicOfCourse();
		
		if(idCourse == -1){
			return null;
		}
		
		courseDao.addTopicToCourse(idCourse, idOfTopic);
		
		Course course = courseDao.getCourseById(idCourse);
		
		return course;
	}

	@Override
	public void deleteCourse(int idCourse) {
		courseDao.deleteCourse(idCourse);
	}
	
	/**
	 * Returned true if course edited is success.
	 * Returned false if Course do not exists by id course.
	 * Returned false if name of field is wrong. 
	 */
	@Override
	public boolean editCourse(EditField field, int idCourse) {
		String valueOfField = field.getValueOfField();
		String nameOfField = field.getNameOfField();
		
		Course course = courseDao.getCourseById(idCourse);
		
		if(course == null){
			return false;
		}
		
		if(nameOfField.equals("name")){
			courseDao.updateNameOfCourse(valueOfField, idCourse);
			
			return true;
		}
		
		if(nameOfField.equals("startDate")){
			Date date = Date.valueOf(valueOfField);
			
			courseDao.updateStartDate(date, idCourse);
			
			return true;
		}
		
		if(nameOfField.equals("endDate")){
			Date date = Date.valueOf(valueOfField);
			
			courseDao.updateEndDate(date, idCourse);
			
			return true;
		}
		
		if(nameOfField.equals("numberParticipants")){
			int number = Integer.valueOf(valueOfField);
			
			courseDao.updateNumberParticipants(number, idCourse);
			
			return true;
		}
		
		if(nameOfField.equals("topic")){
			int newTopicId = Integer.valueOf(valueOfField);
			int oldTopicId = course.getTopic().getId();
			
			courseDao.replaceCurrentTopicToNewTopic(idCourse, oldTopicId, newTopicId);
			
			return true;
		}
		
		return false;
	}
	
	@Override
	public void addCourseToLecturer(int idLecturer, int idCourse) {
		courseDao.addCourseToLecturer(idLecturer, idCourse);
	}

	@Override
	public List<Course> getCoursesHaventLecturer() {
		List<Course> courses = courseDao.getCoursesHaventLecturer();
		
		return courses;
	}
	
	/**
	 * Returned false if list of coursesId selected by idLecturer is empty.
	 */
	@Override
	public boolean isExistsAnyCourses(int idLecturer) {
		List<Course> courses =  courseDao.getCoursesByLecturer(idLecturer);
		
		if(courses.isEmpty()){
			return false;
		}
		
		return true;
	}
	
	@Override
	public List<Course> getCoursesByLecturer(int idLecturer) {
		List<Course> courses = courseDao.getCoursesByLecturer(idLecturer);
		
		return courses;
	}
	
	/**
	 * Returned false if student has enrolled.
	 */
	@Override
	public boolean enrollStudentToCourse(int idStudent, int idCourse) {
		boolean isEnrollAtThisCourse = courseDao.isEnrollStudentAtThisCourse(idStudent, idCourse);
		
		if(isEnrollAtThisCourse){
			return false;
		}
		
		courseDao.enrollStudentToCourse(idStudent, idCourse);
		
		return true;
	}

	@Override
	public Course getCourseById(int id) {
		return courseDao.getCourseById(id);
	}

	@Override
	public List<Course> getCoursesHaventStart(int idStudent) {
		Date now = nowDate();	
		
		List<Course> courses = courseDao.getCoursesStudentRegisteredButHaventStart(idStudent, now);
		
		return courses;
	}

	@Override
	public List<Course> getCoursesInProgress(int idStudent) {
		Date now = nowDate();
		
		List<Course> courses = courseDao.getCoursesInProgress(idStudent, now);
		
		return courses;
	}
	
	private Date nowDate(){
		java.util.Date d = new java.util.Date();
		
		long time = d.getTime();
		
		Date now = new Date(time);
		
		return now;
	}

	@Override
	public List<EvaluationStudentOfCourse> getCourseThatOver(int idStudent) {
		Date now = nowDate();
		
		List<EvaluationStudentOfCourse> courses = courseDao.getCoursesThatOver(idStudent, now);
		
		return courses;
	}
}
