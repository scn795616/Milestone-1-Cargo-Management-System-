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
<title>Add Cargo Page</title>
<%@include file="/includes/header.jsp"%>
     <style>
        .scrollable-div {
            margin-top: -20px;
            overflow-y: auto; /* Makes the div vertically scrollable */
            padding: 10px;
        }
        span {
            color: red;
        }
        .error {
            color: red;
        }
        .form-container {
            background: #ffffffd2;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .form-container h2 {
            margin-bottom: 20px;
            font-size: 2rem;
            color: #007bff;
        }
        .form-group label {
            font-weight: bold;
        }
        .form-control {
            border-radius: 5px;
            transition: all 0.3s;
        }
        .form-control:focus {
            border-color: #007bff;
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
            transition: background-color 0.3s, border-color 0.3s;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        .btn-primary:focus {
            box-shadow: 0 0 5px rgba(0, 123, 255, 0.5);
        }
        
        .error {
            color: red;
            font-size: 0.875rem;
            margin-top: 5px;
        }
        
        .card {
            border-radius: 15px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        .card-header {
            background-color: #17a2b8;
            color: white;
            border-top-left-radius: 15px;
            border-top-right-radius: 15px;
        }
      
    </style>
</head>
<body>
     <%@include file="/includes/navbar.jsp"%>
    <div class="container mt-5 w-50">
    <div class="scrollable-div">
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger mt-3"><i class="fas fa-exclamation-circle"></i> ${errorMessage}</div>
        </c:if>
        <div class="form-container card">
            <div class="card-header text-center">
                <h2 style="color:white;"><i class="fas fa-box"></i> Add New Cargo</h2>
            </div>
            <div class="card-body">
                <form action="AddCargoServlet" method="post" id="cargoForm">
                    <div class="form-group">
                        <label for="description"><i class="fas fa-info-circle"></i> Description:<span> *</span></label>
                        <input type="text" class="form-control" id="description" name="description" required>
                        <div class="error" id="descriptionError"></div>
                    </div>
                    <div class="form-group">
                        <label for="weight"><i class="fas fa-weight"></i> Weight:<span> *</span></label>
                        <input type="number" class="form-control" id="weight" name="weight" oninput="calculatePrice()" required>
                    </div>
                    <div class="form-group">
                        <label for="price"><i class="fas fa-dollar-sign"></i> Price:<i class="fas fa-question-circle" title="Price is calculated as Weight * 400"></i></label>
                        <input type="text" class="form-control" id="price" name="price" readonly>
                    </div>
                    <div class="form-group">
                        <label for="origin"><i class="fas fa-map-marker-alt"></i> Origin:<span> *</span></label>
                   		<select class="form-control" id="origin" name="origin">
                   			<c:forEach var="location" items="${locationList}">
                   				<option><c:out value="${location.location_name}"/></option>
                   			</c:forEach>
                   		</select> 
                    </div>
                    <div class="form-group">
                        <label for="destination"><i class="fas fa-map-marker-alt"></i> Destination:<span> *</span></label>
                    	<select class="form-control" id="destination" name="destination">
                   			<c:forEach var="location" items="${locationList}">
                   				<option><c:out value="${location.location_name}"/></option>
                   			</c:forEach>
                   		</select> 
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary"><i class="fas fa-plus-circle"></i> Add Cargo</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
    <%@include file="/includes/footer.jsp"%>
    
    <script>

	    function calculatePrice() {
            var weight = document.getElementById("weight").value;
            var price = weight * 400;
            document.getElementById("price").value = price;
        }
	    document.getElementById('description').addEventListener('blur', validateDescription);

        function validateDescription() {
            const description = document.getElementById('description').value;
            if (/\d/.test(description)) {
                document.getElementById('descriptionError').innerText = 'Description should not contain digits.';
            } else if (description.length <4) {
                document.getElementById('descriptionError').innerText = 'Invalid description Name.';
            } else {
                document.getElementById('descriptionError').innerText = '';
            }
        }
        
       
        document.getElementById('cargoForm').addEventListener('submit', function(event) {
            event.preventDefault();
            validateDescription();

            if (document.querySelectorAll('.error:empty').length === 1) {
            	this.submit();
            }
        });
        
       
        
   

    </script>
    
    <!-- Bootstrap JS, Popper.js, and jQuery -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	
</body>
</html>