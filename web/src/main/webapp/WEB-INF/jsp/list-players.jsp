<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Список игроков</title>
</head>
<body>
<h2>Игроки</h2>
<h3>Фильтр: </h3>
<form action="${pageContext.request.contextPath}/list-players" method="post" accept-charset="UTF-8">
    <table border="1" cellspacing="0" cellpadding="2">
        <tr>
            <td><strong>Позиция: </strong></td>
            <td><strong>Команда: </strong></td>
            <td><strong>Возраст: </strong></td>
            <td><strong>Отобразить: </strong></td>
        </tr>
        <tr>
            <td>
                <select name="position">
                    <c:forEach var="position" items="${requestScope.positions}">
                        <option value="${position.name}">${position.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td>
                <input type="radio" name="team" value="All" checked/> Все
                <input type="radio" name="team" value="noTeam"> Без команды
            </td>
            <td>
                от <input required type="number" name="ageFrom">
                до <input required type="number" name="ageTo">
            </td>
            <td>
                строк на странице <input required type="number" name="limit" size="5">
                страница № <input required type="number" name="page" size="5">
            </td>
    </table>
    <small>
        <input type="submit" name="ОК" value="ОК">
    </small>
</form>
<p></p>
<%@ include file="list-players-player.jsp" %>
</body>
</html>
