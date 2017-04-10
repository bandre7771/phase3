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
    <title>Update TH</title>
    <h1 align="center">Update TH</h1>
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
    out.println("<h3>All other user feedbacks: <h3>");
    out.println(feedback.getAllOtherUserFeedback((String)session.getAttribute("currentUser"), connector.stmt));
    %>

Please enter TH information Below:
    <form name="th_update" method=get onsubmit="return check_all_fields(this)" action="updateTH.jsp">
        <input type=hidden name="searchFidAttribute" value="fid">
        <input type=text name="fidAttributeValue" length=10 placeholder="Fid">
        Rating:
        <input type=hidden name="searchRatingAttribute" value="rating">
        <select>
            <option name="ratingAttributeValue" value="0">useless</option>
            <option name="ratingAttributeValue" value="1">useful</option>
            <option name="ratingAttributeValue" value="2">very useful</option>
        </select>
        <br><br>
        <input type=submit>
    </form>
    <BR><a href="updateTH.jsp"> Clear All Fields</a><BR>
    <BR><a href="th.jsp"> TH Menu </a>
    <%

} else {
        String hidAttributeValue = request.getParameter("hidAttributeValue");
        String nameAttributeValue = request.getParameter("nameAttributeValue");
        String categoryAttributeValue = request.getParameter("categoryAttributeValue");
        String addressAttributeValue = request.getParameter("addressAttributeValue");
        String phoneAttributeValue = request.getParameter("phoneAttributeValue");
        String yearAttributeValue = request.getParameter("yearAttributeValue");
        String pictureAttributeValue = request.getParameter("pictureAttributeValue");
        String urlAttributeValue = request.getParameter("urlAttributeValue");
        TH th = new TH();
        if (th.isOwnerOfTH((String)session.getAttribute("currentUser"), hidAttributeValue, connector.stmt))
        {
            if (th.updateTH(hidAttributeValue, categoryAttributeValue, (String)session.getAttribute("currentUser"), nameAttributeValue, addressAttributeValue, urlAttributeValue, phoneAttributeValue, yearAttributeValue, pictureAttributeValue, connector.stmt))
            {
                    %>
                    <b>Successfully Updated TH</b>
                    <%
            }
        }
        else
        {
                %>
                <b>Failed to Update TH</b>
                <%
        }

    %>
    <BR><BR><a href="updateTH.jsp"> Update Another TH </a>
    <%
    }
    %>
</body>
