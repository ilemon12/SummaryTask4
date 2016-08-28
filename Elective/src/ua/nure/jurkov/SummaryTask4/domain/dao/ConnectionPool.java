package ua.nure.jurkov.SummaryTask4.domain.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.log4j.Logger;

public class ConnectionPool {
	private static final Logger LOG = Logger.getLogger(ConnectionPool.class);
	
	private static DataSource dataSource;
	
	public static synchronized Connection getConnection() {
		if (dataSource == null) {
			try {
				Context initContext = new InitialContext();
				Context envContext  = (Context)initContext.lookup("java:/comp/env");
				dataSource = (DataSource)envContext.lookup("jdbc/electivedb");
			} catch (NamingException e) {
				LOG.error("Cannot find the data source: ", e);
			}
		}
		
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			LOG.error("Cannot establish connection: ", e);
			return null;
		}
	}
}
