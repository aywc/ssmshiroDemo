<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/15
  Time: 10:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
</head>
<body>
<center>
    <form action="/login" method="post">
        <div>
            账号：<input type="text" name="username">
        </div>
        <div>
            密码：<input type="password" name="password">
        </div>
        <div>
            <input type="submit" value="登入">
        </div>
    </form>
</center>
</body>
</html>
