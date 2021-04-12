package es.deusto.client.main;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import javax.swing.JFrame;

import es.deusto.client.controller.ControllerAdmin;
import es.deusto.client.service.ServiceLocator;
import es.deusto.client.windows.WindowAdmin;
import es.deusto.server.dto.EmployeeDTO;

public class Main {

	public static void main(String[] args) {
		ServiceLocator serviceLocator = new ServiceLocator();
		serviceLocator.setService(args[0], args[1], args[2]);

		ControllerAdmin controllerAdmin = new ControllerAdmin(serviceLocator);

		WindowAdmin windowAdmin = new WindowAdmin(controllerAdmin);
		windowAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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
		ArrayList<EmployeeDTO> employees = new ArrayList<EmployeeDTO>();

		
		 boolean repeat = true;
			while (repeat) {
				System.out.println("MENU:");
				System.out.println("1. Manage Employees");
				System.out.println("2. Quit");
				
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
	                			System.out.println("3. Exit");


	                			try {

	                				System.out.println("Select an option");
	                				option = sn.nextInt();

	                				switch (option) {
	                				case 1:

	                					System.out.println("Insert Employee ID");
	                					id = sn.nextInt();
	                					sn.nextLine();
	                					System.out.println("Insert Employee Name");
	                					name = sn.nextLine();

	                					System.out.println("Insert Employee Address");
	                					address = sn.nextLine();

	                					System.out.println("Insert deparment number");
	                					System.out.println("1.IT Department");
	                					optionDepartment = sn.nextInt();
	                					switch (optionDepartment) {
	                					case 1:
	                						department = "IT Department";
	                						EmployeeDTO employee = new EmployeeDTO(id,name,address,department);
	                						System.out.println(employee.getId());
	                						System.out.println(employee.getName());
	                						System.out.println(employee.getAddress());
	                						System.out.println(employee.getDepartment());
	                						employees.add(employee);
	                						break;

	                					}

	                					break;
	                				case 2:
	                					if(employees.size() == 0 ) {
	                						System.out.println("There are no employees");
	                						break;
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
	                					leave = true;
	                					break;
	                				default:
	                					System.out.println("Only number 1 or 2");
	                				}
	                			} catch (InputMismatchException e) {
	                				System.out.println("You have to put a 1 or 2");
	                				sn.next();
	                			}

	                		}
	                        break;
	                    case 2:
	                    	repeat = false;
	                        break;
	                    default:
	                        System.out.println("You must insert one of the numbers.");
	                }
	            } catch (InputMismatchException e) {
	                System.out.println("You must insert one of the numbers.");
	            }
	        }
		

	}

}