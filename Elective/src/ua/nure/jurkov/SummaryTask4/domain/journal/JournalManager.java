package ua.nure.jurkov.SummaryTask4.domain.journal;

/**
 * Manager Interface of Journal which declared 
 * base methods for operation with journal. 
 * 
 * @author Eugene Jurkov
 *
 */
public interface JournalManager {
	
	/**
	 * Estimate student.
	 * 
	 * @param idStudent
	 * @param idCourse
	 * @param evaluation
	 */
	void estimate(int idStudent, int idCourse, int evaluation);
	
	/**
	 * Returned journal by id of course.
	 * 
	 * @param idCourse
	 * @return journal by id of course.
	 */
	Journal getJournal(int idCourse);
}
