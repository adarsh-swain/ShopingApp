<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <style>
        .profile-container {
            margin-top: 20px;
        }
        .profile-header {
            margin-bottom: 20px;
        }
        .profile-info {
            margin-bottom: 10px;
        }
        .profile-picture {
            width: 150px;
            height: 150px;
            border-radius: 50%;
            object-fit: cover;
            margin-bottom: 20px;
        }
        .alert {
            margin-bottom: 20px;
        }
    </style>
</head>
<body>
    <div class="container profile-container">
        <div class="profile-header text-center">
            <h1>User Profile</h1>
        </div>

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

        <!-- Profile details -->
        <c:if test="${not empty profile}">
            <div class="row justify-content-center">
                <div class="col-md-6 col-lg-4 text-center">
                    <div class="profile-info">
                        <p><strong>Name:</strong> ${profile.username}</p>
                        <p><strong>Email:</strong> ${profile.email}</p>
                        <p><strong>Address:</strong> ${profile.address}</p> <!-- Add more fields as needed -->
                    </div>
                </div>
            </div>
        </c:if>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
