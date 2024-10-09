package com.crimsonlogic.cms.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crimsonlogic.cms.dao.BookingDao;
import com.crimsonlogic.cms.dao.InventoryDao;
import com.crimsonlogic.cms.dao.ShipDao;
import com.crimsonlogic.cms.exception.CargoException;
import com.crimsonlogic.cms.model.Booking;
import com.crimsonlogic.cms.model.Inventory;
import com.crimsonlogic.cms.model.Ship;
import com.crimsonlogic.cms.model.User;
import com.crimsonlogic.cms.util.DatabaseUtil;

/**
 * Servlet implementation class AddShipDetails
 */
@WebServlet("/AddShipDetails")
public class AddShipDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddShipDetails() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String cargoId=request.getParameter("cargoId");
		String type=request.getParameter("type");
		request.setAttribute("type", type);
			try (Connection connection = DatabaseUtil.getConnection()) {
				String sql="select u.user_id, u.user_fname,u.user_number,c.cargo_id,c.description,c.weight,c.origin,c.destination from Users u join Cargo c on c.user_id=u.user_id where c.cargo_id=?;";
		    	 PreparedStatement stmt=connection.prepareStatement(sql);
		    	 stmt.setString(1, cargoId);
		    	 try (ResultSet rs = stmt.executeQuery()) {
		                if (rs.next()) {
		                	request.getSession().setAttribute("userid", rs.getInt("user_id"));
		                    request.setAttribute("cargoId", cargoId);
		                    request.setAttribute("userName", rs.getString("user_fname"));
		                    request.setAttribute("userNumber", rs.getString("user_number"));
		                    request.setAttribute("origin", rs.getString("origin"));
		                    request.setAttribute("destination", rs.getString("destination"));
		                    request.setAttribute("item", rs.getString("description"));
		                    request.setAttribute("weight", rs.getDouble("weight"));
		                }
		            }
//		    	 request.getRequestDispatcher("cancelBooking.jsp").forward(request, response);
		    	 } catch (SQLException e) {
		    	 throw new ServletException("Error retrieving booking list", e);
		    }
	     request.getRequestDispatcher("addShipDetails.jsp").forward(request, response);

		}
    

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		User user=(User)request.getSession().getAttribute("user");
		String cargoId=request.getParameter("cargoId");
		Ship ship=new Ship();
		ship.setCargoId(cargoId);
		ship.setUserName(request.getParameter("userName"));
		ship.setUserNumber(request.getParameter("userNumber"));
		ship.setShipName(request.getParameter("shipName"));
		ship.setShipNumber(request.getParameter("shipNumber"));
		ship.setOrigin(request.getParameter("origin"));
		ship.setDestination(request.getParameter("destination"));
		ship.setItem(request.getParameter("item"));
		ship.setWeight(Double.parseDouble(request.getParameter("weight")));
		String type=request.getParameter("type");
		
		Inventory inventory=new Inventory();
		inventory.setUserId(Integer.parseInt(request.getSession().getAttribute("userid").toString()));
		inventory.setCargoId(cargoId);
		inventory.setUserId(user.getId());
		inventory.setQuantity(Double.parseDouble(request.getParameter("weight")));
		inventory.setWarehouseLocation(request.getParameter("origin"));
		
		try (Connection connection = DatabaseUtil.getConnection()) {
	    	 ShipDao shipDAO = new ShipDao(connection);
	    	 if(type.equals("Add")) {
	    		 shipDAO.addShip(ship);
//				 request.setAttribute("cargoId", cargoId);
				 
				 
				 InventoryDao inventoryDao=new InventoryDao(connection);
				 inventoryDao.addInventory(inventory);
	    	 }
	    	 else {
	    		 shipDAO.update(ship);
	    	 }
			
			 response.sendRedirect("ViewShipDetails");
		} catch (SQLException e) {
	    	 try {
				throw new CargoException("Error retrieving ship list");
			} catch (CargoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	    }
	}

}
