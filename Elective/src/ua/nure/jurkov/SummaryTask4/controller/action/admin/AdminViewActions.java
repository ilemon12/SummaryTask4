package ua.nure.jurkov.SummaryTask4.controller.action.admin;

import ua.nure.jurkov.SummaryTask4.controller.action.Action;
import ua.nure.jurkov.SummaryTask4.controller.action.admin.courses.SelectActionForCoursesAction;
import ua.nure.jurkov.SummaryTask4.controller.action.admin.lecturers.SelectActionForLecturersAction;
import ua.nure.jurkov.SummaryTask4.controller.action.admin.students.ViewStudentsAction;;

public class AdminViewActions {
	
	public static Action getSelectedAction(String nameAction){
		
		switch(nameAction){
			case "courses" : 
				return new SelectActionForCoursesAction();
			
			case "lecturer":
				return new SelectActionForLecturersAction();
				
			case "students":
				return new ViewStudentsAction();
				
			default:
				return new SelectActionForCoursesAction();
		}
	}
}
