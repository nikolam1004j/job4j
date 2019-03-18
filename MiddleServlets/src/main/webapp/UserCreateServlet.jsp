<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserCreateServlet</title>
</head>
<body>
<form action='<%=String.format("%s/create", request.getContextPath())%>' method="post">
    Добавление новой записи:<br>
    <label for="name">Имя:</label>
    <input id="name" type="text" name="name"><br>
    <label for="login">Логин:</label>
    <input id="login" type="text" name="login"><br>
    <label for="email">Email:</label>
    <input id="email" type="text" name="email"><br>
    <input type="submit" value="Send">
</form>
</body>
</html>