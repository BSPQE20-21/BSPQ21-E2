package es.deusto.server;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import es.deusto.serialization.EmployeeData;
import es.deusto.serialization.EmployeeList;
import es.deusto.testing.junit.ContiperfSimple;

public class ServerTest {
	
	private Server server;
	
	final Logger logger = Logger.getLogger(ServerTest.class.getName());
	
	
	@Before
	public void init() {
		server = new Server();
	}
	
	@Test
	public void testAddEmployee() {
		EmployeeData employee = new EmployeeData(30, "Iker", "Bilbao", "IT");
		logger.info("Testing AddEmployee");
		
		try {
			assertTrue(server.addEmployee(employee) != null);
		} catch (AssertionError e) {
			fail();
		}
		
	}
	
	@Test
	public void testUpdateEmployee() {
		EmployeeData employee = new EmployeeData(30, "Iker", "Bilbao", "IT");	
		logger.info("Testing UpdateEmployee");

		try {
			assertTrue(server.updateEmployee(employee) != null);
		} catch (AssertionError e) {
			fail();
		}
		
	}
	
//	@Test
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
	public void testDeleteEmployee() {
		int employeeID = 30;
		logger.info("Testing DeleteEmployee");

		
		try {
			assertTrue(server.deleteEmployee(employeeID) != null);
		} catch (AssertionError e) {
			fail();
		}
		
	}
	
	@Test
	public void testGetEmployees() {
		logger.info("Testing GetEmployee");

		try {
			assertTrue(server.getEmployees() != null);
		} catch (AssertionError e) {
			fail();
		}
	}

}
