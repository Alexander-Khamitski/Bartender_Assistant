<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
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

        .btn {
            background-color: #ffc107;
            color: black;
        }

        .form-container {
            max-width: 600px;
            margin: 0 auto; /* Center the form */
            padding: 20px;
            border: 2px solid #ffc107; /* Yellow border */
            border-radius: 10px;
            background-color: #1b1b1b; /* Form background */
        }

        .password-container {
            position: relative;
        }

        .password-container input {
            flex: 1;
            padding-right: 40px; /* Add some padding to the right for the icon */
        }

        .password-container .toggle-password {
            position: absolute;
            right: 10px;
            transform: translateY(-50%);
            top: 50%;
            cursor: pointer;
            color: #ffc107; /* Yellow color for contrast */
            padding: 5px;
            background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent background */
            border-radius: 50%; /* Rounded background */
        }

        .password-container .toggle-password:hover {
            color: #fff; /* Change icon color on hover */
            background-color: #333; /* Darker background on hover */
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
<div class="form-container">
    <h2>Update user:</h2>
    <form:form action="${pageContext.request.contextPath}/user/profile/update" method="post"
               modelAttribute="existingUser">
        <div class="form-group mb-3">
            <label>ID:</label>
            <form:errors path="id" cssClass="text-danger"/>
            <form:input type="number" path="id" class="form-control" id="id" value="${existingUser.id}"
                        readonly="true"/>
        </div>
        <div class="form-group mb-3">
            <label for="username">Username:</label>
            <br>
            <form:errors path="username" cssClass="text-danger"/>
            <form:input type="text" path="username" class="form-control" id="username"
                        value="${existingUser.username}"/>
        </div>
        <div class="form-group mb-3">
            <label for="login">Login:</label>
            <br>
            <form:errors path="login" cssClass="text-danger"/>
            <form:input type="text" path="login" class="form-control" id="login" value="${existingUser.login}"/>
        </div>
        <div class="form-group mb-3">
            <label for="password" path="password">Password:</label>
            <br>
            <form:errors path="password" cssClass="text-danger"/>
            <div class="password-container">
                <form:password path="password" class="form-control" id="password" oninput="checkPasswordMatch()"/>
                <i class="fa fa-eye toggle-password eye-1" onclick="togglePassword('password', 'eye-1')"></i>
            </div>
        </div>
        <div class="form-group mb-3">
            <label for="confirm-password">Confirm password:</label>
            <br>
            <span class="text-danger" id="confirmPasswordError"></span>
            <div class="password-container">
                <input type="password" class="form-control" id="confirm-password" name="confirmPassword"
                       oninput="checkPasswordMatch()"/>
                <i class="fa fa-eye toggle-password eye-2" onclick="togglePassword('confirm-password', 'eye-2')"></i>
            </div>
        </div>
        <div class="form-group mb-3">
            <label for="role">Role:</label>
            <form:errors path="role" cssClass="text-danger"/>
            <form:input type="text" path="role.roleName" class="form-control" id="role"
                        value="${existingUser.role.roleName}" readonly="true"/>
            <form:hidden path="role.id" value="${existingUser.role.id}"/>
        </div>
        <button type="submit" class="btn btn-primary w-100">Update</button>
        <br>
        <a href="/user/profile" class="btn btn-secondary w-100 mt-2">Back to profile</a>
    </form:form>
</div>

<script>
    function togglePassword(elementId, toggle) {
        const passwordField = document.getElementById(elementId);
        const eyeIcon = document.querySelector(toggle);
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

    function checkPasswordMatch() {
        const password = document.getElementById("password").value;
        const confirmPassword = document.getElementById("confirm-password").value;
        if (password !== confirmPassword) {
            document.getElementById("confirm-password").setCustomValidity("Passwords do not match!");
        } else {
            document.getElementById("confirm-password").setCustomValidity("");
        }
    }
</script>
</body>
</html>
