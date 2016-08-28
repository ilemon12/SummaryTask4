package ua.nure.jurkov.SummaryTask4.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.View.TypeDispatch;

public class LogInViewAction extends Action{
	private static final Logger LOG = Logger.getLogger(LogInViewAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		View view = new View("LogIn", TypeDispatch.FORWARD);
		
		LOG.debug("Finished processing Action");
		return view;
	}

}
