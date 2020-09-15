<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h1>Login page</h1>
<h4 style="color: crimson">${errorMessage}</h4>
<form method="post" action="${pageContext.request.contextPath}/login">
    <label for="login">Login:</label><br>
    <input type="text" id="login" name="login"><br>
    <label for="pwd">Password:</label><br>
    <input type="password" id="pwd" name="pwd"><br>
    <input type="submit" value="Login">
</form>
<br>
<form action="${pageContext.request.contextPath}/registration">
    <input type="submit" value="Registration!"><br></form>
</body>
</html>
