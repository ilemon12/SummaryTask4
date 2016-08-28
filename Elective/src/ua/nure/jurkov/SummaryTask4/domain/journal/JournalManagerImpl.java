package ua.nure.jurkov.SummaryTask4.domain.journal;

import ua.nure.jurkov.SummaryTask4.domain.dao.DaoFactory;
import ua.nure.jurkov.SummaryTask4.domain.dao.JournalDao;

/**
 * Class implements JournalManager.
 * 
 * @author Eugene Jurkov
 *
 */
public class JournalManagerImpl implements JournalManager{
	private JournalDao journalDao;
	
	public JournalManagerImpl(DaoFactory daoFactory){
		journalDao = daoFactory.getJournalDao();
	}
	
	@Override
	public void estimate(int idStudent, int idCourse, int evaluation) {
		journalDao.updateEvaluation(idStudent, idCourse, evaluation);
	}

	@Override
	public Journal getJournal(int idCourse) {
		return journalDao.getJournal(idCourse);
	}
}
