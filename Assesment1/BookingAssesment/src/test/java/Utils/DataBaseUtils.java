package Utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.math3.stat.descriptive.summary.Product;

public class DataBaseUtils {

	static final String DB_URL = "jdbc:mysql://localhost/";
	static final String USER = "root";
	static final String PASS = "Dhee@123";
	public static Connection conn;
	public static Statement stmt;

	public DataBaseUtils() {
		try{
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();	      

		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}

	public static void createConnection() throws Exception {
		try {
			String sql = "CREATE DATABASE flightDetails";
			stmt.executeUpdate(sql);
			System.out.println("Database created successfully...");  
		}catch(Exception e) {
			String sql = "CREATE DATABASE flightDetails";
			stmt.executeUpdate("Drop database flightDetails");
			stmt.executeUpdate(sql);
			System.out.println("Database created successfully...");  
		}
	}
	public static void CreateTable() throws Exception {
		stmt.executeUpdate("Use flightDetails");
		stmt.executeUpdate("create table flightAvailablityDetails (id varchar(5),flightname varchar(50),stops varchar(50),price varchar(100),departuretime varchar(10),destinationtime varchar(10))");
	}
	public static void InsertDataintoTable(String ID, String Flightname, String Stops, String Price, String DepartureTime, String DestinationTime) throws Exception {
		String Insertsql = " insert into flightAvailablityDetails (id, flightname, stops, price, departuretime, destinationtime)"
				+ " values (?, ?, ?, ?, ?, ?)";
		PreparedStatement preparedStmt = conn.prepareStatement(Insertsql);
		preparedStmt.setString(1, ID);
		preparedStmt.setString(2, Flightname);
		preparedStmt.setString(3, Stops);
		preparedStmt.setString(4, Price);
		preparedStmt.setString(5, DepartureTime);
		preparedStmt.setString(6, DestinationTime);
		preparedStmt.execute();
	}
	
	public static ArrayList SqlQuery(String query) throws Exception {
		ArrayList productList = new ArrayList();
		Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
		  while(rs.next()) {
		    String id = rs.getString("id");
		    String flightname = rs.getString("flightname");
		    String stops = rs.getString("stops");
		    String price = rs.getString("price");
		    String departuretime = rs.getString("departuretime");
		    String destinationtime = rs.getString("destinationtime");
		    String Value = id+"\t\t"+flightname+"\t\t"+stops+"\t\t"+price+"\t\t"+departuretime+"\t\t"+destinationtime;
		    productList.add(Value);
		  }
		  return productList;
	}


}

