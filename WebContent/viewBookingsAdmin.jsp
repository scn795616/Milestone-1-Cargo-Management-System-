<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
 if(request.getSession().getAttribute("user")==null){
	 response.sendRedirect("login.jsp");
 }
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Admin view Booking Details Page</title>
<%@include file="/includes/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
<div class="container mt-5">
    <h2 class="text-center">View Bookings</h2>
    <table class="table table-hover table-striped mt-4">
        <thead class="table-info">
            <tr>
                <th>ID</th>
                <th>Cargo ID</th>
                <th>User ID</th>
                <th>Booking Date</th>
                <th>Scheduled Date</th>
                <th>Status</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="booking" items="${bookingList}">
                <tr>
                    <td>${booking.bookingId}</td>
                    <td>${booking.cargoId}</td>
                    <td>${booking.userId}</td>
                    <td>${booking.bookingDate}</td>
                    <td>${booking.scheduledDate}</td>
                    <td>
                        <span class="badge badge-${booking.status == 'Approved' ? 'success' : booking.status == 'Rejected' ? 'danger' : 'warning'}">
                            ${booking.status}
                        </span>
                    </td>
                    <td>
                        <a href="AddShipDetails?cargoId=${booking.cargoId}&type=<%="add" %>" class="btn btn-sm btn-primary">
                            Add
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="/includes/footer.jsp"%>

</body>
</html>