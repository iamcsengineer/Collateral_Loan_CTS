<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="canonical"
	href="https://getbootstrap.com/docs/4.3/examples/starter-template/">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<title>PORTAL BRANCH</title>
<link rel="stylesheet" href="style.css" type="text/css">
</head>
<body class="bg-light" onload="myFunction()">
	<div id="loading"></div>
	<div class="container">
		<nav class="navbar navbar-dark navbar-expand-sm bg-primary fixed-top">
			<div class="container">
				<a href="#" class="navbar-brand"><h2>PORTAL BRANCH</h2></a>
			</div>
		</nav>
	</div>
	<div style="margin-top: 50px;"></div>
	<div class="jumbotron jumbotron-fluid mt-4 text-center">
		<div class="container">
			<h1 class="display-4">Welcome to Loan Portal</h1>
			<p class="lead">
				Save Your Loan, Get Your Loan and Analyse Your Loan Risk,<strong>
					LET'S EXPLORE TOGETHER</strong>
			</p>

			${msg }
			<div class="row" style="text-align: left">
				<div class="col-lg-3 col-md-6 col-sm-12 mt-2">
					<div class="card mt-3">
						<div class="card-body">
							<div class="row">
								<div class="col">
									<h5>GET LOAN DETAILS</h5>
								</div>
								<div class="col text-right">
									<a href="/getloan" class="btn btn-info btn text-white">ENTER</a>
								</div>
							</div>
							<div class="row justify-content-between mt-3">
								<div class="col">
									<small class="text-muted">Here You will receive your
										loan details whatever you have inserted before. Now enter your
										Loan-ID to get your loan details</small>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-12 mt-2">
					<div class="card mt-3">
						<div class="card-body">
							<div class="row">
								<div class="col">
									<h5>INSERT CASH DEPOSIT DETAILS</h5>
								</div>
								<div class="col text-right">
									<a href="/getdeposit" class="btn btn-info btn text-white">ENTER</a>
								</div>
							</div>
							<div class="row justify-content-between mt-3">
								<div class="col">
									<small class="text-muted">Here You Need to Insert Your
										Cash Deposit Details to Add into The Our Loan Database</small>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-12 mt-2">
					<div class="card mt-3">
						<div class="card-body">
							<div class="row">
								<div class="col">
									<h5>INSERT REAL ESTATE DETAILS</h5>
								</div>
								<div class="col text-right">
									<a href="/getrealestate" class="btn btn-info btn text-white">ENTER</a>
								</div>
							</div>
							<div class="row justify-content-between mt-3">
								<div class="col">
									<small class="text-muted">Here You Need to Insert Your
										Real Estate Details to Add into The Our Loan Database</small>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-lg-3 col-md-6 col-sm-12 mt-2">
					<div class="card mt-3">
						<div class="card-body">
							<div class="row">
								<div class="col">
									<h5>GET RISK DETAILS</h5>
								</div>
								<div class="col text-right">
									<a href="/getrisk" class="btn btn-info btn text-white">ENTER</a>
								</div>
							</div>
							<div class="row justify-content-between mt-3">
								<div class="col">
									<small class="text-muted">Here You will receive your
										Loan Risk Details. Whatever you have inserted before, Here
										will give you percentage of Your Loan Risk based on current
										Market Value.</small>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<div class="container ">


		<div style="margin-top: 30px;"></div>
		<a href="/logout" class="btn btn-danger btn-block text-white"
			data-toggle="modal" data-target="#modal1">Logout</a>
		<div style="margin-top: 80px;"></div>
		<div class="modal" id="modal1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">
							<strong>Warning</strong> to ${name }
						</h5>
						<button class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						Are you sure you want to navigate away from this page?<br>
						You will lose any unsaved modifications to this document.<br>
						Press OK to continue, or Cancel to stay on the current page.
					</div>
					<div class="modal-footer">
						<a class="btn btn-danger" href="/logout">OK</a>
						<button class="btn btn-info" data-dismiss="modal">Cancel</button>
					</div>
				</div>
			</div>
		</div>
		<div class="modal" id="modal1">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">
							<strong>Warning</strong> to ${name }
						</h5>
						<button class="close" data-dismiss="modal">&times;</button>
					</div>
					<div class="modal-body">
						Are you sure you want to navigate away from this page?<br>
						You will lose any unsaved modifications to this document.<br>
						Press OK to continue, or Cancel to stay on the current page.
					</div>
					<div class="modal-footer">
						<a class="btn btn-danger" href="/logout">OK</a>
						<button class="btn btn-info" data-dismiss="modal">Cancel</button>
					</div>
				</div>
			</div>
		</div>

	</div>
	<%@include file="footer.jsp" %>
	
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script>
		$('#myAlert').on('closed.bs.alert', function() {
			console.log('Alert closed...');
		})
		var preloader = document.getElementById("loading");
		function myFunction() {
			preloader.style.display = 'none';
		}
	</script>
</body>
</html>