<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

<div align="center">
    <h2><s:message code="login.header"/></h2><br>

    <c:if test="${param.error != null}">
        <h3><span class="yellow"><s:message code="login.userNotFound"/></span></h3>
    </c:if>

    <sf:form action="/logIn" method="post">
        <table>
            <tr>
                <td>
                    <label for="username" class="cellWidth"><s:message code="reg.username"/>:</label>
                    <c:if test="${param.error == null}">
                        <input type="text" name="username" id="username" class="black"/>
                    </c:if>
                    <c:if test="${param.error != null}">
                        <input type="text" name="username" id="username" class="errorField black"/>
                    </c:if><br>

                    <label for="password" class="cellWidth"><s:message code="reg.password"/>:</label>
                    <c:if test="${param.error == null}">
                        <input type="password" name="password" id="password" class="black"/>
                    </c:if>
                    <c:if test="${param.error != null}">
                        <input type="password" name="password" id="password" class="errorField black"/>
                    </c:if><br>

                    <label for="remember_me" class="cellWidth"><s:message code="login.rememberMe"/>:</label>
                    <input id="remember_me" name="remember-me" type="checkbox"/><br>
                </td>
            </tr>
            <tr>
                <td align="left" colspan="2"><br>
                    <input type="submit" value="<s:message code="index.login"/>" class="black button150"></td>
            </tr>
        </table>
    </sf:form>

    <br>
    <a href="javascript:history.back();"><s:message code="index.back"/></a> |
    <a href="<c:url value="/" />"><s:message code="index.home"/></a>
</div>

</body>
</html>
