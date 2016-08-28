package ua.nure.jurkov.SummaryTask4.controller.action.admin.courses;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.Action;
import ua.nure.jurkov.SummaryTask4.controller.action.View;
import ua.nure.jurkov.SummaryTask4.controller.action.View.TypeDispatch;
import ua.nure.jurkov.SummaryTask4.controller.util.ValidatorFields;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManager;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManagerImpl;
import ua.nure.jurkov.SummaryTask4.domain.course.EditField;

/**
 * Action edits course.
 * 
 * @author Eugene Jurkov
 *
 */
public class CourseEditAction extends Action{
	private static final Logger LOG = Logger.getLogger(CourseEditAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		View view = new View("ViewEditCourses", TypeDispatch.SEND_REDIRECT);
		
		String invalidateField = validateField(request);
		
		String strIdCourse = request.getParameter("idCourse");
		
		if(!invalidateField.isEmpty()){
			request.setAttribute("errorInput", invalidateField);
			LOG.trace("Set as attribute erorrInput: " + invalidateField);
			Action action = new ViewEditCoursesAction();
			
			view = action.process(request, response);
			
			return view;
		} 
		else if(!validateIdCourse(strIdCourse).isEmpty()){
			request.setAttribute("errorInput", strIdCourse);
			LOG.trace("Set as attribute errorInput: " + strIdCourse);
			Action action = new ViewEditCoursesAction();
			
			view = action.process(request, response);
			
			return view;
		}
		
		int idCourse = Integer.valueOf(strIdCourse);
		
		EditField field = getEditField(request);
		LOG.trace("Got EditField: " + field);
		
		CoursesManager courseManager = new CoursesManagerImpl(daoFactory);
		
		boolean successEdit = courseManager.editCourse(field, idCourse);
		
		if(!successEdit){
			
			String error = "Failed edit course.";
		
			request.setAttribute("errorInput", error);
			LOG.trace("Set as attribute errorInput: " + error);
			
			Action action = new ViewEditCoursesAction();
			
			view = action.process(request, response);
			
			return view;
		}
		
		LOG.debug("Finished processing Action");
		return view;
	}
	
	private EditField getEditField(HttpServletRequest request){
		String name = request.getParameter("newName");
		String strOfdateOfStart = request.getParameter("newStartDate");
		String strOfdateOfEnd = request.getParameter("newEndDate");
		String strOfNumberOfParticipants = request.getParameter("newNumberOfParticipants");
		String strIdTopicOfCourse = request.getParameter("idOfNewTopic");
		
		String nameOfField = "";
		String valueOfField = "";
		
		if(name != null){
			nameOfField = "name";
			valueOfField = name;
		}
		else if(strOfdateOfStart != null){
			nameOfField = "startDate";
			valueOfField = strOfdateOfStart;
		}
		else if(strOfdateOfEnd != null){
			nameOfField = "endDate";
			valueOfField = strOfdateOfEnd;
		}
		else if(strOfNumberOfParticipants != null){
			nameOfField = "numberParticipants";
			valueOfField = strOfNumberOfParticipants;
		}
		else if(strIdTopicOfCourse != null){
			nameOfField = "topic";
			valueOfField = strIdTopicOfCourse;
		}
		
		EditField field = new EditField(nameOfField, valueOfField);
		
		return field;
	}
	
	/**
	 * Returned string of invalidate editing filed.
	 * 
	 * @param request
	 * @return string of invalidate editing filed.
	 */
	private String validateField(HttpServletRequest request){
		String dateOfStart = request.getParameter("newStartDate");
		String dateOfEnd = request.getParameter("newEndDate");
		String numberOfParticipants = request.getParameter("newNumberOfParticipants");
		String idTopicOfCourse = request.getParameter("idOfNewTopic");
		
		if(dateOfStart != null){
			return validateStartDate(dateOfStart);
		}
		
		if(dateOfEnd != null){
			String startDate = request.getParameter("startDate");
			
			return validateEndDate(startDate, dateOfEnd);
		}
		
		if(numberOfParticipants != null){
			return validateNumberOfParticipants(numberOfParticipants);
		}
		
		if(idTopicOfCourse != null){
			return validateIdTopic(idTopicOfCourse);
		}
		
		return "";
	}
	
	private String validateStartDate(String dateOfStart){
		if(!ValidatorFields.validateDate(dateOfStart)){
			return "Date of start has invalid format: " + dateOfStart;
		}
		
		if(!ValidatorFields.afterNowDate(dateOfStart) && ValidatorFields.validateDate(dateOfStart)){
			return "Date of start should be later today: " + dateOfStart;
		}
		
		return "";
	}
	
	private String validateEndDate(String dateOfStart, String dateOfEnd){
		if(!ValidatorFields.validateDate(dateOfEnd)){
			return "Date of end has invalid format: " + dateOfEnd;
		}
		
		if(ValidatorFields.validateDate(dateOfStart) && ValidatorFields.validateDate(dateOfEnd)){
			if(!ValidatorFields.beforeDate(dateOfStart, dateOfEnd)){
				return "Date of start must be before date of end " + dateOfStart +
						", " + dateOfEnd;
			}
		}
		
		return "";
	}
	
	private String validateNumberOfParticipants(String number){
		if(!ValidatorFields.onlyNumbers(number)){
			return "Number of participants must be only number: " + number;
		}
		
		return "";
	}
	
	private String validateIdTopic(String id){
		if((!ValidatorFields.onlyNumbers(id) || Integer.valueOf(id) < 0)){
			return "Id of topic course must be only number and number > 0: "
					+ id;
		}
		
		return "";
	}
	
	private String validateIdCourse(String id){
		if((!ValidatorFields.onlyNumbers(id) || Integer.valueOf(id) < 0)){
			return "Id of course must be only number and number > 0: "
				   + id;
		}
		
		return "";
	}
}
