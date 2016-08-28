 package ua.nure.jurkov.SummaryTask4.controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.View.TypeDispatch;
import ua.nure.jurkov.SummaryTask4.domain.course.Course;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManager;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManagerImpl;
import ua.nure.jurkov.SummaryTask4.domain.course.KindOfSortCourses;
import ua.nure.jurkov.SummaryTask4.domain.customer.Customer;
import ua.nure.jurkov.SummaryTask4.domain.customer.CustomerManager;
import ua.nure.jurkov.SummaryTask4.domain.customer.CustomerManagerImpl;
import ua.nure.jurkov.SummaryTask4.domain.topic.Topic;
import ua.nure.jurkov.SummaryTask4.domain.topic.TopicManager;
import ua.nure.jurkov.SummaryTask4.domain.topic.TopicManagerImpl;

public class CoursesViewAction extends Action{
	private static final Logger LOG = Logger.getLogger(CoursesViewAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Sart processing Action");
		
		View view = new View("CoursesView", TypeDispatch.FORWARD);
		
		TopicManager topicManager = new TopicManagerImpl(daoFactory);
		
		List<Topic> topics = topicManager.getAllTopics();
		LOG.trace("Got all topics " + topics);
		
		List<Course> courses =  (List<Course>) request.getAttribute("courses");
		LOG.trace("Got courses from attribute of request: " + courses);
		
		CustomerManager customerManager = new CustomerManagerImpl(daoFactory);
		
		List<Customer> lecturers = customerManager.getAllLecturers();
		LOG.trace("Got all lecturers: " + lecturers);
		
		if(courses != null){
			LOG.trace("Set courses as attribute");
			request.setAttribute("courses", courses);
		}
		else{
			CoursesManager managerCourses = new CoursesManagerImpl(daoFactory);
			
			courses = managerCourses.getAllCourses(KindOfSortCourses.BY_NAME_A_Z);
			LOG.trace("Got all courses: " + courses);
			
			request.setAttribute("courses", courses);
			LOG.trace("Set courses as attribute");
		}
		
		request.setAttribute("topics", topics);
		LOG.trace("Set topic as attribute");
		
		request.setAttribute("lecturers", lecturers);
		LOG.trace("Set lecturers as attribute");
		
		return view;
	}
}
