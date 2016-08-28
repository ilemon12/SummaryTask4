package ua.nure.jurkov.SummaryTask4.controller.action.lecturer;

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
public class ProfileLecturerAction extends Action{
	private static final Logger LOG = Logger.getLogger(ProfileLecturerAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		String nameOfAction = request.getParameter("nameAction");
		LOG.trace("Got nameAction from request: " + nameOfAction);
		
		if(nameOfAction == null){
			nameOfAction = "courses";
			LOG.trace("Set nameOfAction: " + nameOfAction);
		}
		
		Action action = LecturerViewActions.getSelectedAction(nameOfAction);
		LOG.trace("Got action: " + action);
		
		View view = action.process(request, response);
		LOG.debug("Finished processing Action");
		return view;
	}

}
