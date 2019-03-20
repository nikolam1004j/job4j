<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SignInServlet</title>
</head>
<body>
    <c:if test="${error != null}">
        <p style='color: white; background: red; padding: 2px;'>${error}</p>
    </c:if>
    <form action="${pageContext.servletContext.contextPath}/signin" method="post">
        <label for="login">Логин:</label><br />
        <input type="text" id="login" name="login"/><br />
        <label for="pass">Пароль:</label><br />
        <input type="password" id="pass" name="pass" maxlength="25" /><br />
        <input type="submit" value="Sign In" />
    </form>
</body>
</html>
