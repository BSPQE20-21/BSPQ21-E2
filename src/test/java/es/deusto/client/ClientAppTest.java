package es.deusto.client;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import es.deusto.client.windows.EmployeeInfoWindow;
import es.deusto.serialization.EmployeeData;

public class ClientAppTest {
	
	EmployeeInfoWindow employeeWindow; 
	EmployeeData employee;
	
	@Before
	public void init() {
		employee = new EmployeeData();
		employee.setId(0);
		employee.setName("test");
		employee.setAddress("testAddres");
		employee.setDepartment("testDepart");
		
		//employeeWindow = new EmployeeInfoWindow("test2", employee);

	}

	@Test
	public void prueba() {
		assertEquals(Integer.parseInt(employeeWindow.gettId().getText()), employee.getId());
		assertEquals(employeeWindow.gettName().getText(), employee.getName());
		assertEquals(employeeWindow.gettAddress().getText(), employee.getAddress());
		assertEquals(employeeWindow.gettDepartment().getText(), employee.getDepartment());

	}
	
}
