package ua.nure.jurkov.SummaryTask4.controller.action.admin.lecturers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.nure.jurkov.SummaryTask4.controller.action.Action;
import ua.nure.jurkov.SummaryTask4.controller.action.View;

public class SelectActionForLecturersAction extends Action{

	@Override
	public View process(HttpServletRequest request, HttpServletResponse response) {
		String nameOfLecturerAction = request.getParameter("nameOfLectuerAction");
		
		if(nameOfLecturerAction == null){
			nameOfLecturerAction = "viewLecturers";
		}
		
		Action action = LecturersViewActions.getSelectedAction(nameOfLecturerAction);
		
		View view = action.process(request, response);
		
		return view;
	}

}
