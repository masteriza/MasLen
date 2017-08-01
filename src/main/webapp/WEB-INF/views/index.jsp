<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Welcome to MASLEN (=</title>
</head>
<body>
<form:form action="userLogin" method="post" commandName="user">
    <table>
        <tr>
            Sing in
        </tr>
        <tr>
            <td>E-mail:</td>
            <td><form:input placeholder="E-mail" path="email"/></td>
            <td><form:errors path="email"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><form:password placeholder="Password" path="password"/></td>
            <td><form:errors path="password"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Sing in"/></td>
        </tr>
    </table>
</form:form>


<form:form action="user" method="post" commandName="user">
    <table>
        <tr>
            Registration new user
        </tr>
        <tr>
            <td>E-mail:</td>
            <td><form:input placeholder="E-mail" path="email"/></td>
            <td><form:errors path="email"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><form:password placeholder="Password" path="password"/></td>
            <td><form:errors path="password"/></td>
        </tr>
        <tr>
            <td>Repeat password:</td>
            <td><form:password placeholder="Confirm password" path="repeatPassword"/></td>
            <td><form:errors path="repeatPassword"/></td>
        </tr>
        <tr>
            <td>First name:</td>
            <td><form:input placeholder="First name" path="firstName"/></td>
            <td><form:errors path="firstName"/></td>
        </tr>
        <tr>
            <td>Last name:</td>
            <td><form:input placeholder="Last name" path="lastName"/></td>
            <td><form:errors path="lastName"/></td>
        </tr>
        <tr>
            <td>Phone:</td>
            <td><form:input placeholder="Phone" path="phone"/></td>
            <td><form:errors path="phone"/></td>
        </tr>
        <tr>
            <td><input type="submit" value="Sing up"/></td>
        </tr>
    </table>
</form:form>
</body>
</html>