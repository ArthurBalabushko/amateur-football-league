<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Игрок</title>
</head>
<body>
<h2>Информация об игроке</h2>
<table border="1" cellspacing="0" cellpadding="10">
    <tr>
        <td><strong>Имя</strong></td>
        <td><strong>Фамилия</strong></td>
        <td><strong>Дата рождения</strong></td>
        <td><strong>Номер телефона</strong></td>
        <td><strong>e-mail</strong></td>
    </tr>
    <tr>
        <td>${requestScope.player.firstName}</td>
        <td>${requestScope.player.lastName}</td>
        <td>${requestScope.player.birthDay}</td>
        <td>${requestScope.player.phoneNumber}</td>
        <td>${requestScope.player.email}</td>
    </tr>
</table>
</body>
</html>
