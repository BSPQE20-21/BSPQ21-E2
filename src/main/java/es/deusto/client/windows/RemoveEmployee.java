package es.deusto.client.windows;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import es.deusto.serialization.EmployeeData;




public class RemoveEmployee extends JFrame{
	
	public RemoveEmployee jframe;
	static ResourceBundle resourceBundle = ResourceBundle.getBundle("SystemMessages", Locale.forLanguageTag("en"));
	private static final Logger log = Logger.getLogger(RemoveEmployee.class.getName());
	
	
	public RemoveEmployee(String title, ArrayList<EmployeeData> employees) {
		jframe = this;
		jframe.initialize(title, employees);
		jframe.setVisible(true);
	}
	
	private void initialize(String title, ArrayList<EmployeeData> employees) {
		//log.info(resourceBundle.getString("ch_leader_w_init"));
		jframe.setTitle(title);
		jframe.setBounds(100, 100, 450, 300);
		jframe.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel southPanel = new JPanel(new FlowLayout());
		jframe.getContentPane().add(southPanel, BorderLayout.SOUTH);
		
		JButton bRmvEmp = new JButton(resourceBundle.getString("b_rmv_empl"));
		JButton bExit = new JButton(resourceBundle.getString("b_quit"));
		southPanel.add(bRmvEmp);
		southPanel.add(bExit);
		
		JPanel centralPanel = new JPanel();
		jframe.getContentPane().add(centralPanel, BorderLayout.CENTER);
		
		
		
		JComboBox<String> comboBox = new JComboBox<String>();
		for (EmployeeData employee : employees) {
			System.out.println(employee.getName());
			comboBox.addItem(employee.getName());
		}
		centralPanel.add(comboBox);

		bExit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ClientWindow.menuFrame.setEnabled(true);
				jframe.dispose();
			}
		});
		
		bRmvEmp.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int optionDelete = comboBox.getSelectedIndex();
				employees.remove(optionDelete);				
				
				
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
}
