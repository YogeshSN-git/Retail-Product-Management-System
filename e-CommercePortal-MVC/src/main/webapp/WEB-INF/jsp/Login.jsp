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
<link href="style.css" rel="stylesheet" type="text/css" />
<style>
body {
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
}
</style>
</head>

<body>
	<br />
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

	</div>
</body>
</html>