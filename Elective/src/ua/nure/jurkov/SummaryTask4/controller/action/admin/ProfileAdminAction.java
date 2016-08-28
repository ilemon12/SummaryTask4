package ua.nure.jurkov.SummaryTask4.controller.action.admin;

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
public class ProfileAdminAction extends Action{
	private static final Logger LOG = Logger.getLogger(ProfileAdminAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		String nameAction = request.getParameter("nameOfAction");
		LOG.trace("Got nameAction from request parameter: " + nameAction);
		
		if(nameAction == null){
			nameAction = "courses";
			LOG.trace("Set nameAction: courses");
		} 
		
		Action action = AdminViewActions.getSelectedAction(nameAction);
		LOG.trace("Got action by name Action from AdminViewAction: " + action);
		
		View view = action.process(request, response);
		
		LOG.debug("Finished processing Action");
		return view;
	}
}
