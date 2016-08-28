package ua.nure.jurkov.SummaryTask4.controller.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.util.ValidatorFields;
import ua.nure.jurkov.SummaryTask4.domain.course.Course;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManager;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManagerImpl;

/**
 * Action which select courses by lecturer.
 * 
 * @author Eugene Jurkov
 *
 */
public class CoursesSelectByLecturerAction extends Action{
	private static final Logger LOG = Logger.getLogger(CoursesSelectByLecturerAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		String strIdLecturer = request.getParameter("idLecturer");
		LOG.trace("Got idLecturer " + strIdLecturer);
		
		CoursesViewAction coursesView = new CoursesViewAction();
		
		View view = coursesView.process(request, response);
		
		if(!ValidatorFields.onlyNumbers(strIdLecturer)){
			LOG.trace("idLecturer invalid " + strIdLecturer);
			return view;
		}
		
		int idLecturer = Integer.valueOf(request.getParameter("idLecturer"));
	
		CoursesManager coursesManager = new CoursesManagerImpl(daoFactory);
		
		List<Course> courses = coursesManager.getCoursesByLecturer(idLecturer);
		LOG.trace("Got courses by idLecturer: " + idLecturer + ", courses: " + courses);
		
		request.setAttribute("courses", courses);
		LOG.trace("courses set as attribute");
		
		LOG.debug("Finished processing Action");
		return view;
	}

}
