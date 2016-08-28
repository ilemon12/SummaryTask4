package ua.nure.jurkov.SummaryTask4.domain.course;

import java.util.List;

/**
 * Manager Interface of Course which declared 
 * base methods for operation with courses. 
 * 
 * @author Eugene Jurkov
 *
 */
public interface CoursesManager {
	
	/**
	 * Returned list of course sorted by kind of sort.
	 * 
	 * @param kind of sort course.
	 * @return list of course sorted by kind of sort.
	 */
	 List<Course> getAllCourses(KindOfSortCourses kind);
	 
	 /**
	  * Returned list of course sorted by name of topic.
	  * 
	  * @param topic name of topic.
	  * @return list of course sorted by name of topic.
	  */
	 List<Course> getAllCoursesByTopic(String topic);
	 
	 /**
	  * Returned created course.
	  * 
	  * @param info for create course.
	  * @return created course.
	  */
	 Course createNewCourse(CourseCreateInfo info);
	 
	 /**
	  * Returned true if course edited, success.
	  * 
	  * @param field of edit
	  * @param idCourse course of edit.
	  * @return true if edit course success.
	  */
	 boolean editCourse(EditField field, int idCourse);
	 
	 /**
	  * Delete course by id of course.
	  * 
	  * @param idCourse id course which will delete.
	  */
	 void deleteCourse(int idCourse);
	 
	 /**
	  * Add course to lecturer.
	  * 
	  * @param idLecturer who to add course.
	  * @param idCourse id course which will add.
	  */
	 void addCourseToLecturer(int idLecturer, int idCourse);
	 
	 /**
	  * Returned list of courses have not lecturer.
	  * 
	  * @return list of courses have not lecturer.
	  */
	 List<Course> getCoursesHaventLecturer();
	 
	 /**
	  * Returned true if lecturer has more one course.
	  * 
	  * @param idLecturer
	  * @return true if lecturer has more one course.
	  */
	 boolean isExistsAnyCourses(int idLecturer);
	 
	 /**
	  * Returned list of course selected by id lecturer.
	  * 
	  * @param idLecturer
	  * @return list of course selected by id lecturer.
	  */
	 List<Course> getCoursesByLecturer(int idLecturer);
	 
	 /**
	  * Returned true if student enrolled to course success.
	  * 
	  * @param idStudent
	  * @param idCourse
	  * @return true if enrolled student to course success.
	  */
	 boolean enrollStudentToCourse(int idStudent, int idCourse);
	
	 /**
	  * Returned course by id of course.
	  * 
	  * @param id
	  * @return course by id of course.
	  */
	 Course getCourseById(int id);
	 
	 /**
	  * Returned list of courses which do not start.
	  * 
	  * @param idStudent
	  * @return list of courses which do not start.
	  */
	 List<Course> getCoursesHaventStart(int idStudent);
	 
	 /**
	  * Returned list of courses which in progress.
	  * 
	  * @param idStudent
	  * @return list of courses which in progress.
	  */
	 List<Course> getCoursesInProgress(int idStudent);
	 
	 /**
	  * Returned list of evaluation student of course.
	  * 
	  * @param idStudent
	  * @return list of evaluation student of course.
	  */
	 List<EvaluationStudentOfCourse> getCourseThatOver(int idStudent);
}
