<%@ page import="cs5530.Application" %>
<%@ page import="cs5530.Available" %>
<%@ page import="cs5530.Connector" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: bandre7771
  Date: 4/3/17
  Time: 3:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Record Visit(s)</title>
    <h1 align="center">Record Visit(s)</h1>
    <h3>Enter Information for a Visit</h3>
</head>
<body>

<%
    String thInput = request.getParameter("thInput");
    String pidInput = request.getParameter("pidInput");
    String fromdateInput = request.getParameter("fromdateInput");
    String toDateInput = request.getParameter("todateInput");
    Application app = new Application();
    app._currentUser = (String)session.getAttribute("currentUser");
    List<List<String>> visits = (List<List<String>>)session.getAttribute("visits");
    app.visits = visits;

    if (thInput == null || pidInput == null || fromdateInput == null || toDateInput == null) {
%>

Record a Visit
<form name="record_visit" method=get onsubmit="return check_all_fields(this)" action="record_visit.jsp">
    <%--<input type=hidden name="searchAttribute" value="login">--%>
    <input type=text name="thInput" length=10 placeholder="THID"> <BR><BR>
    <input type="text" name="pidInput" length=10 placeholder="PID"> <BR><BR>
    <input type=text name="fromdateInput" length=10 placeholder="mm/dd/yyyy"> <BR><BR>
    <input type="text" name="todateInput" length=10 placeholder="mm/dd/yyyy"> <BR><BR>
        Add Visit
        <input type=submit>

    <%
    } else {
        List<String> visit = new ArrayList<>();
        visit.add(request.getParameter("thInput"));
        visit.add(request.getParameter("pidInput"));
        visit.add(request.getParameter("fromdateInput"));
        visit.add(request.getParameter("todateInput"));

        visits.add(visit);
        session.setAttribute("visits", visits);

    %>
    <%--<jsp:forward page="make_reservation.jsp"/>--%>
        <%
            }
    %>
</form>
Done recording visits?
<form name="checkout" method=post action="visit_checkout.jsp">
    <input type=submit>
</form>
Would you like to record another visit?
<form name="anotherReservation" method=post action="record_visit.jsp">
    <input type=submit>
</form>
<%
    Connector connector = (Connector)session.getAttribute("connector");
    if(connector == null) {
        connector = new Connector();
        session.setAttribute("connector", connector);
    }
    Available availPeriod = new Available();
    String usersReservations = app.getUsersReservations(connector);
    String tableReservationsVisits = "<b> All your reservations <b> <table> <td>" + usersReservations + "</td>";
   // out.println("All reservations made: <br>" + usersReservations + "<BR><BR>");
%>
<%
    String output = "";
    if(!visits.isEmpty()){
        output = "<b> All your visits you have added so far</b> <table>";
        output += "<tr> <th> hid </th> <th> pid </th> <th> from date </th> <th> to date </th> </tr>";

        for(List<String> visit: visits) {
            output += "<tr><td>" + visit.get(0) + "</td>"
                    + "<td>" + visit.get(1) + "</td>"
                    + "<td>" + visit.get(2) + "</td>"
                    + "<td>" + visit.get(3) + "</td></tr>";
        }
        output += "</table>";
    }
    tableReservationsVisits += "<td valign=\"top\">" + output + "</td> </table>";
    out.println(tableReservationsVisits + "<BR><BR>");
    %><BR><a href="userDashboard.jsp">User Dashboard</a><%
%>
</body>
</html>
