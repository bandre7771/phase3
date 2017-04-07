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

    List<List<String>> visits = (List<List<String>>)session.getAttribute("visits");

    if (checkoutAttribute == null) {
%>

</form>
Would you like to Checkout?
<form name="checkout" method=get onsubmit="return check_all_fields(this)" action="visit_checkout.jsp">
    <input type=hidden name="checkoutAttribute" value="checkout">
    <input type=submit>
</form>
<%
        if (!visits.isEmpty()) {
            String output = "<table>";
            output += "<tr> <th> hid </th> <th> pid </th> <th> from date </th> <th> to date </th> </tr>";

            for (List<String> visit : visits) {
                output += "<tr><td>" + visit.get(0) + "</td>"
                        + "<td>" + visit.get(1) + "</td>"
                        + "<td>" + visit.get(2) + "</td>"
                        + "<td>" + visit.get(3) + "</td></tr>";
            }
            output += "</table>";
            out.println("All the reservations made so far: <br>" + output + "<BR><BR>");
        }
    }
    else {
            if (!visits.isEmpty()){
                Application app = new Application();

                app.visits = visits;
                app._currentUser = currentUser;
                Connector connector = new Connector();
                Period period = new Period();
                List<List<String>> chosenPeriods = new ArrayList<>();
                List<String> chosenPeriod = new ArrayList<>();
                for(List<String> visit: visits) {
                    chosenPeriod = period.getPeriod(visit.get(1), connector.stmt).get(0);
                    chosenPeriods.add(chosenPeriod);
                }
                app.recordVisit(chosenPeriods, connector);
                session.setAttribute("visits", new ArrayList<>());
            }
%>
<jsp:forward page = "userDashboard.jsp" />
<%
        }


%>
</body>
</html>
