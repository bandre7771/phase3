<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="cs5530.*" %><%--
  Created by IntelliJ IDEA.
  User: bandre7771
  Date: 4/3/17
  Time: 3:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Favorite</title>
    <h1 align="center">Favorite</h1>
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
    String thID = request.getParameter("thInput");

    List<List<String>> reservations = (List<List<String>>)session.getAttribute("reservations");
    Application app = new Application();

    app.reservations = reservations;
    app._currentUser = currentUser;
    Connector connector = (Connector)session.getAttribute("connector");
    if(connector == null) {
        connector = new Connector();
        session.setAttribute("connector", connector);
    }
    if (thID == null) {
%>

</form>
Enter the TH you would like to favorite
<form name="checkout" method=get onsubmit="return check_all_fields(this)" action="favorite_th.jsp">
    <input type=text name="thInput" length=10 placeholder="THID">
    <input type=submit>
</form>
<%
    String visitedTHs = app.getVisitedTHS(connector.stmt);
    out.println("All the THs you have visited: <br><br>" + visitedTHs + "<BR><BR>");
    %><BR><%
    out.println("Your favorite TH's");
    Favorites favorites = new Favorites();
    out.println(favorites.getUserFavoriteTHs(currentUser, connector.stmt));
%><BR><a href="userDashboard.jsp">User Dashboard</a><%

} else {
    app.declareTHAsFavorite(thID, connector);
    out.println("TH " + thID + " has been favorited");

%>
Would you like to favorite another TH
<form name="addAnother" method=get action="favorite_th.jsp">
    <button name="yes" type="submit">Yes</button>
</form>
<form name="done" method=get action="userDashboard.jsp">
    <button name="no" type="submit">No</button>
</form>
<%
    }


%>
</body>
</html>
