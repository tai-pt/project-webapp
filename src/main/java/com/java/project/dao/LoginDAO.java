package com.java.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.project.config.Constants;
import com.java.project.model.Login;

public class LoginDAO {

	private Connection getConnection() throws ClassNotFoundException {
		
		Connection con = null;
		
		Class.forName(Constants.dbDriver);
		
		try {
			con = DriverManager.getConnection(Constants.jdbcURL,Constants.jdbcUsername,Constants.jdbcPassword);
			if (con != null) {
				System.out.println("Connect Success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	  public Login Logincheck (String email , String password) throws ClassNotFoundException, SQLException {
		  String sql = "select * from project_all.users where email = ? and password = ?";
		  Connection con = getConnection();
		  PreparedStatement st = con.prepareStatement(sql);
		  st.setString(1,"email");
		  st.setString(2,"password");
		  ResultSet rs = st.executeQuery();
		  while (rs.next()) {
			  Login login = new Login (email , password);
		  }
		return null;
	  }
	  
}
