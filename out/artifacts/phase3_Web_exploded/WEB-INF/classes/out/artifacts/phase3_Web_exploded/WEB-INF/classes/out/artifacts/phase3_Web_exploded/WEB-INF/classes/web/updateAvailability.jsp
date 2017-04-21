<%@ page language="java" import="cs5530.*" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <script LANGUAGE="javascript">
        function check_all_fields(form_obj){
            alert(form_obj.searchHidAttribute.value+"='"+form_obj.hidAttributeValue.value+"'\n"
                + form_obj.searchPidAttribute.value+"='"+form_obj.pidAttributeValue.value+"'\n"
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
    <title>Update Availability</title>
    <h1 align="center">Update Availability</h1>
</head>
<body>
    <%
String searchHidAttribute = request.getParameter("searchHidAttribute");
String searchPidAttribute = request.getParameter("searchPidAttribute");
String searchStartDateAttribute = request.getParameter("searchStartDateAttribute");
String searchEndDateAttribute = request.getParameter("searchEndDateAttribute");
String searchCostAttribute = request.getParameter("searchCostAttribute");
Connector connector = (Connector)session.getAttribute("connector");
if(connector == null) {
    connector = new Connector();
    session.setAttribute("connector", connector);
}
if(searchHidAttribute == null || searchPidAttribute == null || searchStartDateAttribute == null || searchEndDateAttribute == null || searchCostAttribute == null)
{
%>
Please enter Availability information Below:
    <form name="availability_Update" method=get onsubmit="return check_all_fields(this)" action="updateAvailability.jsp">
        Hid: <BR>
        <input type=hidden name="searchHidAttribute" value="hid">
        <input type=text name="hidAttributeValue" length=10 placeholder="Hid">
        <br>
        Pid: <BR>
        <input type=hidden name="searchPidAttribute" value="pid">
        <input type=text name="pidAttributeValue" length=10 placeholder="Pid">
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
        Application app = new Application();
        out.println(app.getAllAvailableForLoginTH((String)session.getAttribute("currentUser"), connector.stmt));
    }
    else
        {
        String hidAttributeValue = request.getParameter("hidAttributeValue");
        String pidAttributeValue = request.getParameter("pidAttributeValue");
        String startDateAttributeValue = request.getParameter("startDateAttributeValue");
        String endDateAttributeValue = request.getParameter("endDateAttributeValue");
        String costAttributeValue = request.getParameter("costAttributeValue");
        Application app = new Application();
        TH th = new TH();
        Period period = new Period();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date sDate = app.convertToDate(startDateAttributeValue, formatter);
        Date eDate = app.convertToDate(endDateAttributeValue, formatter);
        if(sDate.after(eDate))
        {
            %>
            <b>The Start Date must be before the End Date</b>
            <BR><BR><a href="updateAvailability.jsp">Try another Period of Availability</a>
            <%
        }
        else if (!th.isOwnerOfTH((String)session.getAttribute("currentUser"), hidAttributeValue, connector.stmt))
        {
            %>
            <b>You don't own this TH or it doesn't exist</b>
            <BR><BR><a href="updateAvailability.jsp">Try another Period of Availability</a>
            <%
        }
        else if(period.getPeriod(pidAttributeValue, connector.stmt).isEmpty())
        {
            %>
            <b>Invalid Pid</b>
            <BR><BR><a href="updateAvailability.jsp">Try another Period of Availability</a>
            <%
        }
        else
        {
            String newPid = "";
            List<List<String>> pids = period.getPeriod(sDate.toString(), eDate.toString(), connector.stmt);
            if(pids.isEmpty())
            {
                period.addPeriod(sDate.toString(), eDate.toString(), connector.stmt);
                newPid = period.getLastPeriod(connector.stmt);
            }
            else
            {
                newPid = pids.get(0).get(0);
            }
            Available available = new Available();
            if(available.addAvailable(hidAttributeValue, newPid, costAttributeValue, connector.stmt) && available.deleteAvailable(hidAttributeValue, pidAttributeValue, connector.stmt))
            {
                %>
                <b>Successfully Updated Period of Availability</b>
                <%
            }
            else
            {
                %>
                <b>Could Not Update Period of Availability</b>
                <%
            }
            pids.clear();
            %>
            <BR><BR><a href="updateAvailability.jsp">Update another Period of Availability</a>
            <%
        }
    }  // We are ending the braces for else here
    %>
</body>
