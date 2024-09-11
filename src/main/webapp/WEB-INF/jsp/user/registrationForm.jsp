<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"/>
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

        .form-container {
            max-width: 600px;
            margin: 0 auto; /* Center the form */
            padding: 20px;
            border: 2px solid #ffc107; /* Yellow border */
            border-radius: 10px;
            background-color: #1b1b1b; /* Form background */
        }

        .btn {
            background-color: #ffc107;
            color: black;
        }

        .form-group label {
            font-weight: bold;
        }

        .text-warning {
            color: red !important; /* Ensure text-warning class uses red color */
            text-align: center;
            font-size: 1.2em; /* Slightly larger font size */
            margin-top: 20px;
        }

        .password-container {
            position: relative;
        }

        .password-container .toggle-password {
            position: absolute;
            top: 50%;
            right: 10px;
            transform: translateY(-50%);
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container">
    <h2 class="mt-5">Register</h2>
    <div class="form-container">
        <p class="text-warning">${message}</p>
        <form:form action="${pageContext.request.contextPath}/user/registration" method="post" modelAttribute="userCreateDto">
            <div class="form-group mb-3">
                <label for="username">Username:</label>
                <form:input type="text" path="username" class="form-control" id="username"/>
                <form:errors path="username" cssClass="text-danger"/>
            </div>
            <div class="form-group mb-3">
                <label for="login">Login:</label>
                <form:input type="text" path="login" class="form-control" id="login"/>
                <form:errors path="login" cssClass="text-danger"/>
            </div>
            <div class="form-group mb-3 password-container">
                <label for="password">Password:</label>
                <form:password path="password" class="form-control" id="password"/>
                <i class="fa fa-eye toggle-password" onclick="togglePassword()"></i>
                <form:errors path="password" cssClass="text-danger"/>
            </div>
            <button type="submit" class="btn btn-primary w-100">Register</button>
            <a href="/main" class="btn btn-secondary w-100 mt-2">Back to Main</a>
        </form:form>
    </div>
</div>

<script>
    function togglePassword() {
        const passwordField = document.getElementById('password');
        const eyeIcon = document.querySelector('.toggle-password');

        if (passwordField.type === 'password') {
            passwordField.type = 'text';
            eyeIcon.classList.remove('fa-eye');
            eyeIcon.classList.add('fa-eye-slash');
        } else {
            passwordField.type = 'password';
            eyeIcon.classList.remove('fa-eye-slash');
            eyeIcon.classList.add('fa-eye');
        }
    }
</script>
</body>
</html>
