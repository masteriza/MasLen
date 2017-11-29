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

<div class="container">
    <form id="form-login" class="form-signin" role="form" action="/restorePassword" method="post">
        <h2 class="form-signin-heading">Restore password</h2>
        <input id="email" name="username" type="email" class="form-control" placeholder="Email address" required
               autofocus>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Restore</button>
    </form>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<%--<script src="/js/login.js"></script>--%>

</body>
</html>