package ua.nure.jurkov.SummaryTask4.domain.course;

import java.sql.Date;

/**
 * Class represents date of course(startDate, endDate, duration(days))
 * 
 * @author Eugene Jurkov
 *
 */
public class DateOfCourse {
	private Date startDate;
	private Date endDate;
	private int daysDuration;
	
	public DateOfCourse(Date startDate, Date endDate, int daysDuaration) {
		this.startDate = startDate;
		this.endDate = endDate;
		this.daysDuration = daysDuaration;
	}
	
	public Date getStartDate() {
		return startDate;
	}
	
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	public Date getEndDate() {
		return endDate;
	}
	
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public int getDaysDuration() {
		return daysDuration;
	}

	public void setDaysDuration(int daysDuaration) {
		this.daysDuration = daysDuaration;
	}
}
