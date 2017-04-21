<%@ page language="java" import="cs5530.*" %>
<html>
<head>
    <script LANGUAGE="javascript">

        function check_all_fields(form_obj) {
            alert(form_obj.searchFidAttribute.value+"='"+form_obj.fidAttributeValue.value+"'\n"
                + form_obj.searchRatingAttribute.value+"='"+form_obj.ratingAttributeValue.value+"'\n");
            if(form_obj.fidAttributeValue.value == "" || form_obj.ratingAttributeValue.value == "") {
                alert("All fields should be nonempty");
                return false;
            } else {
                return true;
            }
        }
    </script>
    <title>Usefulness Ratings</title>
    <h1 align="center">Usefulness Ratings</h1>
</head>
<body>

    <%
String searchFidAttribute = request.getParameter("searchFidAttribute");
String searchRatingAttribute = request.getParameter("searchRatingAttribute");


Connector connector = (Connector)session.getAttribute("connector");
if(connector == null) {
    connector = new Connector();
    session.setAttribute("connector", connector);
}
if(searchFidAttribute == null || searchRatingAttribute == null)
{
    Feedback feedback = new Feedback();
    out.println("<h3>All other user feedbacks<h3>");
    out.println(feedback.getAllOtherUserFeedback((String)session.getAttribute("currentUser"), connector.stmt));
    %>

Please enter usefulness ratings below:
    <form name="usefulness_ratings" method=get onsubmit="return check_all_fields(this)" action="usefulness_ratings.jsp">
        <input type=hidden name="searchFidAttribute" value="fid">
        <input type=text name="fidAttributeValue" length=10 placeholder="Fid">
        Rating:
        <input type=hidden name="searchRatingAttribute" value="rating">
        <select name="ratingAttributeValue">
            <option value="0">useless</option>
            <option value="1">useful</option>
            <option value="2">very useful</option>
        </select>
        <br><br>
        <input type=submit>
    </form>
    <BR><a href="usefulness_ratings.jsp"> Clear All Fields</a><BR>
    <BR><a href="userDashboard.jsp">User Dashboard</a>
    <%
} else {
        String fidAttributeValue = request.getParameter("fidAttributeValue");
        String ratingAttributeValue = request.getParameter("ratingAttributeValue");
        Rates rates = new Rates();
        if (rates.addRating(fidAttributeValue, (String)session.getAttribute("currentUser"), ratingAttributeValue, connector.stmt))
        {
            %>
            <b>Successfully Rated Feedback</b>
            <%
        }
        else
        {
            %>
            <b>Failed to Rate Feedback</b>
            <%
        }
    %>
    <BR><BR><a href="usefulness_ratings.jsp">Rate Another Feedback</a>
    <%
    }
    %>
</body>
