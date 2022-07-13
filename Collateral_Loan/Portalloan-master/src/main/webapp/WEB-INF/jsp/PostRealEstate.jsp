<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<link rel="canonical"
	href="https://getbootstrap.com/docs/4.3/examples/starter-template/">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
	<link rel="stylesheet" href="style.css" type="text/css">
<meta charset="ISO-8859-1">
<title>REAL ESTATE</title>
</head>
<body class="bg-light" onload="myFunction()">
	<div id="loading"></div>
	<nav class="navbar navbar-dark navbar-expand-lg bg-primary fixed-top">
		<div class="container">
			<a href="#" class="navbar-brand"><h2>WELCOME TO LOAN PORTAL</h2></a>
			<button class="navbar-toggler" data-toggle="collapse"
				data-target="#navbarNav">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a href="/logout"
						class="btn btn-danger btn-block" data-toggle="modal"
			data-target="#modal1">Logout</a></li>&nbsp;
					<li class="nav-item"><a href="/portalbranch"
						class="btn btn-primary btn-block">Home Page</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div style="padding-top: 90px;"></div>
	<div class="container ">
		${ msg}
		<form:form modelAttribute="realestate" action="/postrealestate"
			method="post">
			<div align="center">
				<a href="#" class="btn btn-info btn center mb-2">LOAN</a>
			</div>
			<div class="row mb-3">
				<div class="input-group col">
					<div class="input-group-prepend">
						<button class="input-group-text" href="#">
							<strong>LOAN ID</strong>
						</button>
					</div>
					<form:input path="loanId" id="loanId" placeholder="Enter loan Id"
						cssClass="form-control" cssErrorClass="form-control is-invalid" />
					<form:errors path="loanId" cssClass="invalid-feedback" />
				</div>
				<div class="input-group col">
					<div class="input-group-prepend">
						<button class="input-group-text" href="#">
							<strong>VALUE</strong>
						</button>
					</div>
					<form:input path="collateralValue" id="collateralValue"
						placeholder="Enter Value" cssClass="form-control"
						cssErrorClass="form-control is-invalid" />
					<form:errors path="collateralValue" cssClass="invalid-feedback" />
				</div>
			</div>

			<div class="row mb-3">
				<div class="input-group col">
					<div class="input-group-prepend">
						<button class="input-group-text" href="#">
							<strong>TENURE</strong>
						</button>
					</div>
					<form:input path="tenure" cssClass="form-control" id="tenure"
						placeholder="Enter Tenure" cssErrorClass="form-control is-invalid" />
					<form:errors path="tenure" cssClass="invalid-feedback" />
				</div>
				<div class="input-group col">
					<div class="input-group-prepend">
						<button class="input-group-text" href="#">
							<strong>INTEREST</strong>
						</button>
					</div>
					<form:input path="interest" id="interest"
						placeholder="Enter interest" cssClass="form-control"
						cssErrorClass="form-control is-invalid" />
					<form:errors path="interest" cssClass="invalid-feedback" />
				</div>
			</div>
			<div align="center">
				<a href="#" class="btn btn-info btn center mb-2">REAL ESTATE</a>
			</div>
			<div class="row mb-3">
				<div class="input-group col">
					<div class="input-group-prepend">
						<button class="input-group-text" href="#">
							<strong>COLLATERAL TYPE</strong>
						</button>
					</div>
					<form:input path="collateralType" id="collateralType"
						cssClass="form-control" placeholder="Enter Collateral Type"
						cssErrorClass="form-control is-invalid" />
					<form:errors path="collateralType" cssClass="invalid-feedback" />
				</div>
				<div class="input-group col">
					<div class="input-group-prepend">
						<button class="input-group-text" href="#">
							<strong>ADDRESS</strong>
						</button>
					</div>
					<form:input path="detail" id="detail" cssClass="form-control"
						placeholder="Enter Address"
						cssErrorClass="form-control is-invalid" />
					<form:errors path="detail" cssClass="invalid-feedback" />
				</div>
			</div>

			<div class="row mb-3">
				<div class="input-group col">
					<div class="input-group-prepend">
						<button class="input-group-text" href="#">
							<strong>VALUE</strong>
						</button>
					</div>
					<form:input path="currentvalue" id="currentvalue"
						cssClass="form-control" placeholder="Enter Value"
						cssErrorClass="form-control is-invalid" />
					<form:errors path="currentvalue" cssClass="invalid-feedback" />
				</div>
				<div class="input-group col">
					<div class="input-group-prepend">
						<button class="input-group-text" href="#">
							<strong>RATE / SQ. FT.</strong>
						</button>
					</div>
					<form:input path="detail1" id="detail1" cssClass="form-control"
						placeholder="Enter Rate / Sq. Ft."
						cssErrorClass="form-control is-invalid" />
					<form:errors path="detail1" cssClass="invalid-feedback" />
				</div>
				<div class="input-group col">
					<div class="input-group-prepend">
						<button class="input-group-text" href="#">
							<strong>DEPRECIATION RATE</strong>
						</button>
					</div>
					<form:input path="detail2" id="detail2" cssClass="form-control"
						placeholder="Enter Depreciation Rate"
						cssErrorClass="form-control is-invalid" />
					<form:errors path="detail2" cssClass="invalid-feedback" />
				</div>
			</div>
			<div align="center">
				<a href="#" class="btn btn-info btn center mb-2">CUSTOMER
					DETAILS</a>
			</div>
			<div class="row mb-3">
				<div class="input-group col">
					<div class="input-group-prepend">
						<button class="input-group-text" href="#">
							<strong>CUSTOMER NAME</strong>
						</button>
					</div>
					<form:input path="custname" id="custname" cssClass="form-control"
						placeholder="Enter Customer Name"
						cssErrorClass="form-control is-invalid" />
					<form:errors path="custname" cssClass="invalid-feedback" />
				</div>
			</div>
			<div class="row mb-3">
				<div class="input-group col">
					<div class="input-group-prepend">
						<button class="input-group-text" href="#">
							<strong>CUSTOMER ADDRESS</strong>
						</button>
					</div>
					<form:input path="custaddress" id="custaddress"
						cssClass="form-control" placeholder="Enter Customer Address"
						cssErrorClass="form-control is-invalid" />
					<form:errors path="custaddress" cssClass="invalid-feedback" />
				</div>
			</div>
			<div class="row mb-3">
				<div class="input-group col">
					<div class="input-group-prepend">
						<button class="input-group-text" href="#">
							<strong>CUSTOMER PHONE NO</strong>
						</button>
					</div>
					<form:input path="custphoneNo" id="custphoneNo"
						cssClass="form-control" placeholder="Enter Customer Phone Number"
						cssErrorClass="form-control is-invalid" />
					<form:errors path="custphoneNo" cssClass="invalid-feedback" />
				</div>
			</div>
			<div class="form-row">
				<div class="col">
					<button type="submit" class="btn btn-success btn-block text-white">SUBMIT</button>
				</div>
				<div class="col">
					<button type="reset" class="btn btn-secondary btn-block text-white">RESET</button>
				</div>
			</div>

		</form:form>
		<br>
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
	<div style="margin-top: 90px;"></div>
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