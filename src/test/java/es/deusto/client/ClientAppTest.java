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


	@Test
	public void prueba() {

	}
}
