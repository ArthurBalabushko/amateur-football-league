<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Пользователь</title>
</head>
<body>
<h2>Информация о пользователе</h2>
<table border="1" cellspacing="0" cellpadding="10">
    <tr>
        <td><strong>Имя</strong></td>
        <td><strong>Фамилия</strong></td>
        <td><strong>Дата рождения</strong></td>
        <td><strong>Номер телефона</strong></td>
        <td><strong>e-mail</strong></td>
    </tr>
    <tr>
        <td>${requestScope.user.firstName}</td>
        <td>${requestScope.user.lastName}</td>
        <td>${requestScope.user.birthDay}</td>
        <td>${requestScope.user.phoneNumber}</td>
        <td>${requestScope.user.email}</td>
    </tr>
</table>
</body>
</html>
