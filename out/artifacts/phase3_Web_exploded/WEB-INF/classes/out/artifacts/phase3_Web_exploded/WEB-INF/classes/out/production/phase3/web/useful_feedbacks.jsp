<%@ page language="java" import="cs5530.*" %>
<html>
<head>
    <script LANGUAGE="javascript">

        function check_all_fields(form_obj) {
            alert(form_obj.searchHidAttribute.value+"='"+form_obj.hidAttributeValue.value+"'\n"
                + form_obj.searchDisplayCountAttribute.value+"='"+form_obj.displayCountAttributeValue.value+"'\n");
            if(form_obj.hidAttributeValue.value == "" || form_obj.displayCountAttributeValue.value == "") {
                alert("All fields should be nonempty");
                return false;
            } else {
                return true;
            }
        }
    </script>
    <title>Top Most Useful Feedback's For a Given TH</title>
    <h1 align="center">Useful Feedback's</h1>
</head>
<body>

    <%
String searchHidAttribute = request.getParameter("searchHidAttribute");
String searchDisplayCountAttribute = request.getParameter("searchDisplayCountAttribute");

Connector connector = (Connector)session.getAttribute("connector");
if(connector == null) {
    connector = new Connector();
    session.setAttribute("connector", connector);
}
if(searchHidAttribute == null || searchDisplayCountAttribute == null)
{
    %>
Please enter information below:
    <form name="usefulness_ratings" method=get onsubmit="return check_all_fields(this)" action="useful_feedbacks.jsp">
        <input type=hidden name="searchHidAttribute" value="hid">
        <input type=text name="hidAttributeValue" length=10 placeholder="Hid">
        Amount of Feedbacks To Display:
        <input type=hidden name="searchDisplayCountAttribute" value="displayCount">
        <input type=text name="displayCountAttributeValue" length=10 placeholder="Display Count">
        <br><br>
        <input type=submit>
    </form>
    <BR><a href="useful_feedbacks.jsp"> Clear All Fields</a><BR>
    <%
        TH th = new TH();
        out.println("<BR><h3>All Th's</h3>");
        out.println(th.getAllTH(connector.stmt));
        out.println("<BR><a href=\"userDashboard.jsp\">User Dashboard</a>");
} else {
    String hidAttributeValue = request.getParameter("hidAttributeValue");
    String displayCountAttributeValue = request.getParameter("displayCountAttributeValue");
    Application app = new Application();
    out.println("<h3>Top Most Useful Feedback's For a Given TH</h3>");
    out.println(app.mostUsefulFeedbacksForTh(displayCountAttributeValue, hidAttributeValue, connector.stmt));
    %>
    <BR><BR><a href="useful_feedbacks.jsp">Display Another TH's Top Most Useful Feedback's</a>
    <%
    }
    %>
</body>
