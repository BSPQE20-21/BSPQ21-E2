package es.deusto.serialization;

public class EmployeeData {
	public int id;
	public String name;
	public String address;
	public String department;
	public boolean leader;
	
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
		return "Employee" + id + ": " + name + ", " + address + ", " + department + ". Leader=" + leader;
		//return "EmployeeData [id=" + id + ", name=" + name + ", address=" + address + ", department=" + department
				//+ ", leader=" + leader + "]";
	}

	
	
}
