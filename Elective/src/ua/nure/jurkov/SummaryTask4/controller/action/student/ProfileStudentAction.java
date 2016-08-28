package ua.nure.jurkov.SummaryTask4.controller.action.student;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.Action;
import ua.nure.jurkov.SummaryTask4.controller.action.View;

/**
 * Action which return view by specified parameter name of Action.
 * 
 * @author Eugene Jurkov
 *
 */
public class ProfileStudentAction extends Action{
	private static final Logger LOG = Logger.getLogger(ProfileStudentAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		View view = setStudentViewSelectAction(request, response);
		
		LOG.debug("Finished processing Action");
		return view;
	}
	
	private View setStudentViewSelectAction(HttpServletRequest request, HttpServletResponse response){
		
		String nameOfAction = request.getParameter("nameOfAction");
		LOG.trace("Got nameOfAction from request: nameOfAction");
		
		if(nameOfAction == null){
			nameOfAction = "dontStart";
			LOG.trace("Set nameOfAction: " + nameOfAction);
		}
		
		Action action = StudentViewActions.getSelectedAction(nameOfAction);
		LOG.trace("Got action: " + action);
		View view = action.process(request, response);
		
		LOG.debug("Finished processing Action");
		return view;
	}
}
