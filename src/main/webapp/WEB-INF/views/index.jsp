<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<center>
    <h1>ONLINE SHOP "SMEREKA"<br>${time}</h1>
    <br>
    <h2>Buttons for all people</h2>
    <form action="${pageContext.request.contextPath}/inject-data">
        <input type="submit" value="Inject data"><br></form>
    <form action="${pageContext.request.contextPath}/registration">
        <input type="submit" value="Registration"><br></form>
    <form action="${pageContext.request.contextPath}/logout">
        <input type="submit" value="Logout"><br></form>
    <br>
    <h2>Buttons for users</h2>
    <form action="${pageContext.request.contextPath}/shopping-cart">
        <input type="submit" value="See shopping-cart"><br></form>
    <form action="${pageContext.request.contextPath}/orders/all">
        <input type="submit" value="See all orders"><br></form>
    <form action="${pageContext.request.contextPath}/products/all">
        <input type="submit" value="See all products"><br></form>
    <br>
    <h2>Buttons for admin</h2>
    <form action="${pageContext.request.contextPath}/users/all">
        <input type="submit" value="See all users"><br></form>
    <form action="${pageContext.request.contextPath}/products/add">
        <input type="submit" value="Add product"><br></form>
    <form action="${pageContext.request.contextPath}/orders">
        <input type="submit" value="See all orders Admin"><br></form>
    <form action="${pageContext.request.contextPath}/products/manage">
        <input type="submit" value="Manage products Admin"><br></form>

</body>
</center>
</html>
