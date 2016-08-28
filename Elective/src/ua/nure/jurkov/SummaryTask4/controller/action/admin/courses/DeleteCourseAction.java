package ua.nure.jurkov.SummaryTask4.controller.action.admin.courses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.Action;
import ua.nure.jurkov.SummaryTask4.controller.action.View;
import ua.nure.jurkov.SummaryTask4.controller.action.View.TypeDispatch;
import ua.nure.jurkov.SummaryTask4.controller.util.ValidatorFields;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManager;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManagerImpl;

/**
 * Action deletes course.
 * 
 * @author Eugene Jurkov
 *
 */
public class DeleteCourseAction extends Action{
	private static final Logger LOG = Logger.getLogger(DeleteCourseAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		View view = new View("ViewEditCourses", TypeDispatch.SEND_REDIRECT);
		
		String strIdCourse = request.getParameter("idCourse");
		LOG.trace("Got idCourse from request parameter: " + strIdCourse);
		
		if(!ValidatorFields.onlyNumbers(strIdCourse)){
			LOG.trace("Invalidate idCourse: " + strIdCourse);
			Action action = new ViewEditCoursesAction();
			
			view = action.process(request, response);
			
			return view;
		}
		
		int idCourse = Integer.valueOf(strIdCourse);
		
		CoursesManager coursesManager = new CoursesManagerImpl(daoFactory);
		
		coursesManager.deleteCourse(idCourse);
		LOG.trace("Course deleted");
		LOG.debug("Finished processing Action");
		return view;
	}
}
