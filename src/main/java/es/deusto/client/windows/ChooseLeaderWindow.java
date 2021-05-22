package es.deusto.client.windows;

import java.awt.BorderLayout; 
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import es.deusto.serialization.EmployeeData;
import es.deusto.server.Server;

public class ChooseLeaderWindow extends JFrame{
	
	public ChooseLeaderWindow jframe;
	public JComboBox<String> departCombo;
	public JComboBox<String> employeeCombo;
	private ArrayList<EmployeeData> employees;
	private ArrayList<String> departmentList;
	public HashMap<String, ArrayList<EmployeeData>> departmentMap;
	private static final Logger log = Logger.getLogger(ChooseLeaderWindow.class.getName());
	static ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.forLanguageTag("en"));
	
	/**
	 * Window that handles the choose leader functionality.
	 * @param title The name of the window.
	 * @param employees A list of all the employees.
	 */
	public ChooseLeaderWindow(String title, ArrayList<EmployeeData> employees) {
		jframe = this;
		jframe.employees = employees;
		jframe.mapEmployees();
		jframe.initialize(title);
		jframe.setVisible(true);
	}
	
	/**
	 * Initializes all the contents of the ChooseLeaderWindow
	 * @param title The name of the window.
	 */
	private void initialize(String title) {
		log.info(resourceBundle.getString("ch_leader_w_init"));
		jframe.setTitle(title);
		jframe.setBounds(100, 100, 450, 200);
		jframe.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel comboPanel = new JPanel(new FlowLayout());
		JLabel dptLabel = new JLabel(resourceBundle.getString("err_add_empl"));
		departCombo = new JComboBox(departmentList.toArray());
		departCombo.setPrototypeDisplayValue("teeeeeeeeeeeeext");
		comboPanel.add(dptLabel);
		comboPanel.add(departCombo);
		
		JPanel centralPanel = new JPanel(new BorderLayout());
		
		JPanel southPanel = new JPanel(new FlowLayout());
		JButton btnClose = new JButton(resourceBundle.getString("b_quit"));
		southPanel.add(btnClose);
		
		jframe.add(comboPanel, BorderLayout.NORTH);
		jframe.add(centralPanel, BorderLayout.CENTER);
		jframe.add(southPanel, BorderLayout.SOUTH);
		
		
		departCombo.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				log.info(resourceBundle.getString("chosen_dpt")+" "+departCombo.getSelectedItem().toString());
				JLabel questionLabel = new JLabel(resourceBundle.getString("l_ch_leader"), SwingConstants.CENTER);
				String dptSelection = departCombo.getSelectedItem().toString();
				JComboBox<EmployeeData> employeeCombo = new JComboBox(departmentMap.get(dptSelection).toArray());
				JPanel comboPanel = new JPanel(new BorderLayout());
				JButton mkLeader = new JButton(resourceBundle.getString("b_mk_leader"));
				
				comboPanel.add(employeeCombo, BorderLayout.NORTH);
				comboPanel.add(mkLeader, BorderLayout.SOUTH);
				centralPanel.add(questionLabel, BorderLayout.NORTH);
				centralPanel.add(comboPanel, BorderLayout.CENTER);
				
				jframe.revalidate();
				jframe.repaint();
				
				mkLeader.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						int emplNumber = employeeCombo.getSelectedIndex();
						log.info(resourceBundle.getString("chosen_leader")+" "+departmentMap.get(dptSelection).get(emplNumber).getName());

						for (int i = 0; i < departmentMap.get(dptSelection).size(); i++) {
							if (i == emplNumber) {
								(departmentMap.get(dptSelection)).get(i).setLeader(true);
							} else {
								(departmentMap.get(dptSelection)).get(i).setLeader(false);
							}
						}
						
						ClientWindow.clientApp.updateEmployees(departmentMap.get(dptSelection));
					}
				});
			}
		});
		
		btnClose.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientWindow.menuFrame.setEnabled(true);
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
	
	/**
	 * Creates a list of all the departments and a list of employees hash map, where the department name is the key.
	 */
	private void mapEmployees() {
		departmentList = new ArrayList<>();
		departmentMap = new HashMap<>();
		
		for (EmployeeData employee : employees) {
			if(!departmentList.contains(employee.getDepartment())) {
				departmentList.add(employee.getDepartment());
				ArrayList<EmployeeData> employeeList = new ArrayList<>();
				employeeList.add(employee);
				departmentMap.put(employee.getDepartment(), employeeList);				
			
			} else {
				departmentMap.get(employee.getDepartment()).add(employee);
			}
		}
	}
	
	
}
