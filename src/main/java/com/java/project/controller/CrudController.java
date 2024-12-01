package com.java.project.controller;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.java.project.dao.CrudDAO;
import com.java.project.model.Crud;
@MultipartConfig(maxFileSize = 104857600) // 100MB
public class CrudController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = req.getServletPath();

		try {
			switch (action) {
			case "/new":
				shownewForm(req, resp);
				break;
			case "/insert":
				insertuser(req, resp);
				break;
			case "/delete":
				delete(req, resp);
				break;
			case "/edit":
				edituser(req, resp);
				break;
			case "/upadate":
				updateuser(req, resp);
			default:
				ListUser(req, resp);
				break;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void ListUser(HttpServletRequest req, HttpServletResponse resp)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		CrudDAO crud = new CrudDAO();
		List<Crud> list = crud.getAllCrud();
		req.setAttribute("list", list);
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/users-list.jsp");
		dispatcher.forward(req, resp);

	}

	private void edituser(HttpServletRequest req, HttpServletResponse resp)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(req.getParameter("id"));
		CrudDAO crud = new CrudDAO();
		crud.Slect(id);
		req.setAttribute("crud", crud);
		req.getRequestDispatcher("WEB-INF/views/users-form.jsp").forward(req, resp);

	}

	private void updateuser(HttpServletRequest req, HttpServletResponse resp)
	        throws ClassNotFoundException, SQLException, IOException, ServletException {
	    CrudDAO crud = new CrudDAO();
	    String name = req.getParameter("name");
	    String infor = req.getParameter("infor");
	    String address = req.getParameter("address");

	    InputStream inputStream = null;
	    Part filePart = req.getPart("photo");

	    if (filePart != null) {
	        inputStream = filePart.getInputStream(); // Nếu có ảnh, lấy InputStream
	    }

	    int id = Integer.parseInt(req.getParameter("id"));
	    Crud a = new Crud(id, name, infor, address, inputStream);

	    boolean success = crud.Update(a);

	    if (success) {
	        resp.sendRedirect("list");
	    } else {
	        req.setAttribute("message", "Update failed");
	        req.getRequestDispatcher("WEB-INF/views/users-form.jsp").forward(req, resp);
	    }
	}


	private void delete(HttpServletRequest req, HttpServletResponse resp)
			throws ClassNotFoundException, SQLException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		CrudDAO crud = new CrudDAO();
		crud.Delete(id);
		resp.sendRedirect("list");

	}

	private void insertuser(HttpServletRequest req, HttpServletResponse resp)
	        throws IOException, ServletException, ClassNotFoundException, SQLException {
	    CrudDAO crud = new CrudDAO();
	    String name = req.getParameter("name");
	    String infor = req.getParameter("infor");
	    String address = req.getParameter("address");

	    InputStream inputStream = null;
	    Part filePart = req.getPart("photo");
	    
	    if (filePart != null) {
	        inputStream = filePart.getInputStream(); 
	    }
	    Crud a = new Crud(name, infor, address, inputStream);
	    boolean success = crud.Insert(a);

	    if (success) {
	        req.setAttribute("message", "File uploaded and saved into database");
	    } else {
	        req.setAttribute("message", "File upload failed");
	    }
	    
	    req.getRequestDispatcher("WEB-INF/views/users-list.jsp").forward(req, resp);
	}


	private void shownewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/users-form.jsp");
		dispatcher.forward(req, resp);
	}

}
