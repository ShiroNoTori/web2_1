<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Index</title>
</head>
<body>

<div style="width: 80%;" align="center">
<a href="/admin/userAdd">Add new user</a>
<br>

<table border="1" width="80%">
    <tbody>
    <tr>
        <td width="10%">id</td>
        <td width="20%">login</td>
        <td width="20%">name</td>
        <td width="20%">password</td>
        <td width="10%">role</td>
        <td width="10%">update</td>
        <td width="10%">delete</td>
    </tr>
    <jsp:useBean id="userList" scope="request" type="java.util.List"/>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td>${user.getId()}</td>
            <td>${user.getLogin()}</td>
            <td>${user.getName()}</td>
            <td>${user.getPassword()}</td>
            <td>${user.getRole()}</td>
            <td>
                <a href="/admin/userUpdate?id=${user.getId()}">
                    <input type="button" value="update" style="width: 100%; height: 100%;">
                </a>
            </td>
            <td>
                <a href="/admin/userDelete?id=${user.getId()}">
                    <input type="button" value="delete" style="width: 100%; height: 100%;">
                </a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</div>

</body>
</html>