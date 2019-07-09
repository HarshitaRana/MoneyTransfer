package com.moneytransfer;

import org.apache.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

import com.moneytransfer.api.AccountApi;
import com.moneytransfer.misc.SampleData;

//This is the start point of our Application

public class MoneyTransfer {
	private static Logger log = Logger.getLogger(MoneyTransfer.class);

	public static void main(String[] args) {
		
		try{
			SampleData data = new SampleData();
			data.loadData();
			startServer();
		}catch(Exception e){
			log.error("Unable to initialize application!");
		}
	}

	private static void startServer() throws Exception {
		Server server = new Server(8080);
		ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		ServletHolder servletHolder = context.addServlet(ServletContainer.class, "/*");
		servletHolder.setInitParameter("jersey.config.server.provider.classnames",
				AccountApi.class.getCanonicalName());
		try {
			server.start();
			server.join();
		} finally {
			server.destroy();
		}
	}
		
}
