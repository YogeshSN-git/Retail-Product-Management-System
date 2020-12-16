<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
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
<title>E-commerce portal</title>
</head>
<style>
.card {
	float: left;
	margin-left: 7%;
	margin-top: 1%;
}

.card-header {
	background-color: lightgrey;
	font-size: 27px;
}

.card-horizontal {
	display: flex;
	flex: 1 1 auto;
}

.row {
	display: flex;
	flex-wrap: wrap;
}

.row>div[class*='row-'] {
	display: flex;
}

#price {
	float: right;
}

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

	<c:forEach items="${productList}" var="product">
		<div class="row">
			<div class="card" style="width: 80%">
				<div class="card-horizontal">
					<div class="img-square-wrapper">
						<img class="" src="${product.imageName}" height="150px" />
					</div>
					<div class="card-body">
						<h5 class="card-title">
							<c:out value="${product.productName }"></c:out>
						</h5>
						<div class="card-text">
							Description:
							<c:out value="${product.description }" />
							<br> Date added to WishList:
							<c:out value="${wishList.dateAddedtoWishlist }" />
							<br>
							<p id="price">
								Price:
								<c:out value="${product.productPrice }" />
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:forEach>
</body>
</html>