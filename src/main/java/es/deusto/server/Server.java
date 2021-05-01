package es.deusto.server;

import javax.jdo.PersistenceManager; 
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.Transaction;

import es.deusto.serialization.*;
import es.deusto.server.dto.Employee;

import javax.ws.rs.GET;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.client.WebTarget;

@Path("/server")
@Produces(MediaType.APPLICATION_JSON)
public class Server {

	
	// ResourceBundle class will use SystemMessages.properties file
	private static final Logger log = Logger.getLogger("Main");
	static ResourceBundle resourceBundle;
	
	public Server() {
	resourceBundle = ResourceBundle.getBundle("SystemMessages",	Locale.forLanguageTag("en"));
	}

	@POST
	@Path("/addEmployee")
	public Response addEmployee(EmployeeData employeeData) {
		return Response.ok().build();
	}
	
	@POST
	@Path("/updateEmployee")
	public Response updateEmployee(EmployeeData employeeData) {
		return Response.ok().build();
	}
	
	@POST
	@Path("/deleteEmployee")
	public Response deleteEmployee(int id) {
		return Response.ok().build();
	}

	//WIP
	@GET
	@Path("/getEmployees")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployees() {
		/**
		try
		{	 
	        tx.begin();
	        log.info(resourceBundle.getString("getting_employee"));
			Query q = pm.newQuery("SQL", "SELECT * FROM Employee");
			try {
				System.out.println("Retrieving a list of employees...");
				try {
					List<EmployeeData> employeeList = q.executeResultList(EmployeeData.class);
					q.close();
					ArrayList<EmployeeData> employees = new ArrayList<EmployeeData>(employeeList);
					return Response.ok(employees).build();
					
				} catch (Exception e) {
					System.out.println("Exception launched: " + e.getMessage());
				}
				
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				System.out.println("Exception launched: " + jonfe.getMessage());
			}     
		}
		finally
		{
            if (tx.isActive())
            {
                tx.rollback();
            }
		}
		**/
		return Response.ok().build();
	}
	
}
