<%@ page import="servlets.ValidateService" %>
<%@ page import="models.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserUpdateServlet</title>
</head>
<body>
<%
    final ValidateService validateService = ValidateService.newInstance();
    int id = Integer.parseInt(request.getParameter("id"));
    User user = validateService.findById(new User(id));
%>
<form action='<%=String.format("%s/edit", request.getContextPath())%>' method="post">
    <%=String.format("id: %d", id)%><br>
    <input type="hidden" name="id" value='<%=user.getId()%>'>
    <label for="name">Имя:</label>
    <input id="name" type="text" value='<%=user.getName()%>' name="name"><br>
    <label for="login">Логин:</label>
    <input id="login" type="text" value='<%=user.getLogin()%>' name="login"><br>
    <label for="email">Email:</label>
    <input id="email" type="text" value='<%=user.getEmail()%>' name="email"><br>
    <input type="submit" value="Send">
</form>
</body>
</html>
