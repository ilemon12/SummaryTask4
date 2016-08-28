package ua.nure.jurkov.SummaryTask4.controller.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.controller.action.admin.ProfileAdminAction;
import ua.nure.jurkov.SummaryTask4.controller.action.admin.courses.CourseCreateAction;
import ua.nure.jurkov.SummaryTask4.controller.action.admin.courses.CourseEditAction;
import ua.nure.jurkov.SummaryTask4.controller.action.admin.courses.DeleteCourseAction;
import ua.nure.jurkov.SummaryTask4.controller.action.admin.courses.SelectActionForCoursesAction;
import ua.nure.jurkov.SummaryTask4.controller.action.admin.courses.ViewEditCoursesAction;
import ua.nure.jurkov.SummaryTask4.controller.action.admin.courses.ViewNewCourseAction;
import ua.nure.jurkov.SummaryTask4.controller.action.admin.lecturers.CoursesHaveThisLecturerViewAction;
import ua.nure.jurkov.SummaryTask4.controller.action.admin.lecturers.RegisterLecturerAction;
import ua.nure.jurkov.SummaryTask4.controller.action.admin.lecturers.SelectActionForLecturersAction;
import ua.nure.jurkov.SummaryTask4.controller.action.admin.lecturers.SetLecturerOnCourseAction;
import ua.nure.jurkov.SummaryTask4.controller.action.admin.lecturers.SetLecturersOnCourseViewAction;
import ua.nure.jurkov.SummaryTask4.controller.action.admin.lecturers.ViewLecturerAction;
import ua.nure.jurkov.SummaryTask4.controller.action.admin.students.BlockStudentAction;
import ua.nure.jurkov.SummaryTask4.controller.action.admin.students.UnblockStudentAction;
import ua.nure.jurkov.SummaryTask4.controller.action.admin.students.ViewStudentsAction;
import ua.nure.jurkov.SummaryTask4.controller.action.lecturer.EstimateAction;
import ua.nure.jurkov.SummaryTask4.controller.action.lecturer.ProfileLecturerAction;
import ua.nure.jurkov.SummaryTask4.controller.action.lecturer.ViewJournalAction;
import ua.nure.jurkov.SummaryTask4.controller.action.student.ProfileStudentAction;

/**
 * Storage actions.
 * 
 * @author Eugene Jurkov
 *
 */
public class ActionFactory {
	private static Map<String, Action> actions = new HashMap<>();
	private static final Logger LOG = Logger.getLogger(ActionFactory.class);
	
	static {
		LOG.debug("ActionFactory init start");
		
		actions.put("GET/CoursesView", new CoursesViewAction());
		actions.put("GET/CoursesSort", new CoursesSortAction());
		actions.put("GET/CoursesSelectByTopic", new CoursesSelectByTopicAction());
		actions.put("GET/CoursesSelectByLecturer", new CoursesSelectByLecturerAction());
		actions.put("GET/Register", new RegisterViewAction());
		actions.put("POST/Register", new RegisterAction());
		actions.put("GET/LogOut", new LogOutAction());
		actions.put("GET/LogIn", new LogInViewAction());
		actions.put("POST/LogIn", new LogInAction());
		actions.put("GET/Profile", new ProfileViewAction());
		actions.put("GET/ru", new SetLanguageAction());
		actions.put("GET/en", new SetLanguageAction());
		
		//student
		actions.put("GET/ViewSelectedStudentAction", new ProfileStudentAction());
		actions.put("POST/EnrollAtTheCourse", new EnrollAtTheCourseAction());
		
		//lecturer
		actions.put("GET/ViewSelectedLecturerAction", new ProfileLecturerAction());
		actions.put("GET/Journal", new ViewJournalAction());
		actions.put("POST/Estimate", new EstimateAction());
		
		//admin
		actions.put("GET/AdminViewLecturers", new ViewLecturerAction());
		actions.put("GET/ViewSelectedCourses", new SelectActionForCoursesAction());
		actions.put("GET/ViewSelectedLecturers", new SelectActionForLecturersAction());
		actions.put("GET/SetLecturerOnCourseView", new SetLecturersOnCourseViewAction());
		actions.put("GET/CoursesOfLecturer", new CoursesHaveThisLecturerViewAction());
		actions.put("POST/SetLecturerOnCourse", new SetLecturerOnCourseAction());
		actions.put("GET/ViewStudents", new ViewStudentsAction());
		actions.put("POST/AddBlockStudent", new BlockStudentAction());
		actions.put("POST/DeletBlockStudent", new UnblockStudentAction());
		actions.put("GET/ViewSelectedAdminAction", new ProfileAdminAction());
		actions.put("GET/ViewEditCourses", new ViewEditCoursesAction());
		actions.put("GET/ViewNewCourse", new ViewNewCourseAction());
		actions.put("POST/createCourse", new CourseCreateAction());
		actions.put("POST/CourseEdit", new CourseEditAction());
		actions.put("POST/RegisterLecturer", new RegisterLecturerAction());
		actions.put("POST/CourseDelete", new DeleteCourseAction());
		
		LOG.trace("Storage actions --> " + actions);
		LOG.debug("ActionFactory init finished");
	}
	
	/**
	 * Returned specified Action by request.
	 * 
	 * @param request
	 * @return specified Action by request.
	 */
	public static Action getAction(HttpServletRequest request){
		String pathInfo = request.getPathInfo();
		String method = request.getMethod();
		LOG.trace("PathInfo: " + pathInfo + ", method: " + method);
		
		return actions.get(method + pathInfo);
	}
}
