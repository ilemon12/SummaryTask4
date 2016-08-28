package ua.nure.jurkov.SummaryTask4.controller.action.admin.courses;

import ua.nure.jurkov.SummaryTask4.controller.action.Action;

public class CoursesViewActions {
	
public static Action getSelectedAction(String nameAction){
		
		switch(nameAction){
			case "editCourses" :
				return new ViewEditCoursesAction();
				
			case "addCourse"   :
				return new ViewNewCourseAction();
				
			default			   :
				return new ViewEditCoursesAction();
		}
	}
}
