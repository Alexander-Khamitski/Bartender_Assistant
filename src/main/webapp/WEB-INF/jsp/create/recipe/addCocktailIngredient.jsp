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
    <h2 class="mt-5">Add cocktail ingredient:</h2>
    <div class="form-container">
        <form:form action="${pageContext.request.contextPath}/cocktail/ingredient/add" method="post" modelAttribute="cocktailIngredient">
            <div class="form-group mb-3">
                <label for="cocktail">Cocktail:</label>
                <br>
                <form:errors path="cocktail" cssClass="text-danger"/>
                <form:input type="text" path="cocktail.name" class="form-control" id="cocktail" value="${cocktail.name}" readonly="true"/>
                <form:hidden path="cocktail.id" value="${cocktail.id}"/>
            </div>
            <div class="form-group mb-3">
                <label for="ingredient">Ingredient:</label>
                <br>
                <form:errors path="ingredient" cssClass="text-danger"/>
                <form:select path="ingredient" class="form-control" id="ingredient">
                    <form:option value="" label="-- Select ingredient --"/>
                    <form:options items="${ingredients}" itemValue="id" itemLabel="name"/>
                </form:select>
            </div>
            <div class="form-group mb-3">
                <label for="amount">Amount:</label>
                <br>
                <form:errors path="amount" cssClass="text-danger"/>
                <form:input type="number" path="amount" class="form-control" id="amount"/>
            </div>
            <div class="form-group mb-3">
                <label for="unit">Unit:</label>
                <br>
                <form:errors path="unit" cssClass="text-danger"/>
                <form:input type="text" path="unit" class="form-control" id="unit"/>
            </div>
            <button type="submit" class="btn btn-primary w-100">Add</button>
            <a href="/recipe/get?id=${cocktail.id}" class="btn btn-secondary w-100 mt-2">Back to cocktail recipe</a>
        </form:form>
    </div>
</div>
</body>
</html>
