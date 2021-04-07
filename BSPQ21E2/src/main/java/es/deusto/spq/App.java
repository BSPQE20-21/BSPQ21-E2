package es.deusto.spq;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        
        Employee e1 = new Employee(1, "A", "Bilbao", "IT");
        Employee e2 = new Employee(2, "B", "Bilbao", "IT");
        Employee e3 = new Employee(3, "C", "Bilbao", "IT");
        Employee e4 = new Employee(4, "D", "Bilbao", "IT");
        
        List<Employee> members = new ArrayList<Employee>();
        
        members.add(e1);
        members.add(e2);
        members.add(e3);
        members.add(e4);
        
        TeamManager tm = new TeamManager();
        Team t1 = tm.createTeam(members);
        
        System.out.println(t1.toString());
    }
}
