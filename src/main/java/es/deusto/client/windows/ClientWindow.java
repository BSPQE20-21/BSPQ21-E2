package es.deusto.client.windows;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

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
	static ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.forLanguageTag("en"));

	public ClientWindow(ClientApp clientApp) {
		this.clientApp = clientApp;
		
		menuFrame = new JFrame();
		menuFrame.setTitle(resourceBundle.getString("title"));
		menuFrame.setBounds(100, 100, 450, 300);
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.getContentPane().setLayout(new BorderLayout());
		
		JButton btnClose = new JButton(resourceBundle.getString("b_quit"));
		menuFrame.getContentPane().add(btnClose, BorderLayout.SOUTH);

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(0, 1, 0, 0));
		menuFrame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		
		JLabel manageLabel = new JLabel(resourceBundle.getString("l_mng_empl"));
		mainPanel.add(manageLabel);
		
		JButton addEmp = new JButton(resourceBundle.getString("b_add_empl"));
		JButton rmvEmp = new JButton(resourceBundle.getString("b_rmv_empl"));
		JButton seeEmp = new JButton(resourceBundle.getString("b_see_empl"));

		mainPanel.add(addEmp);
		mainPanel.add(rmvEmp);
		mainPanel.add(seeEmp);

		JLabel manageTeamLabel = new JLabel(resourceBundle.getString("l_mng_team"));
		mainPanel.add(manageTeamLabel);
		
		JButton bLeader = new JButton(resourceBundle.getString("b_leader"));
		mainPanel.add(bLeader);
		
		btnClose.addActionListener(new ActionListener() {
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
							EmployeeInfoWindow auxWindow = new EmployeeInfoWindow(resourceBundle.getString("b_add_empl"));							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		
		rmvEmp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							menuFrame.setEnabled(false);
							RemoveEmployee auxWindow = new RemoveEmployee(resourceBundle.getString("b_rmv_empl"), null);							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

				
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
		
		
		bLeader.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				clientApp.getEmployees();
				
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							menuFrame.setEnabled(false);
							ChooseLeaderWindow auxWindow = new ChooseLeaderWindow(resourceBundle.getString("b_leader"),clientApp.employees);							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});

		menuFrame.setVisible(true);
	}
}
