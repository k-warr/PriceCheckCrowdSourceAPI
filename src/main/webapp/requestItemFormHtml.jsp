<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 4/3/17
  Time: 9:55 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Request Item Form</title>
</head>
<body>
<div>
    <h3>Working examples:</h3>
    <table>
        <th>Item Name</th><th>Brand Name</th><th>Lat</th><th>Long</th><th>Distance</th>
        <tr><td>Ketchup</td><td>Huntz</td><td>43.183093000</td><td>-89.213428000</td><td>10</td></tr>
    </table>
</div>
<br /><br />
<form method="GET" action="http://localhost:8080/pricerequest/HTML/request">
<%--<form method="GET" action="http://52.14.136.34:8080/pricecheck/pricerequest/HTML/request">--%>
    <fieldset>
        <legend>Item Information</legend>
        <label for="name">Item Name: </label>
        <input id="name" type="text" name="name" />
        <label for="brand">Brand: </label>
        <input type="text" id="brand" name="brand" />
    </fieldset>
    <fieldset>
        <legend>User Location Information</legend>
        <label for="lat">Latitude (deg): </label>
        <input type="text" id="lat" name="lat"/>
        <label for="lon">Longitude (deg): </label>
        <input type="text" id="lon" name="lon" />
        <label for="distance">Radius (mi): </label>
        <input type="text" id="distance" name="distance" />
    </fieldset>
    <input type="submit" value="Submit"/>
</form>
</body>
</html>
<style>
    table, tr, th, td {
        border: .1em solid black;
    }
</style>
