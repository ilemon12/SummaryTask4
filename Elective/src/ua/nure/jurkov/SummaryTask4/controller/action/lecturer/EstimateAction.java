package ua.nure.jurkov.SummaryTask4.controller.action.lecturer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.Action;
import ua.nure.jurkov.SummaryTask4.controller.action.View;
import ua.nure.jurkov.SummaryTask4.controller.action.View.TypeDispatch;
import ua.nure.jurkov.SummaryTask4.controller.util.ValidatorFields;
import ua.nure.jurkov.SummaryTask4.domain.journal.JournalManager;
import ua.nure.jurkov.SummaryTask4.domain.journal.JournalManagerImpl;

/**
 * Actions estimate student.
 * 
 * @author Eugene Jurkov
 *
 */
public class EstimateAction extends Action{
	private static final Logger LOG = Logger.getLogger(EstimateAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing ACtion");
		
		View view = new View("Journal", TypeDispatch.SEND_REDIRECT);
		
		HttpSession session = request.getSession();
		
		String strEvaluation = request.getParameter("evaluation");
		String strIdCourse = String.valueOf(session.getAttribute("currentCourseId"));
		String strIdStudent = request.getParameter("idStudent");
		LOG.trace("Got evaluation, idCourse, idStudent: " + strEvaluation + ", " + 
				strIdCourse + ", " + strIdStudent);
		
		if(!ValidatorFields.onlyNumbers(strEvaluation) || 
		   !ValidatorFields.onlyNumbers(strIdCourse) ||
		   !ValidatorFields.onlyNumbers(strIdStudent))
		{	
			LOG.trace("Invalidate fields");
			view.setTypeDispatch(TypeDispatch.FORWARD);
			
			return view;
		}
		
		int idCourse = Integer.valueOf(strIdCourse);
		int idStudent = Integer.valueOf(strIdStudent);
		int evaluation = Integer.valueOf(strEvaluation);
		
		JournalManager journalManager = new JournalManagerImpl(daoFactory);
		
		journalManager.estimate(idStudent, idCourse, evaluation);
		LOG.trace("Estimate success");
		LOG.debug("Finished processing Action");
		return view;
	}
}
