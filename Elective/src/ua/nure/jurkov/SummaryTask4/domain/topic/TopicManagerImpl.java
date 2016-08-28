package ua.nure.jurkov.SummaryTask4.domain.topic;

import java.util.List;

import ua.nure.jurkov.SummaryTask4.domain.dao.DaoFactory;
import ua.nure.jurkov.SummaryTask4.domain.dao.TopicDao;

public class TopicManagerImpl implements TopicManager{
	private TopicDao daoTopic;
	
	public TopicManagerImpl(DaoFactory daoFactory){
		daoTopic = daoFactory.getTopicDao();
	}
	
	@Override
	public List<Topic> getAllTopics() {
		List<Topic> topics = daoTopic.getAllTopics();
		
		return topics;
	}

	@Override
	public Topic getTopicByCourseId(int id) {
		Topic topic = daoTopic.getTopicByCourseId(id);
		
		return topic;
	}
}
