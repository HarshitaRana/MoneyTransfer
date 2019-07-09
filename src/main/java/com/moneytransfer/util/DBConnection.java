package com.moneytransfer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;

//DB connection class : Establishes connection with H2 DB
public class DBConnection {

	//Returns H2 DB connection instance to its requesters
	public Connection getConnection(){
		final HashMap<String, String> dbProps = loadProps();
		Connection con=null;
		try {
			DbUtils.loadDriver(dbProps.get("h2_driver"));
			con = DriverManager.getConnection(dbProps.get("h2_connection_url"), dbProps.get("h2_user"),dbProps.get("h2_password"));
		} catch (SQLException e) {
			log.error("DBConnection : Error setting up DB connection");
		}

		return con; 
	}

	//Read config parameters for DB connection
	private HashMap<String, String> loadProps() {
		ConfigResource resource=null;
		try {
			resource = ConfigResource.getInstance();
		} catch (ConfigurationException e) {
			log.error("DBConnection: Error reading/loading DB configs");
		}
		final HashMap<String, String> dbProps = resource.getProperties();
		return dbProps;
	}

	private static Logger log = Logger.getLogger(DBConnection.class);
}
