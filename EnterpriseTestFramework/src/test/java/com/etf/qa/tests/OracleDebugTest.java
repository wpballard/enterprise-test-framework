package com.etf.qa.tests;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.etf.qa.common.TestBase;
import com.etf.qa.db.DBConnector;

public class OracleDebugTest extends TestBase {
	
	
//	@Test() 
//	public void ValidateQueryinKgen() throws SQLException{
//		String QueryString;
//		Connection connection;
//		ResultSet resultSet;
//		DBConnector DBConnector = new DBConnector();
//		//CustomerID = "1931008067";
//		//connection = DBConnector.getOracleConnection("dbusername","dbpassword","@dbserverhostname","1525","DBNAME");

//		connection = DBConnector.getOracleConnection(
//				DBConnector.dbProp.getProperty("kgenDbUserID"), 
//				DBConnector.dbProp.getProperty("kgenDbPassword"), 
//				DBConnector.dbProp.getProperty("kgenDbServer"), 
//				DBConnector.dbProp.getProperty("kgenDbPort"), 
//				DBConnector.dbProp.getProperty("kgenDbName")
//				);
//		
//		QueryString = "select query_code from query where customer_id = '1931008067'";
//		System.out.println(QueryString);
//		resultSet = DBConnector.queryDB(connection, QueryString);
//	String SVID = null ;
//		while(resultSet.next()){
//			SVID =resultSet.getString(1);
//		}
//		
//		System.out.println(SVID);
//		Assert.assertTrue(!SVID.isEmpty(), "SVID Created");
//	}

}
