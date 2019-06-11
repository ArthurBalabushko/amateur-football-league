<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список игроков</title>
</head>
<body>
<c:if test="${requestScope.players ne null}">
    <table border="1" cellspacing="0" cellpadding="2">
        <tr>
            <td><strong>Фамилия, Имя</strong></td>
            <td><strong>Дата рождения</strong></td>
            <td><strong>Позиция на поле</strong></td>
            <td><strong>Рост</strong></td>
            <td><strong>Вес</strong></td>
            <td><strong>Команда</strong></td>
        </tr>
        <c:forEach items="${requestScope.players}" var="player">
            <tr>
                <td>${player.lastName} ${player.firstName}</td>
                <td>${player.birthDay}</td>
                <td>${player.position}</td>
                <td>${player.growth}, см</td>
                <td>${player.weight}, кг</td>
                <td>
                    <c:if test="${player.team ne null}">
                        ${player.team}
                    </c:if>
                    <c:if test="${player.team eq null}">
                        нет команды
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
