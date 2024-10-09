package com.crimsonlogic.cms.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crimsonlogic.cms.dao.BookingDao;
import com.crimsonlogic.cms.dao.PaymentDao;
import com.crimsonlogic.cms.model.Booking;
import com.crimsonlogic.cms.model.Payment;
import com.crimsonlogic.cms.model.User;
import com.crimsonlogic.cms.util.DatabaseUtil;
import com.crimsonlogic.cms.util.Mailer;

/**
 * Servlet implementation class PaymentServlet
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
    public PaymentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try (Connection connection = DatabaseUtil.getConnection()) {
	    	 PaymentDao paymentDAO = new PaymentDao();
	    	 List<Payment> paymentList = paymentDAO.getAllPayments();
	    	 request.setAttribute("paymentList", paymentList);
	    	 request.getRequestDispatcher("paymentdetails.jsp").forward(request, response);
	    	 } catch (SQLException e) {
	    	 throw new ServletException("Error retrieving payment list", e);
	    }
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String cargoId = request.getParameter("cargoId");
		 double amount = Double.parseDouble(request.getParameter("amount"));
		 String paymentMethod = request.getParameter("paymentMethod");
		 
		 User user= (User)request.getSession().getAttribute("user");
		 
		 Payment payment = new Payment();
		 payment.setCargoId(cargoId);
		 payment.setAmount(amount);
		 payment.setPaymentMethod(paymentMethod);
		 payment.setPaymentStatus("Completed");
		 payment.setPaymentDate(new Timestamp(System.currentTimeMillis()));
		 
		 Booking booking=new Booking();
		 booking.setUserId(user.getId());
		 booking.setCargoId(cargoId);
		 booking.setScheduledDate(new Timestamp(System.currentTimeMillis()));
		 
		 try(Connection connection = DatabaseUtil.getConnection()) {
			 PaymentDao paymentDao=new PaymentDao();
			 paymentDao.addPayment(payment);
			 request.setAttribute("payment", payment);
			 
			 BookingDao bookingDao=new BookingDao(connection);
			 bookingDao.addBooking(booking);
			 
//		 	String to = "krishnavamsireddy02@gmail.com"; 
//	        String subject = "Payment Confirmation";
//	        String msg = "Your payment was successful.\nCargo ID: " + cargoId + "\nAmount: " + amount + "\nPayment Method: " + paymentMethod;
//
//	        Mailer.send(to, subject, msg);
			 request.getRequestDispatcher("paymentSuccess.jsp").forward(request, response);
		 } catch (SQLException e) {
		 throw new ServletException(e);
		 }
	}

	

}
