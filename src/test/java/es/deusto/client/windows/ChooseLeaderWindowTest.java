package es.deusto.client.windows;

import static org.junit.Assert.*;

import java.awt.HeadlessException;
import java.util.ArrayList;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.junit.Before;
import org.junit.Test;

import es.deusto.client.ClientApp;
import es.deusto.serialization.EmployeeData;

public class ChooseLeaderWindowTest {

	ChooseLeaderWindow clw;
	ClientApp ca;
	ClientWindow cw;
	ArrayList<EmployeeData> al;

	@Before
	public void setUp() {
		try {
			
			ca = new ClientApp("127.0.0.1", "8080");
			cw = new ClientWindow(ca);
			EmployeeData employee1 = new EmployeeData(345322, "Gonzalo", "testAdress1", "testDepart1");
			EmployeeData employee2 = new EmployeeData(234521, "Mikel", "testAdress2", "testDepart2");
			al = new ArrayList<EmployeeData>();
			al.add(employee1);
			al.add(employee2);
			
		} catch(HeadlessException e) {
			//TODO
		}
	}
	
	@Test
	@PerfTest(invocations = 100, threads = 2)
	@Required(max = 200, average = 100)
	public void WindowTest() {
		try {
			clw = new ChooseLeaderWindow("Chosse Leader Window test", al);
			assertTrue(clw.isVisible());
			assertTrue(clw.jframe.getBounds().getHeight() > 0);
			assertTrue(clw.jframe.getBounds().getWidth() > 0);
		} catch (HeadlessException e) {
			//TODO
		}
		
	}

}

