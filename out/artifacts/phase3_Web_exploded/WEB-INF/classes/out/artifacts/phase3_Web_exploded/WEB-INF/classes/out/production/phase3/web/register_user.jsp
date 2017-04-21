<%@ page import="cs5530.Users" %>
<%@ page import="cs5530.Connector" %><%--
  Created by IntelliJ IDEA.
  User: bandre7771
  Date: 4/3/17
  Time: 11:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register User</title>
    <h1>Register User</h1>
</head>
<body>

<%
    String usernameAttribute = request.getParameter("usernameInput");
    String passwordAttribute = request.getParameter("passwordInput");
    String fullnameAttribute = request.getParameter("fullnameValue");
    String addressAttribute = request.getParameter("addressValue");
    String phonenumberAttribute = request.getParameter("phonenumberValue");

    if (usernameAttribute == null || passwordAttribute == null || fullnameAttribute == null || addressAttribute == null || phonenumberAttribute == null) {
%>

Please choose a login name
<form name="register_user" method=get onsubmit="return check_all_fields(this)" action="register_user.jsp">
    <%--<input type=hidden name="searchAttribute" value="login">--%>
    <input type=text name="usernameInput" length=10> <BR><BR>
    Please enter a password <BR>
    <input type="text" name="passwordInput" length=10> <BR><BR>
    Please enter your full name <BR>
    <input type=text name="fullnameValue" length=10> <BR><BR>
    Please enter your address <BR>
    <input type="text" name="addressValue" length=10> <BR><BR>
    Please enter your phone number <BR>
    <input type=text name="phonenumberValue" length=10> <BR><BR>
    <input type=submit>
</form>

<%

} else {
    usernameAttribute = request.getParameter("usernameInput");
    passwordAttribute = request.getParameter("passwordInput");
    fullnameAttribute = request.getParameter("fullnameValue");
    addressAttribute = request.getParameter("addressValue");
    phonenumberAttribute = request.getParameter("phonenumberValue");
    Connector connector = (Connector)session.getAttribute("connector");
    if(connector == null) {
        connector = new Connector();
        session.setAttribute("connector", connector);
    }
    Users user = new Users();
    user.addUser(usernameAttribute, fullnameAttribute, passwordAttribute, addressAttribute, phonenumberAttribute, connector.stmt);
%>
<jsp:forward page="login.jsp"/>
<%
    }
%>
</body>
</html>
