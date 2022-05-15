package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;


public class Employee {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/employeeservice?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertEmployee(String empName, String empAddress, String empEmail, String empPhone)  
	{   
		String output = ""; 	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for inserting."; } 
	 
			// create a prepared statement 
			String query = " insert into emp(`empID`,`empName`,`empAddress`,`empEmail`,`empPhone`)" + " values (?, ?, ?, ?, ?)"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, empName);
			 preparedStmt.setString(3, empAddress);
			 preparedStmt.setString(4, empEmail);
			 preparedStmt.setString(5, empPhone);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newEmployee = readEmployee(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Employee.\"}";  
			System.err.println(e.getMessage());   
		} 		
	  return output;  
	} 	
	
	public String readEmployee()  
	{   
		String output = ""; 
		try   
		{    
			Connection con = connect(); 
		
			if (con == null)    
			{
				return "Error while connecting to the database for reading."; 
			} 
	 
			// Prepare the html table to be displayed    
			output = "<table border=\'1\'><tr><th>Name</th><th>Address</th><th>Email</th><th>Phone Number</th><th>Update</th><th>Remove</th></tr>";
	 
			String query = "select * from emp";    
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
	 
			// iterate through the rows in the result set    
			while (rs.next())    
			{     
				String empID = Integer.toString(rs.getInt("empID"));
				 String empName = rs.getString("empName");
				 String empAddress = rs.getString("empAddress");
				 String empEmail = rs.getString("empEmail");
				 String empPhone = rs.getString("empPhone");
				 
				// Add into the html table 
				output += "<tr><td><input id=\'hidEmployeeIDUpdate\' name=\'hidEmployeeIDUpdate\' type=\'hidden\' value=\'" + empID + "'>" 
							+ empName + "</td>"; 
				output += "<td>" + empAddress + "</td>";
				output += "<td>" + empEmail + "</td>";
				output += "<td>" + empPhone + "</td>";
	 
				// buttons     
				output +="<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary'></td>"       
						+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-employeeid='" + empID + "'>" + "</td></tr>"; 
			
			}
			con.close(); 
	   
			output += "</table>";   
		}   
		catch (Exception e)   
		{    
			output = "Error while reading the Employee.";    
			System.err.println(e.getMessage());   
		} 	 
		return output;  
	}
	
	public String updateEmployee(String empID, String empName, String empAddress, String empEmail, String empPhone)  
	{   
		String output = "";  
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{return "Error while connecting to the database for updating."; } 
	 
			// create a prepared statement    
			String query = "UPDATE emp SET empName=?,empAddress=?,empEmail=?,empPhone=?"  + "WHERE empID=?";  	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, empName);
			 preparedStmt.setString(2, empAddress);
			 preparedStmt.setString(3, empEmail);
			 preparedStmt.setString(4, empPhone);
			 preparedStmt.setInt(5, Integer.parseInt(empID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close();  
			String newEmployee = readEmployee();    
			output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Employee.\"}";   
			System.err.println(e.getMessage());   
		} 	 
	  return output;  
	} 
	
	public String deleteEmployee(String empID)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 			
			} 
	 
			// create a prepared statement    
			String query = "delete from emp where empID=?"; 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(empID)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newEmployee = readEmployee();    
			output = "{\"status\":\"success\", \"data\": \"" +  newEmployee + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "Error while deleting the Employee.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
}
