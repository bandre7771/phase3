<%--
  Created by IntelliJ IDEA.
  User: John
  Date: 4/3/17
  Time: 11:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String currentUser = (String)session.getAttribute("currentUser");
        if(currentUser == null) {
            session.setAttribute("currentUser", "jpy".toString());
            currentUser = "jpy";
        }
    %>
    <title>JSP Example</title>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
</head>

<body>
<a href="orders.sql">orders.sql</a><br>
<a href="orders.jsp">orders.jsp</a><br>
<a href="login.jsp">login</a><br>
<a href="th.jsp">TH</a><br>
</body>
</html>