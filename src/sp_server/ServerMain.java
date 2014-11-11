package sp_server;

import javax.xml.ws.Endpoint;

public class ServerMain {
	public static void main(String[] args) {
		System.out.println("Server main");
		String address = "http://localhost:8181/spkurs/server";
		Server server = new Server();
//		Endpoint.publish(address, server);
//		System.out.println("WSDL is published on\n" + address + "?wsdl");
	}
}
