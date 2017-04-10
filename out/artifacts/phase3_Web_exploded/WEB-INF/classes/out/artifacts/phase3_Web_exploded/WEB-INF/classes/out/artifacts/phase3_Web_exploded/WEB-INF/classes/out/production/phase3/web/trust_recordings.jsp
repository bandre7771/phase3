<%@ page language="java" import="cs5530.*" %>
<html>
<head>
    <script LANGUAGE="javascript">

        function check_all_fields(form_obj) {
            alert(form_obj.searchLoginAttribute.value+"='"+form_obj.loginAttributeValue.value+"'\n"
                + form_obj.searchTrustedAttribute.value+"='"+form_obj.trustedAttributeValue.value+"'\n");
            if(form_obj.loginAttributeValue.value == "" || form_obj.trustedAttributeValue.value == "") {
                alert("All fields should be nonempty");
                return false;
            } else {
                return true;
            }
        }
    </script>
    <title>Trust Recordings</title>
    <h1 align="center">Trust Recordings</h1>
</head>
<body>

    <%
String searchLoginAttribute = request.getParameter("searchLoginAttribute");
String searchTrustedAttribute = request.getParameter("searchTrustedAttribute");

Connector connector = (Connector)session.getAttribute("connector");
if(connector == null) {
    connector = new Connector();
    session.setAttribute("connector", connector);
}
if(searchLoginAttribute == null || searchTrustedAttribute == null)
{
    Users users = new Users();
    out.println("<h3>All other users<h3>");
    out.println(users.getAllOtherUsers((String)session.getAttribute("currentUser"), connector.stmt));
    %>

Please enter trust recording below:
    <form name="usefulness_ratings" method=get onsubmit="return check_all_fields(this)" action="trust_recordings.jsp">
        <input type=hidden name="searchLoginAttribute" value="login">
        <input type=text name="loginAttributeValue" length=10 placeholder="Login">
        <input type=hidden name="searchTrustedAttribute" value="trusted">
        <select name="trustedAttributeValue">
            <option value="1">trusted</option>
            <option value="0">not trusted</option>
        </select>
        <br><br>
        <input type=submit>
    </form>
    <BR><a href="trust_recordings.jsp"> Clear All Fields</a><BR>
    <BR><a href="userDashboard.jsp">User Dashboard</a>
    <%
} else {
        String loginAttributeValue = request.getParameter("loginAttributeValue");
        String trustedAttributeValue = request.getParameter("trustedAttributeValue");
        Trust trust = new Trust();
        if (trust.addTrust((String)session.getAttribute("currentUser"), loginAttributeValue, trustedAttributeValue, connector.stmt))
        {
            %>
            <b>Successfully Recorded Users Trust</b>
            <%
        }
        else
        {
            %>
            <b>Failed to Record Users Trust</b>
            <%
        }
    %>
    <BR><BR><a href="trust_recordings.jsp">Recorded Another Users Trust</a>
    <%
    }
    %>
</body>
