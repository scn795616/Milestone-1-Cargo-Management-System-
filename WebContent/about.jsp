<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>About Us</title>
    <%@include file="/includes/header.jsp"%>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f8f9fa;
        }
        .about-section {
            background-color: #5e9198f9; /* Bootstrap info color */
            color: white;
            padding: 60px 0;
        }
        .about-section h1 {
            font-size: 3rem;
            margin-bottom: 20px;
        }
        .about-section p {
            font-size: 1.2rem;
        }
        .card {
            margin-bottom: 20px;
            transition: transform 0.3s;
        }
        .card:hover {
            transform: translateY(-10px);
        }
        .team-section {
            padding: 60px 0;
        }
        .team-section h2 {
            font-size: 2.5rem;
            margin-bottom: 40px;
            text-align: center;
        }
        .team-member {
            text-align: center;
            margin-bottom: 30px;
        }
        .team-member img {
            border-radius: 50%;
            width: 150px;
            height: 150px;
            object-fit: cover;
            margin-bottom: 15px;
        }
        .team-member h5 {
            font-size: 1.2rem;
            margin-bottom: 5px;
        }
        .team-member p {
            font-size: 1rem;
            color: #6c757d;
        }
        .contact-section {
            background-color: #343a40;
            color: white;
            padding: 60px 0;
        }
        .contact-section h2 {
            font-size: 2.5rem;
            margin-bottom: 40px;
            text-align: center;
        }
        .contact-section .form-control {
            background-color: #495057;
            border: none;
            color: white;
        }
        .contact-section .btn-primary {
            background-color: #007bff;
            border: none;
        }
    </style>
</head>
<body>
    <%@include file="/includes/navbar.jsp"%>

    <div class="about-section text-center">
        <div class="container">
            <h1>About Us</h1>
            <p>Welcome to Cargo Management System! Our mission is to revolutionize the logistics and transportation industry by providing a comprehensive, user-friendly platform that streamlines cargo management processes.</p>
        </div>
    </div>

    <div class="container mt-5">
        <div class="row">
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><i class="fas fa-eye"></i> Our Vision</h5>
                        <p class="card-text">At Cargo Management System, we envision a world where logistics and transportation are seamless, efficient, and transparent. We aim to be the leading solution provider in the industry, helping businesses of all sizes manage their cargo operations with ease and precision.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><i class="fas fa-bullseye"></i> Our Mission</h5>
                        <p class="card-text">Our mission is to deliver innovative and reliable cargo management solutions that enhance operational efficiency, reduce costs, and improve customer satisfaction. We are committed to leveraging the latest technologies to provide our clients with the best possible service.</p>
                    </div>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="col-md-12">
                <div class="card">
                    <div class="card-body">
                        <h5 class="card-title"><i class="fas fa-list"></i> What We Offer</h5>
                        <ul class="list-unstyled">
                            <li><i class="fas fa-check-circle"></i> Real-Time Tracking: Monitor your cargo in real-time with our advanced tracking system.</li>
                            <li><i class="fas fa-check-circle"></i> Efficient Scheduling: Optimize your shipment schedules to ensure timely deliveries.</li>
                            <li><i class="fas fa-check-circle"></i> Inventory Management: Keep track of your inventory with our integrated management tools.</li>
                            <li><i class="fas fa-check-circle"></i> Comprehensive Reporting: Generate detailed reports to analyze and improve your logistics operations.</li>
                            <li><i class="fas fa-check-circle"></i> User-Friendly Interface: Enjoy a seamless experience with our intuitive and easy-to-use platform.</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>

        <%@include file="/includes/footer.jsp"%>
    

	<script>
    window.addEventListener('scroll', function() {
        if (window.scrollY === 0) {
            window.location.href = 'index.jsp';
        }
    });
</script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

