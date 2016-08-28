package ua.nure.jurkov.SummaryTask4.domain.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.domain.dao.ConnectionPool;
import ua.nure.jurkov.SummaryTask4.domain.dao.TopicDao;
import ua.nure.jurkov.SummaryTask4.domain.topic.Topic;

public class TopicDaoMySqlImpl implements TopicDao{
	private static final Logger LOG = Logger.getLogger(TopicDaoMySqlImpl.class);
	
	@Override
	public List<Topic> getAllTopics() {
		String sql = "SELECT * FROM electivedb.topics";
		
		List<Topic> topics = new ArrayList<>();
		
		try(Connection connection = ConnectionPool.getConnection();
			Statement statement = connection.createStatement())
		{
			ResultSet resultSet = statement.executeQuery(sql);
			
			while(resultSet.next()){
				
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				
				Topic topic = new Topic(id, name);
				
				topics.add(topic);
			}
			
			return topics;
			
		} catch(SQLException e){
			LOG.error("Can not get all topics ", e);
			
			return topics;
		}
	}

	@Override
	public Topic getTopicById(int id) {
		String sql = "SELECT * FROM electivedb.topics " +
					 "WHERE topics.topic_id = ?";
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement	= connection.prepareStatement(sql))
		{
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(!resultSet.next()){
				
				return null;
			}
			
			int idTopic = resultSet.getInt(1);
			String name = resultSet.getString(2);
			
			Topic topic = new Topic(idTopic, name);
			
			return topic;
			
		} catch(SQLException e){
			LOG.error("Can not get topic by id ", e);
			
			return null;
		}
	}

	@Override
	public Topic getTopicByCourseId(int id) {
		String sql = "SELECT topic_id, name " +
					 "FROM topics " +
					 "INNER JOIN(SELECT topics_of_courses.topic_id " +
					 			"FROM topics_of_courses " +
					 			"WHERE topics_of_courses.course_id = ?) as topicId " +
					 "ON topics.topic_id = topicID.topic_id;";
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(!resultSet.next()){
				return null;
			}
			
			int topicId = resultSet.getInt(1);
			String name = resultSet.getString(2);
			
			Topic topic = new Topic(topicId, name);
			
			return topic;
			
		} catch(SQLException e){
			LOG.error("Can not get topic by course id ", e);
			
			return null;
		}
	}
}
