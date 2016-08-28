package ua.nure.jurkov.SummaryTask4.domain.customer;

import java.util.List;

import ua.nure.jurkov.SummaryTask4.domain.dao.CustomerDao;
import ua.nure.jurkov.SummaryTask4.domain.dao.DaoFactory;

/**
 * Class implements CustomerManager.
 * 
 * @author JAVA__)
 *
 */
public class CustomerManagerImpl implements CustomerManager{
	private CustomerDao customerDao;
	
	public CustomerManagerImpl(DaoFactory daoFactory){
		customerDao = daoFactory.getCustomerDao();
	}
	
	@Override
	public Customer registeredCustomer(CustomerInfo info) {
		String email = info.getEmail();
		
		Customer customer = customerDao.getCustomerByEmail(
				email);
		
		if(customer != null){
			return null;
		}
		
		customer = customerDao.addCustomer(info);
		
		return customer;
	}

	@Override
	public Customer validateCustomer(String email, String pass) {
		Customer customer = customerDao.getCustomerByEmail(
				email);
		
		if(customer != null){
			
			String passCustomer = customer.getCustomerInfo().getPass();
			
			if(passCustomer.equals(pass)){
				return customer;
			}
		}
		
		return null;
	}

	@Override
	public List<Customer> getAllLecturers() {
		List<Customer> lecturers = customerDao.getAllLecturers();
		
		return lecturers;
	}

	@Override
	public List<Customer> getAllStudents() {
		List<Customer> students = customerDao.getAllStudents();
		
		return students;
	}

	@Override
	public boolean addBlockToStudent(int idStudent) {
		
		if(isBlockedStudent(idStudent)){
			return false;
		}
		
		customerDao.addBlockStudent(idStudent);
		
		return true;
	}

	@Override
	public boolean deleteBlockFromStudent(int idStudent) {
		
		if(!isBlockedStudent(idStudent)){
			return false;
		}
		
		customerDao.deleteBlockStudent(idStudent);
		
		return true;
	}

	@Override
	public boolean isBlockedStudent(int idStudent) {
		
		return customerDao.isBlockedStudent(idStudent);
	}

}
