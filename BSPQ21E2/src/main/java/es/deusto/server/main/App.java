package es.deusto.server.main;

import java.util.ArrayList;
import java.util.List;

import es.deusto.server.domainObjects.Employee;
import es.deusto.server.domainObjects.Team;
import es.deusto.server.domainObjects.TeamManager;
import es.deusto.server.dto.EmployeeDTO;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        EmployeeDTO e1 = new EmployeeDTO(1, "A", "Bilbao", "IT");
        EmployeeDTO e2 = new EmployeeDTO(2, "B", "Bilbao", "IT");
        EmployeeDTO e3 = new EmployeeDTO(3, "C", "Bilbao", "IT");
        EmployeeDTO e4 = new EmployeeDTO(4, "D", "Bilbao", "IT");
        
        List<EmployeeDTO> members = new ArrayList<EmployeeDTO>();
        
        members.add(e1);
        members.add(e2);
        members.add(e3);
        members.add(e4);
        
        TeamManager tm = new TeamManager();
        Team t1 = tm.createTeam(members);
        
        System.out.println(t1.toString());
    }
}
