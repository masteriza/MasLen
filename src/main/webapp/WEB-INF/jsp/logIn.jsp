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
<a href="/logIn">
    <button type="button" class="btn btn-primary">Вход</button>
</a>
<a href="/registration">
    <button type="button" class="btn btn-info">Регистрация</button>
</a>


<div class="container">
    <form class="form-signin" role="form">
        <h2 class="form-signin-heading">Please sign in</h2>
        <input type="email" class="form-control" placeholder="Email address" required autofocus>
        <input type="password" class="form-control" placeholder="Password" required>
        <label class="checkbox">
            <input type="checkbox" value="remember-me"> Remember me
        </label>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>
    </form>
</div>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/index.js"></script>

</body>
</html>