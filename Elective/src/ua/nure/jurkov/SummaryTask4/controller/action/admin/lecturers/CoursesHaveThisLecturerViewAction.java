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

public class CoursesHaveThisLecturerViewAction extends Action{
	private static final Logger LOG = Logger.getLogger(CoursesHaveThisLecturerViewAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		View view = new View("admin/CoursesHaveThisLecturer", TypeDispatch.FORWARD);
		
		String strIdLecturer = request.getParameter("idLecturer");
		LOG.trace("Got idLecturer form request parameter: " + strIdLecturer);
		
		if(!ValidatorFields.onlyNumbers(strIdLecturer)){
			LOG.trace("Invalidate idLecturer");
			Action action = new ViewLecturerAction();
			
			view = action.process(request, response);
			
			return view;
		}
		
		int idLecturer = Integer.valueOf(request.getParameter("idLecturer"));
		
		List<Course> courses = getCourses(idLecturer);
		LOG.trace("Got courses by idLecturer: " + courses);
		
		request.setAttribute("courses", courses);
		LOG.trace("Set as attribute courses: " + courses);
		LOG.debug("Finished processing Action");
		return view;
	}
	
	private List<Course> getCourses(int idLecturer){
		CoursesManager coursesManager = new CoursesManagerImpl(daoFactory);
		
		List<Course> courses = coursesManager.getCoursesByLecturer(idLecturer);
		
		return courses;
	}
}
