package es.deusto.client.windows;

import static org.junit.Assert.*;

import java.awt.HeadlessException;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.junit.Before;
import org.junit.Test;

import es.deusto.client.ClientApp;
import es.deusto.serialization.EmployeeData;

public class EmployeeInfoWindowContTest {
	EmployeeInfoWindow eiw;
	ClientApp ca;
	EmployeeData expected;
	ClientWindow cw;

	@Before
	public void setUp() {
		try {
			
			ca = new ClientApp("127.0.0.1", "8080");
			cw = new ClientWindow(ca);
			expected = new EmployeeData(983473, "Gorka", "testAdress", "testDepart");
			
		} catch(HeadlessException e) {
			//TODO
		}
	}
	
	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void testEmployeeInfoWindow() {
		try {
			eiw = new EmployeeInfoWindow("Employee Info Window test");
			assertTrue(eiw.isVisible());
		} catch (HeadlessException e) {
			//TODO
		}

	}

}
