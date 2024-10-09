<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>index Page</title>
<%@include file="/includes/header.jsp"%>
<style>
    body {
        background-color: #f8f9fa;
    }
    .hero {
        background: url('images/image1.jpg') no-repeat center center;
        background-size: cover;
        color: white;
        height: 60vh;
        display: flex;
        align-items: center;
        justify-content: center;
        text-align: center;
    }
    .hero h1 {
        font-size: 4rem;
    }
    .features {
        padding: 50px 0;
    }
    .card {
        margin: 20px 0;
        background-color: rgb(210, 180, 180);
        transition: transform 0.3s, box-shadow 0.3s;
    }
    .card:hover {
        transform: translateY(-10px);
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }
    .card-body {
        text-align: center;
        padding: 20px;
    }
    .card-body i {
        font-size: 3rem;
        margin-bottom: 15px;
        color: #007bff;
    }
    .ship-container {
        position: relative;
        width: 100%;
        height: 100px;
        overflow: hidden;
        background:rgba(204, 206, 204, 0.806);
    }
    .ship {
        position: absolute;
        bottom: 0;
        left: -100px;
        width: 100px;
        height: 100px;
        background: url('images/ship.jpg') no-repeat center center;
        background-size: contain;
        animation: moveShip 10s linear infinite;
    }
    @keyframes moveShip {
        0% {
            left: -100px;
        }
        100% {
            left: 100%;
        }
    }
</style>
</head>
<body>
    <%@include file="/includes/navbar.jsp"%>
    <div class="hero">
        <div class="container">
            <h1>Welcome to the Cargo Management System</h1>
            <p>Manage your cargo efficiently and effectively.</p>
            
            <a href="addCargo.jsp" class="btn btn-primary btn-lg">Get Started</a>
            
        </div>
    </div>

    <!-- Features Section -->
 
    <div class="container features">
       <h3 class="text-center"> Our Features</h4>
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <i class="fas fa-box"></i>
                        <h3 class="card-title">Add Cargo</h3>
                        <p class="card-text">Add new cargo with details like description, weight, origin, and destination.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <i class="fas fa-eye"></i>
                        <h3 class="card-title">View Cargo</h3>
                        <p class="card-text">View all cargo entries and their current status.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <i class="fas fa-truck"></i>
                        <h3 class="card-title">Track Cargo</h3>
                        <p class="card-text">Track the status of your cargo using a tracking ID.</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <i class="fas fa-chart-line"></i>
                        <h3 class="card-title">Payment</h3>
                        <p class="card-text">Make your payments very securely</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <i class="fas fa-user-shield"></i>
                        <h3 class="card-title">Secure</h3>
                        <p class="card-text">Ensure the security of your cargo data with robust authentication.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="card">
                    <div class="card-body">
                        <i class="fas fa-cogs"></i>
                        <h3 class="card-title">Manage</h3>
                        <p class="card-text">Efficiently manage all aspects of your cargo operations.</p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Animated Ship -->
    <div class="ship-container">
        <div class="ship"></div>
    </div>

    <!-- Footer -->
    <%@include file="/includes/footer.jsp"%>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	    <script>
        window.addEventListener('scroll', function() {
            if ((window.innerHeight + window.scrollY) >= document.body.offsetHeight) {
                window.location.href = 'about.jsp';
            }
        });
    </script>
	
</body>
</html>
</body>
</html>
</body>
</html>


</body>
</html>