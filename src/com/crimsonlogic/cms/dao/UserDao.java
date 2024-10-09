package com.crimsonlogic.cms.dao;

import java.sql.*;

import com.crimsonlogic.cms.model.User;

public class UserDao {
	
	private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO Users (username, password,user_fname,user_lname,user_email,user_number) VALUES (?, ?, ?,?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getUserFirstName());
            stmt.setString(4, user.getUserLastName());
            stmt.setString(5, user.getUserEmail());
            stmt.setString(6, user.getUserNumber());
            stmt.executeUpdate();
        }
    }

    public User getUserById(int id) throws SQLException {
        String sql = "SELECT * FROM Users WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setUserFirstName(rs.getString("user_fname"));
                    user.setUserLastName(rs.getString("user_lname"));
                    user.setUserEmail(rs.getString("user_email"));
                    user.setUserNumber(rs.getString("user_number"));
                    return user;
                }
            }
        }
        return null;
    }

    public User getUserByUsername(String username) throws SQLException {
        String sql = "SELECT * FROM Users WHERE username = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, username);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    User user = new User();
                    user.setId(rs.getInt("user_id"));
                    user.setUsername(rs.getString("username"));
                    user.setPassword(rs.getString("password"));
                    user.setRole(rs.getString("role"));
                    return user;
                }
            }
        }
        return null;
    }

    public void updateUser(User user) throws SQLException {
        String sql = "UPDATE Users SET username = ?, user_fname = ?, user_lname=?,user_email=?,user_number=?  WHERE user_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getUserFirstName());
            stmt.setString(3, user.getUserLastName());
            stmt.setString(4, user.getUserEmail());
            stmt.setString(5, user.getUserNumber());
            stmt.setInt(6, user.getId());

           stmt.executeUpdate();
        }
    }

    public void deleteUser(int id) throws SQLException {
        String sql = "DELETE FROM Users WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
    
    public boolean userExists(String email) {
        boolean exists = false;
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(*) FROM users WHERE user_email = ?")) {
            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                exists = rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return exists;
    }

}
