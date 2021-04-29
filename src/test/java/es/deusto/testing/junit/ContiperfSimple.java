package es.deusto.testing.junit;

import org.junit.*;
import org.databene.contiperf.*;
import org.databene.contiperf.junit.ContiPerfRule;

public class ContiperfSimple {
 @Rule
 public ContiPerfRule i = new ContiPerfRule();
 @Test
 @PerfTest(invocations = 1000, threads = 20)
 @Required(max = 1200, average = 250)
 public void test1() throws Exception {
 Thread.sleep(200);
 }
}
