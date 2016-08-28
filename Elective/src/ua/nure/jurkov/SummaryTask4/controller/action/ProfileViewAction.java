package ua.nure.jurkov.SummaryTask4.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.admin.ProfileAdminAction;
import ua.nure.jurkov.SummaryTask4.controller.action.lecturer.ProfileLecturerAction;
import ua.nure.jurkov.SummaryTask4.controller.action.student.ProfileStudentAction;
import ua.nure.jurkov.SummaryTask4.domain.customer.Customer;

public class ProfileViewAction extends Action{
	private static final Logger LOG = Logger.getLogger(ProfileViewAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		HttpSession session = request.getSession();
		
		Customer customer = (Customer) session.getAttribute("customer");
		LOG.trace("Set as attribute customer from session: " + customer);
		
		String role = customer.getCustomerInfo().getRole();
		LOG.trace("Got role customer: " + role);
		
		Action action = getViewProfile(role);
		LOG.trace("Got action by role: " + action);
		
		View view = action.process(request, response);
		
		LOG.debug("Finished processing Action");
		return view;
	}
	
	/**
	 * Returned ProfileAction by role of user. 
	 * If role dont exists, returned CoursesViewAction.
	 * 
	 * @param role specified role of user.
	 * @return Action by role of user.
	 */
	private Action getViewProfile(String role){
		
		if(role.equals("Admin")){
			
			return new ProfileAdminAction();
		}
		
		if(role.equals("Student")){
			
			return new ProfileStudentAction();
		}
		
		if(role.equals("Lecturer")){
			return new ProfileLecturerAction();
		}
		
		return new CoursesViewAction();
	}
}
