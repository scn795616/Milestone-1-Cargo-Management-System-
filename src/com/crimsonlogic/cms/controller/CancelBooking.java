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

import com.crimsonlogic.cms.dao.BookingDao;
import com.crimsonlogic.cms.exception.CargoException;
import com.crimsonlogic.cms.model.Booking;
import com.crimsonlogic.cms.model.User;
import com.crimsonlogic.cms.util.DatabaseUtil;

/**
 * Servlet implementation class CancelBooking
 */
@WebServlet("/CancelBooking")
public class CancelBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CancelBooking() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	// TODO Auto-generated method stub
    	String cargoId=request.getParameter("cargoId");
		try (Connection connection = DatabaseUtil.getConnection()) {
	    	 BookingDao bookingDAO = new BookingDao(connection);
			 bookingDAO.deleteBooking(cargoId);
			 request.setAttribute("cargoId", cargoId);
	    	 request.getRequestDispatcher("cancelBooking.jsp").forward(request, response);
	    	 } catch (SQLException e) {
	    		 try {
	 				throw new CargoException("Error in cancelling the booked cargo");
	 			} catch (CargoException e1) {
	 				// TODO Auto-generated catch block
	 				e1.printStackTrace();
	 			}	    	 }
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
