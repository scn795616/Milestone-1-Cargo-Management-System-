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
<title>Ship details Page</title>
<%@include file="/includes/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
<div class="container mt-5">
    <h2 class="text-center">View Ship Details</h2>
    <table class="table table-hover table-striped mt-4">
        <thead class="table-info">
            <tr>
                <th>Cargo ID</th>
                <th>User Name</th>
                <th>User Number</th>
                <th>Ship Name</th>
                <th>Ship Number</th>
                <th>Origin</th>
                <th>Destination</th>
                <th>Item</th>
                <th>Weight</th>
                <th>Action</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="ship" items="${shipList}">
                <tr>
                    <td><a href="InventoryServlet?cargoId=${ship.cargoId}&type=<%="check"%>">${ship.cargoId}</a></td>
                    <td>${ship.userName}</td>
                    <td>${ship.userNumber}</td>
                    <td>${ship.shipName}</td>
                    <td>${ship.shipNumber}</td>
                    <td>${ship.origin}</td>
                    <td>${ship.destination}</td>
                    <td>${ship.item}</td>
                    <td>${ship.weight}</td>
                    <td>
                        <a href="AddShipDetails?cargoId=${ship.cargoId}&type=<%="update"%>" class="btn btn-sm btn-primary">
                            Update
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