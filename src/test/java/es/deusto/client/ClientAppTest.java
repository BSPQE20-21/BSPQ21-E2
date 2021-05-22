package es.deusto.client;

import org.junit.Before;
import org.junit.Test;

import es.deusto.serialization.EmployeeData;

public class ClientAppTest {

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

<<<<<<< HEAD
	@Test
	public void prueba() {

	}
=======
//	@Test
//	public void prueba() {
//		assertEquals(Integer.parseInt(employeeWindow.gettId().getText()), employee.getId());
//		assertEquals(employeeWindow.gettName().getText(), employee.getName());
//		assertEquals(employeeWindow.gettAddress().getText(), employee.getAddress());
//		assertEquals(employeeWindow.gettDepartment().getText(), employee.getDepartment());
//
//	}
>>>>>>> 5aefa49b8dd7432e2d3874479747db8859e31187
	
}
