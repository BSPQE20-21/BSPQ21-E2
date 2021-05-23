package es.deusto.profiling;

import static org.junit.Assert.*;

import java.awt.HeadlessException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.Before;
import org.junit.Test;


public class EmployeeTest {
	
	private Path path;
	
	@Before
	public void init() {
		try {
//			this.path = Paths.get("x:\\path\\Employee");
		} 
		catch(HeadlessException e) {
		
		} 
	}

	@Test
	public void testReadListFromFolder() throws IOException{
		try {
//			Path file = this.path.resolve("testFolder");
//			boolean content = Files.isReadable(file);
//			assertTrue(content);
		} catch (HeadlessException e){
		
		} 
	}

}
