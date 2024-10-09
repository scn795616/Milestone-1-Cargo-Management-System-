package com.crimsonlogic.cms.dao;

import java.sql.*;
import java.util.*;

import com.crimsonlogic.cms.model.Booking;

public class BookingDao {
	private Connection connection;

    public BookingDao(Connection connection) {
        this.connection = connection;
    }

    public void addBooking(Booking booking) throws SQLException {
        String sql = "INSERT INTO Bookings (cargo_id, user_id, scheduled_date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, booking.getCargoId());
            stmt.setInt(2, booking.getUserId());
            stmt.setTimestamp(3, booking.getScheduledDate());
            stmt.executeUpdate();
        }
    }

    public List<Booking> getAllBookings() throws SQLException {
        List<Booking> bookingList = new ArrayList<>();
        String sql = "SELECT * FROM Bookings";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Booking booking = new Booking();
                booking.setBookingId(rs.getInt("booking_id"));
                booking.setCargoId(rs.getString("cargo_id"));
                booking.setUserId(rs.getInt("user_id"));
                booking.setBookingDate(rs.getTimestamp("booking_date"));
                booking.setScheduledDate(rs.getTimestamp("scheduled_date"));
                booking.setStatus(rs.getString("status"));
                bookingList.add(booking);
            }
        }
        return bookingList;
    }

    public List<Booking> getBookingById(int id) throws SQLException {
        List<Booking> bookingList = new ArrayList<>();
        String sql = "SELECT * FROM Bookings WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Booking booking = new Booking();
                    booking.setBookingId(rs.getInt("booking_id"));
                    booking.setCargoId(rs.getString("cargo_id"));
                    booking.setUserId(rs.getInt("user_id"));
                    booking.setBookingDate(rs.getTimestamp("booking_date"));
                    booking.setScheduledDate(rs.getTimestamp("scheduled_date"));
                    booking.setStatus(rs.getString("status"));
                    bookingList.add(booking);
                }
            }
        }
        return bookingList;
    }

    public void updateBooking(Booking booking) throws SQLException {
        String sql = "UPDATE Bookings SET cargo_id = ?, user_id = ?, booking_date = ?, scheduled_date = ?, status = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, booking.getCargoId());
            stmt.setInt(2, booking.getUserId());
            stmt.setTimestamp(3, booking.getBookingDate());
            stmt.setTimestamp(4, booking.getScheduledDate());
            stmt.setString(5, booking.getStatus());
            stmt.setInt(6, booking.getBookingId());
            stmt.executeUpdate();
        }
    }

    public void deleteBooking(String id) throws SQLException {
        String sql = "DELETE FROM Bookings WHERE cargo_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        }
    }

}
