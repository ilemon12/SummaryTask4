package ua.nure.jurkov.SummaryTask4.controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.domain.course.Course;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManager;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManagerImpl;

/**
 * Action which select courses by topic.
 * 
 * @author Eugene Jurkov
 *
 */
public class CoursesSelectByTopicAction extends Action{
	private static final Logger LOG = Logger.getLogger(CoursesSelectByTopicAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		String nameOfTopic = request.getParameter("nameOfTopic");
		LOG.trace("Got name of topic: " + nameOfTopic);
		
		CoursesManager coursesManager = new CoursesManagerImpl(daoFactory);
		
		List<Course> courses = coursesManager.getAllCoursesByTopic(nameOfTopic);
		LOG.trace("Got courses by topic: " + courses);
		
		request.setAttribute("courses", courses);
		LOG.trace("courses set as attribute");
		
		CoursesViewAction coursesView = new CoursesViewAction();
		
		View view =	coursesView.process(request, response);
		
		LOG.debug("Finished processing Action");
		return view;
	}
}
