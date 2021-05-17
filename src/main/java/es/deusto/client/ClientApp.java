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
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import es.deusto.client.windows.ClientWindow;
import es.deusto.serialization.EmployeeData;


public class ClientApp {

	private Client client;
	private WebTarget webTarget;
	static ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.forLanguageTag("en"));;
	public static ArrayList<EmployeeData> employees = new ArrayList<EmployeeData>();
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

	public void registerUser(EmployeeData employeeData) {
		System.out.println(employeeData);
		WebTarget addEmployeeWebTarget = webTarget.path("addEmployee");
		Invocation.Builder invocationBuilder = addEmployeeWebTarget.request(MediaType.APPLICATION_JSON);
		
		Response response = invocationBuilder.post(Entity.entity(employeeData, MediaType.APPLICATION_JSON));
		
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			System.out.println("Employee correctly registered.");
		}
	}
	
	public void updateUser(List<EmployeeData> employees, int id, String name, String address, String department, boolean leader ) {
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
				System.out.println("Error connecting with the server. Code: " + response.getStatus());
			} else {
			System.out.println("Employee correctly registered.");
			}
		}
	}
	
	public void deleteEmployee(int id) {
		WebTarget deleteEmployeeWebTarget = webTarget.path("deleteEmployee");
		Invocation.Builder invocationBuilder = deleteEmployeeWebTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.post(Entity.entity(id, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			System.out.println("Employee correctly deleted.");
		}
	}
	
	public void getEmployees(){
		WebTarget getEmployeesWebTarget = webTarget.path("/getEmployees");
		Invocation.Builder invocationBuilder = getEmployeesWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		
		} else {
			employees = response.readEntity((new ArrayList<EmployeeData>()).getClass());
			//IDEA: hacer una clase que sea una lista de empleados y que se devuelva eso.
			//employees = getEmployeesWebTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get((new ArrayList<EmployeeData>()).getClass());
			//System.out.println(response.readEntity(ArrayList.class));
			System.out.println("Employees correctly read.");
		}
	}
	
}