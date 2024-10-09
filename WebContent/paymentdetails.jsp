<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment details Page</title>
<%@include file="/includes/header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
<div class="container mt-5">
    <h2 class="text-center">View Payments</h2>
    <table class="table table-hover table-striped mt-4">
        <thead class="table-info">
            <tr>
                <th>ID</th>
                <th>Cargo ID</th>
                <th>Amount</th>
                <th>Payment Method</th>
                <th>Payment Status</th>
                <th>Timestamp</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="payment" items="${paymentList}">
                <tr>
                    <td>${payment.paymentId}</td>
                    <td>${payment.cargoId}</td>
                    <td>${payment.amount}</td>
                    <td>${payment.paymentMethod}</td>
                    <td>
                        <span class="badge badge-${payment.paymentStatus == 'Completed' ? 'success' : payment.paymentStatus == 'Pending' ? 'warning' : 'danger'}">
                            ${payment.paymentStatus}
                        </span>
                    </td>
                    <td>${payment.paymentDate}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<%@include file="/includes/footer.jsp"%>

</body>
</html>