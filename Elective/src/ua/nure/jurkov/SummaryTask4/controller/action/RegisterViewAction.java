package ua.nure.jurkov.SummaryTask4.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.View.TypeDispatch;

public class RegisterViewAction extends Action{
	private static final Logger LOG = Logger.getLogger(RegisterViewAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		View view = new View("Register", TypeDispatch.FORWARD);
		
		LOG.debug("Finished processing Action");
		return view;
	}

}
