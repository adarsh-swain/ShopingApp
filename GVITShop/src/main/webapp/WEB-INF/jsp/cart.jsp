<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>My Cart</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .card {
            margin-bottom: 1rem;
        }
        .card-body {
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .card-body .details {
            flex: 1;
        }
        .card-body .actions {
            text-align: right;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="my-4">My Cart</h1>
        
        <!-- Display success or error messages -->
        <c:if test="${not empty success}">
            <div class="alert alert-success">
                ${success}
            </div>
        </c:if>
        <c:if test="${not empty error}">
            <div class="alert alert-danger">
                ${error}
            </div>
        </c:if>
        
        <!-- Cart items cards -->
        <c:if test="${not empty cartItems}">
            <div class="row">
                <c:forEach var="item" items="${cartItems}">
                    <div class="col-12 col-md-6 col-lg-4">
                        <div class="card">
                            <div class="card-body">
                                <div class="details">
                                    <h5 class="card-title">${item.product.name}</h5>
                                    <p class="card-text">Quantity: ${item.quantity}</p>
                                    <p class="card-text">Price: $${item.product.price}</p>
                                    <p class="card-text">Total: $${item.product.price * item.quantity}</p>
                                </div>
                                <div class="actions">
                                    <!-- Add actions like remove or update -->
                                    <a href="${pageContext.request.contextPath}/remove-from-cart?itemId=${item.id}" class="btn btn-danger">Remove</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>
        
        <c:if test="${empty cartItems}">
            <p>Your cart is empty.</p>
        </c:if>
        
        <!-- Add checkout button or other actions -->
        <a href="${pageContext.request.contextPath}/checkout" class="btn btn-primary">Proceed to Checkout</a>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
