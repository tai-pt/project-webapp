package com.java.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.java.project.config.Constants;
import com.java.project.model.Signin;

public class SigninDAO {

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
	public Signin SignCheck(String email, String password) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO project_all.users (email, password)\r\n"
				+ "VALUES (?,?);";
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, email);
		st.setString(2, password);
		int rs = st.executeUpdate();
	    if (rs >0) {
	    	Signin a = new Signin();
	    }else {
	    	System.out.println("error");
	    }
		con.close();
		return null;
		
	}
}
