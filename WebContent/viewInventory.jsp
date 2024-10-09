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
<title>Inventory Page</title>
<%@include file="/includes/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
	 <div class="container mt-5">
		 <h2 class="text-center">View Inventory</h2>
		 <div class="table-responsive">
		 <table class="table table-bordered table-hover table-striped">
			 <thead class="table-info">
				 <tr>
					 <th>ID</th>
					 <th>Cargo ID</th>
					 <th>Warehouse Location</th>
					 <th>Quantity</th>
					 <th>Last Updated</th>
					 <c:choose>
						<c:when test="${sessionScope.role=='admin'}">
						<th>Action</th>
						</c:when>
					</c:choose>
					 
				 </tr>
			 </thead>
			 <tbody>
				 <c:forEach var="inventory" items="${inventoryList}">
					 <tr>
						 <td>${inventory.id}</td>
						 <td>${inventory.cargoId}</td>
						 <td>${inventory.warehouseLocation}</td>
						 <td>${inventory.quantity}</td>
						 <td>${inventory.lastUpdated}</td>
						 <c:choose>
							<c:when test="${sessionScope.role=='admin'}">
							
							<td><a href="updateInventory.jsp?cargoId=${inventory.cargoId}" class="btn btn-sm btn-primary">Update</a></td>
							</c:when>
						</c:choose>
				 	</tr>
				 </c:forEach>
			 </tbody>
		 </table>
		 </div>
	 </div>

	<%@include file="/includes/footer.jsp"%>

</body>
</html>