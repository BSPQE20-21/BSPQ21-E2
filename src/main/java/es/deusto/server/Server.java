package es.deusto.server;

import javax.jdo.PersistenceManager; 
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.Transaction;

import es.deusto.serialization.*;
import es.deusto.server.dto.Employee;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/server")
@Produces(MediaType.APPLICATION_JSON)
public class Server {

	private int cont = 0;
	private PersistenceManager pm=null;
	private Transaction tx=null;

	public Server() {
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
	}

	@POST
	@Path("/addEmployee")
	public Response addEmployee(EmployeeData employeeData) {
		try
        {	
            tx.begin();
			Employee employee = null;
			try {
				employee = pm.getObjectById(Employee.class, employeeData.getId());
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				System.out.println("Exception launched: " + jonfe.getMessage());
			}
			System.out.println("Employee: " + employee);
			if (employee != null) {
				System.err.println("The employee is already in the database");
//				System.out.println("Setting address: " + employeeData.getAddress());
//				employee.setAddress(employeeData.getAddress());
//				System.out.println("Setting department: " + employeeData.getDepartment());
//				employee.setDepartment(employeeData.getDepartment());				
			} else {
				System.out.println("Adding employee: " + employee);
				employee = new Employee(employeeData.getId(), employeeData.getName(), employeeData.getAddress(), employeeData.getDepartment());
				pm.makePersistent(employee);					 
				System.out.println("Employee added: " + employee);
			}
			tx.commit();
			return Response.ok().build();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
      
		}
	}
	
	@POST
	@Path("/deleteEmployee")
	public Response deleteEmployee(int id) {
		try
        {	
            tx.begin();
			Employee employee = null;
			try {
				System.out.println("Retrieving employee data...");
				employee = pm.getObjectById(Employee.class, id);
				System.out.println("Deleting employee...");
				pm.deletePersistent(employee);
				System.out.println("Employee successfully deleted");
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				System.out.println("Exception launched: " + jonfe.getMessage());
			}
			tx.commit();
			return Response.ok().build();
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
      
		}
	}

	@GET
	@Path("/getEmployees")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getEmployees() {
		try
		{	 
	        tx.begin();
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
		return Response.ok().build();
	}
}
