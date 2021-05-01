package es.deusto.profiling;

import java.io.File;


public class Employee {
	
public static void main(String[] args) {
		
		if(args.length == 0)
		{
			System.out.println("enter employee list location in this form x:\\path\\Employee");
			return;
		}
		
		try {
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Employee employeeList = new Employee();
		employeeList.readListFromFolder(args[0]);
		
		try {
			System.in.read();
			System.in.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void readListFromFolder(String name) {
		
		if(name == null)
		{
			System.out.println("invalid folder name");
			return;
		}
		
		File file = new File(name);
		if(!file.exists() || !file.isDirectory())
		{
			System.out.println("invalid folder name " + name);
			return;
		}
		
		EmployeeProfiling info = new EmployeeProfiling();
		info.readData(name);
		
		System.out.println(info.getContent());
		
	}

}
