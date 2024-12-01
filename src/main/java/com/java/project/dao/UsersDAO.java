package com.java.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.project.config.Constants;
import com.java.project.model.User;
import com.mysql.cj.exceptions.RSAException;

public class UsersDAO {

	private Connection getConnection() throws ClassNotFoundException {

		Connection con = null;

		Class.forName(Constants.dbDriver);
		try {
			con = DriverManager.getConnection(Constants.jdbcURL, Constants.jdbcUsername, Constants.jdbcPassword);
			if (con != null) {
				System.out.println("Connect Success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;

	}

	// Add users
	public boolean addUser(User user) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO users (name, address, avatar, email) VALUES (?, ?, ?, ?) ";
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, user.getName());
		st.setString(2, user.getAddress());
		st.setString(3, user.getAvatar());
		st.setString(4, user.getEmail());
		return st.executeUpdate() > 0;
	}

	//	Slect all users 
	public List<User> getAllUser() throws SQLException, ClassNotFoundException {
		String sql = "select * from project_all.users";
		List<User> users = new ArrayList<User>();
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		System.out.println(st);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			users.add(new User(rs.getInt("id"), rs.getString("name"), rs.getString("avatar"), rs.getString("address"),
					rs.getString("email")));
		}
		return users;
	}

	//
	public User findUserById(int id) throws ClassNotFoundException, SQLException {
		User user = null;
		String sql = "SELECT * FROM project_all.users WHERE id = ?";
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("avatar"), rs.getString("address"),
					rs.getString("email"));
		}
		return user;

	}

	/*
	 * 
	 * 
	 * 
	 * */
	public boolean updateUser(User user) throws SQLException, ClassNotFoundException {
		
		boolean rowUpdate;
		
		String sql = "update project_all.users set name = ?,email= ?, avatar =?, address =? where id = ?;";
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, user.getName());
		st.setString(2, user.getEmail());
		st.setString(3, user.getAvatar());
		st.setString(4, user.getAddress());
		st.setInt(5, user.getId());
		rowUpdate = st.executeUpdate() > 0;

		return rowUpdate;

	}
	
	/*
	 * 
	 * 
	 * 
	 * */
	public boolean Delete(int id) throws SQLException, ClassNotFoundException {
		boolean delete;
		String sql = "delete from project_all.users where id = ?";
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		delete = st.executeUpdate() > 0;
		return delete;

	}

	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
