<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%-- <%
	if(request.getSession().getAttribute("Add")==null){
		response.sendRedirect("index.jsp");
	}
%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Ship details page</title>
<%@include file="/includes/header.jsp"%>
	<style>
		body {
	        background: url('images/shipImage.jpg') no-repeat center center;
	        background-size: cover;
	        height: 100%;
    	}
    	span{
    		color:red;
    	}
        .error {
            color: red;
        }
        
        .card {
        border-radius: 15px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }
    .card-header {
        background-color: #17a2b8;
    	color:white;
        border-top-left-radius: 15px;
        border-top-right-radius: 15px;
    }
    .btn-primary {
        background-color: #007bff;
        border: none;
    }
    .btn-primary:hover {
        background-color: #0056b3;
    }
    .form-control:focus {
        border-color: #007bff;
        box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
    }
    </style>
	
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>

<%
	request.getSession().setAttribute("Add",null);
/* 	String cargoId = request.getParameter("cargoId"); */
	String cargoId=(String) request.getAttribute("cargoId");
	String userName=(String)request.getAttribute("userName");
	String userNumber=(String)request.getAttribute("userNumber");
	String origin=(String)request.getAttribute("origin");
	String destination=(String)request.getAttribute("destination");
	String item=(String)request.getAttribute("item");
	Double weight=(Double)request.getAttribute("weight");
%>
	<div class="container mt-5 w-50">
    <div class="card">
        <div class="card-header text-center">
            <c:choose>
                <c:when test="${type=='add'}">
                    <h2><i class="fas fa-ship"></i> Add Ship Details</h2>
                </c:when>
                <c:when test="${type=='update'}">
                    <h2><i class="fas fa-edit"></i> Update Ship Details</h2>
                </c:when>
            </c:choose>
        </div>
        <div class="card-body">
            <c:choose>
                <c:when test="${type=='add'}">
                    <form action="AddShipDetails?type=<%="Add"%>" method="post" class="mt-4">
                </c:when>
                <c:when test="${type=='update'}">
                    <form action="AddShipDetails?type=<%="Update"%>" method="post" class="mt-4">
                </c:when>
            </c:choose>
                <div class="form-group">
                    <label for="cargoId"><i class="fas fa-box"></i> Cargo ID:</label>
                    <input type="text" id="cargoId" name="cargoId" class="form-control" value="<%=cargoId %>" readonly>
                </div>
                <div class="form-group">
                    <label for="userName"><i class="fas fa-user"></i> User Name:</label>
                    <input type="text" id="userName" name="userName" class="form-control" value="<%=userName %> " readonly>
                </div>
                <div class="form-group">
                    <label for="userNumber"><i class="fas fa-phone"></i> User Number:</label>
                    <input type="text" id="userNumber" name="userNumber" class="form-control" value="<%=userNumber %> " readonly>
                </div>
                <div class="form-group">
                    <label for="shipName"><i class="fas fa-ship"></i> Ship Name:<span> *</span></label>
                    <select class="form-control" id="shipName" name="shipName">
                   			<c:forEach var="location" items="${locationList}">
                   				<option><c:out value="${location.ship_name}"/></option>
                   			</c:forEach>
                   		</select> 
                    <div class="error" id="shipName"></div>
                </div>
                <div class="form-group">
                    <label for="shipNumber"><i class="fas fa-hashtag"></i> Ship Number:<span> *</span></label>
                    <input type="text" id="shipNumber" name="shipNumber" class="form-control" value=" " >
                    <div class="error" id="shipNumber"></div>
                </div>
                <div class="form-group">
                    <label for="origin"><i class="fas fa-map-marker-alt"></i> Origin:</label>
                    <input type="text" id="origin" name="origin" class="form-control" value="<%=origin %> " readonly>
                </div>
                <div class="form-group">
                    <label for="destination"><i class="fas fa-map-marker-alt"></i> Destination:</label>
                    <input type="text" id="destination" name="destination" class="form-control" value="<%=destination %> " readonly>
                </div>
                <div class="form-group">
                    <label for="item"><i class="fas fa-box-open"></i> Item Type:</label>
                    <input type="text" id="item" name="item" class="form-control" value="<%=item %> " readonly>
                </div>
                <div class="form-group">
                    <label for="weight"><i class="fas fa-weight"></i> Weight:</label>
                    <input type="text" id="weight" name="weight" class="form-control" value="<%=weight %> " readonly>
                </div>
                <c:choose>
                    <c:when test="${type=='add'}">
                        <button type="submit" class="btn btn-primary btn-block" name="type"><i class="fas fa-plus-circle"></i> Add</button>
                    </c:when>
                    <c:when test="${type=='update'}">
                        <button type="submit" class="btn btn-primary btn-block" name="type"><i class="fas fa-save"></i> Save</button>
                    </c:when>
                </c:choose>
            </form>
        </div>
    </div>
</div>
</body>
</html>