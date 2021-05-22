package es.deusto.client.windows;

import static org.junit.Assert.*;

import org.junit.Test;

import es.deusto.client.ClientApp;
import es.deusto.serialization.EmployeeData;

public class RemoveEmployeeTest {
	EmployeeData employee;

	@Test
	public void testRemoveEmployee() {
		employee = new EmployeeData(8364, "Eneko", "Gernika", "este");
		ClientApp ca = new ClientApp("127.0.0.1", "8080");
		ClientWindow cw = new ClientWindow(ca);
		ca.deleteEmployee(employee.getId());
		assertNull(employee);
	}

}
