package es.deusto.server;

import java.util.ArrayList;  
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import es.deusto.serialization.*;
import es.deusto.server.dto.Employee;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.jdo.Query;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/server")
@Produces(MediaType.APPLICATION_JSON)
public class Server {
	private PersistenceManager pm=null;
	private Transaction tx=null;
	// ResourceBundle class will use SystemMessages.properties file
	private static final Logger log = Logger.getLogger(Server.class.getName());
	static ResourceBundle resourceBundle;
	
	public Server() {
		resourceBundle = ResourceBundle.getBundle("SystemMessages",	Locale.forLanguageTag("en"));
		
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
	}

	@POST
	@Path("/addEmployee")
	public Response addEmployee(EmployeeData employeeData) {
		try {	
            tx.begin();
            log.info(resourceBundle.getString("add_employee"));
			Employee employee = null;
			try {
				employee = pm.getObjectById(Employee.class, employeeData.getId());
				log.info(resourceBundle.getString("err_empl_already_in_db"));
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				log.info(resourceBundle.getString("ok_empl_not_found"));
				employee = new Employee(employeeData.getId(), employeeData.getName(), employeeData.getAddress(), employeeData.getDepartment());
				pm.makePersistent(employee);
				tx.commit();
				log.info(resourceBundle.getString("empl_add_correct"));
				return Response.ok().build();
			}
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
		}
		return Response.status(Status.BAD_REQUEST).entity("The employee is already in the database").build();
	}
	
	@POST
	@Path("/updateEmployee")
	public Response updateEmployee(EmployeeData employeeData) {
		try {	
            tx.begin();
            log.info(resourceBundle.getString("update_employee"));
			Employee employee = null;
			try {
				employee = pm.getObjectById(Employee.class, employeeData.getId());
				pm.retrieve(employee);
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				log.info(resourceBundle.getString("err_empl_not_found"));
			}
			employee.setId(employeeData.getId());
			employee.setName(employeeData.getName());
			employee.setAddress(employeeData.getAddress());
			employee.setDepartment(employeeData.getDepartment());
			pm.makePersistent(employee);
			tx.commit();
			log.info(resourceBundle.getString("empl_upd_correct"));
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
		} 
		return Response.ok().build();
	}
	
	@POST
	@Path("/deleteEmployee")
	public Response deleteEmployee(int id) {
		try {	
            tx.begin();
            log.info(resourceBundle.getString("deleting_employee"));
			Employee employee = null;
			try {
	            log.info(resourceBundle.getString("getting_employee"));
				employee = pm.getObjectById(Employee.class, id);
				pm.deletePersistent(employee);
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				log.info(resourceBundle.getString("err_empl_not_found"));
			}
			tx.commit();
            log.info(resourceBundle.getString("ok_del_empl"));
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
		}		return Response.ok().build();
	}

	@GET
	@Path("/getEmployees")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployees() {
		try
		{	 
	        tx.begin();
	        log.info(resourceBundle.getString("getting_employee"));
			Query q = pm.newQuery("SQL", "SELECT * FROM Employee");
			try {
				System.out.println("Retrieving a list of employees...");
				try {
					List<EmployeeData> employeeList = q.executeResultList(EmployeeData.class);
					//q.close();
					ArrayList<EmployeeData> employees = new ArrayList<EmployeeData>(employeeList);
					return Response.ok(employees).build();
				} catch (Exception e) {
					System.out.println("Exception 1 launched: " + e.getMessage());
				}
				
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				System.out.println("Exception 2 launched: " + jonfe.getMessage());
			} 
			tx.commit();
		}
		finally
		{
            if (tx.isActive())
            {
                tx.rollback();
            }
		}
		return Response.ok().build();
	}
	
}
