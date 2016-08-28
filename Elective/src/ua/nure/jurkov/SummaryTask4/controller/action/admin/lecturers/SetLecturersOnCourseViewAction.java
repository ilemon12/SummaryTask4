package ua.nure.jurkov.SummaryTask4.controller.action.admin.lecturers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.Action;
import ua.nure.jurkov.SummaryTask4.controller.action.View;
import ua.nure.jurkov.SummaryTask4.controller.action.View.TypeDispatch;
import ua.nure.jurkov.SummaryTask4.controller.util.ValidatorFields;
import ua.nure.jurkov.SummaryTask4.domain.course.Course;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManager;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManagerImpl;

public class SetLecturersOnCourseViewAction extends Action{
	private static final Logger LOG = Logger.getLogger(SetLecturersOnCourseViewAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
	
		View view = new View("admin/SetLecturersOnCourseView", TypeDispatch.FORWARD);
		
		String strIdLecturer = request.getParameter("idLecturer");
		LOG.trace("Got idLecturer from request: " + strIdLecturer);
		
		if(!ValidatorFields.onlyNumbers(strIdLecturer)){
			LOG.trace("idLecturer is invalidate");
			view.setNameView("admin/AdminViewLecturers");
			
			return view;
		}
		
		int idLecturer = Integer.valueOf(strIdLecturer);
		
		request.setAttribute("idLecturer", idLecturer);
		LOG.trace("Set as attribute idLecturer: " + idLecturer);
		
		CoursesManager coursesManager = new CoursesManagerImpl(daoFactory);
		
		List<Course> courses = coursesManager.getCoursesHaventLecturer();
		LOG.trace("Got courses have not lecturer");
		
		request.setAttribute("courses", courses);
		LOG.trace("Set as attribute courses: " + courses);
		LOG.debug("Finished processing Action");
		return view;
	}
}
