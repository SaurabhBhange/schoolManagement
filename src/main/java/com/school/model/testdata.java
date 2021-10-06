package com.school.model;

import java.sql.*;

class testdata {
	public static void main(String args[]) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://testingdatabase.cs46otricqzq.ap-south-1.rds.amazonaws.com:3307/testingdatabase", "admin", "$Rajat20#");
//here sonoo is database name, root is username and password  
			Statement stmt = con.createStatement();
			if(con == null) {
				System.out.println("null connection");
			}
			else {
				System.out.println("connected");
			}
			ResultSet rs = stmt.executeQuery("select * from Persons");
			while (rs.next())
				System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}