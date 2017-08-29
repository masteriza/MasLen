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
<a href="logInq.html">
    <button type="button" class="btn btn-primary">Вход</button>
</a>
<a href="registration.html">
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
<br>
<br>
<br>
<br>
<h2>Регистрация</h2>
<form class="form-horizontal">
    <div class="form-group">
        <label class="control-label col-xs-3" for="lastName">Фамилия:</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="lastName" placeholder="Введите фамилию">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="firstName">Имя:</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="firstName" placeholder="Введите имя">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="fatherName">Отчество:</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="fatherName" placeholder="Введите отчество">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3">Дата рождения:</label>
        <div class="col-xs-3">
            <select class="form-control">
                <option>Дата</option>
            </select>
        </div>
        <div class="col-xs-3">
            <select class="form-control">
                <option>Месяц</option>
            </select>
        </div>
        <div class="col-xs-3">
            <select class="form-control">
                <option>Год</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="inputEmail">Email:</label>
        <div class="col-xs-9">
            <input type="email" class="form-control" id="inputEmail" placeholder="Email">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="inputPassword">Пароль:</label>
        <div class="col-xs-9">
            <input type="password" class="form-control" id="inputPassword" placeholder="Введите пароль">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="confirmPassword">Подтвердите пароль:</label>
        <div class="col-xs-9">
            <input type="password" class="form-control" id="confirmPassword" placeholder="Введите пароль ещё раз">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="phoneNumber">Телефон:</label>
        <div class="col-xs-9">
            <input type="tel" class="form-control" id="phoneNumber" placeholder="Введите номер телефона">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="postalAddress">Адрес:</label>
        <div class="col-xs-9">
            <textarea rows="3" class="form-control" id="postalAddress" placeholder="Введите адрес"></textarea>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3">Пол:</label>
        <div class="col-xs-2">
            <label class="radio-inline">
                <input type="radio" name="genderRadios" value="male"> Мужской
            </label>
        </div>
        <div class="col-xs-2">
            <label class="radio-inline">
                <input type="radio" name="genderRadios" value="female"> Женский
            </label>
        </div>
    </div>
    <div class="form-group">
        <div class="col-xs-offset-3 col-xs-9">
            <label class="checkbox-inline">
                <input type="checkbox" value="agree"> Я согласен с <a href="#">условиями</a>.
            </label>
        </div>
    </div>
    <br/>
    <div class="form-group">
        <div class="col-xs-offset-3 col-xs-9">
            <input type="submit" class="btn btn-primary" value="Регистрация">
            <input type="reset" class="btn btn-default" value="Очистить форму">
        </div>
    </div>

</form>
<p><label>E-mail</label><input id="email"></p>

<p><label>Password</label><input type="password" id="rawPassword"></p>

<p><label>Confirm password</label><input type="password" id="repeatRawPassword"></p>

<p><label>First name</label><input id="firstName"></p>

<p><label>Last name</label><input id="lastName"></p>

<p><label>Phone</label><input id="phone"></p>

<div id="btSingUp"><input type="button" value="Sign Up"></div>

<br>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/index.js"></script>

</body>
</html>