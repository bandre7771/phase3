<%@ page language="java" import="cs5530.*" %>
<html>
<head>
    <script LANGUAGE="javascript">

        function check_all_fields(form_obj) {
            alert(form_obj.searchTypeAttribute.value+"='"+form_obj.typeAttributeValue.value+"'\n"
                + form_obj.searchDisplayCountAttribute.value+"='"+form_obj.displayCountAttributeValue.value+"'\n");
            if(form_obj.typeAttributeValue.value == "" || form_obj.displayCountAttributeValue.value == "") {
                alert("All fields should be nonempty");
                return false;
            } else {
                return true;
            }
        }
    </script>
    <title>Statistics</title>
    <h1 align="center">Statistics</h1>
</head>
<body>

    <%
String searchTypeAttribute = request.getParameter("searchTypeAttribute");
String searchDisplayCountAttribute = request.getParameter("searchDisplayCountAttribute");

Connector connector = (Connector)session.getAttribute("connector");
if(connector == null) {
    connector = new Connector();
    session.setAttribute("connector", connector);
}
if(searchTypeAttribute == null || searchDisplayCountAttribute == null)
{
    %>
Please enter information below:
    <form name="stats" method=get onsubmit="return check_all_fields(this)" action="statistics.jsp">
        Show the top
        <input type=hidden name="searchDisplayCountAttribute" value="displayCount">
        <input type=text name="displayCountAttributeValue" length=10 placeholder="Display Count">
        most
        <input type=hidden name="searchTypeAttribute" value="searchType">
        <select name="typeAttributeValue">
            <option value="popular">popular</option>
            <option value="expensive">expensive</option>
            <option value="highly rated">highly rated</option>
        </select>
        TH's for each category
        <br><br>
        <input type=submit>
    </form>
    <BR><a href="statistics.jsp"> Clear All Fields</a><BR>
    <BR><a href="userDashboard.jsp">User Dashboard</a><BR>
    <%
} else {
    String typeAttributeValue = request.getParameter("typeAttributeValue");
    String displayCountAttributeValue = request.getParameter("displayCountAttributeValue");
    Application app = new Application();
    out.println("<h3>Top "+displayCountAttributeValue+" Most "+typeAttributeValue+" TH's for each category</h3>");
    if(typeAttributeValue.equals("popular")) {
        out.println(app.mostPopularThsPerCategory(displayCountAttributeValue, connector.stmt));
    } else if (typeAttributeValue.equals("expensive")) {
        out.println(app.mostExpensiveThsPerCategory(displayCountAttributeValue, connector.stmt));
    } else {
        out.println(app.mostHighlyRatedThsPerCategory(displayCountAttributeValue, connector.stmt));
    }
    %>
    <BR><BR><a href="statistics.jsp">Search Another Statistic</a>
    <%
    }
    %>
</body>
