package com.crimsonlogic.cms.dao;

import java.sql.*;
import java.util.*;

import com.crimsonlogic.cms.model.Cargo;

public class CargoDao {
	private Connection connection;

    public CargoDao(Connection connection) {
        this.connection = connection;
    }

    public void addCargo(Cargo cargo) throws SQLException {
        String sql = "INSERT INTO Cargo (user_id,description, weight, origin, destination) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
        	stmt.setInt(1, cargo.getUesr_id());
            stmt.setString(2, cargo.getDescription());
            stmt.setDouble(3, cargo.getWeight());
            stmt.setString(4, cargo.getOrigin());
            stmt.setString(5, cargo.getDestination());
            stmt.executeUpdate();
        }
    }

    public List<Cargo> getAllCargo() throws SQLException {
        List<Cargo> cargoList = new ArrayList<>();
        String sql = "SELECT * FROM Cargo";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Cargo cargo = new Cargo();
                cargo.setId(rs.getString("cargo_id"));
                cargo.setDescription(rs.getString("description"));
                cargo.setWeight(rs.getDouble("weight"));
                cargo.setOrigin(rs.getString("origin"));
                cargo.setDestination(rs.getString("destination"));
                cargo.setStatus(rs.getString("status"));
                cargoList.add(cargo);
            }
        }
        return cargoList;
    }

    public List<Cargo> getCargoById(int id) throws SQLException {
        List<Cargo> cargoList = new ArrayList<>();
        String sql = "SELECT * FROM Cargo WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Cargo cargo = new Cargo();
                    cargo.setId(rs.getString("cargo_id"));
                    cargo.setDescription(rs.getString("description"));
                    cargo.setWeight(rs.getDouble("weight"));
                    cargo.setOrigin(rs.getString("origin"));
                    cargo.setDestination(rs.getString("destination"));
                    cargo.setStatus(rs.getString("status"));
                    cargoList.add(cargo);
                }
            }
        }
        return cargoList;
    }

    public void updateCargo(String cargoId,String status) throws SQLException {
        String sql = "UPDATE Cargo SET status=? where cargo_id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, status);
            stmt.setString(2, cargoId);
            stmt.executeUpdate();
        }
    }

    public void deleteCargo(int id) throws SQLException {
        String sql = "DELETE FROM Cargo WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
