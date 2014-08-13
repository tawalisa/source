package com.lijia.jetty;

import org.eclipse.jetty.proxy.ProxyServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

public class ProxyTest {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Server server = new Server(8080); 
        
        ServletHandler servletHandler = new ServletHandler(); 
        servletHandler.addServletWithMapping(ProxyServlet.class, "/*"); 
        server.setHandler(servletHandler); 
         
        server.start(); 
        server.join();
	}

}
