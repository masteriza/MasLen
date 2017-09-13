<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Welcome to MASLEN (=</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/bootstrap.min.css.map" rel="stylesheet">
    <link href="css/logIn.css" rel="stylesheet">
</head>
<body>

<h2>Регистрация</h2>
<!--<form class="form-horizontal" method="post">-->
<form class="form-horizontal">
    <div class="form-group">
        <label class="control-label col-xs-3" for="lastName">Фамилия:</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="lastName" placeholder="Введите фамилию"
                   required/><span class="errorspan">1111</span>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="firstName">Имя:</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="firstName" placeholder="Введите имя" required><span
                class="errorspan">1111</span>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="middleName">Отчество:</label>
        <div class="col-xs-9">
            <input type="text" class="form-control" id="middleName" placeholder="Введите отчество"
                   required><span class="errorspan">1111</span>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3">Дата рождения:</label>
        <div id="birthday">
            <div class="col-xs-3">
                <select id="birthday_Day" class="form-control" required>
                    <option disabled selected>Дата</option>
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                    <option>6</option>
                    <option>7</option>
                    <option>8</option>
                    <option>9</option>
                    <option>10</option>
                    <option>11</option>
                    <option>12</option>
                    <option>13</option>
                    <option>14</option>
                    <option>15</option>
                    <option>16</option>
                    <option>17</option>
                    <option>18</option>
                    <option>19</option>
                    <option>20</option>
                    <option>21</option>
                    <option>22</option>
                    <option>23</option>
                    <option>24</option>
                    <option>25</option>
                    <option>26</option>
                    <option>27</option>
                    <option>28</option>
                    <option>29</option>
                    <option>30</option>
                    <option>31</option>
                </select>
            </div>
            <div class="col-xs-3">
                <select id="birthday_Month" class="form-control" required>
                    <option disabled selected>Месяц</option>
                    <option value="0">Январь</option>
                    <option value="1">Февраль</option>
                    <option value="2">Март</option>
                    <option value="3">Апрель</option>
                    <option value="4">Май</option>
                    <option value="5">Июнь</option>
                    <option value="6">Июль</option>
                    <option value="7">Август</option>
                    <option value="8">Сентябрь</option>
                    <option value="9">Октябрь</option>
                    <option value="10">Ноябрь</option>
                    <option value="11">Декабрь</option>
                </select>
            </div>
            <div class="col-xs-3">
                <select id="birthday_Year" class="form-control" required>
                    <option disabled selected>Год</option>
                    <option>2017</option>
                    <option>2016</option>
                    <option>2015</option>
                    <option>2014</option>
                </select>
            </div>
            <span class="errorspan">1111</span>
        </div>

    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="email">Email:</label>
        <div class="col-xs-9">
            <input id="email" type="email" class="form-control" placeholder="Email" required><span
                class="errorspan">1111</span>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="rawPassword">Пароль:</label>
        <div class="col-xs-9">
            <input id="rawPassword" type="password" class="form-control" placeholder="Введите пароль" required><span
                class="errorspan">1111</span>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="repeatRawPassword">Подтвердите пароль:</label>
        <div class="col-xs-9">
            <input id="repeatRawPassword" type="password" class="form-control" placeholder="Введите пароль ещё раз"
                   required><span class="errorspan">1111</span>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-xs-3" for="phone">Телефон:</label>
        <div class="col-xs-9">
            <input id="phone" class="form-control" type="tel" placeholder="+38(___) ___ __ __" required><span
                class="errorspan">Ошибка. Проверьте номер телефона!</span>
        </div>
    </div>
    <div class="form-group">
        <div id="gender">
            <label class="control-label col-xs-3">Пол:</label>
            <div class="col-xs-2">
                <label class="radio-inline">
                    <input type="radio" name="genderRadios" value="M" required> Мужской
                </label>
            </div>
            <div class="col-xs-2">
                <label class="radio-inline">
                    <input type="radio" name="genderRadios" value="W"> Женский
                </label>
            </div>
        </div>
        <span class="errorspan">1111</span>
    </div>
    <div class="form-group">
        <div class="col-xs-offset-3 col-xs-9">
            <label class="checkbox-inline">
                <input id="agree" type="checkbox" required> Я согласен с <a href="#">условиями</a>.<span
                    class="errorspan">1111</span>
            </label>
        </div>
    </div>
    <br/>
    <div class="form-group">
        <div class="col-xs-offset-3 col-xs-9">
            <input id="btnRegistration" type="button" class="btn btn-primary" value="Регистрация">
            <input type="reset" class="btn btn-default" value="Очистить форму">
        </div>
    </div>
</form>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="/js/registration.js"></script>
</body>
</html>