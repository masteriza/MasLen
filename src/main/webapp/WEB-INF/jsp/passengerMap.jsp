<%--<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>--%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to MASLEN (=</title>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="css/driverMap.css">
</head>
<body>
<div>
    <input type="text" id="address-input" size="50">
    <input type="text" id="address-destination-input" size="50">
</div>
<div id="map_canvas"></div>

<div id="panel" style="width: 300px; float: right;"></div>

<button id="searchRoute">Search route</button>
<button id="showSelectedRoutes">Show selected routes</button>

<br><br>
<div id="searched_routes"></div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCaIaBi2tNjoT4GRI8ZSwZ1DM-5C6S04RQ&libraries=places&callback=initMap"
        async defer></script>

<script src="js/passengerMap.js"></script>
</body>
</html>