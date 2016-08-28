package ua.nure.jurkov.SummaryTask4.domain.course;

import java.sql.Date;

/**
 * Class use when course create.
 * 
 * @author JAVA__)
 *
 */
public class CourseCreateInfo {
	private String name;
	private Date startDate;
	private Date endDate;
	private int numberOfParticipants;
	private int idOfTopicOfCourse;
	
	public CourseCreateInfo(String name, Date startDate, Date endDate, 
			int numberOfParticipants, int idOfTopicOfCourse) {
		
		this.name = name;
		this.setStartDate(startDate);
		this.endDate = endDate;
		this.numberOfParticipants = numberOfParticipants;
		this.idOfTopicOfCourse = idOfTopicOfCourse;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getNumberOfParticipants() {
		return numberOfParticipants;
	}

	public void setNumberOfParticipants(int numberOfParticipants) {
		this.numberOfParticipants = numberOfParticipants;
	}

	public int getIdOfTopicOfCourse() {
		return idOfTopicOfCourse;
	}

	public void setIdOfTopicOfCourse(int idOfTopicOfCourse) {
		this.idOfTopicOfCourse = idOfTopicOfCourse;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
}
