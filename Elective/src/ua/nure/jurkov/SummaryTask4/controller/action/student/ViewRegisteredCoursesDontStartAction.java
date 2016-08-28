package ua.nure.jurkov.SummaryTask4.controller.action.student;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.Action;
import ua.nure.jurkov.SummaryTask4.controller.action.View;
import ua.nure.jurkov.SummaryTask4.controller.action.View.TypeDispatch;
import ua.nure.jurkov.SummaryTask4.domain.course.Course;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManager;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManagerImpl;
import ua.nure.jurkov.SummaryTask4.domain.customer.Customer;

public class ViewRegisteredCoursesDontStartAction extends Action{
	private static final Logger LOG = Logger.getLogger(ViewRegisteredCoursesDontStartAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		View view = new View("CoursesViewForStudent", TypeDispatch.FORWARD);
		
		CoursesManager coursesManager = new CoursesManagerImpl(daoFactory);
		
		HttpSession session = request.getSession();
		
		Customer customer = (Customer)session.getAttribute("customer");
		LOG.trace("Got customer from session: " + customer);
		
		int idStudent = customer.getId();
		
		List<Course> courses = coursesManager.getCoursesHaventStart(idStudent);
		LOG.trace("Got courses by idStudent: " + idStudent + ", courses: " + courses);
		
		request.setAttribute("courses", courses);
		LOG.trace("Set as attribute courses: " + courses);
		LOG.debug("Start processing Action");
		return view;
	}
}
