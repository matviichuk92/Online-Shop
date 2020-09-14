<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order   </title>
</head>
<body>
<h1>Thank you for order!</h1>
<br>
<form action="${pageContext.request.contextPath}/">
    <input type="submit" value="Back to main page"><br></form>
<form action="${pageContext.request.contextPath}/product/all">
    <input type="submit" value="Continue to shopping"><br></form>
<form action="${pageContext.request.contextPath}/shopping-cart">
    <input type="submit" value="Back to shopping-cart"><br></form>
<form action="${pageContext.request.contextPath}/order/details">
    <input type="submit" value="See details order"><br></form>
</body>
</html>
