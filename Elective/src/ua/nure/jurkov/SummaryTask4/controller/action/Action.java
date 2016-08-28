package ua.nure.jurkov.SummaryTask4.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.jurkov.SummaryTask4.domain.dao.DaoFactory;

/**
 * Class represent specified action, that 
 * process request and response.
 * 
 * @author Eugene Jurkov
 *
 */
public abstract class Action {
	
	/**
	 * Set DaoFactory for all actions.
	 */
	protected static DaoFactory daoFactory = 
			DaoFactory.getDAOFactory(DaoFactory.MY_SQL);
	
	/**
	 * Method must implements all actions and that process
	 * request and response.  
	 * 
	 * @param request
	 * @param response
	 * @return specified View.
	 */
	public abstract View process(HttpServletRequest request,
				       HttpServletResponse response);
	
	public String toString(){
		return getClass().getSimpleName();
	}
}
