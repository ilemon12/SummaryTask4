package ua.nure.jurkov.SummaryTask4.domain.dao;

import java.util.List;

import ua.nure.jurkov.SummaryTask4.domain.customer.Customer;
import ua.nure.jurkov.SummaryTask4.domain.customer.CustomerInfo;

public interface CustomerDao {
	
	/**
	 * Returned added customer.
	 * 
	 * @param info
	 * @return added customer.
	 */
	Customer addCustomer(CustomerInfo info);
	
	/**
	 * Rturned customer by email.
	 * 
	 * @param email
	 * @return customer by email.
	 */
	Customer getCustomerByEmail(String email);
	
	/**
	 * Returned list of all lecturers.
	 * 
	 * @return list of all lecturers
	 */
	List<Customer> getAllLecturers();
	
	/**
	 * Returned list of students.
	 * 
	 * @return
	 */
	List<Customer> getAllStudents();
	
	/**
	 * Add block to student.
	 * 
	 * @param idStudent
	 */
	void addBlockStudent(int idStudent);
	
	/**
	 * Returned customer by id.
	 * 
	 * @param id
	 * @return
	 */
	Customer getCustomerById(int id);
	
	/**
	 * Returned true if student have block.
	 * 
	 * @param idStudent
	 * @return
	 */
	boolean isBlockedStudent(int idStudent);
	
	/**
	 * Delete block from student.
	 * 
	 * @param idStudent
	 */
	void deleteBlockStudent(int idStudent);
	
	/**
	 * Returned list of student by course id.
	 * 
	 * @param idCourse
	 * @return
	 */
	List<Customer> getStudentsByCourseId(int idCourse);
}
