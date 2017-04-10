<%--
  Created by IntelliJ IDEA.
  User: Punitha Anandan
  Date: 4/4/2017
  Time: 7:45 PM
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

    <form method="get" action="http://localhost:8080/pricerequest/HTML/items">
    <%--<form method="get" action="http://52.14.136.34:8080/pricecheck/pricerequest/HTML/items">--%>
        <input type="submit" value="All Items in HTML">
    </form>


</div>
<div>
    <h4>Get all Stores</h4>

    <form method="get" action="http://localhost:8080/pricerequest/HTML/stores">
    <%--<form method="get" action="http://52.14.136.34:8080/pricecheck/pricerequest/HTML/stores">--%>
        <input type="submit" value="All Stores in HTML">
    </form>

</div>
<div>
    <h4>Get All Brands</h4>

    <form method="get" action="http://localhost:8080/pricerequest/HTML/brands">
    <%--<form method="get" action="http://52.14.136.34:8080/pricecheck/pricerequest/HTML/brands">--%>
        <input type="submit" value="All Brands in HTML">
    </form>

</div>

</body>
</html>
