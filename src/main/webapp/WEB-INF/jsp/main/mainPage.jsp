<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ include file="/WEB-INF/jsp/common/navbar.jsp" %>
<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8" %>
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

        h1, h2, h3 {
            font-family: 'Georgia', serif;
            color: #f8f9fa;
        }

        .main-heading {
            font-size: 4em;
            text-align: center;
            margin-top: 30px;
        }

        .subheading {
            font-size: 2.5em;
            font-weight: bold;
            text-align: center;
            margin-bottom: 30px;
        }

        .section {
            margin-bottom: 50px;
        }

        .card {
            background-color: #1b1b1b;
            color: #f8f9fa;
            border: 1px solid #ffc107;
            width: 26rem;
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
<!-- Main Section -->
<div class="container">
    <div class="main-heading">
        <h1>Bartender Assistant</h1>
    </div>
    <div class="text-center subheading">
        <h2>Your Guide to Crafting the Perfect Cocktail</h2>
    </div>

    <!-- Cocktail Section -->
    <div class="row section">
        <div class="col-6">
            <div class="card w-100">
                <div class="card-body">
                    <h3 class="card-title">Signature Cocktails</h3>
                    <p class="card-text">Discover unique and classic cocktails curated by our community of
                        bartenders.</p>
                </div>
            </div>
            <br>
            <div class="card w-100">
                <div class="card-body">
                    <h3 class="card-title">Suggest recipes</h3>
                    <p class="card-text">We are very welcome improve our menu and we are always open for new recipes</p>
                </div>
            </div>
            <br>
            <div class="card w-100">
                <div class="card-body">
                    <h3 class="card-title">Join our team</h3>
                    <p class="card-text">Our team is growing! Are you looking for new opportunities? Join the team! </p>
                </div>
            </div>
        </div>
        <div class="col-6 d-flex justify-content-center">
            <img src="${pageContext.request.contextPath}/images/home/pornstarMartini.png" alt="Cocktail Image"
                 class="img-fluid" width="600" height="500">
        </div>
    </div>
    <!-- Review Section -->
    <div class="row section">
        <div class="col-6 d-flex justify-content-center">
            <br>
            <img src="${pageContext.request.contextPath}/images/home/bartenderAtWork.png" alt="Bartender at Work"
                 class="img-fluid" width="600" height="400">
        </div>
        <div class="col-6">
            <div class="main-heading">
                <h3>Customer Reviews</h3>
            </div>
            <div id="carouselExampleDark" class="carousel carousel-dark slide" data-bs-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <p>"Amazing cocktails! A true delight for any cocktail lover! The selection of drinks is vast,
                            and each recipe is crafted with precision. I love how easy it is to follow the instructions,
                            and the results are always fantastic. This has become my go-to resource for both casual and
                            special occasions."</p>
                    </div>
                    <div class="carousel-item">
                        <p>"The best bartending tips and recipes in one place! Whether you're a professional bartender
                            or just mixing drinks at home, this platform has everything you need. The tutorials are
                            clear and concise, making it easy to learn new techniques. I have definitely improved my
                            cocktail-making skills!"</p>
                    </div>
                    <div class="carousel-item">
                        <p>"I found my favorite cocktail thanks to Bartender Assistant! Not only did I discover new
                            recipes, but I also learned a lot about ingredients and how to balance flavors. The detailed
                            explanations and tips make it fun and educational at the same time. So, I can mix drinks for
                            my friends & they love it!"</p>
                    </div>
                </div>
                <br>
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="0" class="active"
                            aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="1"
                            aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#carouselExampleDark" data-bs-slide-to="2"
                            aria-label="Slide 3"></button>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
