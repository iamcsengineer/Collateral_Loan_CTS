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
<title>LOAN DETAIL</title>
</head>
<body class="bg-light" onload="myFunction()">
	<div id="loading"></div>
	<nav class="navbar navbar-dark navbar-expand-lg bg-primary fixed-top">
		<div class="container">
			<a href="#" class="navbar-brand"><h2>LOAN DETAIL</h2></a>
			<button class="navbar-toggler" data-toggle="collapse"
				data-target="#navbarNav">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse" id="navbarNav">
				<ul class="navbar-nav ml-auto">
					<li class="nav-item"><a href="/logout" class="btn btn-danger btn-block" data-toggle="modal"
			data-target="#modal1">Logout</a></li>&nbsp;
					<li class="nav-item"><a href="/portalbranch" class="btn btn-info btn-block">Home Page</a></li>&nbsp;
					<li class="nav-item"><a href="/getloan" class="btn btn-info btn-block">Get Loan</a></li>
				</ul>
			</div>
		</div>
	</nav>
	<div style="padding-top: 90px;"></div>

	<div class="container ">
		<div align="center">
			<a href="#" class="btn btn-info btn center mb-4">LOAN</a>
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<button class="input-group-text" href="#">
					<strong>LOAN ID</strong>
				</button>
			</div>
			<input class="form-control" type="text" readonly="readonly"
				value="<c:out value="${Loan.loanId }" />">
		</div>
		<div class="row">
			<div class="input-group mb-3 col">
				<div class="input-group-prepend">
					<button class="input-group-text" href="#">
						<strong>LOAN VALUE</strong>
					</button>
				</div>
				<input class="form-control" type="text" readonly="readonly"
					value="<c:out value="${Loan.collateralValue }" />">
			</div>
			<div class="input-group mb-3 col">
				<div class="input-group-prepend">
					<button class="input-group-text" href="#">
						<strong>DATE LOAN ISSUED</strong>
					</button>
				</div>
				<input class="form-control" type="text" readonly="readonly"
					value="<c:out value="${Loan.pledgedDate }" />">
			</div>
		</div>
		<div class="row">
			<div class="input-group mb-4 col">
				<div class="input-group-prepend">
					<button class="input-group-text" href="#">
						<strong>TENURE</strong>
					</button>
				</div>
				<input class="form-control" type="text" readonly="readonly"
					value="<c:out value="${Loan.tenure }" />">
			</div>
			<div class="input-group mb-4 col">
				<div class="input-group-prepend">
					<button class="input-group-text" href="#">
						<strong>INTEREST</strong>
					</button>
				</div>
				<input class="form-control" type="text" readonly="readonly"
					value="<c:out value="${Loan.interest }" />">
			</div>
			<div class="input-group mb-4 col">
				<div class="input-group-prepend">
					<button class="input-group-text" href="#">
						<strong>EMI</strong>
					</button>
				</div>
				<input class="form-control" type="text" readonly="readonly"
					value="<c:out value="${Loan.emi }" />">
			</div>
		</div>
		<div align="center">
			<a href="#" class="btn btn-info btn center mb-4"><c:out
					value="${Loan.collateralName }" /></a>
		</div>
		<div class="row">
			<div class="input-group mb-3 col">
				<div class="input-group-prepend">
					<button class="input-group-text" href="#">
						<strong>COLLATERAL TYPE</strong>
					</button>
				</div>
				<input class="form-control" type="text" readonly="readonly"
					value="<c:out value="${Type.collateralType }" />">
			</div>
			<div class="input-group mb-3 col">
				<div class="input-group-prepend">
					<button class="input-group-text" href="#">
						<strong>ADDRESS</strong>
					</button>
				</div>
				<input class="form-control" type="text" readonly="readonly"
					value="<c:out value="${Type.address }" />">
			</div>
		</div>
		<div class="row">
			<div class="input-group mb-4 col">
				<div class="input-group-prepend">
					<button class="input-group-text" href="#">
						<strong>CURRENT VALUE</strong>
					</button>
				</div>
				<input class="form-control" type="text" readonly="readonly"
					value="<c:out value="${Type.currentvalue }" />">
			</div>
			<div class="input-group mb-4 col">
				<div class="input-group-prepend">
					<button class="input-group-text" href="#">
						<strong>RATE / SQ. FT.</strong>
					</button>
				</div>
				<input class="form-control" type="text" readonly="readonly"
					value="<c:out value="${Type.ratepersqft }" />">
			</div>
			<div class="input-group mb-4 col">
				<div class="input-group-prepend">
					<button class="input-group-text" href="#">
						<strong>DEPRECIATION RATE</strong>
					</button>
				</div>
				<input class="form-control" type="text" readonly="readonly"
					value="<c:out value="${Type.depreciationrate }" />">
			</div>
		</div>
		<div align="center">
			<a href="#" class="btn btn-info btn center mb-4">CUSTOMER DETAILS</a>
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<button class="input-group-text" href="#">
					<strong>CUSTOMER NAME</strong>
				</button>
			</div>
			<input class="form-control" type="text" readonly="readonly"
				value="<c:out value="${Loan.customer.name }" />">
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<button class="input-group-text" href="#">
					<strong>CUSTOMER ADDRESS</strong>
				</button>
			</div>
			<input class="form-control" type="text" readonly="readonly"
				value="<c:out value="${Loan.customer.address }" />">
		</div>
		<div class="input-group mb-3">
			<div class="input-group-prepend">
				<button class="input-group-text" href="#">
					<strong>CUSTOMER PHONE NO</strong>
				</button>
			</div>
			<input class="form-control" type="text" readonly="readonly"
				value="<c:out value="${Loan.customer.phoneNo }" />">
		</div>

		<div class="form-row">
			<div class="col">
				<a href="/logout" class="btn btn-danger btn-block">Logout</a>
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