<%@ page language="java" import="cs5530.*" %>
<html>
<head>
    <title>TH Availability</title>
    <h1 align="center">TH Availability</h1>
</head>
<body>
<ul>
    <li><a href="addAvailability.jsp">Add TH Period of availability</a></li>
    <li><a href="updateAvailability.jsp">Update TH Period of availability</a></li>
    <BR><BR>
    <li><a href="th.jsp">TH Menu</a></li>
</ul>
</body>
<p> All periods of availability</p>
<%
Connector connector = (Connector)session.getAttribute("connector");
if(connector == null) {
    connector = new Connector();
    session.setAttribute("connector", connector);
}
Application app = new Application();
out.println(app.getTHAvailableTimes(connector));
%>
</html>
