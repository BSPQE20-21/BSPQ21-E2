package es.deusto.server.domainObjects;

import java.util.List;

import es.deusto.server.dto.EmployeeDTO;

public class TeamManager {
	
	public Team createTeam(List<EmployeeDTO> members) {
		Team team = new Team(members);
		return team;
	}
}
