package ua.nure.jurkov.SummaryTask4.controller.action.admin.lecturers;

import ua.nure.jurkov.SummaryTask4.controller.action.Action;

public class LecturersViewActions {
	
public static Action getSelectedAction(String nameAction){
		
		switch(nameAction){
			case "viewLecturers"    :
				return new ViewLecturerAction();
				
			case "registerLecturer" :
				return new ViewRegisteredLecturerAction();
				
			default			        :
				return new ViewLecturerAction();
		}
	}
}

