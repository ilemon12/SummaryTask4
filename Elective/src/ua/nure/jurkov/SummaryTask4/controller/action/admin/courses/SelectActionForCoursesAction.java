package ua.nure.jurkov.SummaryTask4.controller.action.admin.courses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.Action;
import ua.nure.jurkov.SummaryTask4.controller.action.View;

/**
 * Action returns View by name of courses action.
 * 
 * @author Eugene Jurkov
 *
 */
public class SelectActionForCoursesAction extends Action {
	private static final Logger LOG = Logger.getLogger(SelectActionForCoursesAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		String nameOfCoursesAction = request.getParameter("nameOfCoursesAction");
		LOG.trace("Got name of courses action from request parameter: " + nameOfCoursesAction);
		
		if(nameOfCoursesAction == null){
			nameOfCoursesAction = "editCourses";
			LOG.trace("Set nameOfCoursesAction: editCourses");
		}
		
		Action action = CoursesViewActions.getSelectedAction(nameOfCoursesAction);
		LOG.trace("Got action: " + action);
		
		View view = action.process(request, response);
		
		LOG.debug("Finished processing Action");
		return view;
	}
}
