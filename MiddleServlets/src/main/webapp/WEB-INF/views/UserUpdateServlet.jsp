<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>UserUpdateServlet</title>
</head>
<body>
    <c:if test="${error != null}">
        <p style='color: white; background: red; padding: 2px;'>${error}</p>
    </c:if>
    <c:if test="${error == null}">
        <form action='${pageContext.servletContext.contextPath}/edit' method="post">
            id: ${user.id}<br>
            <input type="hidden" name="id" value='${user.id}'>
            <label for="name">Имя:</label>
            <input id="name" type="text" value='${user.name}' name="name"><br>
            <label for="login">Логин:</label>
            <input id="login" type="text" value='${user.login}' name="login"><br>
            <label for="email">Email:</label>
            <input id="email" type="text" value='${user.email}' name="email"><br>
            <c:if test="${pageContext.session.getAttribute('role') == 'admin'}">
                <label for="roles">Роль:</label>
                <select id="roles" name="roles">
                    <c:forEach var="role" items="${roles}">
                        <option value="${role.id}">${role.name}</option>
                    </c:forEach>
                </select><br />
            </c:if>
            <input type="submit" value="Send">
        </form>
    </c:if>
</body>
</html>