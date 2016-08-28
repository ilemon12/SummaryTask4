package ua.nure.jurkov.SummaryTask4.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.Action;
import ua.nure.jurkov.SummaryTask4.controller.action.ActionFactory;
import ua.nure.jurkov.SummaryTask4.controller.action.View;
import ua.nure.jurkov.SummaryTask4.controller.action.View.TypeDispatch;

/**
 * Servlet implementation class FrontController. This servlet 
 * handles all requests by the client and then process the specified
 * action by name. 
 */
@WebServlet("/controller/*")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 2702046384543482642L;
	private static final Logger LOG = Logger.getLogger(FrontController.class);
	
	protected void processRequest(HttpServletRequest request, 
				HttpServletResponse response) throws ServletException{
		LOG.debug("Start processing in controller");
		
		Action action = ActionFactory.getAction(request);
	    View view = action.process(request, response);       
	    dispatch(request, response, view);
	    
	    LOG.debug("End processing in controller");
	}
	
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException{
	
		processRequest(request, response);
	}
	
	protected void doPost(HttpServletRequest request, 
				HttpServletResponse response) throws ServletException{
		
		processRequest(request, response);
	}
	
	/**
	 * Foward to jsp by name of View.
	 * 
	 * @param request
	 * @param response
	 * @param view
	 * @throws ServletException
	 */
	private void dispatch(HttpServletRequest request, 
				HttpServletResponse response, View view) throws ServletException{
		LOG.debug("Dispatch start");
		
		TypeDispatch typeDispatch = view.getTypeDispatch();
		String nameView = view.getNameView();
		LOG.trace("Dispatch --> nameView: " + nameView + ", TypeDispatch: " + typeDispatch);
		
		try{
			if(typeDispatch == TypeDispatch.FORWARD){
				request.getRequestDispatcher("/WEB-INF/view/" + nameView + ".jsp").forward(request, response);
				LOG.debug("Dispatch finished");
			}
			else if(typeDispatch == TypeDispatch.SEND_REDIRECT){
				response.sendRedirect(nameView);
				LOG.debug("Dispatch finished");
			}
			
		} catch (Exception e) {
			LOG.error("Failed dispatch --> nameView: " + nameView);
	        throw new ServletException("Dispacth failed.", e);
	    }
	}

}
