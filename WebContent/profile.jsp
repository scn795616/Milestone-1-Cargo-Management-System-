<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import ="com.crimsonlogic.cms.model.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Profile Page</title>
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
        .error {
            color: red;
            font-size: 0.875rem;
            margin-top: 5px;
        }
    </style>
</head>
<body>
	<%@include file="/includes/navbar.jsp"%>

<% 
    User user = (User) request.getAttribute("user");

	String userFname=(String) request.getAttribute("userFname");
	String userLname=(String) request.getAttribute("userLname");
	String userName=(String) request.getAttribute("userName");
	String userEmail=(String) request.getAttribute("userEmail");
	String userNumber=(String) request.getAttribute("userNumber");
%>
<div class="container mt-5 d-flex justify-content-center">
    <div class="card w-50">
        <div class="card-header text-center">
            <h2><i class="fas fa-user"></i> Profile Page</h2>
        </div>
        <div class="card-body">
            <form action="ProfileServlet" method="post" id="registrationForm">
                <div class="form-group">
                    <label for="firstName"><i class="fas fa-user"></i> First Name:<span> *</span></label>
                    <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter the first name" value="<%=userFname%>" required>
                    <div class="error" id="firstNameError"></div>
                </div>
                <div class="form-group">
                    <label for="lastName"><i class="fas fa-user"></i> Last Name:<span> *</span></label>
                    <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter the last name" value="<%=userLname %>" required>
                    <div class="error" id="lastNameError"></div>
                </div>
                <div class="form-group">
                    <label for="email"><i class="fas fa-envelope"></i> Email:<span> *</span></label>
                    <input type="email" class="form-control" id="email" name="email" placeholder="Enter your email" value="<%=userEmail%>" required>
                    <div class="error" id="emailError"></div>
                </div>
                <div class="form-group">
                    <label for="phone"><i class="fas fa-phone"></i> Phone Number:<span> *</span></label>
                    <input type="text" class="form-control" id="phone" name="phone" placeholder="Enter your valid mobile number" value="<%=userNumber%>" required>
                    <div class="error" id="phoneError"></div>
                </div>
                <div class="form-group">
                    <label for="username"><i class="fas fa-user"></i> Username:<span> *</span></label>
                    <input type="text" class="form-control" id="username" name="username" placeholder="Create the user name" value="<%=userName%>" required>
                    <div class="error" id="usernameError"></div>
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary"><i class="fas fa-save"></i> Update</button>
                </div>
            </form>
        </div>
    </div>
</div>


    
    <script>
        document.getElementById('firstName').addEventListener('blur', validateFirstName);
        document.getElementById('lastName').addEventListener('blur', validateLastName);
        document.getElementById('email').addEventListener('blur', validateEmail);
        document.getElementById('phone').addEventListener('blur', validatePhone);
        document.getElementById('username').addEventListener('blur', validateUsername);
    
        function validateFirstName() {
            const firstName = document.getElementById('firstName').value;
            if (/\d/.test(firstName)) {
                document.getElementById('firstNameError').innerText = 'First name should not contain digits.';
            } else if (firstName.length == 1) {
                document.getElementById('firstNameError').innerText = 'Invalid First Name.';
            } else {
                document.getElementById('firstNameError').innerText = '';
            }
        }

        function validateLastName() {
            const lastName = document.getElementById('lastName').value;
            if (/\d/.test(lastName)) {
                document.getElementById('lastNameError').innerText = 'Last name should not contain digits.';
            } else {
                document.getElementById('lastNameError').innerText = '';
            }
        }

        function validateEmail() {
            const email = document.getElementById('email').value;
            const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            if (!emailPattern.test(email)) {
                document.getElementById('emailError').innerText = 'Please enter a valid email address.';
            } else {
                document.getElementById('emailError').innerText = '';
            }
        }

        function validatePhone() {
            const phone = document.getElementById('phone').value;
            if (!/^\d{10}$/.test(phone)) {
                document.getElementById('phoneError').innerText = 'Phone number should be 10 digits.';
            } else {
                document.getElementById('phoneError').innerText = '';
            }
        }

        function validateUsername() {
            const username = document.getElementById('username').value;
            const usernamePattern = /^(?=.*[a-zA-Z])(?=.*\d)[A-Za-z\d]+$/;
            if (!usernamePattern.test(username)) {
                document.getElementById('usernameError').innerText = 'Username should contain both letters and digits.';
            } else {
                document.getElementById('usernameError').innerText = '';
            }
        }

       

        document.getElementById('registrationForm').addEventListener('submit', function(event) {
            event.preventDefault();
            validateFirstName();
            validateLastName();
            validateEmail();
            validatePhone();
            validateUsername();

            if (document.querySelectorAll('.error:empty').length === 5) {
            	this.submit();
            }
        });
    </script>
	
	
	<!-- Bootstrap and jQuery scripts -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>