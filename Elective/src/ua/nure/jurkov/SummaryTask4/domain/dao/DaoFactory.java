package ua.nure.jurkov.SummaryTask4.domain.dao;

import ua.nure.jurkov.SummaryTask4.domain.dao.mysql.MySqlDaoFactory;

public abstract class DaoFactory {
	
	  public static final int MY_SQL = 1;
	  
	  public abstract CourseDao getCourseDao();
	  public abstract TopicDao getTopicDao();
	  public abstract CustomerDao getCustomerDao();
	  public abstract JournalDao getJournalDao();
	  
	  public static DaoFactory getDAOFactory(
	      int whichFactory) {
	 
		  switch (whichFactory) {
	      	case MY_SQL:
	      		return new MySqlDaoFactory();

	      	default    :
	      		return null;
	    }
	  }
}
