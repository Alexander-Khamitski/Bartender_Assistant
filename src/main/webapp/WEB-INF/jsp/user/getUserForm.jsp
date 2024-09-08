<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Get User</title>
</head>
<body>
<h2>${message}</h2>
<form action="/user/get" method="get">
    <div>
        <label for="id"><h3>Enter ID:</h3></label>
        <input type="number" id="id" name="id" />
    </div>
    <div>
        <button type="submit">Submit</button>
    </div>
</form>
</body>
</html>