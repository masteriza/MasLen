<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome to MASLEN (=</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/logIn.css" rel="stylesheet">
</head>
<body>

Security zone

<a href="/driverMap">
    <button type="button" class="btn btn-primary">Водитель</button>
</a>
<a href="/logOut">
    <button type="button" class="btn btn-danger">Выход, пля!</button>
</a>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<%--<script src="/js/login.js"></script>--%>

</body>
</html>