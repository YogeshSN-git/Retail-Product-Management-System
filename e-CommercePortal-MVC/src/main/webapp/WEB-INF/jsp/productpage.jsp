<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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
	vertical-align: middle;
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
	<%-- <input name="productid" value="${productItem.productId }" type="hidden"> --%>
	<div class="container container-fluid" style="width: 50%">
		<div class="card" style="margin: 5%">
			<img src="${productItem.imageName }" class="card-img-top">
			<div class="card-body">
				<h5 class="card-title">${productItem.productName }
					<span class="badge badge-success float-right">${productItem.rating}<i
						class="icon-size material-icons">star_rate</i></span>
				</h5>
				<p class="card-text">Description:&nbsp;${productItem.description }</p>
				<p class="card-text">Price:&nbsp;${productItem.productPrice }</p>
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