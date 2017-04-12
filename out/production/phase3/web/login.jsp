<%@ page language="java" import="cs5530.*, java.io.*, java.util.*" %>
<html>
<head>
    <title>
        UOtel - User Login
    </title>
    <h1>
        UOtel - User Login
    </h1>
    <%
        String currentUser = (String)session.getAttribute("currentUser");
        if(currentUser == null) {
            session.setAttribute("currentUser", "".toString());
        }

        Connector connector = (Connector)session.getAttribute("connector");
        if(connector == null) {
            connector = new Connector();
            session.setAttribute("connector", connector);
        }

        List<List<String>> reservations = (List<List<String>>)session.getAttribute("reservations");
        if(reservations == null) {
            session.setAttribute("reservations", new ArrayList<List<String>>());
        }

        List<List<String>> stays = (List<List<String>>)session.getAttribute("stays");
        if(stays == null) {
            session.setAttribute("visits", new ArrayList<List<String>>());
        }
    %>
    <script LANGUAGE="javascript">

        function check_all_fields(form_obj) {
            alert(form_obj.usernameValue.value + "='" + form_obj.passwordValue.value + "'");
            if (form_obj.usernameValue.value == "" || form_obj.passwordValue.value == "") {
                alert("Please enter a username and password");
                return false;
            }
            return true;
        }

        function redirect(elem){

            elem.setAttribute("action","userDashboard.jsp");
            elem.submit();
        }

    </script>
</head>
<body>

<%
    String usernameAttribute = request.getParameter("usernameValue");
    String passwordAttribute = request.getParameter("passwordValue");

    if (usernameAttribute == null || passwordAttribute == null) {
%>

Login
<form name="user_search" method=get onsubmit="return check_all_fields(this)" action="login.jsp">
    <%--<input type=hidden name="searchAttribute" value="login">--%>
    <input type=text name="usernameValue" length=10> <BR><BR>
        Password <BR>
    <input type="text" name="passwordValue" length=10>
    <input type=submit>
</form>
<BR><BR>
SignUp for UOtel
<form name="register_user" method=get action="register_user.jsp">
    <%--<input type=hidden name="searchAttribute" value="login">--%>
    <input type=submit>
</form>
<BR><BR>

<%

}
else {
    usernameAttribute = request.getParameter("usernameValue");
    passwordAttribute = request.getParameter("passwordValue");
    Application app = new Application();
    Users user = new Users();
    if (user.userExists(usernameAttribute, passwordAttribute, connector.stmt)) {
        currentUser = usernameAttribute;
        session.setAttribute("currentUser", currentUser);
       %>
        Finally
        <jsp:forward page = "userDashboard.jsp" />
       <%
    } else {
        currentUser = "";
        %>
            Login
            <form name="user_search" method=get onsubmit="return check_all_fields(this)" action="login.jsp">
                <%--<input type=hidden name="searchAttribute" value="login">--%>
                <input type=text name="usernameValue" length=10> <BR><BR>
                Password <BR>
                <input type="text" name="passwordValue" length=10>
                <input type=submit>
            </form>
            <BR><BR>
            SignUp for UOtel
            <form name="register_user" method=get action="register_user.jsp">
                <%--<input type=hidden name="searchAttribute" value="login">--%>
                <input type=submit>
            </form>
            <BR><BR>
        <%
    }
}
%>

<%--<p><b>Listing orders in JSP: </b><BR><BR>--%>

    <%--The orders for query: <b><%=searchAttribute%>='<%=attributeValue%>'</b> are as follows:<BR><BR>--%>
    <%--<%=order.getOrders(searchAttribute, attributeValue, connector.stmt)%> <BR><BR>--%>

    <%--<b>Alternate way (servlet method):</b> <BR><BR>--%>
    <%--<%--%>
        <%--out.println("The orders for query: <b>" + searchAttribute + "='" + attributeValue + "'</b> are as follows:<BR><BR>");--%>
        <%--out.println(order.getOrders(searchAttribute, attributeValue, connector.stmt));--%>
    <%--%>--%>

    <%--<%--%>
            <%--connector.closeStatement();--%>
            <%--connector.closeConnection();--%>
        <%--}  // We are ending the braces for else here--%>
    <%--%>--%>

    <%--<BR><a href="orders.jsp"> New query </a></p>--%>

<%--<p>Schema for Order table: <font face="Geneva, Arial, Helvetica, sans-serif">(--%>
    <%--title varchar(100), quantity integer, login varchar(8), director varchar(10)--%>
    <%--)</font></p>--%>

</body>
