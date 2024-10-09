<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Booking Cancel Page</title>
<%@include file="/includes/header.jsp"%>

</head>
<body>
	<c:choose>
		<c:when test="${cancel==null}">
			<% response.sendRedirect("index.jsp"); %>
		</c:when>
	</c:choose>
	<div class="container mt-5">
		 <div class="card">
			 <div class="card-body">
				 <h2 class="card-title text-success">Booking Cancelled Successful</h2>
				 <p class="card-text">Thank you for your time. Your booking has been cancelled successfully. &nbsp;
				 	Your payment amount will credited to your account within 24 hr of working day.
				 </p>
				
				 <div class="details">
					 <p><strong>Cargo ID:</strong> ${cargoId}</p>
				 </div>
				 <a href="index.jsp" class="btn btn-primary mt-3">Go to Home</a>
		 	</div>
	 	</div>
	 </div>
	 <%	request.setAttribute("cancel", null);
	%>
	<%@include file="/includes/footer.jsp"%>

</body>
</html>