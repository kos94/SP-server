package sp_server;

import javax.xml.ws.Endpoint;

public class ServerMain {
	public static void main(String[] args) {
		System.out.println("Server main");
        String protocol = "http://",
                ip_dns = "localhost",
                port = ":8181",
                dir = "/spkurs/",
                service = "server",
                address = protocol + ip_dns + port + dir + service;
        Server server = new Server();
//        Endpoint.publish(address, server);
//        System.out.println("WSDL is published on\n" + address + "?wsdl");
	}
}
