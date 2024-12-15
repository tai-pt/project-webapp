package com.java.project.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.java.project.config.Constanst;
import com.java.project.dao.UserDAO;
import com.java.project.model.Users;
import com.mysql.cj.Constants;


@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 50, // 50MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB

public class UserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String FILE_PATH = "upload";

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
			case "/insertUser":
				insertuser(req, resp);
				break;
//			  case "/displayAvatar":
//	                displayAvatar(req, resp);
//	                break;
			case "/deleteUser":
				delete(req, resp);
				break;
			case "/editUser":
				editUser(req,resp);
				break;
			case "/upadateUser":
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





	    

	 

	
	

	
	
	private void updateuser(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, ClassNotFoundException {
		UserDAO userdao = new UserDAO();
		String name = req.getParameter("name ");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String avatar = "D:/Java/Project-webapp/Project-webapp/src/main/webapp/images";

		File folderUpload = new File(avatar);

		if (!folderUpload.exists()) {
			folderUpload.mkdir();
		}
		Part filePart = req.getPart("file");
		String fileName = filePart.getSubmittedFileName();
		for (Part part : req.getParts()) {
			part.write(folderUpload + File.separator + fileName);
		}
		System.out.println(folderUpload + File.separator + fileName);

		Users user = new Users(name, email, address, avatar);
		try {
			boolean isAdded = userdao.updateUser(user);
			if (isAdded) {
				req.setAttribute("message", "Người dùng đã được thêm thành công.");
				resp.sendRedirect("list"); // Chuyển hướng đến trang danh sách
			} else {
				req.setAttribute("message", "Thêm người dùng thất bại.");
				req.getRequestDispatcher("WEB-INF/views/users-update.jsp").forward(req, resp);
			}
		} catch (SQLException e) {
			req.setAttribute("message", "Lỗi cơ sở dữ liệu: " + e.getMessage());
			req.getRequestDispatcher("list").forward(req, resp);
		}

	}
	
	


	private void ListUser(HttpServletRequest req, HttpServletResponse resp)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		resp.setContentType("image/gif");
		UserDAO user = new UserDAO();
		List<Users> list = user.getAllUser();
		req.setAttribute("list", list);
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/users-list.jsp");
		dispatcher.forward(req, resp);

	}

	private void editUser(HttpServletRequest req, HttpServletResponse resp)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		String id = req.getParameter("id");
      int id1 = Integer.parseInt(id);
      UserDAO userdao = new UserDAO();
      Users user = userdao.findUserById(id1);
      req.getRequestDispatcher("WEB-INF/views/users-update.jsp").forward(req, resp);
      req.setAttribute("user", user);
  
	
	}

	private void delete(HttpServletRequest req, HttpServletResponse resp)
	        throws ClassNotFoundException, SQLException, IOException, ServletException {
	    int id = Integer.parseInt(req.getParameter("id"));
	    UserDAO userdao = new UserDAO();
	    boolean isDeleted = userdao.deleteUser(id);
	    if (isDeleted) {
	        req.setAttribute("message", "User deleted successfully.");
	    } else {
	        req.setAttribute("message", "Failed to delete user. User might not exist.");
	    }
	    resp.sendRedirect("list");
	}


	private void insertuser(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, ClassNotFoundException, SQLException {
		resp.setContentType("image/jpeg");
		UserDAO userdao = new UserDAO();
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String avatar = "D:/Java/Project-webapp/Project-webapp/src/main/webapp/images";
		File folderUpload = new File(avatar);
		if (!folderUpload.exists()) {
			folderUpload.mkdir();
		}
		Part filePart = req.getPart("file");
		String fileName = filePart.getSubmittedFileName();
		for (Part part : req.getParts()) {
			part.write(folderUpload + File.separator + fileName);
		}
		System.out.println(folderUpload + File.separator + fileName);

		Users user = new Users(name, email, address, avatar);
		try {
			boolean isAdded = userdao.addUser(user);
			if (isAdded) {
				req.setAttribute("message", "Người dùng đã được thêm thành công.");
				resp.sendRedirect("list"); // Chuyển hướng đến trang danh sách
			} else {
				req.setAttribute("message", "Thêm người dùng thất bại.");
				req.getRequestDispatcher("WEB-INF/views/users-insert.jsp").forward(req, resp);
			}
		} catch (SQLException e) {
			req.setAttribute("message", "Lỗi cơ sở dữ liệu: " + e.getMessage());
			req.getRequestDispatcher("WEB-INF/views/users-insert.jsp").forward(req, resp);
			req.getAttribute("avatar");
		}
	}
			

	private void shownewForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/users-insert.jsp");
		dispatcher.forward(req, resp);
	}
}
