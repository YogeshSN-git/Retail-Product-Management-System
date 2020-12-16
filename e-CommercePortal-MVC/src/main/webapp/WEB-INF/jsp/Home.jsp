<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
<title>E-Commerce Portal</title>
<style>
body {
	background-color: lightgrey;
}

.product-link {
	color: black;
	text-decoration: none;
}

.product-link:hover {
	text-decoration: none;
	color: black;
}

.card:hover {
	transform: scale(0.99);
	box-shadow: 0 5px 10px rgba(0, 0, 0, .12), 0 2px 4px rgba(0, 0, 0, .06);
}

ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	overflow: hidden;
	position: -webkit-sticky;
	position: sticky;
	top: 0;
	background-color: #4DC889;
}

li {
	float: left;
}

li a {
	display: block;
	color: white;
	text-align: center;
	padding: 14px 16px;
	text-decoration: none;
}

li a:hover:not(.active) {
	background-color: lightgrey;
}

.active:hover, .link:hover {
	text-decoration: none;
	color: white;
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
	margin-top: 10px;
	margin-bottom: 10px;
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
}
</style>
</head>
<body>
	<header>
		<nav>
			<ul>
				<li><img
					src="https://tse1.mm.bing.net/th?id=OIP.4MlkDP01Clf_bJ3p420wOQHaD6&pid=Api&P=0&w=324&h=172https://tse1.mm.bing.net/th?id=OIP.4MlkDP01Clf_bJ3p420wOQHaD6&pid=Api&P=0&w=324&h=172"
					alt="Retail Icon" width="110" height="52"></li>
				<li><a class="active" href="home">Home</a></li>
				<div class="search">
					<form method="post" modelAttribute="productName"
						action="searchProduct">
						<input type="text" placeholder=" Search Product ID or Name"
							name="productName" required>
						<button>
							<i class="fa fa-search" style="font-size: 18px;"> </i>
						</button>
					</form>
				</div>
				<li style="float: right"><a class="link" href="logout">Logout</a></li>
				<li style="float: right"><a class="link" href="cart">Cart</a></li>
				<li style="float: right"><a class="link" href="wishlist">Wishlist</a></li>
				<li style="float: right"><a class="active" href="#">Welcome
						${name}</a></li>
			</ul>
		</nav>
	</header>
	<p>${errormsg}</p>
	<p>
	<center>${error}</center>
	</p>
	<c:if test="${productList !=null}">
		<div class="container w-100">
			<div class="row">
				<c:forEach items="${productList}" var="product">
					<div class="column w-50">
						<!-- <div class="card-deck"> -->
						<a class="product-link"
							href="/showProduct?productId=<c:out value="${product.productId}"></c:out>">
							<div class="card  bg-light mb-3 border-dark" style="margin: 1%">
								<img src="${product.imageName}" height="400px"
									class="card-img-top" alt="card image cap" />
								<div class="card-body">
									<h5 class="card-title">
										<c:out value="${product.productName}" />
										<span class="badge badge-danger float-right"><c:out
												value="${product.rating}" /></span>
									</h5>
									<div class="card-text">
										<c:out value="${product.description}" />
										<br>
										<c:out value="${product.productPrice}" />
									</div>
								</div>
							</div>
							<br>
						</a>
						<!-- </div> -->
					</div>
				</c:forEach>
			</div>
		</div>
	</c:if>
</body>
</html>