package es.deusto.client;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import es.deusto.serialization.EmployeeData;

public class ClientAppContTest {
	
	@Rule
	public ContiPerfRule i = new ContiPerfRule();

	EmployeeData employee = new EmployeeData(1, "test", "testAddres", "testDepart");
	List<EmployeeData> employees = new ArrayList<EmployeeData>();
	String hostname = "127.0.0.1";
	String port = "8080";
	ClientApp client = new ClientApp(hostname, port);

	@Test
	@PerfTest(invocations = 10, threads = 2)
	@Required(max = 5000, average = 3000)
	public void testRegisterEmployee() {
		employees.add(employee);
		boolean works = false;
		try {
			client.registerEmployee(employee);
			works = true;
		} catch (Exception e) {	
		}
		assertTrue(works);
	}
	
//	@Test
//	@PerfTest(invocations = 10, threads = 2)
//	@Required(max = 5000, average = 3000)
//	public void testUpdateEmployee() {
//		employees.add(employee);
//		boolean works = false;
//		try {
//			client.updateEmployee(employees, 50, "test50", "address50", "department50", false);;
//			works = true;
//		} catch (Exception e) {	
//		}
//		assertTrue(works);
//	}
	
	@Test
	@PerfTest(invocations = 10, threads = 2)
	@Required(max = 5000, average = 3000)
	public void testRegisterEmployees() {
		employees.add(employee);
		boolean works = false;
		try {
			client.updateEmployees(employees);
			works = true;
		} catch (Exception e) {	
		}
		assertTrue(works);
	}
	
	@Test
	@PerfTest(invocations = 10, threads = 2)
	@Required(max = 5000, average = 3000)
	public void testDeleteEmployee() {
		employees.add(employee);
		boolean works = false;
		try {
			client.deleteEmployee(employee.getId());
			works = true;
		} catch (Exception e) {	
		}
		assertTrue(works);
	}
	
	@Test
	@PerfTest(invocations = 10, threads = 2)
	@Required(max = 5000, average = 3000)
	public void testGetEmployees() {
		employees.add(employee);
		boolean works = false;
		try {
			client.getEmployees();
			works = true;
		} catch (Exception e) {	
		}
		assertTrue(works);
	}
}
