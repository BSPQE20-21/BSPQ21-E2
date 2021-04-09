package es.deusto.server.services;

public class Service {
	
	private static Service instance;
	
	private Service() { }
	
	public static Service getInstance() {
		if (instance == null) {
			instance = new Service();
		}
		return instance;
	}

}
