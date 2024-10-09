package com.crimsonlogic.cms.dao;

import java.sql.*;
import java.util.*;

import com.crimsonlogic.cms.model.Inventory;

public class InventoryDao {
	private Connection connection;

    public InventoryDao(Connection connection) {
        this.connection = connection;
    }

    public void addInventory(Inventory inventory) throws SQLException {
        String sql = "INSERT INTO Inventory (user_id,cargo_id, warehouse_location, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        	stmt.setInt(1, inventory.getUserId());
            stmt.setString(2, inventory.getCargoId());
            stmt.setString(3, inventory.getWarehouseLocation());
            stmt.setDouble(4, inventory.getQuantity());
            stmt.executeUpdate();
        }
    }

    public List<Inventory> getAllInventory() throws SQLException {
        List<Inventory> inventoryList = new ArrayList<>();
        String sql = "SELECT * FROM Inventory";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Inventory inventory = new Inventory();
                inventory.setUserId(rs.getInt("user_id"));
                inventory.setId(rs.getInt("inventory_id"));
                inventory.setCargoId(rs.getString("cargo_id"));
                inventory.setWarehouseLocation(rs.getString("warehouse_location"));
                inventory.setQuantity(rs.getDouble("quantity"));
                inventory.setLastUpdated(rs.getTimestamp("last_updated"));
                inventoryList.add(inventory);
            }
        }
        return inventoryList;
    }

    public List<Inventory> getInventoryById(String id) throws SQLException {
        List<Inventory> inventoryList = new ArrayList<>();
    	String sql = "SELECT * FROM Inventory WHERE cargo_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                	 Inventory inventory = new Inventory();
                     inventory.setUserId(rs.getInt("user_id"));
                     inventory.setId(rs.getInt("inventory_id"));
                     inventory.setCargoId(rs.getString("cargo_id"));
                     inventory.setWarehouseLocation(rs.getString("warehouse_location"));
                     inventory.setQuantity(rs.getDouble("quantity"));
                     inventory.setLastUpdated(rs.getTimestamp("last_updated"));
                    inventoryList.add(inventory);
                }
            }
        }
        return inventoryList;
    }

    public void updateInventory(String id,String warehouse) throws SQLException {
        String sql = "UPDATE Inventory SET warehouse_location = ? WHERE cargo_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, warehouse);
            stmt.setString(2, id);
            stmt.executeUpdate();
        }
    }

    public void deleteInventory(int id) throws SQLException {
        String sql = "DELETE FROM Inventory WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

	public List<Inventory> getInventoryByUserId(int id) throws SQLException {
		  List<Inventory> inventoryList = new ArrayList<>();
	    	String sql = "SELECT * FROM Inventory WHERE user_id = ?";
	        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
	            stmt.setInt(1, id);
	            try (ResultSet rs = stmt.executeQuery()) {
	                while (rs.next()) {
	                	 Inventory inventory = new Inventory();
	                     inventory.setUserId(rs.getInt("user_id"));
	                     inventory.setId(rs.getInt("inventory_id"));
	                     inventory.setCargoId(rs.getString("cargo_id"));
	                     inventory.setWarehouseLocation(rs.getString("warehouse_location"));
	                     inventory.setQuantity(rs.getDouble("quantity"));
	                     inventory.setLastUpdated(rs.getTimestamp("last_updated"));
	                    inventoryList.add(inventory);
	                }
	            }
	        }
	        return inventoryList;
	}

}
