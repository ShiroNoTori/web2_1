<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UserAdd</title>
</head>
<body>

<form action="/_web_war_exploded/userAdd" method="post">
    <label>
        Login
        <input type="text" name="login"/>
    </label>
    <br>
    <label>
        Name
        <input type="text" name="name"/>
    </label>
    <br>
    <label>
        Password
        <input type="text" name="password"/>
    </label>
    <input type="submit" value="addUser">
</form>

</body>
</html>