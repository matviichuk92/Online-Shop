
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Hello admin</h1>
<form action="${pageContext.request.contextPath}/">
    <input type="submit" value="Back to main page"><br></form>
<form action="${pageContext.request.contextPath}/admin/order">
    <input type="submit" value="See all orders"><br></form>
<form action="${pageContext.request.contextPath}/admin/product">
    <input type="submit" value="Delete some product"><br></form>
</body>
</html>
