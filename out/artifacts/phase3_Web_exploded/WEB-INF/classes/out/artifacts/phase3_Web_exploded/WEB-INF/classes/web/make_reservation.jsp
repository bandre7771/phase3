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
    <title>Make Reservation</title>
    <h1>Enter Information for a Reservation</h1>
</head>
<body>

<%
    String thInput = request.getParameter("thInput");
    String pidInput = request.getParameter("pidInput");
    String fromdateInput = request.getParameter("fromdateInput");
    String toDateInput = request.getParameter("todateInput");
    Application app = new Application();
    List<List<String>> reservations = (List<List<String>>)session.getAttribute("reservations");

    if (thInput == null || pidInput == null || fromdateInput == null || toDateInput == null) {
%>

Make a Reservation
<form name="make_reservation" method=get onsubmit="return check_all_fields(this)" action="make_reservation.jsp">
    <%--<input type=hidden name="searchAttribute" value="login">--%>
    <input type=text name="thInput" length=10 placeholder="THID"> <BR><BR>
    <input type="text" name="pidInput" length=10 placeholder="PID"> <BR><BR>
    <input type=text name="fromdateInput" length=10 placeholder="mm/dd/yyyy"> <BR><BR>
    <input type="text" name="todateInput" length=10 placeholder="mm/dd/yyyy"> <BR><BR>
        Add Reservation
        <input type=submit>

    <%
    } else {
        List<String> reservation = new ArrayList<>();
        reservation.add(request.getParameter("thInput"));
        reservation.add(request.getParameter("pidInput"));
        reservation.add(request.getParameter("fromdateInput"));
        reservation.add(request.getParameter("todateInput"));

        Connector connector = new Connector();

        reservations.add(reservation);
        session.setAttribute("reservations", reservations);

    %>
    <%--<jsp:forward page="make_reservation.jsp"/>--%>
        <%
            }
    %>
</form>
Review your items
<form name="checkout" method=post action="reservation_checkout.jsp">
    <input type=submit>
</form>
Would you like to make another reservation?
<form name="anotherReservation" method=post action="make_reservation.jsp">
    <input type=submit>
</form>
<%
    Connector con = new Connector();
    Available availPeriod = new Available();
    String availTimes = app.getTHAvailableTimes(con);
    String availTimesReservations = "<b> All Available TH's and there times <b> <table> <td>" + availTimes + "</td>";
//    out.println("All Available TH's and there available times: <br>" + availTimes + "<BR><BR>");
%>
<%
    String output = "";
    if(!reservations.isEmpty()){
        output = "<b> Current Reservations being added </b><table>";
        output += "<tr> <th> hid </th> <th> pid </th> <th> from date </th> <th> to date </th> </tr>";

        for(List<String> reservation: reservations) {
            output += "<tr><td>" + reservation.get(0) + "</td>"
                    + "<td>" + reservation.get(1) + "</td>"
                    + "<td>" + reservation.get(2) + "</td>"
                    + "<td>" + reservation.get(3) + "</td></tr>";
        }
        output += "</table>";

    }
    availTimesReservations += "<td>" + output + "</td> </table>";
    out.println(availTimesReservations + "<BR><BR>");

%>
</body>
</html>
