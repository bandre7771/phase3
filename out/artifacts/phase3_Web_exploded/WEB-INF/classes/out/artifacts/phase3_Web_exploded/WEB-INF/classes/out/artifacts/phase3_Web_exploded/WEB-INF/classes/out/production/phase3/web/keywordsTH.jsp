<%@ page language="java" import="cs5530.*" %>
<html>
<head>
    <title>TH Keywords</title>
    <h1 align="center">TH Keywords</h1>
</head>
<body>
<ul>
    <li><a href="addKeyword.jsp">Add Keyword</a></li>
    <li><a href="updateKeyword.jsp">Update Keyword</a></li>
    <li><a href="assignKeyword.jsp">Assign TH Keyword</a></li>
    <li><a href="unassignKeyword.jsp">Unassign TH Keyword</a></li>
    <BR><BR>
    <li><a href="th.jsp">TH Menu</a></li>
</ul>
</body>
<p> TH's with Keywords</p>
<%
Connector connector = (Connector)session.getAttribute("connector");
if(connector == null) {
    connector = new Connector();
    session.setAttribute("connector", connector);
}
Application app = new Application();
out.println(app.getAllHasKeyWordDescription(connector.stmt));
%>
</html>
