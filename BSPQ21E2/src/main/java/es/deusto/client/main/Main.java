package es.deusto.client.main;

import javax.swing.JFrame;

import es.deusto.client.controller.ControllerAdmin;
import es.deusto.client.service.ServiceLocator;
import es.deusto.client.windows.WindowAdmin;

public class Main {

	public static void main(String[] args) {
		ServiceLocator serviceLocator = new ServiceLocator();
		serviceLocator.setService(args[0], args[1], args[2]);
		
		ControllerAdmin controllerAdmin = new ControllerAdmin(serviceLocator);
		
		WindowAdmin windowAdmin = new WindowAdmin(controllerAdmin);
		windowAdmin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
