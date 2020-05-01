<%@ page import="DAO.UserDAO" %><%--
  Created by IntelliJ IDEA.
  User: LAW
  Date: 30.04.2020
  Time: 18:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="DAO.UserDAO" %>
<html>
<head>
    <title>StartPage</title>
</head>
<body>
<h1>CRUD</h1>
<h2>Создание нового пользователя</h2><br/>

<form method="post" action="">

    <label><input type="text" name="name"></label>Имя<br>

    <label><input type="text" name="surname"></label>Фамилия<br>

    <input type="submit" value="Ok" name="Ok"><br>
</form>

<div class="space">
    <h2>Все пользователи</h2><br/>

    <%@ page import="service.UserService" %>
    <%@ page import="model.User" %>


    <ul>
        <%
            for (User user : UserService.getInstance().allUser()) {
                out.println("<p>" + user + "</p>");
            }
        %>
    </ul>



</div>


</body>
</html>
