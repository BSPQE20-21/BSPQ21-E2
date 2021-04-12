package es.deusto.client.windows;

import javax.swing.JFrame;

import es.deusto.client.controller.ControllerAdmin;

public class WindowAdmin extends JFrame {
	
	private static final long serialVersionUID = 1L;
	private static WindowAdmin instance;
	
	
	public WindowAdmin(ControllerAdmin controllerAdmin) {
		// TODO Auto-generated constructor stub
	}


	public static WindowAdmin getInstance() {
		return instance;
	}

}
