<%@ page language="java" import="cs5530.*" %>
<%@ page import="java.security.Key" %>
<html>
<head>
    <script LANGUAGE="javascript">

        function check_all_fields(form_obj) {
            alert(form_obj.searchHidAttribute.value+"='"+form_obj.hidAttributeValue.value+"'\n"
                + form_obj.searchWidAttribute.value+"='"+form_obj.widAttributeValue.value+"'\n");
            if(form_obj.searchHidAttribute.value == "" || form_obj.searchWidAttribute.value == "") {
                alert("All search fields should be nonempty");
                return false;
            } else {
                return true;
            }
        }
    </script>
    <title>Assign Keyword to TH</title>
    <h1 align="center">Assign Keyword to TH</h1>
</head>
<body>
    <%
String searchHidAttribute = request.getParameter("searchHidAttribute");
String searchWidAttribute = request.getParameter("searchWidAttribute");
Connector connector = (Connector)session.getAttribute("connector");
if(connector == null) {
    connector = new Connector();
    session.setAttribute("connector", connector);
}
if(searchHidAttribute == null ||  searchWidAttribute == null)
{
    %>
Please enter information Below:
    <form name="assign_Keyword" method=get onsubmit="return check_all_fields(this)" action="assignKeyword.jsp">
        <input type=hidden name="searchHidAttribute" value="hid">
        <input type=text name="hidAttributeValue" length=10 placeholder="Hid">
        <br>
        <input type=hidden name="searchWidAttribute" value="wid">
        <input type=text name="widAttributeValue" length=10 placeholder="Wid">
        <br><br>
        <input type=submit>
    </form>
    <BR><a href="assignKeyword.jsp"> Clear All Fields</a><BR>
    <BR><a href="keywordsTH.jsp"> TH Keywords Menu </a><%
    Keywords keywords = new Keywords();
    TH th = new TH();
    out.println(th.getTHForLogin((String)session.getAttribute("currentUser"), connector.stmt));
    %>
    <BR>
    <%
    out.println(keywords.getAllKeyWords(connector.stmt));
    } else {
        String hidAttributeValue = request.getParameter("hidAttributeValue");
        String widAttributeValue = request.getParameter("widAttributeValue");
        HasKeywords hasKeywords = new HasKeywords();
        TH th = new TH();
        if (th.isOwnerOfTH((String)session.getAttribute("currentUser"), hidAttributeValue, connector.stmt))
        {
            if (hasKeywords.addHasKeyword(hidAttributeValue, widAttributeValue, connector.stmt))
            {
                %>
                <b>Successfully Assigned Keyword to TH</b>
                <%
            }
        }
        else
        {
            %>
            <b>Failed to Assign Keyword to TH</b>
            <%
        }

    %>
    <BR><BR><a href="assignKeyword.jsp">Assign Keyword to Another TH</a>
<%
}
%>
</body>