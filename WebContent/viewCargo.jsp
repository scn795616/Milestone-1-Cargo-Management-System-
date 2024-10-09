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
<title>View Cargo Page</title>
<%@include file="/includes/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

</head>
<body>
    <%@include file="/includes/navbar.jsp"%>
<div class="container mt-5">
    <h2 class="text-center">Check Cargo Status</h2>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger mt-3">${errorMessage}</div>
    </c:if>
    <div class="table-responsive">
        <table class="table table-hover table-striped mt-4">
            <thead class="table-info">
                <tr>
                    <th>ID</th>
                    <th>Description</th>
                    <th>Weight</th>
                    <th>Origin</th>
                    <th>Destination</th>
                    <th>Status</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="cargo" items="${cargoList}">
                    <tr>
                        <td>${cargo.id}</td>
                        <td>${cargo.description}</td>
                        <td>${cargo.weight}</td>
                        <td>${cargo.origin}</td>
                        <td>${cargo.destination}</td>
                        <td>
                            <span class="badge badge-${cargo.status == 'Approved' ? 'success' : cargo.status == 'Rejected' ? 'danger' : 'warning'}">
                                ${cargo.status}
                            </span>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${cargo.status=='Approved'}">
                                    <a href="payment.jsp?cargoId=${cargo.id}&amount=${cargo.weight * 400}&status=${cargo.status}" class="btn btn-sm btn-primary">
                                        <% request.getSession().setAttribute("book", "booking"); %>
                                        Pay Now
                                    </a>
                                </c:when>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<%@include file="/includes/footer.jsp"%>


    <!-- Bootstrap JS, Popper.js, and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>