package ua.nure.jurkov.SummaryTask4.controller.action.admin.lecturers;

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
 * Action returns View by name of lecturer action.
 * 
 * @author Eugene Jurkov
 *
 */
public class SetLecturerOnCourseAction extends Action{
	private static final Logger LOG = Logger.getLogger(SetLecturerOnCourseAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		View view = new View("AdminViewLecturers", TypeDispatch.SEND_REDIRECT);
		
		String strIdCourse = request.getParameter("idCourse");
		String strIdLecturer = request.getParameter("idLecturer");
		LOG.trace("Got strIdCourse and strIdLecturer for request: " + 
				strIdCourse + ", " + strIdLecturer);
		
		if(!ValidatorFields.onlyNumbers(strIdLecturer) || 
				!ValidatorFields.onlyNumbers(strIdCourse)){
			LOG.trace("Invalidate fields: " + strIdCourse + " " + strIdLecturer);
			view.setNameView("admin/AdminViewLecturers");
			view.setTypeDispatch(TypeDispatch.FORWARD);
			
			return view;
		}
		
		int idCourse = Integer.valueOf(strIdCourse);
		int idLecturer = Integer.valueOf(strIdLecturer);
		
		CoursesManager courseManager = new CoursesManagerImpl(daoFactory);
		
		courseManager.addCourseToLecturer(idLecturer, idCourse);
		LOG.trace("Add course to lectturer");
		LOG.debug("Finished processing Action");
		return view;
	}

}
