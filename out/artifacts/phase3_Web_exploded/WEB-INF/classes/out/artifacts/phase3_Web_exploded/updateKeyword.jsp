<%@ page language="java" import="cs5530.*" %>
<%@ page import="java.security.Key" %>
<html>
<head>
    <script LANGUAGE="javascript">

        function check_all_fields(form_obj) {
            alert(form_obj.searchWidAttribute.value+"='"+form_obj.widAttributeValue.value+"'\n"
                + form_obj.searchWordAttribute.value+"='"+form_obj.wordAttributeValue.value+"'\n"
                + form_obj.searchLanguageAttribute.value+"='"+form_obj.languageAttributeValue.value+"'\n");
            if(form_obj.searchWidAttribute.value == "" || form_obj.searchWordAttribute.value == "" || form_obj.searchLanguageAttribute.value == "") {
                alert("All fields should be nonempty");
                return false;
            } else {
                return true;
            }
        }
    </script>
    <title>Update Keyword</title>
    <h1 align="center">Update Keyword</h1>
</head>
<body>
    <%
String searchWidAttribute = request.getParameter("searchWidAttribute");
String searchWordAttribute = request.getParameter("searchWordAttribute");
String searchLanguageAttribute = request.getParameter("searchLanguageAttribute");
Connector connector = (Connector)session.getAttribute("connector");
if(connector == null) {
    connector = new Connector();
    session.setAttribute("connector", connector);
}
if(searchWidAttribute == null ||  searchWordAttribute == null || searchLanguageAttribute == null)
{
    Keywords keywords = new Keywords();

    out.println(keywords.getAllKeyWords(connector.stmt));
    %>
Please enter Keyword information Below:
    <form name="th_update" method=get onsubmit="return check_all_fields(this)" action="updateKeyword.jsp">
        <input type=hidden name="searchWidAttribute" value="wid">
        <input type=text name="widAttributeValue" length=10 placeholder="Wid">
        <br>
        <input type=hidden name="searchWordAttribute" value="word">
        <input type=text name="wordAttributeValue" length=10 placeholder="Word">
        <br>
        <input type=hidden name="searchLanguageAttribute" value="language">
        <input type=text name="languageAttributeValue" length=10 placeholder="Language">
        <br><br>
        <input type=submit>
    </form>
    <BR><a href="updateKeyword.jsp"> Clear All Fields</a><BR>
    <BR><a href="keywordsTH.jsp"> TH Keywords Menu </a>
    <%

} else {
        String widAttributeValue = request.getParameter("widAttributeValue");
        String wordAttributeValue = request.getParameter("wordAttributeValue");
        String languageAttributeValue = request.getParameter("languageAttributeValue");
        Keywords keywords = new Keywords();
        if (keywords.updateKeyword(widAttributeValue, wordAttributeValue, languageAttributeValue, connector.stmt))
        {
            %>
            <p><b>Successfully Updated Keyword</b>
            <%
        }
        else
        {
            %>
            <p><b>Failed to Update Keyword</b>
            <%
        }
    %>
    <BR><BR><a href="updateKeyword.jsp"> Update Another Keyword </a>
    <%
        }  // We are ending the braces for else here
    %>
</body>
