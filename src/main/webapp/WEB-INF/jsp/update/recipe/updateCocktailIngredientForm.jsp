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
<div class="container">
    <h2 class="mt-5">Update cocktail ingredient:</h2>
    <div class="form-container">
        <form:form action="${pageContext.request.contextPath}/cocktail/ingredient/update" method="post" modelAttribute="cocktailIngredient">
            <div class="form-group mb-3">
                <label>ID:</label>
                <form:input type="number" path="id" class="form-control" id="id" value="${cocktailIngredient.id}" readonly="true"/>
                <form:errors path="id" cssClass="text-danger"/>
            </div>
            <div class="form-group mb-3">
                <label for="cocktail">Cocktail:</label>
                <form:input type="text" path="cocktail.name" class="form-control" id="cocktail" value="${cocktailIngredient.cocktail.name}" readonly="true"/>
                <form:hidden path="cocktail.id" value="${cocktailIngredient.cocktail.id}"/>
                <form:errors path="cocktail" cssClass="text-danger"/>
            </div>

            <div class="form-group mb-3">
                <label for="ingredient">Ingredient:</label>
                <form:input type="text" path="ingredient.name" class="form-control" id="ingredient" value="${cocktailIngredient.ingredient.name}" readonly="true"/>
                <form:hidden path="ingredient.id" value="${cocktailIngredient.ingredient.id}"/>
                <form:errors path="ingredient" cssClass="text-danger"/>
            </div>
            <div class="form-group mb-3 password-container">
                <label for="amount">Amount:</label>
                <br>
                <form:errors path="amount" cssClass="text-danger"/>
                <form:input path="amount" class="form-control" id="amount" value="${cocktailIngredient.amount}"/>
            </div>
            <div class="form-group mb-3 password-container">
                <label for="unit">Unit:</label>
                <br>
                <form:errors path="unit" cssClass="text-danger"/>
                <form:input path="unit" class="form-control" id="unit" value="${cocktailIngredient.unit}"/>
            </div>
            <button type="submit" class="btn btn-primary w-100">Update cocktail ingredient</button>
            <br>
            <a href="/recipe/get?id=${cocktailIngredient.cocktail.id}" class="btn btn-secondary w-100 mt-2">Back to cocktail recipe</a>
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
