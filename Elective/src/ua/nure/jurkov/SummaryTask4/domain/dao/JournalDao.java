package ua.nure.jurkov.SummaryTask4.domain.dao;

import ua.nure.jurkov.SummaryTask4.domain.journal.Journal;

public interface JournalDao {
	
	/**
	 * Update evaluation.
	 * 
	 * @param idStudent
	 * @param idCourse
	 * @param evaluation
	 */
	void updateEvaluation(int idStudent, int idCourse, int evaluation);
	
	/**
	 * Get journal by id of course.
	 * 
	 * @param idCourse
	 * @return
	 */
	Journal getJournal(int idCourse);
}
