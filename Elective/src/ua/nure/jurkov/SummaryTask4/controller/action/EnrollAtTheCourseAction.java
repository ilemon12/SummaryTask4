package ua.nure.jurkov.SummaryTask4.controller.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.View.TypeDispatch;
import ua.nure.jurkov.SummaryTask4.domain.course.Course;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManager;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManagerImpl;
import ua.nure.jurkov.SummaryTask4.domain.customer.Customer;

/**
 * Action which enroll student at the course.
 * 
 * @author Eugene Jurkov
 *
 */
public class EnrollAtTheCourseAction extends Action{
	private static final Logger LOG = Logger.getLogger(EnrollAtTheCourseAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		Action coursesView = new CoursesViewAction();
		
		View view = coursesView.process(request, response);
		
		view.setTypeDispatch(TypeDispatch.SEND_REDIRECT);
		
		CoursesManager courseManager = new CoursesManagerImpl(daoFactory);
		
		if(!checkErorr(courseManager, request)){
			view.setTypeDispatch(TypeDispatch.FORWARD);
		}
		
		LOG.debug("Finished processing Action");
		return view;
	}
	
	/**
	 * Returned true if the student have not enrolled at the course
	 * or course have max number registered.
	 * 
	 * @param courseManager
	 * @param request
	 * @return true if the student have not enrolled at the course
	 * 		   or course have max number registered
	 */
	private boolean checkErorr(CoursesManager courseManager, HttpServletRequest request){
		LOG.debug("Start processing check Erorr");
		
		if(!checkMaxNumberOfParticipants(courseManager, request)){
			String erorr = "Not empty places!";
			LOG.trace("Erorr: " + erorr);
			request.setAttribute("errorInput", erorr);
			
			return false;
		}
		else if(!checkEnroll(courseManager, request)){
			String erorr = "You have enrolled at this course!";
			LOG.trace("Erorr: " + erorr);
			request.setAttribute("errorInput", erorr);
			
			return false;
		}
		
		LOG.debug("Finished processing check Erorr");
		return true;
	}
	
	/**
	 * Returned true if student have not enrolled.
	 * 
	 * @param courseManager
	 * @param request
	 * @return true if student have not enrolled.
	 */
	private boolean checkEnroll(CoursesManager courseManager, HttpServletRequest request){
		HttpSession session = request.getSession();
		
		Customer customer = (Customer)session.getAttribute("customer");
		
		int idStudent = customer.getId();
		int idCourse = Integer.valueOf(request.getParameter("idCourse"));
		
		if(!courseManager.enrollStudentToCourse(idStudent, idCourse)){
			return false;
		}
		
		return true;
	}
	
	/**
	 * Returned true if the course have not max number registered. 
	 * 
	 * @param courseManager
	 * @param request
	 * @return
	 */
	private boolean checkMaxNumberOfParticipants(CoursesManager courseManager, HttpServletRequest request){
		
		int idCourse = Integer.valueOf(request.getParameter("idCourse"));
		
		Course course = courseManager.getCourseById(idCourse);
		
		int registered = course.getNumberParticipants().getNumberRegistered();
		int numberOfParticipants = course.getNumberParticipants().getNumberParticipants();
		
		if(numberOfParticipants - registered <= 0){
			return false;
		}
		
		return true;
	}
}
