package ua.nure.jurkov.SummaryTask4.controller.action.lecturer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.Action;
import ua.nure.jurkov.SummaryTask4.controller.action.View;
import ua.nure.jurkov.SummaryTask4.controller.action.View.TypeDispatch;
import ua.nure.jurkov.SummaryTask4.controller.util.ValidatorFields;
import ua.nure.jurkov.SummaryTask4.domain.journal.Journal;
import ua.nure.jurkov.SummaryTask4.domain.journal.JournalManager;
import ua.nure.jurkov.SummaryTask4.domain.journal.JournalManagerImpl;

public class ViewJournalAction extends Action{
	private static final Logger LOG = Logger.getLogger(ViewJournalAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		View view = new View("Journal", TypeDispatch.FORWARD);
		
		String strIdCourse = request.getParameter("idCourse");
		LOG.trace("Got idCourse from request: " + strIdCourse);
		
		HttpSession session = request.getSession();
		
		int idCourse;
		
		if(strIdCourse != null){
			if(!ValidatorFields.onlyNumbers(strIdCourse)){
				LOG.trace("idCourse invalidate field");
				Action viewCoursesOfLecturer = new ViewCoursesOfLecturerAction();
				
				view = viewCoursesOfLecturer.process(request, response);
				
				return view;
			}
			
			idCourse = Integer.valueOf(strIdCourse);
		
			session.setAttribute("currentCourseId", idCourse);
			LOG.trace("Set as attribute in session cuurentCourseId: " + idCourse);
		}
		else{
			idCourse = (Integer)session.getAttribute("currentCourseId");
			LOG.trace("Got idCourse from session: " + idCourse);
		}
		
		JournalManager journalManager = new JournalManagerImpl(daoFactory);
		
		Journal journal = journalManager.getJournal(idCourse);
		LOG.trace("Got journal by idCourse: " + idCourse + "journal: " + journal);
		
		request.setAttribute("journal", journal);
		LOG.trace("Set as attribute journal: " + journal);
		LOG.debug("Finished processing Action");
		return view;
	}
}
