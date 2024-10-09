package com.crimsonlogic.cms.controller;

import java.io.IOException;
import java.sql.*;

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
 * Servlet implementation class AddCargoServlet
 */
@WebServlet("/AddCargoServlet")
public class AddCargoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public AddCargoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String description = request.getParameter("description");
		 double weight = Double.parseDouble(request.getParameter("weight"));
		 String origin = request.getParameter("origin");
		 String destination = request.getParameter("destination");
		 
		 if (isHazardous(description)) {
		        request.setAttribute("errorMessage", "This cargo cannot be added due to security.");
		        request.getRequestDispatcher("addCargo.jsp").forward(request, response);
		        return;
		    }
		 if(origin.equals("Houston")) {
			 	request.setAttribute("errorMessage", "Sorry, service for this location is yet to come");
		        request.getRequestDispatcher("addCargo.jsp").forward(request, response);
		        return;
		 }
		 
		 Cargo cargo = new Cargo();
		 User user=(User)request.getSession().getAttribute("user");
		 cargo.setUesr_id(user.getId());
		 cargo.setDescription(description);
		 cargo.setWeight(weight);
		 cargo.setOrigin(origin);
		 cargo.setDestination(destination);
		 try (Connection connection = DatabaseUtil.getConnection()) {
		 CargoDao cargoDAO = new CargoDao(connection);
		 cargoDAO.addCargo(cargo);
		 response.sendRedirect("ViewCargoServlet");
		 } catch (SQLException e) {
		 throw new ServletException("Error adding cargo", e);
		 }
		 
	}
	
	private boolean isHazardous(String description) {
	    // Simple check for hazardous materials (this can be expanded as needed)
	    String[] hazardousMaterials = {"flammable", "explosive", "radioactive", "corrosive","animal","petrol"};
	    for (String hazardous : hazardousMaterials) {
	        if (description.toLowerCase().contains(hazardous)) {
	            return true;
	        }
	    }
	    return false;
	}

}
