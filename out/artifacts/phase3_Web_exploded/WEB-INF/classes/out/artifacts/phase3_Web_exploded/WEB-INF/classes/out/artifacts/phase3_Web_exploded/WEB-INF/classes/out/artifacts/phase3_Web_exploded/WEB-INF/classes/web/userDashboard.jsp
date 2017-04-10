<%@ page language="java" import="cs5530.*" %>
<html>
<head>
    <title>Dashboard</title>
    <h1 align="center">Welcome To UOTel</h1>
    <script LANGUAGE="javascript">

        function check_all_fields(form_obj){
            alert(form_obj.searchAttribute.value+"='"+form_obj.attributeValue.value+"'");
            if( form_obj.attributeValue.value == "") {
                alert("Fields should be nonempty");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<h2><%=(String)session.getAttribute("currentUser")%>'s Dashboard</h2>
<ul>
    <li><a href="make_reservation.jsp">Make a reservation</a></li>
    <li><a href="th.jsp">TH Menu</a></li>
    <li><a href="record_visit.jsp">Record a Visit</a></li>
    <li><a href="feedback.jsp">Leave Feedback on a Visit</a></li>
    <li><a href="usefullness_ratings.jsp">Rate user Feedback</a></li>
</ul>

</body>
