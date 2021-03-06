<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User's shopping cart</title>
</head>
<body>
<h1>All products in shopping cart</h1>

<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Price</th>
        <th>Action</th>
    </tr>
    <c:forEach var="product" items="${products}">
        <tr>
            <td>
                <c:out value="${product.id}"/>
            </td>
            <td>
                <c:out value="${product.name}"/>
            </td>
            <td>
                <c:out value="${product.price}"/>
            </td>
            <td>
                <a href="${pageContext.request.contextPath}/shopping-cart/delete?id=${product.id}">delete</a>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<form action="${pageContext.request.contextPath}/">
    <input type="submit" value="Back to main page"><br></form>
<form action="${pageContext.request.contextPath}/products/all">
    <input type="submit" value="Continue to shopping"><br></form>
<form action="${pageContext.request.contextPath}/order/complete">
    <input type="submit" value="Create order"><br></form>
</body>
</html>
