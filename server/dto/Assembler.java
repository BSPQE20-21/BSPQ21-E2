package es.deusto.server.dto;

import java.util.ArrayList;
import java.util.List;

import es.deusto.server.domainObjects.Employee;

public class Assembler {
	
	public List<EmployeeDTO> assemble(List<Employee> employees){
		List<EmployeeDTO> employeesDTO = new ArrayList<>();
		for (Employee e : employees) {
			employeesDTO.add(new EmployeeDTO(e.getId(), e.getName(), e.getAddress(), e.getDepartment()));
		}
		System.out.println("Assembling...");
		return employeesDTO;
	}

}
