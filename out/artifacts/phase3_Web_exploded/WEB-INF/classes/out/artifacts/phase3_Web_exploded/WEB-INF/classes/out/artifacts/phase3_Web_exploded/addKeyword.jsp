<%@ page language="java" import="cs5530.*" %>
<html>
<head>
    <script LANGUAGE="javascript">
        function check_all_fields(form_obj){
            alert(form_obj.searchWordAttribute.value+"='"+form_obj.wordAttributeValue.value+"'\n"
                + form_obj.searchLanguageAttribute.value+"='"+form_obj.languageAttributeValue.value+"'\n");
            if( form_obj.wordAttributeValue.value == "" || form_obj.languageAttributeValue.value == "") {
                alert("All search fields should be nonempty");
                return false;
            } else {
                return true;
            }
        }
    </script>
    <title>Add Keyword</title>
    <h1 align="center">Add Keyword</h1>
</head>
<body>
    <%
String searchWordAttribute = request.getParameter("searchWordAttribute");
String searchLanguageAttribute = request.getParameter("searchLanguageAttribute");
if( searchWordAttribute == null || searchLanguageAttribute == null) {
%>
Please enter Keyword information Below:
    <form name="keyword_add" method=get onsubmit="return check_all_fields(this)" action="addKeyword.jsp">
        <input type=hidden name="searchWordAttribute" value="word">
        <input type=text name="wordAttributeValue" length=10 placeholder="Word">
        <br>
        <input type=hidden name="searchLanguageAttribute" value="language">
        <input type=text name="languageAttributeValue" length=10 placeholder="Language">
        <br><br>
        <input type=submit>
    </form>
    <BR><a href="addKeyword.jsp"> Clear All Fields</a><BR>
    <BR><a href="keywordsTH.jsp"> TH Keywords Menu </a>
    <%
} else {
        String wordAttributeValue = request.getParameter("wordAttributeValue");
        String languageAttributeValue = request.getParameter("languageAttributeValue");

        Connector connector = (Connector)session.getAttribute("connector");
        if(connector == null) {
            connector = new Connector();
            session.setAttribute("connector", connector);
        }
        Keywords keywords = new Keywords();
        if (keywords.addKeyword(wordAttributeValue, languageAttributeValue, connector.stmt)) {
            %>
             <b>Successfully Added Keyword</b>
            <%
        } else {
            %>
            <b>Failed to Add Keyword</b>
            <%
        }
    %>
    <BR><BR><a href="addKeyword.jsp"> Add Another Keyword </a>
    <%
    }  // We are ending the braces for else here
    %>
</body>
