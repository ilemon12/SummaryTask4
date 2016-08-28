package ua.nure.jurkov.SummaryTask4.domain.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.domain.customer.Customer;
import ua.nure.jurkov.SummaryTask4.domain.customer.CustomerInfo;
import ua.nure.jurkov.SummaryTask4.domain.dao.ConnectionPool;
import ua.nure.jurkov.SummaryTask4.domain.dao.CustomerDao;

public class CustomerDaoMySqlImpl implements CustomerDao{
	private static final Logger LOG = Logger.getLogger(CustomerDaoMySqlImpl.class);
	
	@Override
	public Customer addCustomer(CustomerInfo info) {
		
		String sql = "INSERT INTO users " +
					 "(surname, first_name, last_name, " +
					 "email, pass, role)" + 
					 "VALUES " + 
					 "(?, ?, ?, ?, ?, ?)";
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS))
		{	
			statement.setString(1, info.getSurname());
			statement.setString(2, info.getFirstName());
			statement.setString(3, info.getLastName());
			statement.setString(4, info.getEmail());
			statement.setString(5, info.getPass());
			statement.setString(6, info.getRole());
			
			statement.executeUpdate();
			
			ResultSet resultSet = statement.getGeneratedKeys();
			
			Customer customer = new Customer(info);
			
			if(resultSet.next()){
				customer.setId(resultSet.getInt(1));
			}
			
			return customer;
		} catch(SQLException e){
			LOG.error("Can not added customer ", e);
			
			return null;
		}
	}

	@Override
	public Customer getCustomerByEmail(String email) {
		String sql = "SELECT * " +
					 "FROM users " +
					 "WHERE users.email = ?";
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql)){
			
			statement.setString(1, email);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(!resultSet.next()){
				return null;
			}
			
			int id = resultSet.getInt(1);
			String surname = resultSet.getString(2);
			String firstName = resultSet.getString(3);
			String lastName = resultSet.getString(4);
			String pass = resultSet.getString(6);
			String role = resultSet.getString(7);
			
			CustomerInfo info = new CustomerInfo(surname, firstName,
					lastName, email, pass, role);
			
			Customer customer = new Customer(id, info);
			
			return customer;
			
		} catch(SQLException e){
			LOG.error("Can not get customer by email ", e);
			
			return null;
		}
	}

	@Override
	public List<Customer> getAllLecturers() {
		String sql = "SELECT * " +
					 "FROM users " +
					 "WHERE users.role = 'Lecturer'";
		
		List<Customer> lecturers = new ArrayList<>();
		
		try(Connection connection = ConnectionPool.getConnection();
			Statement statement = connection.createStatement()){
		
			ResultSet resultSet = statement.executeQuery(sql);
		
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String surname = resultSet.getString(2);
				String firstName = resultSet.getString(3);
				String lastName = resultSet.getString(4);
				String email = resultSet.getString(5);
				String pass = resultSet.getString(6);
				String role = resultSet.getString(7);
		
				CustomerInfo info = new CustomerInfo(surname, firstName,
					lastName, email, pass, role);
		
				Customer customer = new Customer(id, info);
				
				lecturers.add(customer);
			}	
		
			} catch(SQLException e){
				LOG.error("Can not get all lecturers ", e);
		}
		
		return lecturers;
	}

	@Override
	public List<Customer> getAllStudents() {
		String sql = "SELECT * " +
				 	 "FROM users " +
				 	 "WHERE users.role = 'Student'";
		
		List<Customer> students = new ArrayList<>();
		
		try(Connection connection = ConnectionPool.getConnection();
			Statement statement = connection.createStatement()){
		
			ResultSet resultSet = statement.executeQuery(sql);
		
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String surname = resultSet.getString(2);
				String firstName = resultSet.getString(3);
				String lastName = resultSet.getString(4);
				String email = resultSet.getString(5);
				String pass = resultSet.getString(6);
				String role = resultSet.getString(7);
		
				CustomerInfo info = new CustomerInfo(surname, firstName,
					lastName, email, pass, role);
		
				Customer customer = new Customer(id, info);
				
				students.add(customer);
			}	
		
			} catch(SQLException e){
				LOG.error("Can not get all students ", e);
		}
		
		return students;
	}

	@Override
	public void addBlockStudent(int idStudent) {
		String sql = "INSERT INTO students_blocked " +
					 "(student_id) " + 
					 "VALUES(?)";
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{
			statement.setInt(1, idStudent);
			
			statement.executeUpdate();
			
		} catch(SQLException e){
			LOG.error("Can not add block student ", e);
		}
		
	}

	@Override
	public boolean isBlockedStudent(int idStudent) {
		String sql = "SELECT student_id " +
				     "FROM students_blocked " +
				     "WHERE student_id = ?";
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{
			statement.setInt(1, idStudent);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(resultSet.next()){
				return true;
			}
			
		} catch(SQLException e){
			LOG.error("Can not is blocked student ", e);
			
			return false;
		}
		
		return false;
	}

	@Override
	public void deleteBlockStudent(int idStudent) {
		String sql = "DELETE FROM students_blocked " +
					 "WHERE student_id = ?";
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{
			statement.setInt(1, idStudent);
			
			statement.executeUpdate();
			
		} catch(SQLException e){
			LOG.error("Can not delete block student ", e);
		}
	}

	@Override
	public List<Customer> getStudentsByCourseId(int idCourse) {
		String sql = "SELECT users.user_id, users.surname, users.first_name, " +
				 	 "users.last_name, users.email, users.pass, users.role " +
				 	 "FROM users " +
				 	 "INNER JOIN (SELECT student_id " +
				 	 			 "FROM journal " + 
				 	 			 "WHERE course_id = ?) as courses_id_evaluations " +
				 	 "ON users.user_id = courses_id_evaluations.student_id  ";
		
		List<Customer> students = new ArrayList<>();
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{
			statement.setInt(1, idCourse);
			
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String surname = resultSet.getString(2);
				String firstName = resultSet.getString(3);
				String lastName = resultSet.getString(4);
				String email = resultSet.getString(5);
				String pass = resultSet.getString(6);
				
				CustomerInfo info = new CustomerInfo(surname, firstName,
						lastName, email, pass);
				
				Customer customer = new Customer(id, info);
				
				students.add(customer);
			}
			
			return students;
			
		} catch(SQLException e){
			LOG.error("Can not get students by id of course ", e);
			
			return students;
		}
	}

	@Override
	public Customer getCustomerById(int id) {
		String sql = "SELECT * FROM users " +	
					 "WHERE users.user_id = ?;";
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{
			statement.setInt(1, id);
			
			ResultSet resultSet = statement.executeQuery();
			
			if(!resultSet.next()){
				return null;
			}
			
			String surname = resultSet.getString(2);
			String firstName = resultSet.getString(3);
			String lastName = resultSet.getString(4);
			String email = resultSet.getString(5);
			String pass = resultSet.getString(6);
			
			CustomerInfo info = new CustomerInfo(surname, firstName,
					lastName, email, pass);
			
			Customer customer = new Customer(id, info);
			
			return customer;
			
		} catch(SQLException e){
			LOG.error("Can not get customer by id ", e);
			
			return null;
		}
	}	
}
