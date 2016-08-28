package ua.nure.jurkov.SummaryTask4.domain.course;

/**
 * Class consist of course and evaluation.
 * 
 * @author Eugene Jurkov
 *
 */
public class EvaluationStudentOfCourse {
	private Course course;
	private int evaluation;
	
	public EvaluationStudentOfCourse(Course course, int evaluation) {
		this.course = course;
		this.evaluation = evaluation;
	}
	
	public Course getCourse() {
		return course;
	}
	
	public void setCourse(Course course) {
		this.course = course;
	}
	
	public int getEvaluation() {
		return evaluation;
	}
	
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}
}
