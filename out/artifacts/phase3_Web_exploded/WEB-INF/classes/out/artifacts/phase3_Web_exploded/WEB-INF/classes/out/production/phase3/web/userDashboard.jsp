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
<ol>
    <li><a href="make_reservation.jsp">Make a reservation</a></li>
    <li><a href="th.jsp">TH Menu</a></li>
    <li><a href="record_visit.jsp">Record a Visit</a></li>
    <li><a href="favorite_th.jsp">Favorite a TH</a></li>
    <li><a href="feedback.jsp">Leave Feedback on a Visit</a></li>
    <li><a href="usefulness_ratings.jsp">Rate user Feedback</a></li>
    <li><a href="trust_recordings.jsp">Declare a Users Trust</a></li>
    <li><a href="useful_feedbacks.jsp">Show Top Most Useful Feedback For a Given TH</a></li>
    <li><a href="statistics.jsp">Statistics</a></li><BR>
    <li><a href="login.jsp">Log out</a></li>
</ol>

</body>
