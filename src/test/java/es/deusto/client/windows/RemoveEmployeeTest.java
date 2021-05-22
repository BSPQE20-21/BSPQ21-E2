package es.deusto.client.windows;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import es.deusto.serialization.EmployeeData;

public class RemoveEmployeeTest {
	EmployeeData employee;
	RemoveEmployee re;
	ArrayList<EmployeeData> al;

	@Test
	public void testRemoveEmployee() {
		employee = new EmployeeData(8364, "Eneko", "Gernika", "este");
		al = new ArrayList<EmployeeData>();
		al.add(employee);
		re = new RemoveEmployee("test", al);
		assertTrue(re.isVisible());
	}

}
