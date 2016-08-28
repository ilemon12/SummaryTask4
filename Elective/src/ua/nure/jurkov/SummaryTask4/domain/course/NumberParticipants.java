package ua.nure.jurkov.SummaryTask4.domain.course;

/**
 * Class represents number of participants(number of registered and
 * number of participants)
 * 
 * @author Eugene Jurkov
 *
 */
public class NumberParticipants {
	private int numberParticipants;
	private int numberRegistered;
	
	public NumberParticipants(int numberParticipants, int numberRegistered) {
		this.numberParticipants = numberParticipants;
		this.numberRegistered = numberRegistered;
	}
	
	public int getNumberParticipants() {
		return numberParticipants;
	}
	
	public void setNumberParticipants(int numberParticipants) {
		this.numberParticipants = numberParticipants;
	}
	
	public int getNumberRegistered() {
		return numberRegistered;
	}
	
	public void setNumberRegistered(int numberRegistered) {
		this.numberRegistered = numberRegistered;
	}
}
