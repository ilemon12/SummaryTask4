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
import ua.nure.jurkov.SummaryTask4.domain.customer.CustomerInfo;
import ua.nure.jurkov.SummaryTask4.domain.customer.CustomerManager;
import ua.nure.jurkov.SummaryTask4.domain.customer.CustomerManagerImpl;

/**
 * This action registers user and validate his input data.
 * 
 * @author Eugene Jurkov
 *
 */
public class RegisterAction extends Action{
	private static final Logger LOG = Logger.getLogger(RegisterAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		View view = new View("CoursesView", TypeDispatch.SEND_REDIRECT);
		
		String surname = request.getParameter("surname");
		String firstName = request.getParameter("firstname");
		String lastName = request.getParameter("lastname");
		String email = request.getParameter("email");
		String pass = request.getParameter("password");
		LOG.trace("Got data from user --> " + surname + ", " + firstName +
				", " + lastName + ", " + email + ", " + pass);
		
		List<String> invalidateFields = validateFields(surname, firstName, 
				lastName, email, pass);
		
		if(!invalidateFields.isEmpty()){
			request.setAttribute("errorInput", invalidateFields);
			LOG.trace("Set errorInput as attribute: " + invalidateFields);
			
			view.setNameView("Register");
			view.setTypeDispatch(TypeDispatch.FORWARD);
			
			return view;
		}
		
		CustomerInfo info = new CustomerInfo(surname, firstName, lastName, 
				email, pass);
		
		info.setRole("Student");
		
		CustomerManager customerManager = new CustomerManagerImpl(daoFactory);
		
		Customer customer = customerManager.registeredCustomer(info);
		LOG.trace("Got customer: " + customer);
		
		if(customer == null){
			request.setAttribute("errorInput", "This email is already in use");
			LOG.trace("Set errorInput as attribute: This email is already in use");
			
			view.setNameView("Register");
			view.setTypeDispatch(TypeDispatch.FORWARD);
			
			return view;
		}
		
		HttpSession session = request.getSession();
		session.setAttribute("customer", customer);
		LOG.trace("Set as attribute customer: " + customer);
		
		return view;
	}
	
	/**
	 * Returned List of invalidate Field.
	 * 
	 * @param surname user`s
	 * @param firstName user`s
	 * @param lastName user`s
	 * @param email user`s
	 * @param pass user`s
	 * @return List of invalidate Field
	 */
	private List<String> validateFields(String surname, String firstName,
			String lastName, String email, String pass){
		
		List<String> invalidateFields = new ArrayList<>();
		
		if(!ValidatorFields.isOnlyText(surname)){
			invalidateFields.add("Surname: " + surname + ";"); 
		}
		
		if(!ValidatorFields.isOnlyText(firstName)){
			invalidateFields.add("First name: " + firstName + ";"); 
		}
		
		if(!ValidatorFields.isOnlyText(lastName)){
			invalidateFields.add("Last name: " + lastName + ";"); 
		}
		
		if(!ValidatorFields.isEmail(email)){
			invalidateFields.add("Email: " + email + ";"); 
		}
		
		if(!ValidatorFields.isPass(pass)){
			invalidateFields.add("Password: " + pass + ";");
		}
		
		return invalidateFields;
	}
}
