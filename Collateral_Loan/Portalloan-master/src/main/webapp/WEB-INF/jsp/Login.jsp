<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="spring"
uri="http://www.springframework.org/tags"%> <%@ taglib prefix="form"
uri="http://www.springframework.org/tags/form"%> <%@ taglib
uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <link
      rel="canonical"
      href="https://getbootstrap.com/docs/4.3/examples/starter-template/"
    />
    <link
      rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
    />
    <link
      href="https://fonts.googleapis.com/icon?family=Material+Icons"
      rel="stylesheet"
    />
    <link rel="stylesheet" href="style.css" type="text/css" />
    <title>LOAN LOGIN PORTAL</title>
  </head>

  <body class="bg-light" onload="myFunction()">
    <div id="loading"></div>
    <div class="container">
      <nav class="navbar navbar-dark navbar-expand-sm bg-primary fixed-top">
        <div class="container">
          <a href="#" class="navbar-brand"><h2>WELCOME TO LOAN PORTAL</h2></a>
        </div>
      </nav>
    </div>
    <div style="padding-top: 90px"></div>
    <div class="container">
      <!-- <h2>Login Form</h2> -->
      ${msg }
      <form
        name="loginform"
        method="post"
        action="login"
        class="form-container"
      >
        <div class="form-group">
          <div align="center">
            <span class="material-icons admin"> admin_panel_settings </span>
          </div>
          <label for="uid"><strong>Enter User ID</strong></label>
          <input
            type="text"
            placeholder="Enter UserId"
            name="userid"
            class="form-control"
            required
          />
          <br />
          <label for="psw"><strong>Enter Your Password</strong></label>
          <input
            type="password"
            placeholder="Enter Password"
            name="upassword"
            class="form-control"
            required
          />
        </div>
        <button type="submit" class="btn btn-success btn-block text-white">
          LOGIN
        </button>
      </form>
      <div class="modal" id="modal1">
        <div class="modal-dialog">
          <div class="modal-content">
            <div class="modal-header">
              <h5 class="modal-title"><strong>Warning</strong> to ${name }</h5>
              <button class="close" data-dismiss="modal">&times;</button>
            </div>
            <div class="modal-body">
              Are you sure you want to navigate away from this page?<br />
              You will lose any unsaved modifications to this document.<br />
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
    <div class="container">
      <nav class="navbar navbar-dark bg-dark fixed-bottom">
        <div class="container">
          <a href="#" class="navbar-brand"
            >COLLATERAL LOAN MANAGEMENT AND RISK ASSESSMENT &copy;
            2021</a
          >
        </div>
      </nav>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
    <script>
      $("#myAlert").on("closed.bs.alert", function () {
        console.log("Alert closed...");
      });
      var preloader = document.getElementById("loading");
      function myFunction() {
        preloader.style.display = "none";
      }
    </script>
  </body>
</html>
