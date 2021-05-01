package es.deusto.serialization;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.jdo.annotations.Join;
import javax.jdo.annotations.Persistent;

public class EmployeeData {
	private int id;
	private String name;
	private String address;
	private String department;
	private boolean leader;
	
	public EmployeeData() {
		
	}
	
	public EmployeeData(int id, String name, String address, String department) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.department = department;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
	
	public boolean isLeader() {
		return leader;
	}

	public void setLeader(boolean leader) {
		this.leader = leader;
	}

	@Override
	public String toString() {
		return "EmployeeData [id=" + id + ", name=" + name + ", address=" + address + ", department=" + department
				+ ", leader=" + leader + "]";
	}

	
	
}
