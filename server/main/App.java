package es.deusto.server.main;

import java.rmi.Naming;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import es.deusto.server.domainObjects.Employee;
import es.deusto.server.domainObjects.Team;
import es.deusto.server.domainObjects.TeamManager;
import es.deusto.server.dto.EmployeeDTO;
import es.deusto.server.facade.IRemoteFacade;
import es.deusto.server.facade.RemoteFacade;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	
    	
//        System.out.println( "Hello World!" );
//        
//        EmployeeDTO e1 = new EmployeeDTO(1, "A", "Bilbao", "IT");
//        EmployeeDTO e2 = new EmployeeDTO(2, "B", "Bilbao", "IT");
//        EmployeeDTO e3 = new EmployeeDTO(3, "C", "Bilbao", "IT");
//        EmployeeDTO e4 = new EmployeeDTO(4, "D", "Bilbao", "IT");
//        
//        List<EmployeeDTO> members = new ArrayList<EmployeeDTO>();
//        
//        members.add(e1);
//        members.add(e2);
//        members.add(e3);
//        members.add(e4);
//        
//        TeamManager tm = new TeamManager();
//        Team t1 = tm.createTeam(members);
//        
//        System.out.println(t1.toString());
        
        if (System.getSecurityManager() == null) {
        	System.setSecurityManager(new SecurityManager());
        }
        
        String name = "//" + args[0] + ":" + args[1] + "/" + args[2];
        
        try {
        	IRemoteFacade remoteFacade = RemoteFacade.getInstance();
        	Naming.rebind(name, remoteFacade);
        	System.out.println("Server '" + name + "' started!");
        } catch (Exception ex) {
        	System.err.println("#Server Exception: " + ex.getMessage());
        	ex.printStackTrace();
        }
        
        boolean repeat = true;
		while (repeat) {
			System.out.println("MENU:");
			System.out.println("1. Create a Team");
			System.out.println("2. Manage Employees");
			System.out.println("3. Quit");
			
			Scanner option = new Scanner(System.in);
		    System.out.println("Select option: ");
		    
		    try {
                int choose = option.nextInt();
 
                switch (choose) {
                    case 1:
                    	createTeam();
                        break;
                    case 2:
                    	manageEmployee();
                        break;
                    case 3:
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
    
    private static void manageEmployee(){
		System.out.println("MANAGE EMPLOYEES");
    	System.out.println("1. ");
    	System.out.println("2. ");
    	System.out.println("3. ");
    	
    	Scanner option = new Scanner(System.in);
	    System.out.println("Select option: ");
	    
	    try {
            int choose = option.nextInt();

            switch (choose) {
                case 1:
                	
                    break;
                case 2:
                	
                    break;
                case 3:
                	
                    break;
                default:
                    System.out.println("You must insert one of the numbers.");
            }
        } catch (InputMismatchException e) {
            System.out.println("You must insert one of the numbers.");
        }
	}


	private static void createTeam() {
		//TODO	
	}
}
