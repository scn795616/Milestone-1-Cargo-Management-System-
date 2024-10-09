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
<title>Booking Details Page</title>
<%@include file="/includes/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style >


.btn-danger {
    background-color: #dc3545;
    border: none;
    transition: background-color 0.3s;
}

.btn-danger:hover {
    background-color: #c82333;
}

	
</style>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
<div class="container mt-5">
    <h2 class="text-center">View Bookings</h2>
    <div class="table-responsive">
        <table class="table table-hover table-striped mt-4">
            <thead class="table-info">
                <tr>
                    <th>ID</th>
                    <th>Cargo ID</th>
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
                        <td><a href="InventoryServlet?cargoId=${booking.cargoId}&type=<%="check"%>">${booking.cargoId}</a></td>
                        <td>${booking.bookingDate}</td>
                        <td>${booking.scheduledDate}</td>
                        <td>
                            <span class="badge badge-${booking.status == 'Scheduled' ? 'success' : booking.status == 'Pending' ? 'warning' : 'danger'}">
                                ${booking.status}
                            </span>
                        </td>
                        <td>
                            <a href="CancelBooking?cargoId=${booking.cargoId}" class="btn btn-sm btn-danger">
                                <% request.getSession().setAttribute("cancel", "canceled"); %>
                                Cancel Booking
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@include file="/includes/footer.jsp"%>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        const cancelButtons = document.querySelectorAll('.btn-danger');
        cancelButtons.forEach(button => {
            button.addEventListener('click', function(event) {
                if (!confirm('Are you sure you want to cancel this booking?')) {
                    event.preventDefault();
                }
            });
        });
    });
</script>


</body>
</html>