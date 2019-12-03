package book.modal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {

	private static ConnectionPool pool = null;
	private static DataSource ds = null;
	
	public synchronized static ConnectionPool getInstance() {
		if( pool == null) {
			pool = new ConnectionPool();
		} 
		return pool;
	}
	
//	private ConnectionPool() {
//		try {
//			InitialContext ic = new InitialContext();
//			ds = (DataSource) ic.lookup("java:/comp/env/jdbc/my_project");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//	}
	
	public Connection getConnection() {
		try {
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Connection getMySqlConnection() {

			String connectionUrl = "jdbc:mysql://localhost/my_project";
			try {
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn = DriverManager.getConnection(connectionUrl, "root", "thang5893563");
				return conn;
				
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			catch (ClassNotFoundException e) {
				e.printStackTrace();
				return null;
			}	
		
	}
	
	public void freeConnection(Connection conn) {
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
