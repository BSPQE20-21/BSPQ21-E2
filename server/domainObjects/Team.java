package es.deusto.server.domainObjects;

import java.util.List;

import es.deusto.server.dto.EmployeeDTO;

public class Team {
	
	private List<EmployeeDTO> members;

	public Team(List<EmployeeDTO> members) {
		super();
		this.members = members;
	}

	public List<EmployeeDTO> getMembers() {
		return members;
	}

	public void setMembers(List<EmployeeDTO> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		String memberList = "";
		for (EmployeeDTO e : members) {
			memberList = memberList + e.getName() + ", ";
		}
		return "Team [members = " + memberList + "]";
	}

}
