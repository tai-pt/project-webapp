package com.java.project.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.java.project.config.Constants;
import com.java.project.model.Crud;
import com.mysql.cj.exceptions.RSAException;

public class CrudDAO {

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
	public boolean Insert (Crud crud) throws ClassNotFoundException, SQLException {
		String sql = "INSERT INTO Users (name, infor, address, photo) VALUES (?, ?, ?, ?)\" ";
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, crud.getName());
		st.setString(2, crud.getInfor());
		st.setString(3, crud.getAddress());
		st.setBytes(4,crud.getPhoto());
		int rs = st.executeUpdate();
		return false;
	}
//	Slect all users 
	public List <Crud> getAllCrud() throws SQLException, ClassNotFoundException{
		String sql = "select * from project_all.crud";
		List <Crud> crud = new ArrayList<Crud>();
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		System.out.println(st);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			crud.add(new Crud (rs.getInt("id"),rs.getString("name"),rs.getString("infor"),rs.getString("address"),rs.getBytes("photo")));
		}
		return crud;
	}
	//
	public Crud Slect (int id) throws ClassNotFoundException, SQLException {
		Crud crud = null;
		String sql="SELECT * FROM project_all.crud WHERE id = ?";
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(sql);
	    st.setInt(1, crud.getId());
		ResultSet rs = st.executeQuery();
		while (rs.next()) {
			st.setString(1, crud.getName());
			st.setString(2, crud.getInfor());
			st.setString(3, crud.getAddress());
			st.setBytes(4, crud.getPhoto());
			crud = new Crud (id ,crud.getName(),  crud.getInfor(), crud.getAddress() , crud.getPhoto());
		}
		return crud;
		
	}

	public boolean Update(Crud crud) throws SQLException, ClassNotFoundException {
		boolean rowupdate;
		String sql = "update project_all.crud set name = ?,email= ?, country =? where id = ?;";
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, crud.getName());
		st.setString(2, crud.getInfor());
		st.setString(3, crud.getAddress());
		st.setBytes(3, crud.getPhoto());
		st.setInt(1, crud.getId());
		rowupdate = st.executeUpdate() > 0;

		return rowupdate;
		
		
	}
	public boolean Delete (int id) throws SQLException, ClassNotFoundException {
		boolean delete;
		String sql = "delete from project_all.crud where id = ?";
		Connection con = getConnection();
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1,id);
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
