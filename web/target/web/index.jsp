<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@page isELIgnored="false"%>
<html>
<body>
<h2>Üdvözlöm! Válasszon a lehetőségek közül!</h2>
<form action="${pageContext.servletContext.contextPath}/filmek">
    <input type="submit" value="Filmek listázása">
</form>

<form action="${pageContext.servletContext.contextPath}/addFilm">
    <input type="submit" value="Film hozzáadása">
</form>

<form action="${pageContext.servletContext.contextPath}/search">
    <input type="submit" value="Keresés">
</form>

<form action="${pageContext.servletContext.contextPath}/listByMufaj">
    <input type="submit" value="Keresés műfaj alapján">
</form>

</body>
</html>
