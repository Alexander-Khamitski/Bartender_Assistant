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
            padding: 50px 0;
        }

        h2 {
            font-family: 'Georgia', serif;
            color: #f8f9fa;
            text-align: center;
            margin-bottom: 30px;
        }

        .form-container {
            width: 80%; /* Установлена ширина в 80% экрана */
            margin: 0 auto; /* Центрирование формы */
            padding: 20px;
            border: 2px solid #ffc107; /* Желтая граница */
            border-radius: 10px;
            background-color: #1b1b1b; /* Фоновый цвет формы */
        }

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

        .pagination {
            display: flex;
            justify-content: center;
            margin-top: 20px;
        }

        .pagination .page-item {
            list-style-type: none; /* Убираем точки */
        }

        .pagination a {
            color: black;
            background-color: #ffc107;
            border: 1px solid #ffc107;
            margin: 0 5px;
        }

        .pagination .active a {
            background-color: #333;
            color: white;
        }

        .pagination a:hover {
            background-color: #555;
            color: white;
        }

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

        .navbar {
            background-color: #1b1b1b;
            margin-top: 0; /* Убираем нижний отступ у navbar */
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
    <h2>All cocktails:</h2>
    <form>
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Name</th>
                <th>Description</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="cocktail" items="${cocktails}">
                <tr>
                    <td>${cocktail.name}</td>
                    <td>${cocktail.description}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <!-- Горизонтальная пагинация -->
        <nav aria-label="Page navigation example">
            <ul class="pagination">
                <li class="page-item">
                    <a class="page-link" href="${pageUrl}?page=0" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>
                <c:forEach var="i" begin="0" end="${totalPages - 1}">
                    <li class="page-item ${currentPage == i ? 'active' : ''}">
                        <a class="page-link" href="${pageUrl}?page=${i}">${i + 1}</a>
                    </li>
                </c:forEach>
                <li class="page-item">
                    <a class="page-link" href="${pageUrl}?page=${totalPages - 1}" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
            </ul>
        </nav>


        <div class="text-center">
            <div class="col-12 btn-group">
                <sec:authorize access="hasRole('admin')">
                    <a href="/admin/cocktail/create" class="btn btn-group w-100 mt-2">Create cocktail</a>
                    <a href="/admin/cocktail/get" class="btn btn-group w-100 mt-2">Get cocktail</a>
                    <a href="/admin/cocktail/update" class="btn btn-group w-100 mt-2">Update cocktail</a>
                    <a href="/admin/cocktail/delete" class="btn btn-group w-100 mt-2">Delete cocktail</a>
                </sec:authorize>
            </div>
        </div>
    </form>
</div>
</body>
</html>
