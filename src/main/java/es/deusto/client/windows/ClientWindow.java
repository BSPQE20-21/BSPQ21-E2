package es.deusto.client.windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import es.deusto.client.ClientApp;
import es.deusto.serialization.EmployeeData;

public class ClientWindow {
	
	public static JFrame menuFrame;
	public static EmployeeData employee = new EmployeeData();
	public static ClientApp clientApp;
	
	public ClientWindow(ClientApp clientApp) {
		this.clientApp = clientApp;
		
		menuFrame = new JFrame();
		menuFrame.setTitle("MENU");
		menuFrame.setBounds(100, 100, 450, 300);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.getContentPane().setLayout(new BorderLayout());
		
		JButton btnNewButton = new JButton("Quit");
		menuFrame.getContentPane().add(btnNewButton, BorderLayout.SOUTH);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
		menuFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		JLabel manageLabel = new JLabel("Manage Employees");
		mainPanel.add(manageLabel);
		
		JButton addEmp = new JButton("Add Employee");
		JButton rmvEmp = new JButton("Remove employee");
		JButton seeEmp = new JButton("See Employee");

		mainPanel.add(addEmp);
		mainPanel.add(rmvEmp);
		mainPanel.add(seeEmp);

		JLabel manageTeamLabel = new JLabel("Manage Teams");
		mainPanel.add(manageTeamLabel);
		
		JButton choLeader = new JButton("Choose Leader");
		mainPanel.add(choLeader);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				menuFrame.dispose();
			}
		});
		
		addEmp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							menuFrame.setEnabled(false);
							EmployeeInfoWindow auxWindow = new EmployeeInfoWindow("Add employee", null);							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		
		rmvEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/**EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							menuFrame.setVisible(false);
							ClientApp window3 = new ClientApp(2);				
							window3.frmRemoveEmployee.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});**/

				
			}
		});
		
		seeEmp.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				String sEmployee = ""; 
				clientApp.getEmployees();
				for (EmployeeData employee : clientApp.employees) {
					sEmployee = sEmployee + employee.toString() + "\n";
				}
				JOptionPane.showMessageDialog(menuFrame, sEmployee);
			}
		});
		
		
		choLeader.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clientApp.getEmployees();
				
			}
		});
				
				
		
		menuFrame.setVisible(true);
	}
}
