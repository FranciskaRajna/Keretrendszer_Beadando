<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020. 12. 01.
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Id alapú keresés</title>
</head>
<body>

<form:form method="get" action="search" modelAttribute="film">
    <form:label path="id" name ="id">Id</form:label>
    <form:input  path="id"/>
</form:form>

<form action="${pageContext.servletContext.contextPath}/film/${id}">
    <input    type="submit" value="Keresés">
</form>
</body>
</html>
