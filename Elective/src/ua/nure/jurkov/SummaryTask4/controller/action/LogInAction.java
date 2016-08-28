package ua.nure.jurkov.SummaryTask4.controller.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.View.TypeDispatch;
import ua.nure.jurkov.SummaryTask4.controller.util.ValidatorFields;
import ua.nure.jurkov.SummaryTask4.domain.customer.Customer;
import ua.nure.jurkov.SummaryTask4.domain.customer.CustomerManager;
import ua.nure.jurkov.SummaryTask4.domain.customer.CustomerManagerImpl;

/**
 * Log in Action
 * 
 * @author Eugene Jurkov
 *
 */
public class LogInAction extends Action{
	private static final Logger LOG = Logger.getLogger(LogInAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		View view = new View("CoursesView", TypeDispatch.SEND_REDIRECT);
		
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		LOG.trace("Got email from request: " + email + ", pass: " + pass);
		
		List<String> invalidateFields = validateFields( email, pass);
		
		if(!invalidateFields.isEmpty()){
			request.setAttribute("errorInput", invalidateFields);
			LOG.trace("Set as attribute errorInput: " + invalidateFields);
			
			view.setNameView("LogIn");
			view.setTypeDispatch(TypeDispatch.FORWARD);
			
			return view;
		}
		
		CustomerManager customerManager = new CustomerManagerImpl(daoFactory);
		
		Customer customer = customerManager.validateCustomer(email, pass);
		LOG.trace("Validate email and pass, got customer: " + customer);
		
		if(customer == null){
			request.setAttribute("errorInput", "User dont exists");
			LOG.trace("Set as attribute errorInput: User dont exists" );
			
			view.setNameView("LogIn");
			view.setTypeDispatch(TypeDispatch.FORWARD);
			
			return view;
		}
		
		if(checkBlockCustomer( request, customer, customerManager)){
			view.setNameView("LogIn");
			view.setTypeDispatch(TypeDispatch.FORWARD);
			
			return view;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("customer", customer);
		LOG.trace("Set customer as attribute: " + customer);
		
		return view;
	}
	
	/**
	 * Returned list invalidate Fields.
	 * 
	 * @param email specified email customer.
	 * @param pass specified pass customer.
	 * @return list invalidate Fields.
	 */
	private List<String> validateFields(String email, String pass){
		
		List<String> invalidateFields = new ArrayList<>();
		
		if(!ValidatorFields.isEmail(email)){
				invalidateFields.add("Email: " + email + ";"); 
		}
		
		if(!ValidatorFields.isPass(pass)){
				invalidateFields.add("Password: " + pass + ";");
		}
		
		return invalidateFields;
	}
	
	/**
	 * Returned true if customer has not block.
	 * 
	 * @param request
	 * @param customer
	 * @param customerManager
	 * @return true if customer has not block.
	 */
	private boolean checkBlockCustomer(HttpServletRequest request, 
			Customer customer, CustomerManager customerManager){
		
		int id = customer.getId();
		
		if(customerManager.isBlockedStudent(id)){
			request.setAttribute("errorInput", "This user have block!");
			
			return true;
		}
		
		return false;
	}
}
