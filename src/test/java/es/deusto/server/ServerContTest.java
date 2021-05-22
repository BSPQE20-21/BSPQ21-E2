package es.deusto.server;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import es.deusto.serialization.EmployeeData;
import es.deusto.serialization.EmployeeList;

@PerfTest(invocations = 5)
@Required(max = 10000, average = 5000)
public class ServerContTest {
	
	private Server server;
	private EmployeeData employee;
	
	@Rule
	public ContiPerfRule i = new ContiPerfRule();
	
	@Test
	@PerfTest(invocations = 10, threads = 2)
	@Required(max = 1000, average = 1000)
	public void testAddEmployee() {
		server = new Server();
		employee = new EmployeeData(69, "Iker", "Bilbao", "IT");		
		assertTrue(server.addEmployee(employee) != null);
	}
	
	@Test
	@PerfTest(invocations = 10, threads = 2)
	@Required(max = 2000, average = 1000)
	public void testUpdateEmployee() {
		server = new Server();
		employee = new EmployeeData(69, "Iker", "Bilbao", "IT");		
		assertTrue(server.updateEmployee(employee) != null);
	}
	
//	@Test
//	@PerfTest(invocations = 10, threads = 2)
//	@Required(max = 2000, average = 1000)
//	public void testUpdateEmployees() {
//		EmployeeData employee1 = new EmployeeData(1, "Iker", "Bilbao", "IT");
//		EmployeeData employee2 = new EmployeeData(2, "John", "Bilbao", "IT");
//		Set<EmployeeData> data = new HashSet<EmployeeData>();
//		data.add(employee1);
//		data.add(employee2);
//		EmployeeList employees = new EmployeeList();
//		employees.setEmployees(data);
//		assertTrue(server.updateEmployees(employees) != null);
//	}
	
	@Test
	@PerfTest(invocations = 10, threads = 2)
	@Required(max = 10000, average = 8000)
	public void testDeleteEmployee() {
		server = new Server();
		int employeeID = 69;
		assertTrue(server.deleteEmployee(employeeID) != null);
	}
	
	@Test
	@PerfTest(invocations = 10, threads = 2)
	@Required(max = 2000, average = 1000)
	public void testGetEmployees() {
		server = new Server();
		assertTrue(server.getEmployees() != null);
	}

}
