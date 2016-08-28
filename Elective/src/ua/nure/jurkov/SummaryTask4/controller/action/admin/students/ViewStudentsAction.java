package ua.nure.jurkov.SummaryTask4.controller.action.admin.students;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.Action;
import ua.nure.jurkov.SummaryTask4.controller.action.View;
import ua.nure.jurkov.SummaryTask4.controller.action.View.TypeDispatch;
import ua.nure.jurkov.SummaryTask4.domain.customer.Customer;
import ua.nure.jurkov.SummaryTask4.domain.customer.CustomerManager;
import ua.nure.jurkov.SummaryTask4.domain.customer.CustomerManagerImpl;

public class ViewStudentsAction extends Action{
	private static final Logger LOG = Logger.getLogger(ViewStudentsAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		View view = new View("admin/ViewStudents", TypeDispatch.FORWARD);
		
		CustomerManager customerManager = new CustomerManagerImpl(daoFactory);
		
		List<Customer> students = customerManager.getAllStudents();
		LOG.trace("Got all students: " + students);
		
		request.setAttribute("students", students);
		LOG.trace("Set as atrribute sutdents: " + students);
		LOG.debug("Finished processing Action");
		return view;
	}
}
