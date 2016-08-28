package ua.nure.jurkov.SummaryTask4.controller.action.admin.courses;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.Action;
import ua.nure.jurkov.SummaryTask4.controller.action.View;
import ua.nure.jurkov.SummaryTask4.controller.action.View.TypeDispatch;
import ua.nure.jurkov.SummaryTask4.domain.topic.Topic;
import ua.nure.jurkov.SummaryTask4.domain.topic.TopicManager;
import ua.nure.jurkov.SummaryTask4.domain.topic.TopicManagerImpl;

public class ViewNewCourseAction extends Action{
	private static final Logger LOG = Logger.getLogger(ViewNewCourseAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		View view = new View("admin/AdminNewCourse", TypeDispatch.FORWARD);
		
		TopicManager topicManager = new TopicManagerImpl(daoFactory);
		
	 	List<Topic> topics = topicManager.getAllTopics();
	 	LOG.trace("Got all topics: " + topics);
	 	
	 	request.setAttribute("topics", topics);
	 	LOG.trace("Set as attribute topics: " + topics);
	 	LOG.debug("Finished processing Action");
	 	return view;
	}

}
