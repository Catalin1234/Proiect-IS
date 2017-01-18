<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta http-equiv="Cache-Control" content="no-store" />
    <title>Login | Endava Training Application</title>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900' rel='stylesheet' type='text/css'>
    <link href="${pageContext.request.contextPath}/resources/css/index.css" rel="stylesheet" >
</head>
<body>

    <div class="transbox">
    <div class="text" align="center" >Endava Training Application</div>
        <div class="img">
            <img src="https://danzahariadotbiz.files.wordpress.com/2015/05/endava_logo_jpg.png" width=100px>
        </div>
</div>

<div class="container">
    <form class="login-box animated fadeInUp" action="register.htm" method="POST">
        <div class="box-header">
            <h2>Log In</h2>
        </div>
        <label>Username</label>
        <br/>
        <input type="text" name="username" id="username" placeholder="Enter username" required="required" autofocus="autofocus"/>
        <br/>
        <label>Password</label>
        <br/>
        <input type="password" id="password" name="password" placeholder="Enter password" required="required"/>
        <br/>
        <br>
        <label>First Name</label>
        <input type="text" id="firstName" name="firstName" placeholder="Enter first name" required="required"/>
        </br>
        <br>
        <label>last Name</label>
        <input type="text" id="lastName" name="lastName" placeholder="Enter last name" required="required"/>
        </br>
        <input id="button" type="submit" name="register" value="register"/>
        <c:out value="${message}"/>
        <br/>
    </form>

</div>
</body>
</html>

