<%@page import="model.User"%>
<%@page import="com.UserService"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/users.js"></script>
</head>
<body>

	<div class="container">
		<div class="row">
			<div class="col-6">

				<h1>User Management</h1>
				<form id="formUser" name="formUser">
					User name:
					<input id="userName" name="userName" type="text"
								class="form-control form-control-sm">
								
					<br>User email: 
					<input id="userEmail" name="userEmail" type="text"
									class="form-control form-control-sm">
					
					<br>User phone:
					<input id="userPhone" name="userPhone" type="text"
									class="form-control form-control-sm">
					
					<br>User Acc_no: 
					<input id="userAcc_no" name="userAcc_no" type="text"
									class="form-control form-control-sm">
					
					<br>
					<input id="btnSave" name="btnSave" type="button" value="Save"
								class="btn btn-primary">
					
					<input type="hidden" id="hidUserIDSave"
								name="hidUserIDSave" value="">
					
				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>

				<br>
				<div id="divUsersGrid">
					<%
						User userObj = new User();
						out.print(userObj.readUsers());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
