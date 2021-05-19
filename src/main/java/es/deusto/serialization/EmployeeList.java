package es.deusto.serialization;

import java.util.*;

public class EmployeeList {

	private Set<EmployeeData> employees;
	
	public EmployeeList() {
		employees = new HashSet<>();
	}
	
	public EmployeeList(Set<EmployeeData> employees) {
		this.employees = employees;
	}

	public Set<EmployeeData> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeData> employees) {
		this.employees = employees;
	}
	
	
}
