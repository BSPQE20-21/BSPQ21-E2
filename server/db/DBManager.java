package es.deusto.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	
	private static String dbhost = "jdbc:mysql://localhost/BSPQ21E2?verifyServerCertificate=false&useSSL=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String username = "sd";
	private static String password = "sd";
	private static Connection conn;
	
	@SuppressWarnings("finally")
	public static Connection createNewDBconnection() {
		try  {	
			conn = DriverManager.getConnection(
					dbhost, username, password);	
		} catch (SQLException e) {
			System.out.println("Cannot create database connection");
			e.printStackTrace();
		} finally {
			return conn;	
		}		
	}

}
