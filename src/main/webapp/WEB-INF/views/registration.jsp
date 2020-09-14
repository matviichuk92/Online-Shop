<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h4 style="color: crimson">${message}</h4>
<h1>Hello! Please provide your users details</h1>
<form method="post" action="${pageContext.request.contextPath}/registration">
    <label for="username">Name:</label><br>
    <input type="text" id="username" name="username"><br>
    <label for="login">Login:</label><br>
    <input type="text" id="login" name="login"><br>
    <label for="pwd">Password:</label><br>
    <input type="password" id="pwd" name="pwd"><br>
    <label for="pwd-repeat">Repeat password:</label><br>
    <input type="password" id="pwd-repeat" name="pwd-repeat"><br><br>
    <input type="submit" value="Register">
</form>
<br>
</body>
</html>
