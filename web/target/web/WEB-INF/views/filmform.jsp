<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020. 11. 27.
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Film hozzáadása</title>
</head>
<body>
<form:form method="post" action="addFilm" modelAttribute="film">
    <form:label path="cim">Cím</form:label>
    <form:input path="cim"/>
    <form:label path="megjelenes">Megjelenés dátuma</form:label>
    <form:input type="date" path="megjelenes"/>
    <form:label path="mufaj">Műfaja</form:label>
    <form:select path="mufaj">
        <form:options items="${mufajok}"/>
    </form:select>
    <form:label path="ertekeles">Értékelés</form:label>
    <form:input path="ertekeles"/>
    <input type="submit" value="Hozzáadás"/>
</form:form>
</body>
</html>

