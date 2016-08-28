package ua.nure.jurkov.SummaryTask4.domain.dao.mysql;

import ua.nure.jurkov.SummaryTask4.domain.dao.CourseDao;
import ua.nure.jurkov.SummaryTask4.domain.dao.CustomerDao;
import ua.nure.jurkov.SummaryTask4.domain.dao.DaoFactory;
import ua.nure.jurkov.SummaryTask4.domain.dao.JournalDao;
import ua.nure.jurkov.SummaryTask4.domain.dao.TopicDao;

public class MySqlDaoFactory extends DaoFactory{

	@Override
	public CourseDao getCourseDao() {
		return new CourseDaoMySqlImpl();
	}

	@Override
	public TopicDao getTopicDao() {
		return new TopicDaoMySqlImpl();
	}

	@Override
	public CustomerDao getCustomerDao() {
		return new CustomerDaoMySqlImpl();
	}

	@Override
	public JournalDao getJournalDao() {
		return new JournalDaoMySqlImpl();
	}

}
