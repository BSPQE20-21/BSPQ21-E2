package es.deusto.server.dto;

import static org.junit.Assert.*;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;



public class EmployeeContTest {
	
	private Employee e1 = new Employee(01, "Iker", "Bilbao", "IT");
	private Employee e2 = new Employee(02, "Mikel", "Bilbao", "IT");	
	private Employee e3 = new Employee(03, "Ana", "Bilbao", "IT");
	private Employee e4 = new Employee(04, "Maria", "Bilbao", "IT");
	
	private Employee expected;
	
	 @Rule
	 public ContiPerfRule i = new ContiPerfRule();
	 
	 
	 @Test
	 @PerfTest(invocations = 100, threads = 2)
	 @Required(max = 50, average = 20)
	 public void getId() throws Exception {
	 expected = new Employee(01, "Iker", "Bilbao", "IT"); 
	 assertEquals(expected.getId(), e1.getId());
	 }
	 
	 @Test
	 @PerfTest(invocations = 100, threads = 2)
	 @Required(max = 50, average = 20)
	 public void getName() throws Exception {
	 expected = new Employee(01, "Iker", "Bilbao", "IT"); 
	 assertEquals(expected.getName(), e1.getName());
	 }
	 
	 @Test
	 @PerfTest(invocations = 100, threads = 2)
	 @Required(max = 50, average = 20)
	 public void getAddress() throws Exception {
	 expected = new Employee(01, "Iker", "Bilbao", "IT"); 
	 assertEquals(expected.getAddress(), e1.getAddress());
	 }
	 
	 @Test
	 @PerfTest(invocations = 100, threads = 2)
	 @Required(max = 50, average = 20)
	 public void getDepartment() throws Exception {
	 expected = new Employee(01, "Iker", "Bilbao", "IT"); 
	 assertEquals(expected.getDepartment(), e1.getDepartment());
	 }



}
