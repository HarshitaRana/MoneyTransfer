package com.moneytransfer.misc;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.apache.log4j.Logger;
import org.h2.tools.RunScript;

import com.moneytransfer.util.DBConnection;

//This class will load Sample/test data for our application
//SQL file used : sampleData.sql

public class SampleData {

	public void loadData(){
		DBConnection db = new DBConnection();
		Connection con = db.getConnection();
		try {
			RunScript.execute(con, new FileReader("src/main/resources/sampleData.sql"));
		} catch (FileNotFoundException e) {
			log.error("Sample Data : sampleData.sql not found");
		} catch (SQLException e) {
			log.error("SampleData : Error executing RunScript on H2 Db");
		}finally{
			DbUtils.closeQuietly(con);
		}
	}
	
	private static Logger log = Logger.getLogger(SampleData.class);
}
