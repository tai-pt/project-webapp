package com.java.project.dao;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import com.java.project.config.Constanst;
import com.java.project.model.Users;

public class UserDAO {
//	Connect Data
	private Connection getConnection() throws ClassNotFoundException {

		Connection con = null;
		Class.forName(Constanst.dbDriver);
		try {
			con = DriverManager.getConnection(Constanst.jdbcURL, Constanst.jdbcUsername, Constanst.jdbcPassword);
			if (con != null) {
				System.out.println("Connect Success");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;

	}

	// Add users
	public boolean addUser(Users user) throws ClassNotFoundException, SQLException {

		String sql = "INSERT INTO Users (name, email, address, avatar) VALUES (?, ?, ?, ?)";
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getAddress());
			stmt.setString(4, user.getAvatar());
			return stmt.executeUpdate() > 0;
		}
	}

//	Slect all users 
	public List<Users> getAllUser() throws SQLException, ClassNotFoundException {
		String sql = "select * from project_all.users";
		List<Users> users = new ArrayList<Users>();
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		System.out.println(st);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			users.add(new Users(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("address"),
					rs.getString("avatar")));
		}
		return users;
	}

//	FindUser 
	public Users findUserById(int id) throws ClassNotFoundException, SQLException {
		Connection con = getConnection();
		Users user = null;
		String sql = "SELECT * FROM project_all.users WHERE id = ?";

		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, id);
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			user = new Users(rs.getInt("id"), rs.getString("name"), rs.getString("email"), rs.getString("address"),
					rs.getString("avatar"));
		}
		return null;
	}
// display
//	public String getUserAvatar(int id) throws SQLException, ClassNotFoundException {
//	    String avatarPath = null;
//	    String sql = "SELECT avatar FROM users WHERE id = ?";
//	    Connection con = getConnection();
//	    try (PreparedStatement ps = con.prepareStatement(sql)) {
//	        ps.setInt(1, id);
//	        try (ResultSet rs = ps.executeQuery()) {
//	            if (rs.next()) {
//	                avatarPath = rs.getString("avatar");
//	            }
//	        }
//	    }
//	    return avatarPath;
//	}


//	UpdateUser
	public boolean updateUser(Users user) throws SQLException, ClassNotFoundException {

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

//	DeleteUser
	public boolean deleteUser(int id) throws SQLException, ClassNotFoundException {
		String query = "DELETE FROM project_all.users WHERE id=?";
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setInt(1, id);
			return preparedStatement.executeUpdate() > 0; // Trả về true nếu có ít nhất một dòng bị xóa
		}
	}

//	public Users Display(Users user ) throws ClassNotFoundException, SQLException, IOException {
//		String query = "SELECT * FROM project_all.users";
//	    try (Connection conn = getConnection();
//	         PreparedStatement stmt = conn.prepareStatement(query)) {
//	    	
//	        stmt.setInt(1, user.getId());
//	        stmt.setString(2, user.getName());
//	        ResultSet rs = stmt.executeQuery();
//
//	        while (rs.next()) {
//	            if (rs.getInt(user.getId())==user.getId()) {
//	            	id = rs.getInt(id);
//	            	
//	            }
//	        }
//	    }
//	    return null; // Trả về null nếu không tìm thấy người dùng
//	}

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