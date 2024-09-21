<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
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
        <!-- Login and registration buttons -->
        <div class="d-flex">
            <sec:authorize access="!isAuthenticated()">
                <a class="btn btn-lg me-2" href="/signin">Sign In</a>
                <a class="btn btn-lg" href="/registration">Join Now</a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <a class="btn btn-lg" href="/logout">Log Out</a>
            </sec:authorize>
        </div>
    </div>
</nav>
</body>
