<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
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

        h2 {
            font-family: 'Georgia', serif;
            color: #f8f9fa;
            text-align: center;
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

        /*NavBar*/
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
    </style>
</head>
<body>
<sec:authorize access="hasRole('admin')">
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
                    <a class="nav-link" href="/reviews">Reviews</a>
                </li>
                <sec:authorize access="hasRole('admin') or hasRole('bartender')">
                    <li class="nav-item">
                        <a class="nav-link" href="/recipes">Recipes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/ingredients">Ingredients</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="hasRole('admin')">
                    <li class="nav-item">
                        <a class="nav-link" href="/admin/users">Users</a>
                    </li>
                </sec:authorize>
            </ul>
            <!-- Login and registration buttons -->
            <div class="d-flex">
                <sec:authorize access="!isAuthenticated()">
                    <a class="btn btn-lg me-2" href="/signin">Sign In</a>
                    <a class="btn btn-lg" href="/registration">Join Now</a>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <a class="btn btn-lg me-2" href="/user/profile">My profile</a>
                    <a class="btn btn-lg" href="/logout">Log Out</a>
                </sec:authorize>
            </div>
        </div>
    </nav>
    <div class="container">
        <div class="form-container">
            <p>${message}</p>
            <div class="d-flex justify-content-between mt-4">
                <a href="/admin/users" class="btn btn-primary half-width">Back to users</a>
                <br>
                <a href="/main" class="btn btn-secondary half-width">Main Page</a>
            </div>
        </div>
    </div>
</sec:authorize>
<sec:authorize access="hasRole('user') or hasRole('bartender')">
    <div class="container">
        <div class="form-container">
            <p>Your profile has been edited. You have to relogin.</p>
            <div class="d-flex justify-content-between mt-4">
                <a href="/logout" class="btn btn-secondary half-width">Log Out</a>
            </div>
        </div>
    </div>
</sec:authorize>
</body>
</html>