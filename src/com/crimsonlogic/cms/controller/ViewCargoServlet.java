package com.crimsonlogic.cms.controller;

import java.io.IOException;
import java.sql.*;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crimsonlogic.cms.dao.CargoDao;
import com.crimsonlogic.cms.model.Cargo;
import com.crimsonlogic.cms.model.User;
import com.crimsonlogic.cms.util.DatabaseUtil;

/**
 * Servlet implementation class ViewCargoServlet
 */
@WebServlet("/ViewCargoServlet")
public class ViewCargoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ViewCargoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (Connection connection = DatabaseUtil.getConnection()) {
			 CargoDao cargoDAO = new CargoDao(connection);
			 List<Cargo> cargoList;
			 User user=(User)request.getSession().getAttribute("user");
			 if(user.getRole().equals("admin")) {
				 cargoList = cargoDAO.getAllCargo();
				 request.setAttribute("cargoList", cargoList);
				 request.getRequestDispatcher("viewCargoAdmin.jsp").forward(request, response);
			 }
			 else {
				 cargoList = cargoDAO.getCargoById(user.getId());
				 request.setAttribute("cargoList", cargoList);
				 request.getRequestDispatcher("viewCargo.jsp").forward(request, response);
			 }
			 
			 
			 } catch (SQLException e) {
			 throw new ServletException("Error retrieving cargo list", e);
			 }

	}

	

}
