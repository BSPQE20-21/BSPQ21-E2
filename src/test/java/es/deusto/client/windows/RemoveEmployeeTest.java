package es.deusto.client.windows;

import static org.junit.Assert.*;

import java.awt.HeadlessException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Test;

import es.deusto.serialization.EmployeeData;
import es.deusto.server.ServerContTest;

public class RemoveEmployeeTest {
	EmployeeData employee;
	RemoveEmployee re;
	ArrayList<EmployeeData> al;
	
	final Logger logger = Logger.getLogger(RemoveEmployee.class.getName());

	@Test
	public void testRemoveEmployee() {
		try {
			logger.info("Testing removeEmployee");
			employee = new EmployeeData(8364, "Eneko", "Gernika", "este");
			al = new ArrayList<EmployeeData>();
			al.add(employee);
			re = new RemoveEmployee("test", al);
			assertTrue(re.isVisible());
		} catch (HeadlessException e){
			//TODO
		}

	}

}
