<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Cache-Control" content="no-store" />
    <title>Manage Trainings</title>
    <link href='https://fonts.googleapis.com/css?family=Roboto+Slab:400,100,300,700|Lato:400,100,300,700,900' rel='stylesheet' type='text/css'>
    <link href="${pageContext.request.contextPath}/resources/css/editor.css" rel="stylesheet" >
    <script>
        function submitForEdit(trainingId){
//            alert("hello"+trainingId);
            document.getElementById("trainingId").value = trainingId;
            document.getElementById("editForm").submit();
        }

        function toggle(source) {
            checkboxes = document.getElementsByName('checked');
            for(var i=0, n=checkboxes.length;i<n;i++) {
                checkboxes[i].checked = source.checked;
            }
        }
    </script>
<script>

var ALERT_TITLE = "Confirm delete";
var ALERT_OK_BUTTON_TEXT = "Ok";
var ALERT_CANCEL_BUTTON_TEXT = "Cancel";

if(document.getElementById) {
    window.alert = function(txt) {
        createCustomAlert(txt);
    }
}

function createCustomAlert(txt) {
    d = document;

    if(d.getElementById("modalContainer")) return;

    mObj = d.getElementsByTagName("body")[0].appendChild(d.createElement("div"));
    mObj.id = "modalContainer";
    mObj.style.height = d.documentElement.scrollHeight + "px";

    alertObj = mObj.appendChild(d.createElement("div"));
    alertObj.id = "alertBox";
    if(d.all && !window.opera) alertObj.style.top = document.documentElement.scrollTop + "px";
    alertObj.style.left = (d.documentElement.scrollWidth - alertObj.offsetWidth)/2 + "px";
    alertObj.style.visiblity="visible";

    h1 = alertObj.appendChild(d.createElement("h1"));
    h1.appendChild(d.createTextNode(ALERT_TITLE));

    msg = alertObj.appendChild(d.createElement("p"));
    msg.appendChild(d.createTextNode(txt));
    msg.innerHTML = txt;

    btn1 = alertObj.appendChild(d.createElement("a"));
    btn1.id = "closebtn";
    btn1.appendChild(d.createTextNode(ALERT_OK_BUTTON_TEXT));
    btn1.href = "#";
    btn1.focus();
    btn1.onclick = function() {document.getElementById("ask").submit();}

    btn2 = alertObj.appendChild(d.createElement("a"));
    btn2.id = "cancelbtn";
    btn2.appendChild(d.createTextNode(ALERT_CANCEL_BUTTON_TEXT));
    btn2.href = "#";
    btn2.focus();
    btn2.onclick = function() { removeCustomAlert();return false; }

    alertObj.style.display = "block";

}

function removeCustomAlert() {
    document.getElementsByTagName("body")[0].removeChild(document.getElementById("modalContainer"));
}


</script>
    <%--<script type = "text/javascript" >--%>
        <%--function disableBackButton()--%>
        <%--{--%>
            <%--window.history.forward();--%>
        <%--}--%>
        <%--setTimeout("disableBackButton()", 0);--%>
    <%--</script>--%>



</head>
<body>
<div class="transbox">
    <div class="text" align="center" >Manage Trainings</div>
    <div class="img">
        <img src="https://danzahariadotbiz.files.wordpress.com/2015/05/endava_logo_jpg.png" width=100px>
    </div>
    <div>
        <form action="login.htm" method="GET">
            <input id="logoutButton" type="submit" name="logout" value="logout"/><br>
        </form>
    </div>
    <div id="navbarName"> <c:out value="${name}"/></div>


</div>

