package com.crimsonlogic.cms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.crimsonlogic.cms.model.Cargo;
import com.crimsonlogic.cms.model.Ship;

public class ShipDao {
	private Connection connection;

    public ShipDao(Connection connection) {
        this.connection = connection;
    }
    
    public void addShip(Ship ship) throws SQLException {
        String sql = "INSERT INTO ShipDetails (cargo_id, user_fname, user_number, ship_name, ship_number,origin,destination,item,weight) VALUES (?, ?, ?, ? ,? ,?, ?, ? ,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, ship.getCargoId());
            stmt.setString(2, ship.getUserName());
            stmt.setString(3,ship.getUserNumber());
            stmt.setString(4,ship.getShipName());
            stmt.setString(5,ship.getShipNumber());
            stmt.setString(6,ship.getOrigin());
            stmt.setString(7,ship.getDestination());
            stmt.setString(8,ship.getItem());
            stmt.setDouble(9,ship.getWeight());

            stmt.executeUpdate();
        }
    }
    
    public List<Ship> getAllShip() throws SQLException {
        List<Ship> shipList = new ArrayList<>();
        String sql = "SELECT * FROM ShipDetails";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Ship ship = new Ship();
                ship.setCargoId(rs.getString("cargo_id"));
                ship.setUserName(rs.getString("user_fname"));
                ship.setUserNumber(rs.getString("user_number"));
                ship.setShipName(rs.getString("ship_name"));
                ship.setShipNumber(rs.getString("ship_number"));
                ship.setItem(rs.getString("item"));
                ship.setWeight(rs.getDouble("weight"));
                ship.setOrigin(rs.getString("origin"));
                ship.setDestination(rs.getString("destination"));
                shipList.add(ship);
            }
        }
        return shipList;
    }

	public void update(Ship ship) throws SQLException {
		String sql = "update ShipDetails set ship_name=?,ship_number=? where cargo_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(3, ship.getCargoId());
            stmt.setString(1,ship.getShipName());
            stmt.setString(2,ship.getShipNumber());

            stmt.executeUpdate();
        }
	}

}
