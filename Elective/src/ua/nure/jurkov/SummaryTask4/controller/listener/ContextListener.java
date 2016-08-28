package ua.nure.jurkov.SummaryTask4.controller.listener;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Application Lifecycle Listener implementation class ContextListener
 *
 */
@WebListener
public class ContextListener implements ServletContextListener {
	private static final Logger LOG = Logger.getLogger(ContextListener.class);

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
    	LOG.trace("Servlet context destruction start");
    	
    	LOG.trace("Servlet context destruction finished");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent event)  { 
    	LOG.trace("Servlet context init start");
    	
    	ServletContext servletContext =  event.getServletContext();
    	
    	initLog4J(servletContext);
    	iniI18N(servletContext);
    	
    	LOG.trace("Servlet context init finished");
    }
	
    /**
	 * Initializes log4j framework.
	 *
	 * @param servletContext 
	 */
	private void initLog4J(ServletContext servletContext) {
		
		try {
			PropertyConfigurator.configure(servletContext.getRealPath("WEB-INF/log4j.properties"));
		} catch (Exception ex) {
			LOG.error("Cannot configure Log4j", ex);
		}
		LOG.debug("Log4j has been initialized");
}
	
	/**
	 * Initializes I18N
	 * 
	 * @param servletContext
	 */
	private void iniI18N(ServletContext servletContext){
		LOG.trace("I18N start initialize");
		
		String localesValue = servletContext.getInitParameter("locales");
		if (localesValue == null || localesValue.isEmpty()) {
			LOG.error("locales is empty");
		} else {
			List<String> locales = toList(localesValue);						
			
			servletContext.setAttribute("locales", locales);
			
			LOG.trace("I18N has been initialized");
			LOG.debug("I18N has been initialized");
		}	
	}
	
	/**
	 * Split string by space. 
	 * 
	 * @param str specified String.
	 * @return list of split strings.
	 */
	private List<String> toList(String str){
		String[] arrayStrs = str.split(" ");
		
		List<String> listOfStrs = new ArrayList<>();
		
		for(String s : arrayStrs){
			listOfStrs.add(s);
		}
		
		return listOfStrs;
	}
}
