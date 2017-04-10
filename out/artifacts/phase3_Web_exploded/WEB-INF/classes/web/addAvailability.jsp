<%@ page language="java" import="cs5530.*" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <script LANGUAGE="javascript">
        function check_all_fields(form_obj){
            alert(form_obj.searchHidAttribute.value+"='"+form_obj.hidAttributeValue.value+"'\n"
                + form_obj.searchStartDateAttribute.value+"='"+form_obj.startDateAttributeValue.value+"'\n"
                + form_obj.searchEndDateAttribute.value+"='"+form_obj.endDateAttributeValue.value+"'\n"
                + form_obj.searchCostAttribute.value+"='"+form_obj.costAttributeValue.value+"'\n");
            if( form_obj.hidAttributeValue.value == "" || form_obj.startDateAttributeValue.value == "" || form_obj.endDateAttributeValue.value == "" || form_obj.costAttributeValue.value == "") {
                alert("All fields should be nonempty");
                return false;
            } else {
                return true;
            }
        }
    </script>
    <title>Add Availability</title>
    <h1 align="center">Add Availability</h1>
</head>
<body>
    <%
String searchHidAttribute = request.getParameter("searchHidAttribute");
String searchStartDateAttribute = request.getParameter("searchStartDateAttribute");
String searchEndDateAttribute = request.getParameter("searchEndDateAttribute");
String searchCostAttribute = request.getParameter("searchCostAttribute");
Connector connector = (Connector)session.getAttribute("connector");
if(connector == null) {
    connector = new Connector();
    session.setAttribute("connector", connector);
}
if(searchHidAttribute == null || searchStartDateAttribute == null || searchEndDateAttribute == null || searchCostAttribute == null) {
%>
Please enter Availability information Below:
    <form name="availability_add" method=get onsubmit="return check_all_fields(this)" action="addAvailability.jsp">
        Hid: <BR>
        <input type=hidden name="searchHidAttribute" value="hid">
        <input type=text name="hidAttributeValue" length=10 placeholder="Hid">
        <br>
        Start Date: <BR>
        <input type=hidden name="searchStartDateAttribute" value="startDate">
        <input type=text name="startDateAttributeValue" length=10 placeholder="mm/dd/yyyy">
        <br>
        End Date: <BR>
        <input type=hidden name="searchEndDateAttribute" value="endDate">
        <input type=text name="endDateAttributeValue" length=10 placeholder="mm/dd/yyyy">
        <br>
        Price per night: <BR>
        <input type=hidden name="searchCostAttribute" value="cost">
        <input type=text name="costAttributeValue" length=10 placeholder="Cost">
        <br><br>
        <input type=submit>
    </form>
    <BR><a href="addAvailability.jsp"> Clear All Fields</a><BR>
    <BR><a href="availabilityTH.jsp"> TH Availability Menu </a>
    <%
        TH th = new TH();
        out.println(th.getTHForLogin((String)session.getAttribute("currentUser"), connector.stmt));

    } else {

        String hidAttributeValue = request.getParameter("hidAttributeValue");
        String startDateAttributeValue = request.getParameter("startDateAttributeValue");
        String endDateAttributeValue = request.getParameter("endDateAttributeValue");
        String costAttributeValue = request.getParameter("costAttributeValue");
        Application app = new Application();
        TH th = new TH();
        Period period = new Period();
        String pid;
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date sDate = app.convertToDate(startDateAttributeValue, formatter);
        Date eDate = app.convertToDate(endDateAttributeValue, formatter);
        if(sDate.after(eDate))
        {
            %>
            <b>The Start Date must be before the End Date</b>
            <BR><BR><a href="addAvailability.jsp">Try another Period of Availability</a>
            <%
        }
        else if (!th.isOwnerOfTH((String)session.getAttribute("currentUser"), hidAttributeValue, connector.stmt))
        {
            %>
            <b>You don't own this TH or it doesn't exist</b>
            <BR><BR><a href="addAvailability.jsp">Try another Period of Availability</a>
            <%
        }
        else
        {
            List<List<String>> pids = period.getPeriod(sDate.toString(), eDate.toString(), connector.stmt);
            if(pids.isEmpty())
            {
                period.addPeriod(sDate.toString(), eDate.toString(), connector.stmt);
                pid = period.getLastPeriod(connector.stmt);
            }
            else
            {
                pid = pids.get(0).get(0);
            }
            Available available = new Available();
            if(available.addAvailable(hidAttributeValue, pid, costAttributeValue, connector.stmt))
            {
                %>
                <b>Successfully Added Period of Availability</b>
                <%
            }
            else
            {
                %>
                <b>Could Not Add Period of Availability</b>
                <%
            }
            pids.clear();
            %>
            <BR><BR><a href="addAvailability.jsp">Add another Period of Availability</a>
            <%
        }
    }  // We are ending the braces for else here
    %>
</body>
