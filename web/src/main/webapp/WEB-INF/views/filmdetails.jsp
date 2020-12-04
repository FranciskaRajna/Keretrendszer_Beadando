<%--
  Created by IntelliJ IDEA.
  User: Lenovo
  Date: 2020. 11. 27.
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>${film.id}</title>
</head>
<body>
<table>
    <tr><td>Cím:</td><td>${film.cim}</td></tr>
    <tr><td>Megjelenés dátuma:</td><td>${film.megjelenes}</td></tr>
    <tr><td>Műfaj:</td><td>${film.mufaj}</td></tr>
    <tr><td>Értékelés:</td><td>${film.ertekeles}</td></tr>

</table>
<form action="${pageContext.servletContext.contextPath}/">
    <input type="submit" value="Home">
</form>

</body>
</html>