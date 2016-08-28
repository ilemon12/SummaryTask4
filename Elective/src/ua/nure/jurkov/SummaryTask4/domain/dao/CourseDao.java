package ua.nure.jurkov.SummaryTask4.domain.dao;

import java.sql.Date;
import java.util.List;

import ua.nure.jurkov.SummaryTask4.domain.course.Course;
import ua.nure.jurkov.SummaryTask4.domain.course.CourseCreateInfo;
import ua.nure.jurkov.SummaryTask4.domain.course.EvaluationStudentOfCourse;
import ua.nure.jurkov.SummaryTask4.domain.course.KindOfSortCourses;

public interface CourseDao {
	
	/**
	 * Enroll student to course.
	 * 
	 * @param idStudent
	 * @param idCourse
	 */
	void enrollStudentToCourse(int idStudent, int idCourse);
	
	/**
	 * Returned true if student has enrolled at this course.
	 * 
	 * @param idStudent
	 * @param idCourse
	 * @return
	 */
	boolean isEnrollStudentAtThisCourse(int idStudent, int idCourse);
	
	/**
	 * Returned all courses by kind of sort.
	 * 
	 * @param kind
	 * @return
	 */
	List<Course> getAllCourses(KindOfSortCourses kind);
	
	/**
	 * Returned list of courses at that student registered, 
	 * but they have not start.  
	 * 
	 * @param idStudent
	 * @param now is date of today.
	 * @return list of courses at that student registered, 
	 *		   but they have not start.
	 */
	List<Course> getCoursesStudentRegisteredButHaventStart(int idStudent, Date now);
	
	/**
	 * Returned list of courses that in progress.
	 * 
	 * @param idStudent
	 * @param now is date of today.
	 * @return list of courses that in progress.
	 */
	List<Course> getCoursesInProgress(int idStudent, Date now);
	
	/**
	 * Returned list of courses that over.
	 * 
	 * @param idStudent
	 * @param now is date of today.
	 * @return list of courses that over.
	 */
	List<EvaluationStudentOfCourse> getCoursesThatOver(int idStudent, Date now);
	
	/**
	 * Returned list of courses by topic.
	 * 
	 * @param topic name of topic.
	 * @return
	 */
	List<Course> getCoursesByTopic(String topic);
	
	/**
	 * Returned list of courses by lecturer.
	 * 
	 * @param idLecturer
	 * @return list of courses by lecturer.
	 */
	List<Course> getCoursesByLecturer(int idLecturer);
	
	/**
	 * Returned list of courses that have not lecturer.
	 * 
	 * @return list of courses that have not lecturer.
	 */
	List<Course> getCoursesHaventLecturer();
	
	/**
	 * Returned course by id.
	 * 
	 * @param id
	 * @return course by id.
	 */
	Course getCourseById(int id);
	
	/**
	 * Returned id created course.
	 * 
	 * @param info
	 * @return
	 */
	int createCourse(CourseCreateInfo info);
	
	/**
	 * Returned true if topic added to course.
	 * 
	 * @param idCourse
	 * @param idTopic
	 * @return
	 */
	boolean addTopicToCourse(int idCourse, int idTopic);
	
	/**
	 * Delete course by id.
	 * 
	 * @param idCourse
	 */
	void deleteCourse(int idCourse);
	
	/**
	 * Replace current topic to new topic.
	 * 
	 * @param courseId
	 * @param oldTopicId
	 * @param NewTopicId
	 */
	void  replaceCurrentTopicToNewTopic (int courseId, int oldTopicId, int newTopicId);
	
	/**
	 * Update name of course.
	 * 
	 * @param name
	 * @param idCourse
	 */
	void updateNameOfCourse(String name, int idCourse);
	
	/**
	 * Update date of start of course.
	 * 
	 * @param date
	 * @param idCourse
	 */
	void updateStartDate(Date date, int idCourse);
	
	/**
	 * Update end of date of course.
	 * 
	 * @param date
	 * @param idCourse
	 */
	void updateEndDate(Date date, int idCourse);
	
	/**
	 * Update number participants of course.
	 * 
	 * @param number
	 * @param idCourse
	 */
	void updateNumberParticipants(int number, int idCourse);
	
	/**
	 * Add course to lecturer.
	 * 
	 * @param idLecturer
	 * @param idCourse
	 */
	void addCourseToLecturer(int idLecturer, int idCourse);
}
