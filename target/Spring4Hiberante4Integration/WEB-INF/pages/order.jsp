<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
    <title>订单列表</title>
</head>

<body>
<h2>web frontend</h2>

<table border="1">
    <tr>
        <th>Id</th>
        <th>product_id</th>
        <th>productName</th>
        <th>appOrderId</th>
        <th>payType</th>
        <th>openuid</th>
        <th>order_id</th>
        <th>time</th>
    </tr>

    <c:forEach items="${orders}" var="order">
        <tr>
            <td>${order.id}</td>
            <td>${order.productId}</td>
            <td>${order.productName}</td>
            <td>${order.appOrderId}</td>
            <td>${order.payType}</td>
            <td>${order.openuid}</td>
            <td>${order.order_id}</td>
            <%--<td><fmt:formatDate value='${order.time}' pattern='yyyy-MM-dd'/></td>--%>
        </tr>
    </c:forEach>
</table>

</body>
</html>