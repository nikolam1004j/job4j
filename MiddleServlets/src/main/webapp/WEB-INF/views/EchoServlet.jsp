<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>EchoServlet</title>
</head>
<body>
    <table border='1' cellpadding="1" cellspacing="1">
        <tr>
            <th>id</th>
            <th>namename</th>
            <th>login</th>
            <th>email</th>
            <th>create_date</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <c:if test="${error != null}">
            <p style='color: white; background: red; padding: 2px;'>${error}</p>
        </c:if>
        <c:forEach items="${users}" var="user">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.login}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.createDate}"/></td>
                <c:if test="${pageContext.session.getAttribute('login') == user.login ||
                    pageContext.session.getAttribute('role') == 'admin'}">
                    <td>
                        <form action='${pageContext.servletContext.contextPath}/edit' method='get'>
                            <input type='hidden' name='id' value='${user.id}' />
                            <input type='submit' value='Edit' />
                        </form>
                    </td>
                    <c:if test="${pageContext.session.getAttribute('role') == 'admin'}">
                        <td>
                            <form action='${pageContext.servletContext.contextPath}/delete' method='post'>
                                <input type='hidden' name='id' value='${user.id}' />
                                <input type='submit' value='Delete' />
                            </form>
                        </td>
                    </c:if>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