<form class="newTraining" action="addTraining.htm" method="post">
    <div class="box-header">
        <c:if test="${training == null}">
            <h2>Add new training</h2>
            <input type="hidden" name="action" value="add">
            <input type="hidden" name="trainingId" value="0">
        </c:if>
        <c:if test="${training != null}">
            <h2>Edit training</h2>
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="trainingId" value="${training.trainingId}">
        </c:if>
    </div>
    <div class="box-body">
        <label for="name">Name</label>
        <input type="text" id="name" name="trainingName" placeholder="Enter Training Name" value="${training.trainingName}">
        <br>
        <label for="startDate">Start date</label>
        <input type="date" id="startDate" name="startDate" value="${training.startDate}">
        <br>
        <label for="endDate">End date</label>
        <input type="date" id="endDate" name="endDate" value="${training.stopDate}">
        <br>
        <label>Grade: </label>

        <c:if test="${training != null && training.grade == 'JT'}">
            <input type="radio" name="grade" value="JT" checked="checked">JT
        </c:if>
        <c:if test="${training == null}">
            <input type="radio" name="grade" value="JT" >JT
        </c:if>
        <c:if test="${training != null && training.grade != 'JT'}">
            <input type="radio" name="grade" value="JT" >JT
        </c:if>

        <c:if test="${training != null && training.grade == 'T'}">
            <input type="radio" name="grade" value="T" checked="checked">T
        </c:if>
        <c:if test="${training == null}">
            <input type="radio" name="grade" value="T" >T
        </c:if>
        <c:if test="${training != null && training.grade != 'T'}">
            <input type="radio" name="grade" value="T" >T
        </c:if>

        <c:if test="${training != null && training.grade == 'ST'}">
            <input type="radio" name="grade" value="ST" checked="checked">ST
        </c:if>
        <c:if test="${training == null}">
            <input type="radio" name="grade" value="ST" >ST
        </c:if>
        <c:if test="${training != null && training.grade != 'ST'}">
            <input type="radio" name="grade" value="ST" >ST
        </c:if>

        <c:if test="${training != null && training.grade == 'EN'}">
            <input type="radio" name="grade" value="EN" checked="checked">EN
        </c:if>
        <c:if test="${training == null}">
            <input type="radio" name="grade" value="EN" >EN
        </c:if>
        <c:if test="${training != null && training.grade != 'EN'}">
            <input type="radio" name="grade" value="EN" >EN
        </c:if>

        <c:if test="${training != null && training.grade == 'SE'}">
            <input type="radio" name="grade" value="SE" checked="checked">SE
        </c:if>
        <c:if test="${training == null}">
            <input type="radio" name="grade" value="SE" >SE
        </c:if>
        <c:if test="${training != null && training.grade != 'SE'}">
            <input type="radio" name="grade" value="SE" >SE
        </c:if>

        <c:if test="${training != null && training.grade == 'C'}">
            <input type="radio" name="grade" value="C" checked="checked">C
        </c:if>
        <c:if test="${training == null}">
            <input type="radio" name="grade" value="C" >C
        </c:if>
        <c:if test="${training != null && training.grade != 'C'}">
            <input type="radio" name="grade" value="C" >C
        </c:if>

        <c:if test="${training != null && training.grade == 'SC'}">
            <input type="radio" name="grade" value="SC" checked="checked">SC
        </c:if>
        <c:if test="${training == null}">
            <input type="radio" name="grade" value="SC" >SC
        </c:if>
        <c:if test="${training != null && training.grade != 'SC'}">
            <input type="radio" name="grade" value="SC" >SC
        </c:if>

        <br>
        <label for="trainer">Trainer:</label>
        <input type="text" name="trainerName" id="trainer" placeholder="Enter Trainer's Name" value="${training.trainerName}">
        <br>
        <label for="technology">Technology: </label>
        <input type="text" name="technology" id="technology" placeholder="Enter Technology" value="${training.technology}">
        <br>
        <label for="places">Places: </label>
        <input type="number"  name="places" id="places" placeholder="Places available" value="${training.numberPeople}">
        <br>
        <c:if test="${training == null}">
            <input id="button" type="submit" value="Add training" name="addTraining">
        </c:if>
        <c:if test="${training != null}">
            <input id="button" type="submit" value="Update training" name="addTraining">
        </c:if>
    </div>

</form>
<br><br>
<form id="ask" action="delete.htm" method="post">
<table id="rounded-corner" align="center">
    <th> Name </th>
    <th> Trainer </th>
    <th> Start Date </th>
    <th> End Date </th>
    <th> Grade </th>
    <th> Technology </th>
    <th> Places Available </th>
    <th> Edit </th>
    <th> <input id="deleteButton" type="button" value="delete" name="delete" onclick="alert('Are you sure?');"> </th>
    <th> <input type="checkbox" onClick="toggle(this)" name="selectAll">SelectAll </th>
    <c:forEach items="${trainingList}" var="training">
        <tr>
            <td><c:out value="${training.trainingName}"></c:out> </td>
            <td><c:out value="${training.trainerName}"></c:out> </td>
            <td><c:out value="${training.startDate}"></c:out> </td>
            <td><c:out value="${training.stopDate}"></c:out> </td>
            <td><c:out value="${training.grade}"></c:out> </td>
            <td><c:out value="${training.technology}"></c:out> </td>
            <td><c:out value="${training.numberPeople}"></c:out> </td>
            <td><input type="button" value="edit" name ="edit" onclick="submitForEdit('${training.trainingId}')" > </td>
            <td><input id="editButton" type="checkbox" name="checked" value="${training.trainingId}"> </td>
        </tr>
    </c:forEach>
</table>
    </form>

<form id="editForm" action="editor.htm" method="POST">
    <input type="hidden" name="trainingId" id="trainingId" value=""/>
</form>



</body>
</html>
