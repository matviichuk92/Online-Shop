<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Please, put goods! </title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/add">
    <label for="name">Name:</label><br>
    <input type="text" id="name" name="name"><br>
    <label for="price">Price:</label><br>
    <input type="text" id="price" name="price"><br><br>
    <input type="submit" value="Add">
</form>
</body>
</html>
