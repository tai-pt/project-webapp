package com.java.project.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.java.project.dao.UsersDAO;
import com.java.project.model.User;

@MultipartConfig(maxFileSize = 104857600) // 100MB
public class UserController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String FILE_PATH = "D:\\project-webapp-master\\src\\main\\webapp\\resources\\avatar";
	private File file;

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
//			case "/upadate":
//				updateuser(req, resp);
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
		UsersDAO crud = new UsersDAO();
		List<User> list = crud.getAllUser();
		req.setAttribute("list", list);
		RequestDispatcher dispatcher = req.getRequestDispatcher("WEB-INF/views/users-list.jsp");
		dispatcher.forward(req, resp);

	}

	private void edituser(HttpServletRequest req, HttpServletResponse resp)
			throws ClassNotFoundException, SQLException, ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(req.getParameter("id"));
		UsersDAO crud = new UsersDAO();
		crud.findUserById(id);
		req.setAttribute("crud", crud);
		req.getRequestDispatcher("WEB-INF/views/users-form.jsp").forward(req, resp);

	}

//	private void updateuser(HttpServletRequest req, HttpServletResponse resp)
//	        throws ClassNotFoundException, SQLException, IOException, ServletException {
//	    UsersDAO crud = new UsersDAO();
//	    String name = req.getParameter("name");
//	    String infor = req.getParameter("infor");
//	    String address = req.getParameter("address");
//
//	    InputStream inputStream = null;
//	    Part filePart = req.getPart("photo");
//
//	    if (filePart != null) {
//	        inputStream = filePart.getInputStream(); // Nếu có ảnh, lấy InputStream
//	    }
//
//	    int id = Integer.parseInt(req.getParameter("id"));
//	    User a = new User(id, name, infor, address, inputStream);
//
//	    boolean success = crud.Update(a);
//
//	    if (success) {
//	        resp.sendRedirect("list");
//	    } else {
//	        req.setAttribute("message", "Update failed");
//	        req.getRequestDispatcher("WEB-INF/views/users-form.jsp").forward(req, resp);
//	    }
//	}

	private void delete(HttpServletRequest req, HttpServletResponse resp)
			throws ClassNotFoundException, SQLException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		UsersDAO crud = new UsersDAO();
		crud.Delete(id);
		resp.sendRedirect("list");

	}

	// /Project-webapp/src/main/webapp/resources/avatar
	private void insertuser(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException, ClassNotFoundException, SQLException {
		UsersDAO userDao = new UsersDAO();
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String address = req.getParameter("address");
		String avatar = "";

		
		// handle save file to disk
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		try {
			// Parse the request to get file items.
			List<FileItem> fileItems = upload.parseRequest((RequestContext)req);

			// Process the uploaded file items
			Iterator i = fileItems.iterator();

			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					// Get the uploaded file parameters
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();

					// Write the file
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(FILE_PATH + fileName.substring(fileName.lastIndexOf("\\")));
					} else {
						file = new File(FILE_PATH + fileName.substring(fileName.lastIndexOf("\\") + 1));
					}
					fi.write(file);
					avatar = file.getAbsolutePath() + file.getName();
				}
			}
		} catch (Exception ex) {
			System.out.println(ex);
		}



		if (userDao.addUser(new User(name, avatar,address,email))) {
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
