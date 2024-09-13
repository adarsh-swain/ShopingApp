<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Product List</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .product-card {
            margin-bottom: 20px;
        }
        .product-description {
            max-height: 60px;
            overflow: hidden;
            text-overflow: ellipsis;
            white-space: nowrap;
        }
        .product-image {
            max-height: 200px;
            object-fit: cover;
            width: 100%;
        }
        .btn-add-to-cart {
            background-color: #28a745; /* Green color */
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
            margin-right: 10px;
        }
        .btn-add-to-cart:hover {
            background-color: #218838; /* Darker green */
        }
        .btn-buy-now {
            background-color: #007bff; /* Blue color */
            color: white;
            border: none;
            padding: 10px 20px;
            font-size: 16px;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }
        .btn-buy-now:hover {
            background-color: #0056b3; /* Darker blue */
        }
        .button-group {
            display: flex;
            flex-wrap: wrap;
            gap: 10px; /* Space between buttons */
        }
        .card-body {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }
    </style>
</head>
<body>

<!-- Navbar -->
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}/productList">Shop</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav ml-auto">
            <!-- Home link -->
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/profile">Profile</a>
            </li>
            <!-- Cart link -->
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/cartsOfUser">My Cart</a>
            </li>
            <!-- Logged-in user actions (Logout) -->
            <c:if test="${not empty User}">
                <li class="nav-item">
                    <a class="nav-link" href="#">Welcome, ${User.username}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/logout">Logout</a>
                </li>
            </c:if>
        </ul>
    </div>
</nav>

<div class="container">
    <h1 class="my-4">Product List</h1>
    <div class="row">
        <c:forEach var="product" items="${products}">
            <div class="col-md-4 col-sm-6">
                <div class="card product-card">
                    <img src="${product.imageUrl}" alt="${product.name}" class="card-img-top product-image">
                    <div class="card-body">
                        <h5 class="card-title">${product.name}</h5>
                        <p class="card-text product-description">${product.description}</p>
                        <p class="card-text"><strong>Price:</strong> $${product.price}</p>
                        <div class="button-group">
                            <form action="/GVITShop/add-to-cart" method="post" class="w-100">
                                <input type="hidden" name="productId" value="${product.id}">
                                <input type="number" name="quantity" value="1" min="1" required class="form-control mb-2">
                                <button type="submit" class="btn-add-to-cart w-100 mb-2">Add to Cart</button>
                            </form>                                
                            <form action="${pageContext.request.contextPath}/buy-now" method="get">
                                <input type="hidden" name="productId" value="${product.id}">
                                <button type="submit" class="btn-buy-now w-100">Buy Now</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>

<!-- Bootstrap JS and dependencies -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
