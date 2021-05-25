package es.deusto.server.dto;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

import es.deusto.testing.junit.ContiperfSimple;



public class EmployeeDTOContTest {
	
	private EmployeeDTO e1 = new EmployeeDTO(01, "Iker", "Bilbao", "IT");
	
	private EmployeeDTO expected;
	
	final Logger logger = Logger.getLogger(EmployeeDTOContTest.class.getName());
	
	 @Rule
	 public ContiPerfRule i = new ContiPerfRule();
	 
	 @Test
	 @PerfTest(invocations = 100, threads = 2)
	 @Required(max = 50, average = 20)
	 public void getId() throws Exception {
	 expected = new EmployeeDTO(01, "Iker", "Bilbao", "IT"); 
	 logger.info("Testing getID");
	 assertEquals(expected.getId(), e1.getId());
	 }
	 
	 @Test
	 @PerfTest(invocations = 100, threads = 2)
	 @Required(max = 50, average = 20)
	 public void getName() throws Exception {
	 expected = new EmployeeDTO(01, "Iker", "Bilbao", "IT"); 
	 logger.info("Testing getName");
	 assertEquals(expected.getName(), e1.getName());
	 }
	 
	 @Test
	 @PerfTest(invocations = 100, threads = 2)
	 @Required(max = 50, average = 20)
	 public void getAddress() throws Exception {
	 expected = new EmployeeDTO(01, "Iker", "Bilbao", "IT"); 
	 logger.info("Testing getAddress");
	 assertEquals(expected.getAddress(), e1.getAddress());
	 }
	 
	 @Test
	 @PerfTest(invocations = 100, threads = 2)
	 @Required(max = 50, average = 20)
	 public void getDepartment() throws Exception {
	 expected = new EmployeeDTO(01, "Iker", "Bilbao", "IT"); 
	 logger.info("Testing getDepartment");
	 assertEquals(expected.getDepartment(), e1.getDepartment());
	 }



}
