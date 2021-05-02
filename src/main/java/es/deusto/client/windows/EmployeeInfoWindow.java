package es.deusto.client.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import es.deusto.serialization.EmployeeData;

public class EmployeeInfoWindow extends JFrame {
	
	public EmployeeInfoWindow jframe;
	private JTextField tId;
	private JTextField tName;
	private JTextField tAddress;
	private JTextField tDepartment;
	
	public EmployeeInfoWindow(String title, EmployeeData employee) {
		jframe = this;
		jframe.initialize(title, employee);
		jframe.setVisible(true);
	}
	
	private void initialize(String title, EmployeeData employee) {
		jframe.setTitle(title);
		jframe.setBounds(100, 100, 450, 300);
		jframe.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel southPanel = new JPanel(new FlowLayout());
		jframe.getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		JButton bAddEmp = new JButton("Add Employee");
		JButton bExit = new JButton("Exit");
		southPanel.add(bAddEmp);
		southPanel.add(bExit);

		JPanel centralPanel = new JPanel(new GridLayout(4, 2, 2, 0));
		
		JLabel lId = new JLabel("Employee ID");
		JLabel lname = new JLabel("Employee name");
		JLabel laddress = new JLabel("Employee address");
		JLabel lDepartment = new JLabel("Department");
		
		loadInfo(employee);
		
		centralPanel.add(lId);
		centralPanel.add(tId);
		centralPanel.add(lname);
		centralPanel.add(tName);
		centralPanel.add(laddress);
		centralPanel.add(tAddress);
		centralPanel.add(lDepartment);
		centralPanel.add(tDepartment);
		
		jframe.getContentPane().add(centralPanel, BorderLayout.CENTER);

		bExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientWindow.menuFrame.setEnabled(true);
				jframe.dispose();
			}
		});
		
		bAddEmp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientWindow.employee.setId(Integer.parseInt(tId.getText()));
				ClientWindow.employee.setName(tName.getText());
				ClientWindow.employee.setAddress(tAddress.getText());
				ClientWindow.employee.setDepartment(tDepartment.getText());
				ClientWindow.menuFrame.setEnabled(true);
				ClientWindow.clientApp.registerUser(ClientWindow.employee);
				jframe.dispose();
			}
		});
		
		jframe.addWindowListener(new WindowAdapter() {
			 public void windowClosing(WindowEvent e){
				ClientWindow.menuFrame.setEnabled(true);
				jframe.dispose();
	            }
		});
	}
	
	public void loadInfo(EmployeeData employee) {
		if(employee!=null) {
			tId = new JTextField(employee.getId()+"");
			tName = new JTextField(employee.getName());
			tAddress = new JTextField(employee.getAddress());
			tDepartment = new JTextField(employee.getDepartment());
		} else {
			 tId = new JTextField();
			 tName= new JTextField();
			 tAddress = new JTextField();
			 tDepartment = new JTextField();
		}
	}

	public JTextField gettId() {
		return tId;
	}

	public void settId(JTextField tId) {
		this.tId = tId;
	}

	public JTextField gettName() {
		return tName;
	}

	public void settName(JTextField tName) {
		this.tName = tName;
	}

	public JTextField gettAddress() {
		return tAddress;
	}

	public void settAddress(JTextField tAddress) {
		this.tAddress = tAddress;
	}

	public JTextField gettDepartment() {
		return tDepartment;
	}

	public void settDepartment(JTextField tDepartment) {
		this.tDepartment = tDepartment;
	}
}
