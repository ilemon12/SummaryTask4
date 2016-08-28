package ua.nure.jurkov.SummaryTask4.domain.dao;

import java.util.List;

import ua.nure.jurkov.SummaryTask4.domain.topic.Topic;

public interface TopicDao {
	List<Topic> getAllTopics();
	
	Topic getTopicById(int id);
	
	Topic getTopicByCourseId(int id);
}
