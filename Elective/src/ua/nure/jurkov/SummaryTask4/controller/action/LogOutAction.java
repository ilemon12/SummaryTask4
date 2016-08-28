package ua.nure.jurkov.SummaryTask4.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.View.TypeDispatch;

public class LogOutAction extends Action{
	private static final Logger LOG = Logger.getLogger(LogOutAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		View view = new View("CoursesView", TypeDispatch.SEND_REDIRECT);
		
		HttpSession session = request.getSession();
		
		session.invalidate();
		LOG.trace("Session invalidate");
		
		LOG.debug("Finished processing Action");
		return view;
	}

}
