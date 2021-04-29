package es.deusto.testing.junit;

import org.apache.log4j.Logger;

import java.io.*;
import java.sql.SQLException;
import java.util.*;

public class log4jExample{
	   /* Get actual class name to be printed on */
	   static Logger log = Logger.getLogger(log4jExample.class.getName());
	   
	   private static org.apache.log4j.Logger log2 = Logger.getLogger(log4jExample.class);
	   
	   public static void main(String[] args)throws IOException,SQLException{
	      log.debug("Hello this is a debug message");
	      log.info("Hello this is an info message");
	      
	      log2.trace("Trace Message!");
	      log2.debug("Debug Message!");
	      log2.info("Info Message!");
	      log2.warn("Warn Message!");
	      log2.error("Error Message!");
	      log2.fatal("Fatal Message!");
	   }
	}