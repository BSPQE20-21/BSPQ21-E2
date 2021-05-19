package es.deusto.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import es.deusto.client.windows.ClientWindow;
import es.deusto.serialization.EmployeeData;
import es.deusto.serialization.EmployeeList;
import es.deusto.server.Server;


public class ClientApp {

	private Client client;
	private WebTarget webTarget;
	private static final Logger log = Logger.getLogger(ClientApp.class.getName());
	static ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.forLanguageTag("en"));;
	public ArrayList<EmployeeData> employees = new ArrayList<EmployeeData>();
	public ClientApp() {
		
	}
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Use: java Client.Client [host] [port]");
			System.exit(0);
		}

		String hostname = args[0];
		String port = args[1];

		ClientApp client = new ClientApp(hostname, port);
	}
	
	public ClientApp(String hostname, String port) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/server", hostname, port));
		ClientWindow window = new ClientWindow(this);
	}

	public void registerEmployee(EmployeeData employeeData) {
		WebTarget addEmployeeWebTarget = webTarget.path("addEmployee");
		Invocation.Builder invocationBuilder = addEmployeeWebTarget.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.post(Entity.entity(employeeData, MediaType.APPLICATION_JSON));
		
		if (response.getStatus() != Status.OK.getStatusCode()) {
			log.info(resourceBundle.getString("server_connection_error")+response.getStatus());
		} else {
			log.info(resourceBundle.getString("err_add_empl"));
		}
	}
	
	public void updateEmployee(List<EmployeeData> employees, int id, String name, String address, String department, boolean leader ) {
		WebTarget addEmployeeWebTarget = webTarget.path("updateEmployee");
		Invocation.Builder invocationBuilder = addEmployeeWebTarget.request(MediaType.APPLICATION_JSON);
		if(employees.get(id) != null) {
			EmployeeData employeeData = employees.get(id);
			employees.remove(id);
			employeeData.setId(id);
			employeeData.setName(name);
			employeeData.setAddress(address);
			employeeData.setDepartment(department);
			employeeData.setLeader(leader);
			
			Response response = invocationBuilder.post(Entity.entity(employeeData, MediaType.APPLICATION_JSON));
			
			if (response.getStatus() != Status.OK.getStatusCode()) {
				log.info(resourceBundle.getString("server_connection_error")+response.getStatus());
			} else {
				log.info(resourceBundle.getString("err_upd_empl"));
			}
			
		} else {
			log.info(resourceBundle.getString("err_upd_empl"));
		}
	}
	
	public void updateEmployees(List<EmployeeData> employees) {
		WebTarget addEmployeeWebTarget = webTarget.path("updateEmployees");
		Invocation.Builder invocationBuilder = addEmployeeWebTarget.request(MediaType.APPLICATION_JSON);
		
		EmployeeList employeeList = new EmployeeList();
		employeeList.setEmployees(new HashSet(employees));
		
		Response response = invocationBuilder.post(Entity.entity(employeeList, MediaType.APPLICATION_JSON));
		
		if (response.getStatus() != Status.OK.getStatusCode()) {
			log.info(resourceBundle.getString("server_connection_error")+response.getStatus());
		} else {
			log.info(resourceBundle.getString("err_upd_empls"));
		}
	}
	
	public void deleteEmployee(int id) {
		WebTarget deleteEmployeeWebTarget = webTarget.path("deleteEmployee");
		Invocation.Builder invocationBuilder = deleteEmployeeWebTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.post(Entity.entity(id, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			log.info(resourceBundle.getString("server_connection_error")+response.getStatus());
		} else {
			log.info(resourceBundle.getString("err_del_empl"));
		}
	}
	
	public void getEmployees(){
		WebTarget getEmployeesWebTarget = webTarget.path("/getEmployees");
		Invocation.Builder invocationBuilder = getEmployeesWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		if (response.getStatus() != Status.OK.getStatusCode()) {
			log.info(resourceBundle.getString("server_connection_error")+response.getStatus());
		
		} else {
			EmployeeList employeeList = response.readEntity(EmployeeList.class);
			employees = new ArrayList<EmployeeData>(employeeList.getEmployees());

			log.info(resourceBundle.getString("ok_get_empl"));
		}
	}
	
}