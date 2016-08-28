package ua.nure.jurkov.SummaryTask4.domain.course;

import ua.nure.jurkov.SummaryTask4.domain.topic.Topic;

/**
 * Class represents course.
 * 
 * @author Eugene Jurkov
 *
 */
public class Course {
	private int id;
	private String name;
	private Topic topic;
	private DateOfCourse dateOfCourse;
	private NumberParticipants participants;

	public Course(int id, String name, DateOfCourse dateOfCourse,
			NumberParticipants participants, Topic topic) {
		this.id = id;
		this.name = name;
		this.topic = topic;
		this.dateOfCourse = dateOfCourse;
		this.participants = participants;
	}

	public Topic getTopic() {
		return topic;
	}

	public void setTopic(Topic topic) {
		this.topic = topic;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public DateOfCourse getDateOfCourse() {
		return dateOfCourse;
	}

	public void setDateOfCourse(DateOfCourse dateOfCourse) {
		this.dateOfCourse = dateOfCourse;
	}

	public NumberParticipants getNumberParticipants() {
		return participants;
	}

	public void setNumberParticipants(NumberParticipants participants) {
		this.participants = participants;
	}
	
	public String toString(){
		return "Name: " + name + ", id: " + id;
		
	}
}
