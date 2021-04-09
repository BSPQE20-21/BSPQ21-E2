package es.deusto.client.service;

import java.rmi.Naming;

import es.deusto.server.facade.IRemoteFacade;

public class ServiceLocator {
	
	private IRemoteFacade service;
	
	public void setService(String ip, String port, String serverName) {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
		}
		
		try {
			String URL = "//" + ip + ":" + port + "/" + serverName;
			this.service = (IRemoteFacade) Naming.lookup(URL);
		} catch (Exception ex) {
			System.err.println("#Error locating remote facade: " + ex);
		}
	}
	
	public IRemoteFacade getService() {
		return this.service;
	}

}
