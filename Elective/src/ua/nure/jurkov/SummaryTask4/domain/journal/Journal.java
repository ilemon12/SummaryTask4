package ua.nure.jurkov.SummaryTask4.domain.journal;

import java.util.List;

public class Journal {
	private List<RowJournal> rowsJournal;
	private String nameCourse;
	
	public Journal(List<RowJournal> rowsJournal, String nameCourse) {
		this.rowsJournal = rowsJournal;
		this.nameCourse = nameCourse;
	}
	
	public List<RowJournal> getRowsJournal() {
		return rowsJournal;
	}
	public void setRowsJournal(List<RowJournal> rowsJournal) {
		this.rowsJournal = rowsJournal;
	}
	public String getNameCourse() {
		return nameCourse;
	}
	public void setNameCourse(String course) {
		this.nameCourse = course;
	}
	
	public String toStrin(){
		return "nameCourse: " + nameCourse + 
				"rowsJournal: " + rowsJournal;
	}
}
