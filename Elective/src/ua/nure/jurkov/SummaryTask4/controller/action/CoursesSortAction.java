package ua.nure.jurkov.SummaryTask4.controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.domain.course.Course;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManager;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManagerImpl;
import ua.nure.jurkov.SummaryTask4.domain.course.KindOfSortCourses;

/**
 * Action which sort courses.
 * 
 * @author Eugene Jurkov
 *
 */
public class CoursesSortAction extends Action{
	private static final Logger LOG = Logger.getLogger(CoursesSortAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		String sortBy = request.getParameter("nameOfSort");
		LOG.trace("Got name of sort: " + sortBy);
		
		CoursesManager courseManager = new CoursesManagerImpl(daoFactory);
		
		List<Course> courses = courseManager.getAllCourses(KindOfSortCourses.getKindByName(sortBy));
		LOG.trace("Got courses by " + sortBy);
		
		request.setAttribute("courses", courses);
		LOG.trace("Set courses as attribute");
		
		CoursesViewAction coursesViewAction = new CoursesViewAction();
		
		View view = coursesViewAction.process(request, response);
		
		LOG.debug("Finished processing Action");
		return view;
	}
}
