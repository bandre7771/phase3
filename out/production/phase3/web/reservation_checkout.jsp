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
    <title>Checkout</title>
    <h1>Checkout Reservations</h1>
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
    String checkoutAttribute = request.getParameter("checkoutAttribute");
    String currentUser = (String)session.getAttribute("currentUser");

    List<List<String>> reservations = (List<List<String>>)session.getAttribute("reservations");
    Application app = new Application();

    app.reservations = reservations;
    app._currentUser = currentUser;

    if (checkoutAttribute == null) {
%>

</form>
Would you like to Checkout?
<form name="checkout" method=get onsubmit="return check_all_fields(this)" action="reservation_checkout.jsp">
    <input type=hidden name="checkoutAttribute" value="checkout">
    <input type=submit>
</form>
<%
    Connector connector = (Connector)session.getAttribute("connector");
    if(connector == null) {
        connector = new Connector();
        session.setAttribute("connector", connector);
    }
        if (!reservations.isEmpty()) {
            String output = "<table>";
            output += "<tr> <th> hid </th> <th> pid </th> <th> from date </th> <th> to date </th> </tr>";

            for (List<String> reservation : reservations) {
                output += "<tr><td>" + reservation.get(0) + "</td>"
                        + "<td>" + reservation.get(1) + "</td>"
                        + "<td>" + reservation.get(2) + "</td>"
                        + "<td>" + reservation.get(3) + "</td></tr>";
            }
            output += "</table>";
            out.println("All the reservations made so far: <br>" + output + "<BR><BR>");

            String suggestedTHs = app.suggestTH(reservations, con);
            out.println("Suggested THs based on your reservation(s): <br><br>" + suggestedTHs + "<BR><BR>");
        }
    }
    else {
            if (!reservations.isEmpty()){
                Period period = new Period();
                List<List<String>> chosenPeriods = new ArrayList<>();
                List<String> chosenPeriod = new ArrayList<>();
                for(List<String> reservation: reservations) {
                    chosenPeriod = period.getPeriod(reservation.get(1), connector.stmt).get(0);
                    chosenPeriods.add(chosenPeriod);
                }
                app.makeReservation(chosenPeriods, connector);
                session.setAttribute("reservations", new ArrayList<>());
            }
%>
<jsp:forward page = "userDashboard.jsp" />
<%
        }


%>
</body>
</html>
