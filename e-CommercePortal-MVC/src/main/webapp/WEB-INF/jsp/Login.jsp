<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>E-Commerce portal Login</title>
<link rel="stylesheet" type="text/css" href="style.css">
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css"
	rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://fonts.googleapis.com/icon?family=Material+Icons">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
	integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
	integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
	crossorigin="anonymous"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
	integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
	crossorigin="anonymous"></script>
<style>
/* body {
	background-image:
		url("http://redpithemes.com/Documentation/assets/img/page_bg/page_bg_blur02.jpg");
	background-color: #cccccc;
	height: 500px;
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
	position: relative;
}

.login-card {
	width: 400px;
	background-color: white;
	margin: 0px auto;
	border-radius: 9px;
	box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
	min-height: 440px;
}

.log_head {
	background: #4DC889;
	width: 400px;
	height: 120px;
	border-radius: 9px 9px 0 0px;
}

.login-card h1 {
	text-align: center;
	font-family: open Sans;
	color: #FFFFFF;
	font-weight: 700px;
	font-size: 20px;
	line-height: 27px;
	padding-top: 24px;
}

.error_msg {
	color: red;
}

.lock {
	padding-left: 181px;
	width: 30px;
	height: 37px;
	padding-top: 10px;
}

.log_body {
	padding: 40px 20px 20px 20px;
}

.log_user {
	background: lightgrey;
	color: white;
	border-radius: 10px;
	width: 349px;
	height: 31px;
	padding: 5px;
	font-family: open Sans;
	font-weight: 700px;
	font-size: 15px;
	border: none;
}

.log_Pass {
	background: lightgrey;
	color: white;
	border-radius: 10px;
	width: 349px;
	height: 31px;
	padding: 5px;
	font-family: open Sans;
	font-weight: 700px;
	font-size: 15px;
	border: none;
}

.login-submit {
	background: #4DC889;
	border: none;
	border-radius: 10px;
	width: 359px;
	height: 36px;
	cursor: pointer;
	font-family: open Sans;
	font-weight: 700px;
	font-size: 15px;
	color: #FFFFFF;
}

.log_body a {
	text-decoration: none;
	color: #78EEB2;
	font-family: open Sans;
	font-weight: 900px;
	font-size: 15px;
	line-height: 21px;
} */
body{
	justify-content: center;
	background-color: #cccccc;
	background-image: url("http://redpithemes.com/Documentation/assets/img/page_bg/page_bg_blur02.jpg");
}
.container{
	border-radius: 10px;
	background-color: #4DC889;
	margin-top: 150px;
	padding-top: 30px;
	width: 338px;
}
.login-div{
	color: white;
	background-color: #4DC889;
}
.form-div{
	margin-top: -8px;
	background-color: white;
	margin-left: -15px;
	margin-right: -15px;
	padding-top: 10px;
	padding-bottom: 10px;
	border-radius: 10px;
}
.form-control{
	background: lightgrey;
	border-radius: 10px;
	margin: 20px;
	width: 300px;
	justify-content: center;
	padding: 15px 15px;
	font-family: open Sans;
	
}
#log-user{
	/* margin-top : 100px; */
}
.lock {
	width: 30px;
	height: 30px;
	
}
#button{
	background-color: #4DC889;
	border: none;
	width: 100px;
	margin-left: 110px;
}
.error_msg {
	color: red;
	margin-left: -65px;
}

</style>
</head>

<body class="text-center">
	<div class="container ">
	<div class="login-div">
	<h4>LOGIN
	<img src="https://www.materialui.co/materialIcons/action/lock_grey_192x192.png"
				alt="lock" class="lock" />
	</h4></div>
	<div class="form-div">
		<form name="loginform" method="post" action="authenticate" >
			<div class="form-group" id="log-user">
				<!-- <label for="email">Email address</label> --> <input type="text"
					class="form-control" aria-describedby="emailHelp"
					placeholder="User Name" name="userid" required>
			</div>
			<div class="form-group">
				<!-- <label for="password">Password</label> --> <input type="password"
					class="form-control" placeholder="Password" name="upassword"
					required>
			</div>
			<div class="error_msg">${errormsg}</div>
			<button type="submit" class="btn btn-primary btn-block" id="button" name="login"
				value="Login">Login</button>
		</form>
	</div>
	</div>
	<%-- <br />
	<br />

	<div class="login-card">
		<div class="log_head">
			<h1>LOGIN</h1>
			<img
				src="https://www.materialui.co/materialIcons/action/lock_grey_192x192.png"
				alt="lock" class="lock" />
		</div>

		<div class="log_body">
			<form name="loginform" method="post" action="authenticate">
				<table width="200" border="0" align="center">
					<tr>
						<td><input placeholder="User Name" type="text"
							class="log_user" name="userid" required></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td><input placeholder="Password" type="password"
							class="log_Pass" name="upassword" required></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td>
							<div class="error_msg">${errormsg}</div>
						</td>
					</tr>
					<tr>
						<td><button type="submit" name="login" class="login-submit"
								value="Login">Login</button></td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>

				</table>

			</form>
		</div>

	</div> --%>
</body>
</html> 