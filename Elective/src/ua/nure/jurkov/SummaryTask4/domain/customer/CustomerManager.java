package ua.nure.jurkov.SummaryTask4.domain.customer;

import java.util.List;

/**
 * Manager Interface of Customer which declared 
 * base methods for operation with customer. 
 * 
 * @author Eugene Jurkov
 *
 */
public interface CustomerManager {
	
	/**
	 * Returned registered customer.
	 * 
	 * @param info base information for registration.
	 * @return registered customer.
	 */
	Customer registeredCustomer(CustomerInfo info);
	
	/**
	 * Returned customer if customer do not exists.
	 * 
	 * @param email of customer.
	 * @param pass of customer.
	 * @return customer if customer do not exists.
	 */
	Customer validateCustomer(String email, String pass);
	
	/**
	 * Returned list of customers where role = 'Lecturer'.
	 * 
	 * @return list of customers where role = 'Lecturer'.
	 */
	List<Customer> getAllLecturers();
	
	/**
	 * Returned list of customers where role = 'Student'.
	 * 
	 * @return list of customers where role = 'Student'.
	 */
	List<Customer> getAllStudents();
	
	/**
	 * Returned true if block added to student.
	 * 
	 * @param idStudent
	 * @return true if blocked student added is success.
	 */
	boolean addBlockToStudent(int idStudent);
	
	/**
	 * Returned true if block deleted from student. 
	 * 
	 * @param idStudent
	 * @return true if block deleted from student. 
	 */
	boolean deleteBlockFromStudent(int idStudent);
	
	/**
	 * Returned true if student blocked.
	 * 
	 * @param idStudent
	 * @return
	 */
	boolean isBlockedStudent(int idStudent);
}
