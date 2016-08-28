package ua.nure.jurkov.SummaryTask4.controller.action.lecturer;

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

public class ViewCoursesOfLecturerAction extends Action{
	private static final Logger LOG = Logger.getLogger(ViewCoursesOfLecturerAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		View view = new View("CoursesOfLecturer", TypeDispatch.FORWARD);
		
		HttpSession session = request.getSession();
		
		Customer customer = (Customer)session.getAttribute("customer");
		LOG.trace("Got customer from session: " + customer);
		
		CoursesManager coursesManager = new CoursesManagerImpl(daoFactory);
		
		List<Course> courses = coursesManager.getCoursesByLecturer(customer.getId());
		LOG.trace("Got courses by id: " + customer.getId() + ", --> " + courses);
		
		request.setAttribute("courses", courses);
		LOG.trace("Set as attribute courses: " + courses);
		LOG.debug("Finished processing Action");
		return view;
	}
}
