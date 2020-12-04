<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020. 12. 02.
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>

<html>
<head>
    <title>Módosítás</title>
</head>
<body>
<form:form method="get" action="${pageContext.servletContext.contextPath}/update/${film.id}" modelAttribute="film">
    <form:label path="cim">Cím</form:label>
        <form:input path="cim"/>
    <form:label path="megjelenes">Megjelenés</form:label>
         <form:input type="date" path="megjelenes"/>

    <form:label path="mufaj">Műfaj</form:label>
    <form:select path="mufaj">
        <form:options items="${mufajok}"/>
    </form:select>
    <form:label path="ertekeles">Értékelés</form:label>
        <form:input path="ertekeles"/>
    <input type="submit" value="Küldés"/>
</form:form>
</body>
</html>
