<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <style>
        body {
            background-color: #2d2d2d;
            color: #f8f9fa;
            padding: 0px 50px 200px 50px;
        }

        .navbar {
            background-color: #1b1b1b;
        }

        .nav-link {
            color: #f8f9fa;
            font-size: 1.5em;
            font-family: serif;
        }

        .nav-link:hover {
            color: #ffc107;
        }

        h1, h2, h3 {
            font-family: 'Georgia', serif;
            color: #f8f9fa;
        }

        .btn {
            background-color: #ffc107;
            color: black;
        }

        .carousel-item p {
            font-size: 1.5em;
            font-style: italic;
            color: #f8f9fa;
        }

        .form-container {
            max-width: 600px;
            margin: 0 auto; /* Center the form */
            padding: 20px;
            border: 2px solid #ffc107; /* Yellow border */
            border-radius: 10px;
            background-color: #1b1b1b; /* Form background */
        }
    </style>
</head>
<body>
<!-- Navigation -->
<nav class="navbar navbar-expand-lg">
    <div class="container-fluid d-flex justify-content-between align-items-center">
        <!-- Label -->
        <a class="navbar-brand" href="main">
            <img src="${pageContext.request.contextPath}/images/home/label.png" alt="Bartender Assistant - label"
                 width="120" height="60">
        </a>
        <!-- Navigation links -->
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <li class="nav-item">
                <a class="nav-link" href="/main">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main/cocktails">Cocktails</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/recipes">Recipes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/reviews">Reviews</a>
            </li>
            <sec:authorize access="hasRole('admin')">
                <li class="nav-item">
                    <a class="nav-link" href="/admin/users">Users</a>
                </li>
            </sec:authorize>
        </ul>
    </div>
</nav>

<div class="form-container">
    <h2>Requested user info:</h2>
    <p><strong>ID:</strong> ${userDto.id}</p>
    <p><strong>Username:</strong> ${userDto.username}</p>
    <p><strong>Role:</strong> ${userDto.role.roleName}</p>
    <a href="/admin/user/get" class="btn btn-secondary w-100 btn-back">Back</a>
</div>
</body>
</html>
