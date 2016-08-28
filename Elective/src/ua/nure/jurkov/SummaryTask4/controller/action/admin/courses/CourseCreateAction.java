package ua.nure.jurkov.SummaryTask4.controller.action.admin.courses;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.Action;
import ua.nure.jurkov.SummaryTask4.controller.action.View;
import ua.nure.jurkov.SummaryTask4.controller.action.View.TypeDispatch;
import ua.nure.jurkov.SummaryTask4.controller.util.ValidatorFields;
import ua.nure.jurkov.SummaryTask4.domain.course.Course;
import ua.nure.jurkov.SummaryTask4.domain.course.CourseCreateInfo;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManager;
import ua.nure.jurkov.SummaryTask4.domain.course.CoursesManagerImpl;

/**
 * Action creates course and validate his data. 
 * 
 * @author Eugene Jurkov
 *
 */
public class CourseCreateAction extends Action{
	private static final Logger LOG = Logger.getLogger(CourseCreateAction.class);
	
	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		LOG.debug("Start processing Action");
		
		View view = new View("ViewEditCourses", TypeDispatch.SEND_REDIRECT);
		
		CoursesManager courseManager = new CoursesManagerImpl(daoFactory);
		
		String name = request.getParameter("nameOfCourse");
		String strOfdateOfStart = request.getParameter("dateOfBegin");
		String strOfdateOfEnd = request.getParameter("dateOfEnd");
		String strOfNumberOfParticipants = request.getParameter("numberOfParticipants");
		int idTopicOfCourse = Integer.valueOf(request.getParameter("nameOfTopic"));
		LOG.trace("Got data of create course: " + name  + ", " + strOfdateOfStart +
				", " + strOfdateOfEnd + ", " + strOfNumberOfParticipants + ", " + idTopicOfCourse);
		
		List<String> invalidateFields = validateFields(name, strOfdateOfStart, 
				strOfdateOfEnd, strOfNumberOfParticipants);
		
		if(!invalidateFields.isEmpty()){
			request.setAttribute("errorInput", invalidateFields);
			LOG.trace("Set as attribute errorInput: " + invalidateFields);
			
		    Action action = new ViewNewCourseAction();
			
		    view = action.process(request, response);
			
			return view;
		}
		
		Date dateOfBegin = Date.valueOf(strOfdateOfStart);
		Date dateOfEnd = Date.valueOf(strOfdateOfEnd);
		int numberOfParticipants = Integer.valueOf(strOfNumberOfParticipants);
		
		CourseCreateInfo info = new CourseCreateInfo(name, dateOfBegin,
				dateOfEnd, numberOfParticipants, idTopicOfCourse);
		
		Course course = courseManager.createNewCourse(info);
		LOG.trace("Course created: " + course);
		
		if(course == null){
			request.setAttribute("errorInput", "Course didnt create!");
			LOG.trace("Set as attribute errorInput: Course didnt create");
			
			Action action = new ViewNewCourseAction();
			
		    view = action.process(request, response);
			
			return view;
		}
		
		LOG.debug("Finished processing Action");
		return view;
	}
	
	/**
	 * Returned List of invalidate Fields.
	 * 
	 * @param nameOfCourse 
	 * @param dateOfStart 
	 * @param dateOfEnd 
	 * @param numberOfParticipants 
	 * @return List of invalidate Fields.
	 */
	private List<String> validateFields(String nameOfCourse, String dateOfStart,
			String dateOfEnd, String numberOfParticipants){
		List<String> invalidateFields = new ArrayList<>();
		
		if(!ValidatorFields.validateDate(dateOfStart)){
			invalidateFields.add("Date of start has invalid format: " + dateOfStart);
		}
		else if(!ValidatorFields.afterNowDate(dateOfStart)){
			invalidateFields.add("Date of start should be after today: " + dateOfStart);
		}
		
		if(!ValidatorFields.validateDate(dateOfEnd)){
			invalidateFields.add("Date of end has invalid format: " + dateOfEnd);
		}
		
		if(ValidatorFields.validateDate(dateOfStart) && ValidatorFields.validateDate(dateOfEnd)){
			if(!ValidatorFields.beforeDate(dateOfStart, dateOfEnd)){
				invalidateFields.add("Date of start must be before date of end " + dateOfStart +
						", " + dateOfEnd);
			}
		}
			
		if(!ValidatorFields.onlyNumbers(numberOfParticipants)){
			invalidateFields.add("Number of participants must be only number: " + numberOfParticipants);
		}
		
		return invalidateFields;
	}
}
