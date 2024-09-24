<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
            width: 80%; /* Установлена ширина в 80% экрана */
            margin: 0 auto; /* Центрирование формы */
            padding: 20px;
            border: 2px solid #ffc107; /* Желтая граница */
            border-radius: 10px;
            background-color: #1b1b1b; /* Фоновый цвет формы */
        }

        .table th:nth-child(5), .table td:nth-child(5) {
            width: 200px; /* Ширина для кнопок действий */
        }

        /*Table*/
        .table {
            color: #f8f9fa;
        }

        .table th {
            background-color: #ffc107;
            color: black;
        }

        .table-hover tbody tr:hover {
            background-color: #333;
        }

        /*Buttons*/
        .btn {
            background-color: #ffc107;
            color: black;
            border: none; /* Убираем границу у кнопок */
        }

        .btn:hover {
            background-color: #e0a800; /* Изменение цвета при наведении */
            color: black;
        }

        .btn-group {
            display: flex;
            justify-content: center; /* Центрирование кнопок по горизонтали */
        }

        .btn-group .btn {
            margin: 0 5px; /* Добавляет немного пространства между кнопками */
            width: auto; /* Убирает 100% ширину для кнопок */
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
    <h2>'${cocktail.name}' recipe:</h2>
    <form>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>ID:</th>
                <th>Ingredient:</th>
                <th>Amount:</th>
                <th>Unit:</th>
                <th>Edit:</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cocktailIngredient" items="${cocktailIngredients}">
                <tr>
                    <td>${cocktailIngredient.id}</td>
                    <td>${cocktailIngredient.ingredient.name}</td>
                    <td>${cocktailIngredient.amount}</td>
                    <td>${cocktailIngredient.unit}</td>
                    <td>
                        <a href="/cocktail/ingredient/update?id=${cocktailIngredient.id}" class="btn btn-block">Edit</a>
                        <form action="/cocktail/ingredient/delete?id=${cocktailIngredient.id}" method="POST" style="display:inline;">
                            <button type="submit" class="btn btn-block">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="text-center">
            <div class="col-12 btn-group">
                <a href="/cocktail/ingredient/add?id=${cocktail.id}" class="btn btn-group w-100 mt-2">Add ingredient</a>
                <a href="/recipes" class="btn btn-group w-100 mt-2">Back to recipes</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>
