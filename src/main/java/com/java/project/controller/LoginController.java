package com.java.project.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.java.project.dao.LoginDAO;
import com.java.project.model.Login;

public class LoginController extends HttpServlet {


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
		LoginDAO logindao = new LoginDAO();
	    Login a = logindao.Logincheck(email, password);
	 	if (a == null) {
			resp.sendRedirect("WEB-INF/views/Login.jsp");
		} else {
			resp.sendRedirect("WEB-INF/views/users-list.jsp");
		}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	System.out.println("get");
		 req.getRequestDispatcher("WEB-INF/views/Login.jsp").forward(req, resp);
				
	}
}
