package ua.nure.jurkov.SummaryTask4.domain.customer;

/**
 * Class consist of base information of customer.
 * 
 * @author Eugene Jurkov
 *
 */
public class CustomerInfo {
	private String surname;
	private String firstName;
	private String lastName;
	private String email;
	private String pass;
	private String role;
	
	public CustomerInfo(String surname, String firstName, 
			String lastName, String email, String pass, String role) {
		this(surname, firstName, lastName, email, pass);
		this.role = role; 
	}
	
	public CustomerInfo(String surname, String firstName, 
			String lastName, String email, String pass){
		this.surname = surname;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.pass = pass;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPass() {
		return pass;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	
	public void setRole(String role){
		this.role = role;
	}
	
	public String getRole(){
		return role;
	}
}
