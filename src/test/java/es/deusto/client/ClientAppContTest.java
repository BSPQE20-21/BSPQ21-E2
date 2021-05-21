package es.deusto.client;

import static org.junit.Assert.*;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

import es.deusto.client.windows.EmployeeInfoWindow;
import es.deusto.serialization.EmployeeData;

public class ClientAppContTest {

	EmployeeData employee = new EmployeeData(0, "test", "testAddres", "testDepart");
	EmployeeInfoWindow employeeWindow = new EmployeeInfoWindow("test2"); 
	
	@Rule
	public ContiPerfRule i = new ContiPerfRule();
	
//	@Test
//	@PerfTest(invocations = 100, threads = 2)
//	@Required(max = 200, average = 100)
//	public void prueba() throws Exception{
//		assertEquals(Integer.parseInt(employeeWindow.gettId().getText()), employee.getId());
//		assertEquals(employeeWindow.gettName().getText(), employee.getName());
//		assertEquals(employeeWindow.gettAddress().getText(), employee.getAddress());
//		assertEquals(employeeWindow.gettDepartment().getText(), employee.getDepartment());
//
//	}

}
