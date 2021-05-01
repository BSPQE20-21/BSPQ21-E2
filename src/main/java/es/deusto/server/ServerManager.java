package es.deusto.server;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import es.deusto.serialization.EmployeeData;
import es.deusto.server.dto.Employee;

public class ServerManager implements Runnable {
	private PersistenceManager pm=null;
	private Transaction tx=null;
	private Client client;
	private WebTarget webTarget;
	private static final Logger log = Logger.getLogger("Main");
	static ResourceBundle resourceBundle;
	public static ServerManager manager;
	private Thread thread;
	private final AtomicBoolean running = new AtomicBoolean(false);
	
	public static void main(String[] args) {
		resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.getDefault());
		String hostname = args[0];
		String port = args[1];
		ServerManager server = new ServerManager(hostname, port);
		
		log.info(resourceBundle.getString("starting_msg"));
		log.info(resourceBundle.getString("app_title"));
		log.info(resourceBundle.getString("app_underline"));

	}
	
	public ServerManager(String hostname, String port) {
		manager = this;
		PersistenceManagerFactory pmf = JDOHelper.getPersistenceManagerFactory("datanucleus.properties");
		this.pm = pmf.getPersistenceManager();
		this.tx = pm.currentTransaction();
		
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/server", hostname, port));
		
		thread = new Thread(this);
		thread.start();
	}
	
	public void addEmployee(EmployeeData employeeData) {
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
			}
        } finally {
            if (tx.isActive()) {
                tx.rollback();
            }
		}
	}

	public void updateEmployee(EmployeeData employeeData) {
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
	}
	
	public void deleteEmployee(int id) {
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
		}
	}
	
	public ArrayList<EmployeeData> getEmployees(){
		try {	 
	        tx.begin();
	        log.info(resourceBundle.getString("getting_employee_list"));
			Query q = pm.newQuery("SQL", "SELECT * FROM Employee");
			try {
				try {
					List<EmployeeData> employeeList = q.executeResultList(EmployeeData.class);
					q.close();
					ArrayList<EmployeeData> employees = new ArrayList<EmployeeData>(employeeList);
					//return Response.ok(employees).build();
					return employees;
					
				} catch (Exception e) {
					System.out.println("Exception launched: " + e.getMessage());
				}
				
			} catch (javax.jdo.JDOObjectNotFoundException jonfe) {
				System.out.println("Exception launched: " + jonfe.getMessage());
			}     
		}
		finally {
            if (tx.isActive()) {
                tx.rollback();
            }
		}
		return null;
	}
	
	public void run() {
		running.set(true);
		while(running.get()) {
			try { 
				Thread.sleep(8000);
				System.out.println("Obtaining data from server...");
            } catch (InterruptedException e){ 
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted, Failed to complete operation");
            }
		}
	}

}
