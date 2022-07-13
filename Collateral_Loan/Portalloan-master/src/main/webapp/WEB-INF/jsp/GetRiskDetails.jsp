<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<link rel="stylesheet" href="style.css" type="text/css">
<title>ENTER LOAN DETAILS FOR RISK DETAILS</title>
</head>
<body class="bg-light" onload="myFunction()">
	<div id="loading"></div>
	<div class="container">
		<nav class="navbar navbar-dark navbar-expand-sm bg-primary fixed-top">
			<div class="container">
				<a href="#" class="navbar-brand"><h1>RISK DETAILS</h1></a>
			</div>
		</nav>
	</div>
	<div style="padding-top: 90px;"></div>

	<div class="container">
	${msg}
		<div class="form-group ">
			<form method="post" action="postrisk">

				<label><strong>ENTER LOAN ID</strong></label> <input type="number"
					class="form-control" id="riskid" name="riskid" min="1"
					required="required"><br>
				<button type="submit" class="btn btn-success btn-block text-white">Submit</button>

			</form>
		</div>
		<div class="form-row">
			<div class="col">
				<a href="/logout" class="btn btn-danger btn-block" data-toggle="modal"
			data-target="#modal1">Logout</a>
			</div>
			<div class="col">
				<a href="/portalbranch" class="btn btn-primary btn-block">Home
					Page</a>
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