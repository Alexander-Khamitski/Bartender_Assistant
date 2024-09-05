<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Create Cocktail</title>
</head>
<body>
<h2>${message}</h2>
<form:form action="/cocktail/create" method="post" modelAttribute="cocktail">
    <div>
        <form:label path="name"><h3>Enter name:</h3></form:label>
        <form:input path="name" type="text"  value="" />
    </div>
    <div>
        <form:label path="description"><h3>Enter description:</h3></form:label>
        <form:input path="description" type="text"  value="" />
    </div>
    <div>
        <button>Submit</button>
    </div>
</form:form>
</body>
</html>