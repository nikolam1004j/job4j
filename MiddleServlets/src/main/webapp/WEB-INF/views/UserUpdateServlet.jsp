<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>UserUpdateServlet</title>
</head>
<body>

<form action='${pageContext.servletContext.contextPath}/edit' method="post">
    id: ${user.id}<br>
    <input type="hidden" name="id" value='${user.id}'>
    <label for="name">Имя:</label>
    <input id="name" type="text" value='${user.name}' name="name"><br>
    <label for="login">Логин:</label>
    <input id="login" type="text" value='${user.login}' name="login"><br>
    <label for="email">Email:</label>
    <input id="email" type="text" value='${user.email}' name="email"><br>
    <input type="submit" value="Send">
</form>
</body>
</html>
