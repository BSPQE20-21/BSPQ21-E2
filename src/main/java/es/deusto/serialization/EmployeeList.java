package es.deusto.serialization;

import java.util.*;

public class EmployeeList {

	private Set<EmployeeData> employees;
	
	public void EmployeeList() {
		employees = new HashSet<>();
	}
	
	public void EmployeeList(Set<EmployeeData> employees) {
		this.employees = employees;
	}

	public Set<EmployeeData> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<EmployeeData> employees) {
		this.employees = employees;
	}
	
	
}
