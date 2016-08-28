package ua.nure.jurkov.SummaryTask4.domain.journal;

import ua.nure.jurkov.SummaryTask4.domain.customer.Customer;

public class RowJournal {
	private Customer customer;
	private int evaluation;
	

	public RowJournal(Customer customer, int evaluation) {
		this.customer = customer;
		this.evaluation = evaluation;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public int getEvaluation() {
		return evaluation;
	}
	
	public void setEvaluation(int evaluation) {
		this.evaluation = evaluation;
	}
	
	public String toString(){
		return "Customer: " + customer + 
				", evaluation: " + evaluation;
	}
}
