package es.deusto.server;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import es.deusto.serialization.EmployeeData;
import es.deusto.serialization.EmployeeList;
import es.deusto.server.dto.EmployeeDTOContTest;

@PerfTest(invocations = 5)
@Required(max = 10000, average = 5000)
public class ServerContTest {
	
	private Server server;
	private EmployeeData employee;
	
	final Logger logger = Logger.getLogger(ServerContTest.class.getName());
	
	@Rule
	public ContiPerfRule i = new ContiPerfRule();
	
	@Test
	@PerfTest(invocations = 10, threads = 2)
	@Required(max = 10000, average = 3000)
	public void testAddEmployee() {
		server = new Server();
		employee = new EmployeeData(69, "Iker", "Bilbao", "IT");
		logger.info("Testing AddEmployee");
		
		try {
			assertTrue(server.addEmployee(employee) != null);
		} catch (AssertionError e) {
			fail();
		}
		
	}
	
	@Test
	@PerfTest(invocations = 10, threads = 2)
	@Required(max = 10000, average = 3000)
	public void testUpdateEmployee() {
		server = new Server();
		employee = new EmployeeData(1, "Iker", "Bilbao", "IT");
		logger.info("Testing UpdateEmployee");
		
		try {
			assertTrue(server.updateEmployee(employee) != null);
		} catch (AssertionError e) {
			fail();
		}
		
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
		logger.info("Testing DeleteEmployee");
		server = new Server();
		int employeeID = 69;
		
		try {
			assertTrue(server.deleteEmployee(employeeID) != null);
		} catch (AssertionError e) {
			fail();
		}
		
	}
	
	@Test
	@PerfTest(invocations = 10, threads = 2)
	@Required(max = 5000, average = 3000)
	public void testGetEmployees() {
		logger.info("Testing GetEmployee");
		server = new Server();
		
		try {
			assertTrue(server.getEmployees() != null);
		} catch (AssertionError e) {
			fail();
		}
		
	}

}
