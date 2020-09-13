<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All orders</title>
</head>
<body>
<h1>All orders</h1>
<table border="1">
    <tr>
        <th>User ID</th>
        <th>Order ID</th>
        <th>List products</th>
        <th>Action</th>

        <c:forEach var="order" items="${orders}">
    <tr>
        <td>
            <c:out value="${order.userId}"/>
        </td>
        <td>
            <c:out value="${order.id}"/>
        </td>
        <td>
            <c:out value="${order.products}"/>
        </td>
    <td>
        <a href="${pageContext.request.contextPath}/admin-order/delete?id=${order.id}">delete</a>
    </td>
    </tr>
    </c:forEach>
</table>
<br>
<form action="${pageContext.request.contextPath}/admin">
    <input type="submit" value="Back admin page"><br></form>
</body>
</html>
