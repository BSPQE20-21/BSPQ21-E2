package es.deusto.server;

import java.util.List;

public class Team {
	
	private List<Employee> members;

	public Team(List<Employee> members) {
		super();
		this.members = members;
	}

	public List<Employee> getMembers() {
		return members;
	}

	public void setMembers(List<Employee> members) {
		this.members = members;
	}

	@Override
	public String toString() {
		String memberList = "";
		for (Employee e : members) {
			memberList = memberList + e.getName() + ", ";
		}
		return "Team [members = " + memberList + "]";
	}

}
