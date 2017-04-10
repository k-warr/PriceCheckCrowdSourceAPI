<%--
  Created by IntelliJ IDEA.
  User: Punitha Anandan
  Date: 4/4/2017
  Time: 8:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create New Item</title>
</head>
<body>
<div>
    <h4>Get all items</h4>
    <form method="get" action="http://localhost:8080/pricerequest/JSON/items">
    <%--<form method="get" action="http://52.14.136.34:8080/pricecheck/pricerequest/JSON/items">--%>
        <input type="submit" value="All Items in JSON">
    </form>
</div>
<div>
    <h4>Get all Stores</h4>
    <form method="get" action="http://localhost:8080/pricerequest/JSON/stores">
    <%--<form method="get" action="http://52.14.136.34:8080/pricecheck/pricerequest/JSON/stores">--%>
        <input type="submit" value="All Stores in JSON">
    </form>
</div>
<div>
    <h4>Get All Brands</h4>
    <form method="get" action="http://localhost:8080/pricerequest/JSON/brands">
    <%--<form method="get" action="http://52.14.136.34:8080/pricecheck/pricerequest/JSON/brands">--%>
        <input type="submit" value="All Brands in JSON">
    </form>
</div>
</body>
</html>
