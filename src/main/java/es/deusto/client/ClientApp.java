package es.deusto.client;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.client.ClientResponse;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

import es.deusto.serialization.EmployeeData;


public class ClientApp {

	private Client client;
	private WebTarget webTarget;
	private ArrayList<EmployeeData> employees = new ArrayList<EmployeeData>();
	private JFrame frmMenu;
	private JFrame frmAddEmployees;
	private JFrame frmRemoveEmployee;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	
	public ClientApp() {
		
	}
	
	public static void main(String[] args) {
		if (args.length != 2) {
			System.out.println("Use: java Client.Client [host] [port]");
			System.exit(0);
		}

		String hostname = args[0];
		String port = args[1];

		ClientApp exampleClient = new ClientApp(hostname, port);
	}
	
	public ClientApp(String hostname, String port) {
		client = ClientBuilder.newClient();
		webTarget = client.target(String.format("http://%s:%s/rest/server", hostname, port));
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientApp window = new ClientApp(0);				
					window.frmMenu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});	 
	}

	public void registerUser(int id, String name, String address, String department) {
		WebTarget addEmployeeWebTarget = webTarget.path("addEmployee");
		Invocation.Builder invocationBuilder = addEmployeeWebTarget.request(MediaType.APPLICATION_JSON);
		
		EmployeeData employeeData = new EmployeeData();
		employeeData.setId(id);
		employeeData.setName(name);
		employeeData.setAddress(address);
		employeeData.setDepartment(department);
		
		Response response = invocationBuilder.post(Entity.entity(employeeData, MediaType.APPLICATION_JSON));
		
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			System.out.println("Employee correctly registered.");
		}
	}
	public void updateUser(List<EmployeeData> employees, int id, String name, String address, String department, boolean leader ) {
		WebTarget addEmployeeWebTarget = webTarget.path("updateEmployee");
		Invocation.Builder invocationBuilder = addEmployeeWebTarget.request(MediaType.APPLICATION_JSON);
		if(employees.get(id) != null) {
			EmployeeData employeeData = employees.get(id);
			employees.remove(id);
			employeeData.setId(id);
			employeeData.setName(name);
			employeeData.setAddress(address);
			employeeData.setDepartment(department);
			employeeData.setLeader(leader);
			
			Response response = invocationBuilder.post(Entity.entity(employeeData, MediaType.APPLICATION_JSON));
			
			if (response.getStatus() != Status.OK.getStatusCode()) {
				System.out.println("Error connecting with the server. Code: " + response.getStatus());
			} else {
			System.out.println("Employee correctly registered.");
			}
		}
	}
	public void deleteEmployee(int id) {
		WebTarget deleteEmployeeWebTarget = webTarget.path("deleteEmployee");
		Invocation.Builder invocationBuilder = deleteEmployeeWebTarget.request(MediaType.APPLICATION_JSON);

		Response response = invocationBuilder.post(Entity.entity(id, MediaType.APPLICATION_JSON));
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			System.out.println("Employee correctly deleted.");
		}
	}
	
	public void getEmployees(){
		WebTarget getEmployeesWebTarget = webTarget.path("getEmployees");
		Invocation.Builder invocationBuilder = getEmployeesWebTarget.request(MediaType.APPLICATION_JSON);
		Response response = invocationBuilder.get();
		
		if (response.getStatus() != Status.OK.getStatusCode()) {
			System.out.println("Error connecting with the server. Code: " + response.getStatus());
		} else {
			employees = response.readEntity(ArrayList.class);
			System.out.println("Employees correctly read.");
		}
	}


	
	
	public ClientApp(int numero) {
		if (numero == 0) {
			
			initialize();
			numero++;
		} else if(numero == 1) {
			initialize2();
		} else {
			initialize3();
		}
		
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmMenu = new JFrame();
		frmMenu.setTitle("MENU");
		frmMenu.setBounds(100, 100, 450, 300);
		frmMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenu.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Quit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMenu.setVisible(false);
				frmMenu.dispose();
			}
		});
		frmMenu.getContentPane().add(btnNewButton, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		frmMenu.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, BorderLayout.NORTH);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblNewLabel = new JLabel("Manage Employees");
		panel_1.add(lblNewLabel);
		
		JButton btnAdd = new JButton("Add Employee");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ClientApp window2 = new ClientApp(1);
							frmMenu.setVisible(false);
							frmMenu.dispose();
							window2.frmAddEmployees.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		panel_1.add(btnAdd);
		
		JButton btnRemoveEmployee = new JButton("Remove employee");
		panel_1.add(btnRemoveEmployee);
		btnRemoveEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frmMenu.setVisible(false);
							frmMenu.dispose();
							ClientApp window3 = new ClientApp(2);				
							window3.frmRemoveEmployee.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				
			}
		});
		
		
		JButton btnSee = new JButton("See Employee");
		panel_1.add(btnSee);
		btnSee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				String sEmployee = "";
				
				
				if(employees.isEmpty()) {
					getEmployees();
				} else {
					for (EmployeeData employee : employees) {
						sEmployee = sEmployee + employee.toString() + "\n";
					}
				}
				JOptionPane.showMessageDialog(panel_1, sEmployee);
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Manage Team");
		panel_1.add(lblNewLabel_1);
		
		JButton btnNewButton_3 = new JButton("New button");
		panel_1.add(btnNewButton_3);
	}
	
	private void initialize2() {
		frmAddEmployees = new JFrame();
		frmAddEmployees.setTitle("Add Employees");
		frmAddEmployees.setBounds(100, 100, 450, 300);
		frmAddEmployees.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAddEmployees.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		frmAddEmployees.getContentPane().add(panel_1, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Add Employee");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id = Integer.parseInt(textField.getText());
				String name = textField_1.getText();
				String address = textField_2.getText();
				String department = "IT Department";    						
				registerUser(id, name, address, department);
			}
		});	
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmAddEmployees.setVisible(false);
				frmAddEmployees.dispose();
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							ClientApp window = new ClientApp(0);
							
							window.frmMenu.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});		
		panel_1.add(btnNewButton_1);
		
		JPanel panel_2 = new JPanel();
		frmAddEmployees.getContentPane().add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(3, 1, 0, 0));
		
		JLabel lblNewLabel_1 = new JLabel("Insert Employee ID:");
		panel_2.add(lblNewLabel_1);
		
		textField = new JTextField();
		panel_2.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Insert Employee Name:");
		panel_2.add(lblNewLabel);
		
		textField_1 = new JTextField();
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Insert Employee Address:");
		panel_2.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		panel_2.add(textField_2);
		textField_2.setColumns(10);
	}
	
	private void initialize3() {
		frmRemoveEmployee = new JFrame();
		frmRemoveEmployee.setBounds(100, 100, 450, 300);
		frmRemoveEmployee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRemoveEmployee.getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		JComboBox<String> comboBox = new JComboBox<String>();
		frmRemoveEmployee.getContentPane().add(panel, BorderLayout.SOUTH);
		
		JButton btnNewButton = new JButton("Remove");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					//optionDelete = sn.nextInt();
					String optionDelete = comboBox.getSelectedItem().toString();
					int num = 0;
					int defNum = -1;
					
					for (EmployeeData employee : employees) {
						if(employee.getName().equals(optionDelete)) {
							defNum = num;
						}
						num =+ 1;
					}
					
					if(defNum >=0) {
						employees.remove(defNum);					
					}
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							frmRemoveEmployee.setVisible(false);
							frmRemoveEmployee.dispose();
							ClientApp window = new ClientApp(0);
							
							window.frmMenu.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});
		
		panel.add(btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		frmRemoveEmployee.getContentPane().add(panel_1, BorderLayout.CENTER);
		
		if(employees.size() == 0) {
			getEmployees();
			//System.out.println("Retrieving employees...");
		}
		
//		for(int i = 0; i<employees.size() ;i++) {
//			System.out.println(i + ": " + employees.get(i).getName());
//
//		}
		for (EmployeeData employee : employees) {
			comboBox.addItem(employee.getName());
		}
		
		panel_1.add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(comboBox.getSelectedItem().toString());
			}
		});
	}
	
	
}