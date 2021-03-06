package es.deusto.serialization;

import static org.junit.Assert.*;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;



public class EmployeeDataContTest {
	
	private EmployeeData e1 = new EmployeeData(01, "Iker", "Bilbao", "IT");
	private EmployeeData e2 = new EmployeeData(02, "Mikel", "Bilbao", "IT");	
	private EmployeeData e3 = new EmployeeData(03, "Ana", "Bilbao", "IT");
	private EmployeeData e4 = new EmployeeData(04, "Maria", "Bilbao", "IT");
	
	private EmployeeData expected;
	
	 @Rule
	 public ContiPerfRule i = new ContiPerfRule();
	 
	 
	 @Test
	 @PerfTest(invocations = 100, threads = 2)
	 @Required(max = 200, average = 100)
	 public void getId() throws Exception {
	 expected = new EmployeeData(01, "Iker", "Bilbao", "IT"); 
	 assertEquals(expected.getId(), e1.getId());
	 }
	 
	 @Test
	 @PerfTest(invocations = 100, threads = 2)
	 @Required(max = 200, average = 100)
	 public void getName() throws Exception {
	 expected = new EmployeeData(01, "Iker", "Bilbao", "IT"); 
	 assertEquals(expected.getName(), e1.getName());
	 }
	 
	 @Test
	 @PerfTest(invocations = 100, threads = 2)
	 @Required(max = 200, average = 100)
	 public void getAddress() throws Exception {
	 expected = new EmployeeData(01, "Iker", "Bilbao", "IT"); 
	 assertEquals(expected.getAddress(), e1.getAddress());
	 }
	 
	 @Test
	 @PerfTest(invocations = 100, threads = 2)
	 @Required(max = 100, average = 100)
	 public void getDepartment() throws Exception {
	 expected = new EmployeeData(01, "Iker", "Bilbao", "IT"); 
	 assertEquals(expected.getDepartment(), e1.getDepartment());
	 }
	 


}
