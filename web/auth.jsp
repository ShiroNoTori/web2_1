<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Authorization</title>
</head>
<body>

    <form method="post" action="/auth">
        <input type="text" name="login">
        <input type="password" name="password">
        <input type="submit" value="login">
    </form>

</body>
</html>
