package es.deusto.client;

import static org.junit.Assert.assertTrue;

import java.awt.HeadlessException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import es.deusto.serialization.EmployeeData;

public class ClientAppTest {

	EmployeeData employee;
	List<EmployeeData> employees;
	ClientApp client;
	String hostname;
	String port;
	
	@Before
	public void init() {
		hostname = "127.0.0.1";
		port = "8080";
		
		try {
			client = new ClientApp(hostname, port);
			employee = new EmployeeData();
			employee.setId(1);
			employee.setName("test");
			employee.setAddress("testAddres");
			employee.setDepartment("testDepart");
			employees = new ArrayList<EmployeeData>();
			employees.add(employee);
		} catch (HeadlessException e) {
			//TODO
		}
	}


	@Test
	public void testRegisterEmployee() {
		boolean works = false;
		try {
			client.registerEmployee(employee);
			works = true;
		} catch (Exception e) {	
		}
		assertTrue(works);
	}
	
	@Test
	public void testUpdateEmployee() {
		boolean works = false;
		try {
			client.updateEmployee(employees, 0, "test50", "address50", "department50", false);;
			works = true;
		} catch (Exception e) {	
		}
		assertTrue(works);
	}
	
	@Test
	public void testRegisterEmployees() {
		boolean works = false;
		try {
			client.updateEmployees(employees);
			works = true;
		} catch (Exception e) {	
		}
		assertTrue(works);
	}
	
	@Test
	public void testDeleteEmployee() {
		boolean works = false;
		try {
			client.deleteEmployee(employee.getId());
			works = true;
		} catch (Exception e) {	
		}
		assertTrue(works);
	}
	
	@Test
	public void testGetEmployees() {
		boolean works = false;
		try {
			client.getEmployees();
			works = true;
		} catch (Exception e) {	
		}
		assertTrue(works);
	}
}
