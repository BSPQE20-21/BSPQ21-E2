package es.deusto.server.dto;

import static org.junit.Assert.*;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;



public class EmployeeDTOContTest {
	
	private EmployeeDTO e1 = new EmployeeDTO(01, "Iker", "Bilbao", "IT");
	private EmployeeDTO e2 = new EmployeeDTO(02, "Mikel", "Bilbao", "IT");	
	private EmployeeDTO e3 = new EmployeeDTO(03, "Ana", "Bilbao", "IT");
	private EmployeeDTO e4 = new EmployeeDTO(04, "Maria", "Bilbao", "IT");
	
	private EmployeeDTO expected;
	
	 @Rule
	 public ContiPerfRule i = new ContiPerfRule();
	 
	 
	 @Test
	 @PerfTest(invocations = 100, threads = 2)
	 @Required(max = 200, average = 100)
	 public void getId() throws Exception {
	 expected = new EmployeeDTO(01, "Iker", "Bilbao", "IT"); 
	 assertEquals(expected.getId(), e1.getId());
	 }
	 
	 @Test
	 @PerfTest(invocations = 100, threads = 2)
	 @Required(max = 200, average = 100)
	 public void getName() throws Exception {
	 expected = new EmployeeDTO(01, "Iker", "Bilbao", "IT"); 
	 assertEquals(expected.getName(), e1.getName());
	 }
	 
	 @Test
	 @PerfTest(invocations = 100, threads = 2)
	 @Required(max = 200, average = 100)
	 public void getAddress() throws Exception {
	 expected = new EmployeeDTO(01, "Iker", "Bilbao", "IT"); 
	 assertEquals(expected.getAddress(), e1.getAddress());
	 }
	 
	 @Test
	 @PerfTest(invocations = 100, threads = 2)
	 @Required(max = 200, average = 100)
	 public void getDepartment() throws Exception {
	 expected = new EmployeeDTO(01, "Iker", "Bilbao", "IT"); 
	 assertEquals(expected.getDepartment(), e1.getDepartment());
	 }



}
