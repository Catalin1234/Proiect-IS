<!--
Created by IntelliJ IDEA.
User: ctimbus
Date: 8/9/2016
Time: 2:50 PM
To change this template use File | Settings | File Templates.
-->
<head>
    <link href="${pageContext.request.contextPath}/resources/css/employee.css" rel="stylesheet">
</head>

<form id="register" action="registerEmployee.htm" method="POST">
    <label>Training Name:</label>
    <input readonly type="text" name="trainingName" id="trainingName" required="required" autofocus="autofocus"
           value="${training.trainingName}"/>
    <br/>
    <label>Trainer Name:</label>
    <input readonly type="text" name="trainerName" id="trainerName" required="required" autofocus="autofocus"
           value="${training.trainerName}"/>
    </br>
    <label>Start Date:</label>
    <input readonly type="text" name="startDate" id="startDate" required="required" autofocus="autofocus"
           value="${training.startDate}"/>
    </br>
    <label>Stop Date:</label>
    <input readonly type="text" name="stopDate" id="stopDate" required="required" autofocus="autofocus"
           value="${training.stopDate}"/>
    </br>
    <label>Technology:</label>
    <input readonly type="text" name="technology" id="technology" required="required" autofocus="autofocus"
           value="${training.technology}"/>
    </br>
    <label>Capacity:</label>
    <input readonly type="text" name="numberOfPeopleRegistered" id="numberOfPeopleRegistered" required="required"
           autofocus="autofocus" value="${training.numberPeople}"/>
    </br>
    <input id="button" type="submit" name="register" value="register"/>
    <br/>
</form>
