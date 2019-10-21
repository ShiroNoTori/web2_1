<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>userUpdate</title>
</head>
<body>
    <jsp:useBean id="user" scope="request" type="model.User"/>
    <form action="/_web_war_exploded/userUpdate" method="post">
        <input type="hidden" name="id" value="${user.id}">
        <label>
            Name
            <input type="text" name="name" value="${user.name}"/>
        </label>
        <label>
            Password
            <input type="text" name="password" value="${user.password}"/>
        </label>
        <input type="submit" value="saveChanges">
    </form>
</body>
</html>
