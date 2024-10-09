package com.crimsonlogic.cms.controller;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crimsonlogic.cms.dao.InventoryDao;
import com.crimsonlogic.cms.model.Inventory;
import com.crimsonlogic.cms.model.User;
import com.crimsonlogic.cms.util.DatabaseUtil;

/**
 * Servlet implementation class InventoryServlet
 */
@WebServlet("/InventoryServlet")
public class InventoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public InventoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	 try (Connection connection = DatabaseUtil.getConnection()) {
    	 InventoryDao inventoryDAO = new InventoryDao(connection);
    	 String cargoID=request.getParameter("cargoId");
    	 String type=request.getParameter("type");
    	 List<Inventory> inventoryList=new ArrayList<>();
		 User user=(User)request.getSession().getAttribute("user");

    	 if(type.equals("check")) {
        	 inventoryList = inventoryDAO.getInventoryById(cargoID);
    	 }
    	 else if(!type.equals("check")){
    		 if(user.getRole().equals("admin")) {
            	 inventoryList = inventoryDAO.getAllInventory();

    		 }
    		 else {
            	 inventoryList = inventoryDAO.getInventoryByUserId(user.getId());

    		 }

    	 }
    	 
    	 
    	 request.setAttribute("inventoryList", inventoryList);
    	 request.getRequestDispatcher("viewInventory.jsp").forward(request, response);
    	 } catch (SQLException e) {
    	 throw new ServletException("Error retrieving inventory list", e);
    	 }
    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cargoId = (request.getParameter("cargoId"));
		 String warehouseLocation = request.getParameter("warehouse");
		 Inventory inventory = new Inventory();
		 inventory.setCargoId(cargoId);
		 inventory.setWarehouseLocation(warehouseLocation);
		
		 try (Connection connection = DatabaseUtil.getConnection()) {
		 InventoryDao inventoryDAO = new InventoryDao(connection);
		 inventoryDAO.updateInventory(cargoId,warehouseLocation);
		 } catch (SQLException e) {
		 throw new ServletException("Error adding inventory", e);
		 }
		 response.sendRedirect("index.jsp");
	}

}
