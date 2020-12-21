<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
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

<title>${productItem.productName }</title>



<style type="text/css">
.material-icons {
	vertical-align: top;
	font-size: 15px;
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


	<div class="container w-100 d-flex justify-content-center">
		<div class="card col-xl-7 mt-3" style="margin: 1%">
			<img src="${productItem.imageName }" class="card-img-top">
			<div class="card-body">
				<h5 class="card-title">${productItem.productName }
					<span class="badge badge-success float-right">${productItem.rating}
						<i class="icon-size material-icons">star_rate</i>
					</span>
				</h5>
				<p class="card-text">Description:&nbsp;${productItem.description }</p>
				<p class="card-text">Price:&nbsp;
					₹&nbsp;${productItem.productPrice }</p>
				<p class="card-text">Left in Stock:&nbsp;${stock }</p>

				<c:if test="${stock!=0}">
					<a href="/addToCart?productId=${productItem.productId }"
						class="btn btn-primary">Add To Cart</a>
				</c:if>

				<c:if test="${stock==0}">
					<p class="alert alert-danger" role="alert">Product out of stock</p>
					<a href="/addToWishlist/${productItem.productId}"
						class="btn btn-primary">Add To WishList</a>
				</c:if>

			</div>
		</div>

	</div>







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