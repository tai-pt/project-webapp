package com.java.project.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.project.dao.SigninDAO;

public class SiginController  extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
		resp.setContentType("text/html");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		SigninDAO b = new SigninDAO();
	    b.SignCheck(email, password);
	    if (b != null) {
			resp.sendRedirect("WEB-INF/views/Login.jsp");
		} else {
			resp.sendRedirect("WEB-INF/views/Sigin.jsp");
		}
	    
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.getRequestDispatcher("WEB-INF/views/Sign.jsp").forward(req, resp);
				}
}
