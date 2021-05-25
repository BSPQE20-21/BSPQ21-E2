package es.deusto.testing.junit;

import org.junit.*;
import org.apache.log4j.Logger;
import org.databene.contiperf.*;
import org.databene.contiperf.junit.ContiPerfRule;

public class ContiperfSimple {
	
	final Logger logger = Logger.getLogger(ContiperfSimple.class.getName());

	@Rule
	public ContiPerfRule i = new ContiPerfRule();

	@Test
	@PerfTest(invocations = 1000, threads = 20)
	@Required(max = 1200, average = 250)
	public void test1() throws Exception {
		logger.info("Making initial test");
		Thread.sleep(200);
	}
}
