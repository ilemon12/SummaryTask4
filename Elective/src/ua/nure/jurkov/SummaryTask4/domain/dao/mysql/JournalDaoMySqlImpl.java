package ua.nure.jurkov.SummaryTask4.domain.dao.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import ua.nure.jurkov.SummaryTask4.domain.customer.Customer;
import ua.nure.jurkov.SummaryTask4.domain.customer.CustomerInfo;
import ua.nure.jurkov.SummaryTask4.domain.dao.ConnectionPool;
import ua.nure.jurkov.SummaryTask4.domain.dao.JournalDao;
import ua.nure.jurkov.SummaryTask4.domain.journal.Journal;
import ua.nure.jurkov.SummaryTask4.domain.journal.RowJournal;

public class JournalDaoMySqlImpl implements JournalDao{
	private static final Logger LOG = Logger.getLogger(JournalDaoMySqlImpl.class);
	
	@Override
	public void updateEvaluation(int idStudent, int idCourse, int evaluation) {
		String sql = "UPDATE journal " +
					 "SET evaluation = ? " +
					 "WHERE student_id = ? AND course_id = ?";
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{
			statement.setInt(1, evaluation);
			statement.setInt(2, idStudent);
			statement.setInt(3, idCourse);
			
			statement.executeUpdate();
			
		} catch(SQLException e){
			LOG.error("Can not update evaluated ", e);
		}
	}

	@Override
	public Journal getJournal(int idCourse) {
		String sql = "SELECT users.user_id, users.surname, users.first_name, " + 
						 	"users.last_name, users.email, users.pass, " + 
						 	"users.role, students_evaluations.evaluation, " +
						 	"students_evaluations.name_course " +
					 "FROM users " + 
					 "INNER JOIN (SELECT student_id, evaluation, course.name_course " +
					 			 "FROM journal " +
					 			 "INNER JOIN(SELECT courses.id_course, courses.name as name_course " + 
					 			 			"FROM courses " + 
					 			 			"WHERE id_course = ?) as course " + 
					 			 "ON journal.course_id = course.id_course) as students_evaluations " +
					 "ON users.user_id = students_evaluations.student_id;";
		
		try(Connection connection = ConnectionPool.getConnection();
			PreparedStatement statement = connection.prepareStatement(sql))
		{
			statement.setInt(1, idCourse);
			
			List<RowJournal> rowsJournal = new ArrayList<>();
			
			ResultSet resultSet = statement.executeQuery();
			
			String nameCourse = "";
			
			while(resultSet.next()){
				int id = resultSet.getInt(1);
				String surname = resultSet.getString(2);
				String firstName = resultSet.getString(3);
				String lastName = resultSet.getString(4);
				String email = resultSet.getString(5);
				String pass = resultSet.getString(6);
				String role = resultSet.getString(7);
				int evaluation = resultSet.getInt(8);
				nameCourse = resultSet.getString(9);
				
				CustomerInfo info = new CustomerInfo(surname, firstName, lastName, 
						email, pass, role);
				
				Customer customer = new Customer(id, info);
				
				RowJournal rowJournal = new RowJournal(customer, evaluation);
				
				rowsJournal.add(rowJournal);
			}
			
			Journal journal = new Journal(rowsJournal, nameCourse);
			
			return journal;
			
		} catch(SQLException e){
			LOG.error("Can not get journal ", e);
			
			return null;
		}
	}
}
