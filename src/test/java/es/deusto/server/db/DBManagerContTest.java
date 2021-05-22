package es.deusto.server.db;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

public class DBManagerContTest {
	
	private static String dbhost = "jdbc:mysql://localhost/BSPQ21E2?verifyServerCertificate=false&useSSL=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	private static String username = "sd";
	private static String password = "sd";
	private static Connection conn;

	@Rule
	public ContiPerfRule i = new ContiPerfRule();
	
//	Add more tests when DBManager is completed
	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void createNewDBconnection() throws Exception{
		
		conn = DriverManager.getConnection(dbhost, username, password);	
		
		assertTrue(conn != null);;
	}
}
