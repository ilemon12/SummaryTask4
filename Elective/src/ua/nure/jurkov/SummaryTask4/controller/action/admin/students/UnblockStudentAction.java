package ua.nure.jurkov.SummaryTask4.controller.action.admin.students;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.Action;
import ua.nure.jurkov.SummaryTask4.controller.action.View;
import ua.nure.jurkov.SummaryTask4.controller.action.View.TypeDispatch;
import ua.nure.jurkov.SummaryTask4.controller.util.ValidatorFields;
import ua.nure.jurkov.SummaryTask4.domain.customer.CustomerManager;
import ua.nure.jurkov.SummaryTask4.domain.customer.CustomerManagerImpl;

/**
 * Action unblock student.
 * 
 * @author Eugene Jurkov
 *
 */
public class UnblockStudentAction extends Action{
	private static final Logger LOG = Logger.getLogger(UnblockStudentAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		View view = new View("ViewStudents", TypeDispatch.SEND_REDIRECT);
		
		String strIdStudent = request.getParameter("idStudent");
		LOG.trace("Got idStudent from request: " + strIdStudent);
		
		if(!ValidatorFields.onlyNumbers(strIdStudent)){
			LOG.trace("idStudent invalidate");
			Action action = new ViewStudentsAction();
			
			view = action.process(request, response);
			
			return view;
		}
		
		int idStudent = Integer.valueOf(request.getParameter("idStudent"));
		
		CustomerManager customerManager = new CustomerManagerImpl(daoFactory);
		
		boolean isBlockStudent = customerManager.deleteBlockFromStudent(idStudent);
		LOG.trace("Delete block student: " + isBlockStudent);
		
		if(!isBlockStudent){
			String erorr = "This student have not blocked!";
			request.setAttribute("errorInput", erorr);
			LOG.trace("Set as attribute errorInput: " + erorr);
			
			Action action = new ViewStudentsAction();
			
		    view = action.process(request, response);
			
			return view;
		}
		
		LOG.debug("Finished processing Action");
		return view;
	}
}
