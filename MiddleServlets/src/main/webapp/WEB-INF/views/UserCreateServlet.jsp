<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>UserCreateServlet</title>
</head>
<body>
    <c:if test="${error != null}">
        <p style='color: white; background: red; padding: 2px;'>${error}</p>
    </c:if>
    <c:if test="${error == null}">
        <form action='${pageContext.servletContext.contextPath}/create' method="post">
            Добавление новой записи:<br>
            <label for="name">Имя:</label>
            <input id="name" type="text" name="name"><br>
            <label for="login">Логин:</label>
            <input id="login" type="text" name="login"><br>
            <label for="email">Email:</label>
            <input id="email" type="text" name="email"><br>
            <input type="submit" value="Send">
        </form>
    </c:if>
</body>
</html>