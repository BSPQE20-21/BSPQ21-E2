package es.deusto.server;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;


import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import es.deusto.serialization.EmployeeData;
import es.deusto.serialization.EmployeeList;

public class ServerTest {
	
	private Server server;
	
	
	@Before
	public void init() {
		server = new Server();
	}
	
	@Test
	public void testAddEmployee() {
		EmployeeData employee = new EmployeeData(1, "Iker", "Bilbao", "IT");		
		assertTrue(server.addEmployee(employee) != null);
	}
	
	@Test
	public void testUpdateEmployee() {
		EmployeeData employee = new EmployeeData(1, "Iker", "Bilbao", "IT");		
		assertTrue(server.updateEmployee(employee) != null);
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
		int employeeID = 1;
		assertTrue(server.deleteEmployee(employeeID) != null);
	}
	
	@Test
	public void testGetEmployees() {
		assertTrue(server.getEmployees() != null);
	}

}
