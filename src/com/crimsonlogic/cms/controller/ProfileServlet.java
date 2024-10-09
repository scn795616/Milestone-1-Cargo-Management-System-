package com.crimsonlogic.cms.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crimsonlogic.cms.dao.UserDao;
import com.crimsonlogic.cms.model.User;
import com.crimsonlogic.cms.util.DatabaseUtil;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/ProfileServlet")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user=(User)request.getSession().getAttribute("user");
		
		try (Connection connection = DatabaseUtil.getConnection()) {
			UserDao userDao=new UserDao(connection);
			user=userDao.getUserById(user.getId());
			request.setAttribute("userFname", user.getUserFirstName());
			request.setAttribute("userLname", user.getUserLastName());
			request.setAttribute("userName", user.getUsername());
			request.setAttribute("userEmail", user.getUserEmail());
			request.setAttribute("userNumber", user.getUserNumber());
//	    	 request.getRequestDispatcher("cancelBooking.jsp").forward(request, response);
	    	 } catch (SQLException e) {
	    	 throw new ServletException("Error retrieving booking list", e);
	    }
     request.getRequestDispatcher("profile.jsp").forward(request, response);
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user=(User)request.getSession().getAttribute("user");
		
		try (Connection connection = DatabaseUtil.getConnection()) {
			UserDao userDao=new UserDao(connection);
			user.setId(user.getId());
			user.setUserFirstName(request.getParameter("firstName"));
			user.setUserLastName(request.getParameter("lastName"));
			user.setUsername(request.getParameter("username"));
			user.setUserNumber(request.getParameter("phone"));
			user.setUserEmail(request.getParameter("email"));
			
			userDao.updateUser(user);
//	    	 request.getRequestDispatcher("cancelBooking.jsp").forward(request, response);
	    	 } catch (SQLException e) {
	    	 throw new ServletException("Error retrieving booking list", e);
	    }
		response.sendRedirect("index.jsp");
	}

}
