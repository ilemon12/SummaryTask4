package ua.nure.jurkov.SummaryTask4.controller.action.admin.lecturers;

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

public class ViewLecturerAction extends Action{
	private static final Logger LOG = Logger.getLogger(ViewLecturerAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		View view = new View("admin/AdminViewLecturers", TypeDispatch.FORWARD);
		
		CustomerManager customerManager = new CustomerManagerImpl(daoFactory);
		
		List<Customer> lecturers = customerManager.getAllLecturers();
		LOG.trace("Got all lecturers: lecturers");
		
		request.setAttribute("lecturers", lecturers);
		LOG.trace("Set as attribute lecturers: " + lecturers);
		LOG.debug("Finished processing Action");
		return view;
	}

}
