package com.crimsonlogic.cms.controller;

import java.io.IOException;
import java.sql.*;
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
 * Servlet implementation class BookingServlet
 */
@WebServlet("/BookingServlet")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public BookingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 try (Connection connection = DatabaseUtil.getConnection()) {
    	 List<Booking> bookingList;
    	 BookingDao bookingDAO = new BookingDao(connection);
    	 
		 User user=(User)request.getSession().getAttribute("user");
		 if(user.getRole().equals("admin")) {
			 bookingList = bookingDAO.getAllBookings();
	    	 request.setAttribute("bookingList", bookingList);
	    	 request.getRequestDispatcher("viewBookingsAdmin.jsp").forward(request, response);

		 }
		 else {
			 bookingList = bookingDAO.getBookingById(user.getId());
	    	 request.setAttribute("bookingList", bookingList);
	    	 request.getRequestDispatcher("viewBookings.jsp").forward(request, response);

		 }
    	 } catch (SQLException e) {
    		 try {
 				throw new CargoException("Error retrieving book list");
 			} catch (CargoException e1) {
 				// TODO Auto-generated catch block
 				e1.printStackTrace();
 			}
    	}
    }


}
