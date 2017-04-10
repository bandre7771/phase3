<%@ page import="cs5530.Application" %>
<%@ page import="cs5530.Available" %>
<%@ page import="cs5530.Connector" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="cs5530.Period" %><%--
  Created by IntelliJ IDEA.
  User: bandre7771
  Date: 4/3/17
  Time: 3:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Leave Feedback</title>
    <h1>Leave Feedback</h1>
    <script LANGUAGE="javascript">

        function check_all_fields(form_obj){
            //alert(form_obj.checkoutAttribute.value+"='"+form_obj.attributeValue.value+"'");
            if( form_obj.checkoutValue.value == ""){
                alert("must checkout");
                return false;
            }
            return true;
        }

    </script>
</head>
<body>

<%
    String currentUser = (String)session.getAttribute("currentUser");
    String thID = request.getParameter("thInput");
    String score = request.getParameter("score");
    String summary = request.getParameter("summary");

    List<List<String>> reservations = (List<List<String>>)session.getAttribute("reservations");
    Application app = new Application();

    app.reservations = reservations;
    app._currentUser = currentUser;
    Connector connector = (Connector)session.getAttribute("connector");
    if(connector == null) {
        connector = new Connector();
        session.setAttribute("connector", connector);
    }
    if (thID == null || score == null || summary == null) {
%>

</form>
Enter the TH you would like to leave feedback on
<form name="checkout" method=get onsubmit="return check_all_fields(this)" action="feedback.jsp">
    <input type=text name="thInput" length=10 placeholder="THID">
    <input type=text name="score" length=10 placeholder="Score">
    <input type=text name="summary" length=50 placeholder="Summary">
    <input type=submit>
</form>
<%
    String visitedTHs = app.getVisitedTHS(connector.stmt);
    out.println("All the THs you have visited: <br><br>" + visitedTHs + "<BR><BR>");
} else {
    app.leaveFeedback(thID, score, summary, connector);
    out.println("You have left feedback on TH: " + thID);

%>
Would you like to leave feedback on another TH
<form name="addAnother" method=get action="feedback.jsp">
    <button name="yes" type="submit">Yes</button>
</form>
<form name="done" method=get action="userDashboard.jsp">
    <button name="no" type="submit">No</button>
</form>
<%
    }


%>
</body>
</html>
