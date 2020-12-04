<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020. 12. 02.
  Time: 19:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form:form method="get" action="${pageContext.servletContext.contextPath}/listFilmByMufaj" modelAttribute="film">
    <form:label path="mufaj">Kirendeltség</form:label>
    <form:select path="mufaj">
        <form:options items="${mufajok}"/>
    </form:select>
    <input type="submit" value="Szűrés"/>
</form:form>
<c:if test="${!empty filmek}">
    <table frame="border" rules="all">
        <tr><th>Azonosító</th><th>Cím</th><th>Megjelenés dátuma</th><th>Műfaj</th><th>Értékelés</th></tr>
        <c:forEach items="${filmek}" var="film">
            <tr><td><a href="${pageContext.servletContext.contextPath}/film/${film.id}">${film.id}</a></td>
                <td>${film.cim}</td>
                <td>${film.megjelenes}</td>
                <td>${film.mufaj}</td>
                <td>${film.ertekeles}</td>
                <td>
                    <form action="${pageContext.servletContext.contextPath}/edit/${film.id}">
                        <input type="submit" value="Szerkesztés">
                    </form>
                    <form action="${pageContext.servletContext.contextPath}/delete/${film.id}">
                        <input type="submit" value="Törlés">
                    </form>
                </td>

            </tr>
        </c:forEach>
    </table>
</c:if>
<c:if test="${empty filmek}">
    <c:out value="Nincs film felvéve"/></c:if>

<form action="${pageContext.servletContext.contextPath}/filmek">
    <input type="submit" value="Vissza">
</form>
</body>
</html>
