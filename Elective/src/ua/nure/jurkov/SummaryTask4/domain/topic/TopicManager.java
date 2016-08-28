package ua.nure.jurkov.SummaryTask4.domain.topic;

import java.util.List;

/**
 * Manager Interface of Topic which declared 
 * base methods for operation with topic. 
 * 
 * @author Eugene Jurkov
 *
 */
public interface TopicManager {
	
	/**
	 * Returned list of all topics. 
	 * 
	 * @return
	 */
	List<Topic> getAllTopics();
	
	/**
	 * Returned topic by id.
	 * 
	 * @param id
	 * @return topic by id.
	 */
	Topic getTopicByCourseId(int id);
}
