package com.crimsonlogic.cms.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.lang.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crimsonlogic.cms.dao.CargoDao;
import com.crimsonlogic.cms.dao.UserDao;
import com.crimsonlogic.cms.model.User;
import com.crimsonlogic.cms.util.DatabaseUtil;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userFirstName=request.getParameter("firstName");
		String userLastName=request.getParameter("lastName");
		String userEmail=request.getParameter("email");
		String userNumber=request.getParameter("phone");
		String userName=request.getParameter("username");
		String userPassword=request.getParameter("password");
		
		User user=new User(); 
		user.setUserFirstName(userFirstName);
		user.setUserLastName(userLastName);
		user.setUserEmail(userEmail);
		user.setUserNumber(userNumber);
		user.setPassword(userPassword);
		user.setUsername(userName);
		
		try (Connection connection = DatabaseUtil.getConnection()) {
			 UserDao userDAO = new UserDao(connection);
			 
			 if (userDAO.userExists(userEmail)) {
		            request.setAttribute("errorMessage", "User already exists");
		            request.getRequestDispatcher("register.jsp").forward(request, response);
		      } else {
		    	  	 userDAO.addUser(user);
					 response.sendRedirect("login.jsp");		        }
			 
			 } catch (SQLException e) {
			 throw new ServletException("Error adding cargo", e);
			 }
		

	}

}
