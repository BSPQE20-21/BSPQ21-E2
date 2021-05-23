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
	
	@Rule
	public ContiPerfRule i = new ContiPerfRule();
	
//	Add more tests when DBManager is completed
	
	@Test
	@PerfTest(invocations = 100, threads = 5)
	@Required(max = 5000, average = 3000)
	public void createNewDBconnection() throws Exception{
		
		Connection conn = DBManager.createNewDBconnection();	
		try {
			assertTrue(conn != null);
		} catch (AssertionError e) {
			fail();
		}
	}
}
