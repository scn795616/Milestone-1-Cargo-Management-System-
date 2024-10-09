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
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.18.3/bootstrap-table.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.18.3/bootstrap-table.min.js"></script>
	 
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>
<div class="container mt-5">
    <h2 class="text-center">Update Cargo</h2>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger mt-3">${errorMessage}</div>
    </c:if>
    <div class="form-group w-50 mx-auto">
        <label for="statusFilter" style="font-size: 20px">Filter by Status:</label>
        <select id="statusFilter" class="form-control">
            <option value="all">All</option>
            <option value="Approval Pending">Approval Pending</option>
            <option value="Approved">Approved</option>
            <option value="Rejected">Rejected</option>
        </select>
    </div>
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
                <tr class="cargo-row" data-status="${cargo.status}">
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
                            <c:when test="${cargo.status=='Approval Pending'}">
                                <a href="ApproveServlet?cargoId=${cargo.id}" class="btn btn-sm btn-primary">Approve</a>
                                <a href="RejectServlet?cargoId=${cargo.id}" class="btn btn-sm btn-danger">Reject</a>
                            </c:when>
                        </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>

<script>
    $(document).ready(function() {
        $('#statusFilter').on('change', function() {
            var selectedStatus = $(this).val();
            if (selectedStatus === 'all') {
                $('.cargo-row').show();
            } else {
                $('.cargo-row').each(function() {
                    var rowStatus = $(this).data('status');
                    if (rowStatus === selectedStatus) {
                        $(this).show();
                    } else {
                        $(this).hide();
                    }
                });
            }
        });
    });
</script>

<%@include file="/includes/footer.jsp"%>
</body>
</html>