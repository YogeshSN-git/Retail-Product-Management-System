<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js"
	type="text/javascript"></script>
<link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css"
	rel="stylesheet" type="text/css" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<script src="https://code.iconify.design/1/1.0.4/iconify.min.js"></script>
<script src='https://kit.fontawesome.com/a076d05399.js'></script>
<title>${errorMessage }</title>
<style>
body {
	background-color: lightgrey;
}

.material-icons {
	vertical-align: top;
	font-size: 15px;
}

#nav-head {
	color: white;
	background-color: #4DC889;
	margin: 0;
	padding: 0;
}

.img-fluid {
	padding: 0px;
	height: 52px;
	width: 100px;
}

.nav-item .nav-link:hover {
	opacity: 3;
}

.dropdown .dropbtn {
	opacity: 0.5;
	font-size: 16px;
	border: none;
	outline: none;
	color: white;
	padding: 8px;
	background-color: inherit;
	font-family: inherit;
	margin: 0;
}

.dropbtn:hover {
	opacity: 1;
}

.dropdown-content {
	display: none;
	position: absolute;
	background-color: #4DC889;
	min-width: 50px;
	box-shadow: 0px 8px 16px 0px rgba(0, 0, 0, 0.2);
	z-index: 1;
}

.dropdown-content a {
	float: none;
	color: white;
	text-decoration: none;
	display: block;
	padding: 10px 10px;
}

.dropdown-content a:hover {
	background-color: #14de8d;
}

.dropdown:hover .dropdown-content {
	display: block;
}

.search input[type=text] {
	width: 300px;
	height: 30px;
	border-radius: 25px;
	border: none;
}

.search {
	float: left;
	position: relative;
	left: 10%;
	float: center;
	margin-top: 1px;
	margin-bottom: 1px;
}

.search button {
	background-color: #4DC889;
	color: #f2f2f2;
	float: center;
	padding: 5px 10px;
	margin-right: 16px;
	font-size: 12px;
	border: none;
	cursor: pointer;
	margin-right: 16px;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg justify-content-between"
		id="nav-head">
		<span class="navbar-header col-6"> <img class="img-fluid"
			src="https://tse1.mm.bing.net/th?id=OIP.4MlkDP01Clf_bJ3p420wOQHaD6&pid=Api&P=0&w=324&h=172https://tse1.mm.bing.net/th?id=OIP.4MlkDP01Clf_bJ3p420wOQHaD6&pid=Api&P=0&w=324&h=172"
			alt="Retail Icon" width="110" height="52">
		</span>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navlinks" aria-controls="links" aria-expanded="false"
			aria-label="Toggle navigation">
			<i class="icon-size material-icons">menu</i>
		</button>
		<span class="collapse navbar-collapse col-6" id="navlinks"> <span
			class="navbar-nav search">
				<form method="post" modelAttribute="productName"
					action="searchProduct" class="form-inline">
					<input id="search-bar" type="text"
						placeholder=" Search Product ID or Name" name="productName"
						required class="form-control mr-sm-2">
					<button class="btn btn-outline-success my-2 my-sm-0"
						id="search-button">
						<i class="fa fa-search" style="font-size: 18px;"> </i>
					</button>
				</form> <a class="nav-item nav-link text-white" href="/home?pageno=1">Home</a>
				<a class="nav-item nav-link text-white" href="cart">Cart</a> <a
				class="nav-item nav-link text-white" href="wishlist">Wishlist</a>
				<div class="dropdown ">
					<button class="dropbtn text-white">${name}<i
							class="fa fa-caret-down"></i>
					</button>
					<div class="dropdown-content text-white">
						<a href="logout">Logout</a>
					</div>
				</div>
		</span>
		</span>
	</nav>
	<p class="alert alert-danger mt-3" role="alert">${errorMessage }</p>
	<a class="btn btn-primary" href="/home?pageno=1">Go to home page</a>


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

</body>
</html>