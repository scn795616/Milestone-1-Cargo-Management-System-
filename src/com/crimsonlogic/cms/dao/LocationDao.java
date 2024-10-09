package com.crimsonlogic.cms.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.crimsonlogic.cms.model.Location;

public class LocationDao {
	private Connection connection;

    public LocationDao(Connection connection) {
        this.connection = connection;
    }
    
    public List<Location> getAllLocation() throws SQLException {
        List<Location> locationList = new ArrayList<>();
        String sql = "SELECT * FROM location";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Location location=new Location();
                location.setLocation_id(rs.getInt("location_id"));
                location.setLocation_name(rs.getString("location_name"));
                location.setShip_name(rs.getString("ship_name"));
                locationList.add(location);
            }
        }
        return locationList;
    }

}
