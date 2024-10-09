package com.crimsonlogic.cms.controller;

import java.io.IOException;
import java.sql.*;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crimsonlogic.cms.dao.ReportDao;
import com.crimsonlogic.cms.model.Report;
import com.crimsonlogic.cms.util.DatabaseUtil;

/**
 * Servlet implementation class ReportServlet
 */
@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ReportServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String reportType = request.getParameter("reportType");
		 int generatedBy = Integer.parseInt(request.getParameter("generatedBy"));
		 String reportData = request.getParameter("reportData");
		 Timestamp generatedAt = new Timestamp(System.currentTimeMillis());
		 Report report = new Report();
		 report.setReportType(reportType);
		 report.setGeneratedBy(generatedBy);
		 report.setGeneratedAt(generatedAt);
		 report.setReportData(reportData);
		 try (Connection connection = DatabaseUtil.getConnection()) {
		 ReportDao reportDAO = new ReportDao(connection);
		 reportDAO.addReport(report);
		 } catch (SQLException e) {
		 throw new ServletException("Error generating report", e);
		 }
		 response.sendRedirect("viewReports");

	}

}
