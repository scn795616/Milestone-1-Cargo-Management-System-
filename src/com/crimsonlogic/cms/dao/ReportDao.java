package com.crimsonlogic.cms.dao;

import java.sql.*;
import java.util.*;

import com.crimsonlogic.cms.model.Report;

public class ReportDao {
	private Connection connection;

    public ReportDao(Connection connection) {
        this.connection = connection;
    }

    public void addReport(Report report) throws SQLException {
        String sql = "INSERT INTO Reports (report_type, generated_by, generated_at, report_data) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, report.getReportType());
            stmt.setInt(2, report.getGeneratedBy());
            stmt.setTimestamp(3, report.getGeneratedAt());
            stmt.setString(4, report.getReportData());
            stmt.executeUpdate();
        }
    }

    public List<Report> getAllReports() throws SQLException {
        List<Report> reportList = new ArrayList<>();
        String sql = "SELECT * FROM Reports";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Report report = new Report();
                report.setId(rs.getInt("id"));
                report.setReportType(rs.getString("report_type"));
                report.setGeneratedBy(rs.getInt("generated_by"));
                report.setGeneratedAt(rs.getTimestamp("generated_at"));
                report.setReportData(rs.getString("report_data"));
                reportList.add(report);
            }
        }
        return reportList;
    }

    public Report getReportById(int id) throws SQLException {
        String sql = "SELECT * FROM Reports WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Report report = new Report();
                    report.setId(rs.getInt("id"));
                    report.setReportType(rs.getString("report_type"));
                    report.setGeneratedBy(rs.getInt("generated_by"));
                    report.setGeneratedAt(rs.getTimestamp("generated_at"));
                    report.setReportData(rs.getString("report_data"));
                    return report;
                }
            }
        }
        return null;
    }

    public void updateReport(Report report) throws SQLException {
        String sql = "UPDATE Reports SET report_type = ?, generated_by = ?, generated_at = ?, report_data = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, report.getReportType());
            stmt.setInt(2, report.getGeneratedBy());
            stmt.setTimestamp(3, report.getGeneratedAt());
            stmt.setString(4, report.getReportData());
            stmt.setInt(5, report.getId());
            stmt.executeUpdate();
        }
    }

    public void deleteReport(int id) throws SQLException {
        String sql = "DELETE FROM Reports WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
