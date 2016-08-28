package ua.nure.jurkov.SummaryTask4.controller.action.student;

import ua.nure.jurkov.SummaryTask4.controller.action.Action;

public class StudentViewActions {
	
public static Action getSelectedAction(String nameAction){
		
		switch(nameAction){
			case "dontStart"  : 
				return new ViewRegisteredCoursesDontStartAction();
				
			case "inProgress" :	
				return new ViewCoursesInProgressAction();
				
			case "isOver"     :	
				return new ViewCoursesThatOverAction();
				
			default			  :
				return new ViewRegisteredCoursesDontStartAction();
		}
	}
}
