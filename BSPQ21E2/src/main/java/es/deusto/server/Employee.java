package es.deusto.server;

public class Employee {
	
	private int id;
	private String name;
	private String address;
	private String department;
	
	public Employee() {
		super();
	}
	
	public Employee(int id, String name, String address, String department) {
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

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", address=" + address + ", department=" + department + "]";
	}
	
	

}
