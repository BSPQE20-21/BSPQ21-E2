package es.deusto.server;

import java.util.List;

public class TeamManager {
	
	public Team createTeam(List<Employee> members) {
		Team team = new Team(members);
		return team;
	}
}
