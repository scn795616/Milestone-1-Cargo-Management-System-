<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%
	String status=request.getParameter("status");
	if(status.equals("Approval Pending") || status.equals("Rejected")){
		request.setAttribute("errorMessage", "This cargo might have Rejected or not approved for the booking");
        request.getRequestDispatcher("ViewCargoServlet").forward(request, response);
	}
	if(request.getSession().getAttribute("book")==null){
		response.sendRedirect("index.jsp");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Payment page</title>
<%@include file="/includes/header.jsp"%>
	<style>
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
<%
	request.getSession().setAttribute("book",null);
	String cargoId = request.getParameter("cargoId");
	String amount = request.getParameter("amount");
%>
	<div class="container mt-5 w-50">
    <div class="card">
        <div class="card-header text-center bg-info text-white">
            <h2>Make a Payment</h2>
        </div>
        <div class="card-body">
            <form action="PaymentServlet" method="post" class="mt-4">
                <div class="form-group">
                    <label for="cargoId">Cargo ID:</label>
                    <input type="text" id="cargoId" name="cargoId" class="form-control" value="<%=cargoId %>" readonly>
                </div>
                <div class="form-group">
                    <label for="amount">Amount:</label>
                    <input type="text" id="amount" name="amount" class="form-control" value="<%=amount %>" readonly>
                </div>
                <div class="form-group">
                    <label for="paymentMethod">Payment Method:<span> *</span></label>
                    <select id="paymentMethod" name="paymentMethod" class="form-control">
                        <option value="Credit Card">Credit Card</option>
                        <option value="Debit Card">Debit Card</option>
                        <option value="Net Banking">Net Banking</option>
                    </select>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Pay</button>
                
                </div>
            </form>
        </div>
    </div>
</div>

</body>
</html>