<%@ page import="servlets.ValidateService" %>
<%@ page import="models.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EchoServlet</title>
</head>
<body>
 <%
     final ValidateService validateService = ValidateService.newInstance();
     List<User> users = validateService.findAll();
 %>
    <table border='1'>
        <tr>
            <th>id</th>
            <th>namename</th>
            <th>login</th>
            <th>email</th>
            <th>create_date</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>
        <% for (User user : users) { %>
        <tr>
            <td><%=user.getId()%></td>
            <td><%=user.getName()%></td>
            <td><%=user.getLogin()%></td>
            <td><%=user.getEmail()%></td>
            <td><%=user.getCreateDate()%></td>
            <td><form action='<%=String.format("%s/UserUpdateServlet.jsp", request.getContextPath())%>' method='get'>
                <input type='hidden' name='id' value='<%=user.getId()%>' /><input type='submit' value='Edit' /></form>
            </td>
            <td><form action='<%=String.format("%s/list", request.getContextPath())%>' method='post'>
                <input type='hidden' name='id' value='<%=user.getId()%>' /><input type='submit' value='Delete' /></form>
            </td>
        </tr>
        <% } %>
    </table>

</body>
</html>
