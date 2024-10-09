package com.crimsonlogic.cms.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crimsonlogic.cms.dao.CargoDao;
import com.crimsonlogic.cms.dao.ShipDao;
import com.crimsonlogic.cms.model.Cargo;
import com.crimsonlogic.cms.model.Ship;
import com.crimsonlogic.cms.model.User;
import com.crimsonlogic.cms.util.DatabaseUtil;

/**
 * Servlet implementation class ViewShipDetails
 */
@WebServlet("/ViewShipDetails")
public class ViewShipDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewShipDetails() {
        super();
        // TODO Auto-generated constructor stub
    } 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (Connection connection = DatabaseUtil.getConnection()) {
			 ShipDao shipDAO = new ShipDao(connection);
			 List<Ship> shipList;
			 shipList = shipDAO.getAllShip(); 
			 request.setAttribute("shipList", shipList);
			 request.getRequestDispatcher("viewShipDetails.jsp").forward(request, response);
		
			 
			 } catch (SQLException e) {
			 throw new ServletException("Error retrieving cargo list", e);
			 }
	
	}


}
