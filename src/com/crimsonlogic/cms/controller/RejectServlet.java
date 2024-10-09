package com.crimsonlogic.cms.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crimsonlogic.cms.dao.CargoDao;
import com.crimsonlogic.cms.util.DatabaseUtil;

/**
 * Servlet implementation class RejectServlet
 */
@WebServlet("/RejectServlet")
public class RejectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RejectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cargoId=request.getParameter("cargoId");
		try (Connection connection = DatabaseUtil.getConnection()) {
	    	 CargoDao cargoDAO = new CargoDao(connection);
			 cargoDAO.updateCargo(cargoId,"Rejected");
	    	 request.getRequestDispatcher("ViewCargoServlet").forward(request, response);
	    	 } catch (SQLException e) {
	    	 throw new ServletException("Error retrieving booking list", e);
	    	 }
	}


}
