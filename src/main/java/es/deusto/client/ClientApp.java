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
import java.util.InputMismatchException;
import java.util.Scanner;

import es.deusto.serialization.EmployeeData;

public class ClientApp {

	private Client client;
	private WebTarget webTarget;
	private ArrayList<EmployeeData> employees = new ArrayList<EmployeeData>();
	
	public ClientApp() {
		
	}
	
	public ClientApp(String hostname, String port) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/server", hostname, port));
		
		Scanner sn = new Scanner(System.in);
		boolean leave = false;
		boolean numberIsCorrect = false;
		int option; 
		int optionDepartment;
		int optionDelete;
		int id;
		String name;
		String address;
		String department;
		
		 boolean repeat = true;
			while (repeat) {
				System.out.println("MENU:");
				System.out.println("1. Manage Employees");
				System.out.println("2. Manage Teams");
				System.out.println("3. Quit");
				
				Scanner optionf = new Scanner(System.in);
			    System.out.println("Select an option: ");
			    
			    try {
	                int choose = optionf.nextInt();
	 
	                switch (choose) {
	                    case 1:
	                    	while (!leave) {
	                			numberIsCorrect = false;
	                			System.out.println("1. Add Employee");
	                			System.out.println("2. Remove Employee");
	                			System.out.println("3. See Employee");
	                			System.out.println("3. Exit");

	                			try {
	                				System.out.println("Select an option");
	                				option = sn.nextInt();

	                				switch (option) {
	                				case 1:

	                					System.out.println("Insert Employee ID:");
	                					id = sn.nextInt();
	                					sn.nextLine();
	                					System.out.println("Insert Employee Name:");
	                					name = sn.nextLine();

	                					System.out.println("Insert Employee Address:");
	                					address = sn.nextLine();

	                					System.out.println("Insert deparment number:");
	                					System.out.println("1.IT Department");
	                					optionDepartment = sn.nextInt();
	                					
	                					switch (optionDepartment) {
	                					case 1:
	                						department = "IT Department";    						
	                						registerUser(id, name, address, department);
	                						break;
	                					}

	                					break;
	                					
	                				case 2:
	                					if(employees.size() == 0) {
	                						getEmployees();
	                						System.out.println("Retrieving employees...");
	                					}
	                					while(!numberIsCorrect) {
	                						System.out.println("Select the employee to be removed with the index that are displayed");
	                						System.out.println(employees.size());

	                						for(int i = 0; i<employees.size() ;i++) {
	                							System.out.println(i + ": " + employees.get(i).getName());

	                						}

	                						optionDelete = sn.nextInt();

	                						if(optionDelete >=0 && optionDelete <employees.size()) {
	                							System.out.println(employees.size());
	                							employees.remove(optionDelete);
	                							numberIsCorrect = true;
	                						}
	                					}
	                					break;

	                				case 3:
	                					if(employees.isEmpty()) {
	                						getEmployees();
	                						System.out.println("Retrieving employees...");
	                					} else {
	                						for (EmployeeData employee : employees) {
												System.out.println(employee.toString());
											}
	                					}
	                					break;	   
	                				
	                				case 4:
		                				leave = true;
		                				break;
	                				default:
	                					System.out.println("Please, select one of the available numbers.");
	                				}
	                			} catch (InputMismatchException e) {
	                				System.out.println("Please, select one of the available numbers.");
	                				sn.next();
	                			}
	                		}
	                        break;
	                        
	                    case 2:
	                    	System.err.println("Functionallity not yet implemented.");
	                        break;
	                        
	                    case 3:
	                    	repeat = false;
	                        break;
	                    default:
	                        System.out.println("You must insert one of the numbers.");
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("You must insert one of the numbers.");
	            } finally {
	            	optionf.close();
	            }
	        }
			sn.close();
	}

	public void registerUser(int id, String name, String address, String department) {
		WebTarget addEmployeeWebTarget = webTarget.path("addEmployee");
		Invocation.Builder invocationBuilder = addEmployeeWebTarget.request(MediaType.APPLICATION_JSON);
		
		EmployeeData employeeData = new EmployeeData();
		employeeData.setId(id);
		employeeData.setName(name);
		employeeData.setAddress(address);
		employeeData.setDepartment(department);
		
		Response response = invocationBuilder.post(Entity.entity(employeeData, MediaType.APPLICATION_JSON));
		
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			System.out.println("Employee correctly registered.");
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
		WebTarget getEmployeesWebTarget = webTarget.path("getEmployees");
		Invocation.Builder invocationBuilder = getEmployeesWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			employees = response.readEntity(ArrayList.class);
			System.out.println("Employees correctly read.");
		}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Use: java Client.Client [host] [port]");
			System.exit(0);
		}

		String hostname = args[0];
		String port = args[1];

		ClientApp exampleClient = new ClientApp(hostname, port);
	}
}