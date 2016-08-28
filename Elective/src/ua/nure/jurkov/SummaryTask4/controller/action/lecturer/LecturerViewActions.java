package ua.nure.jurkov.SummaryTask4.controller.action.lecturer;

import ua.nure.jurkov.SummaryTask4.controller.action.Action;

public class LecturerViewActions {
	
	public static Action getSelectedAction(String nameAction){
		
		switch(nameAction){
			case "courses": 
				return new ViewCoursesOfLecturerAction();
		
			default:
				return new ViewCoursesOfLecturerAction();
		}
	}
}
