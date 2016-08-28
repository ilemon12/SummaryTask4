package ua.nure.jurkov.SummaryTask4.domain.customer;

/**
 * Class represents customer.
 * 
 * @author Eugene Jurkov
 *
 */
public class Customer {
	private int id;
	private CustomerInfo info;
	
	public Customer(int id, CustomerInfo info) {
		this(info);
		this.id = id;
	}
	
	public Customer(CustomerInfo info){
		this.info = info;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public CustomerInfo getCustomerInfo() {
		return info;
	}
	
	public void setCustomerInfo(CustomerInfo info) {
		this.info = info;
	}
	
	public String toString(){
		return "id: " + id + ", first name: " + info.getFirstName() +
				", last name: " + info.getLastName() + ", email: " +
				info.getEmail();
	} 
}
