package com.crimsonlogic.cms.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crimsonlogic.cms.dao.BookingDao;
import com.crimsonlogic.cms.dao.CargoDao;
import com.crimsonlogic.cms.exception.CargoException;
import com.crimsonlogic.cms.util.DatabaseUtil;

import sun.util.resources.cldr.fo.CalendarData_fo_FO;

/**
 * Servlet implementation class ApproveRejectServlet
 */
@WebServlet("/ApproveServlet")
public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApproveServlet() {
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
			 cargoDAO.updateCargo(cargoId,"Approved");
			 request.setAttribute("cargoId", cargoId);
	    	 request.getRequestDispatcher("ViewCargoServlet").forward(request, response);
	    	 } catch (SQLException e) {
	    		 try {
	 				throw new CargoException("Error in approval");
	 			} catch (CargoException e1) {
	 				// TODO Auto-generated catch block
	 				e1.printStackTrace();
	 			}
	    	}
	}


}
