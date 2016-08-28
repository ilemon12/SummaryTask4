package ua.nure.jurkov.SummaryTask4.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;

import org.apache.log4j.Logger;

/**
 * Action sets language user`s. 
 * 
 * @author Eugene Jurkov
 *
 */
public class SetLanguageAction extends Action{
	private static final Logger LOG = Logger.getLogger(SetLanguageAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		String locale = request.getPathInfo().substring(1);
		LOG.trace("Got locale from pathInfo(): " + locale);
		
		if (locale != null && !locale.isEmpty()) {
			HttpSession session = request.getSession();
			
			Config.set(session, "javax.servlet.jsp.jstl.fmt.locale", locale);
			LOG.trace("Set locale in scope session of attribute javax.servlet.jsp.jstl.fmt.locale");
			
			session.setAttribute("locale", locale);
			LOG.trace("Set locale as attribute session " + locale);
		}
		
		Action action = new CoursesViewAction();
		
		View view = action.process(request, response);
		
		LOG.debug("Finished processing Action");
		return view;
	}
}
