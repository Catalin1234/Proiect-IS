<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ctimbus
  Date: 8/10/2016
  Time: 12:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900' rel='stylesheet' type='text/css'>
    <link href="${pageContext.request.contextPath}/resources/css/employee.css" rel="stylesheet">
    <title>Registration Status</title>
</head>
<body>

<div class="transbox">
    <div class="text" align="center" >Registration</div>
    <div class="img">
        <img src="https://danzahariadotbiz.files.wordpress.com/2015/05/endava_logo_jpg.png" width=100px>
    </div>
    <div>
        <form action="login.htm" method="GET">
            <input id="logoutButton" type="submit" name="logout" value="Logout"/><br>
        </form>
    </div>
    <div id="navbarName"> <c:out value="${name}"/></div>
</div>

<div align="center">
    <br>
    You are already registered.
</div>

</body>
</html>
