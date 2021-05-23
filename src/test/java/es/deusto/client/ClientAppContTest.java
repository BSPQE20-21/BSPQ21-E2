package es.deusto.client;

import static org.junit.Assert.assertEquals; 

import java.awt.HeadlessException;
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

	EmployeeData employee;
	List<EmployeeData> employees;
	String hostname = "127.0.0.1";
	String port = "8080";
	ClientApp client;

	@Before
	public void init() {
		employee = new EmployeeData(1, "test", "testAddres", "testDepart");
		employees = new ArrayList<EmployeeData>();
		
		try {
			client = new ClientApp(hostname, port);
		} catch (HeadlessException e) {
			//TODO
		}
	}
	
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
		
		assertEquals(true, works);
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
		assertEquals(true, works);
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
		assertEquals(true, works);
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
		assertEquals(true, works);
	}
}
