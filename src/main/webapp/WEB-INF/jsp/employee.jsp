<%--
  Created by IntelliJ IDEA.
  User: ctimbus
  Date: 8/5/2016
  Time: 1:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Registration</title>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900' rel='stylesheet' type='text/css'>
    <link href="${pageContext.request.contextPath}/resources/css/employee.css" rel="stylesheet">
    <script src="http://code.jquery.com/jquery-latest.min.js"
            type="text/javascript"></script>
    <script type="text/javascript">
        function changeValues() {
            var e = document.getElementById("tddl");
            var requiredTraining = e.options[e.selectedIndex].value;
            var selectedTraining = $("#tddl option:selected").text();
            console.log(selectedTraining);
            document.getElementById("trainingName").value = selectedTraining;
            $.get("getTraining.htm?trainingName=" + selectedTraining, function (data, status) {
                $("#trainings").html(data);
            });
        }

        function collectRating() {
            checkboxes = document.getElementsByName('rating');
            var rate = 0;
            for(var i=0, n=checkboxes.length;i<n;i++) {
                if(checkboxes[i].checked()) {
                    rate = checkboxes[i].value;
                }
            }

            alert(rate);
        }
    </script>
</head>


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

<div id="selectTraining">
    <form action="employee.htm" method="GET">
        <label id="label">Select from the following trainings</label>
        <select id="tddl" name="tddl" onchange="changeValues();">
            <c:set var="count" value="0" scope="page"/>
            <c:forEach items="${list}" var="training">
                <c:set var="count" value="${count + 1}" scope="page"/>
                <option id="option" value="count">${training.trainingName}</option>
            </c:forEach>
        </select>
        </br>
    </form>
</div>

<div id="trainings"></div>

<div id="rateTraining">
    <form  action= "employee.htm" method="post">
        <p>
            Rating:
            <span class="starRating">
                <br>
        <input id="rating1" type="radio" name="rating" value="1">
        <label for="rating1">1</label>
        <input id="rating2" type="radio" name="rating" value="2">
        <label for="rating2">2</label>
        <input id="rating3" type="radio" name="rating" value="3">
        <label for="rating3">3</label>
        <input id="rating4" type="radio" name="rating" value="4">
        <label for="rating4">4</label>
        <input id="rating5" type="radio" name="rating" value="5">
        <label for="rating5">5</label>
                <input type="submit" onclick="collectRating()" class="button" value="Rate" name="Rate">
                <input type="hidden" value="" name="trainingName" id="trainingName"/>

      </span>
        </p>
    </form>
</div>
</div>
</body>
</html>