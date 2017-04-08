<%@ page language="java" import="cs5530.*" %>
<html>
<head>
    <title>
        Dashboard
    </title>

    <script LANGUAGE="javascript">

        function check_all_fields(form_obj){
            alert(form_obj.searchAttribute.value+"='"+form_obj.attributeValue.value+"'");
            if( form_obj.attributeValue.value == ""){
                alert("Search field should be nonempty");
                return false;
            }
            return true;
        }

    </script>
</head>
<body>
<a href="make_reservation.jsp">Make a reservation</a><br>
<a href="record_visit.jsp">Record a Visit</a><br>
<a href="favorite_th.jsp">Favorite a TH you have visited</a><br>
<a href="feedback.jsp">Leave Feedback on a Visit</a><br>
<a href="th.jsp">Rate user Feedback</a><br>


</body>
