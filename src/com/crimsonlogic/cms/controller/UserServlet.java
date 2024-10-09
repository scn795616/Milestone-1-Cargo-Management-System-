package com.crimsonlogic.cms.controller;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crimsonlogic.cms.dao.LocationDao;
import com.crimsonlogic.cms.dao.UserDao;
import com.crimsonlogic.cms.model.Location;
import com.crimsonlogic.cms.model.User;
import com.crimsonlogic.cms.util.DatabaseUtil;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		 String password = request.getParameter("password");
		 try (Connection connection = DatabaseUtil.getConnection()) {
			 UserDao userDAO = new UserDao(connection);
			 User user = userDAO.getUserByUsername(username);
			 if (user != null && user.getPassword().equals(password)) {
				 HttpSession session = request.getSession();
				 session.setAttribute("user", user);
				 session.setAttribute("role", user.getRole());
				 
				 LocationDao locationDao=new LocationDao(connection);
				 List<Location> locationList=locationDao.getAllLocation();
				 session.setAttribute("locationList", locationList);
				 
				 
				 response.sendRedirect("index.jsp");
			 } else {
			 request.setAttribute("errorMessage", "Invalid username or password");
			 request.getRequestDispatcher("login.jsp").forward(request, response);
			 }
		 } catch (SQLException e) {
		 throw new ServletException("Error during login", e);
		 }
		 

	}

}
