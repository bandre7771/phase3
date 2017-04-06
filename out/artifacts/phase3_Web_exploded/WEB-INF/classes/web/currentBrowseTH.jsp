<%@ page language="java" import="cs5530.*" %>
<html>
<head>
    <%
        //TODO: load user name
    %>
    <script LANGUAGE="javascript">

        function check_all_fields(form_obj){
            alert(form_obj.searchHidAttribute.value+"='"+form_obj.hidAttributeValue.value+"'\n"
                + form_obj.searchNameAttribute.value+"='"+form_obj.nameAttributeValue.value+"'\n"
                + form_obj.searchCategoryAttribute.value+"='"+form_obj.categoryAttributeValue.value+"'\n"
                + form_obj.searchAddressAttribute.value+"='"+form_obj.addressAttributeValue.value+"'\n"
                + form_obj.searchYearAttribute.value+"='"+form_obj.yearAttributeValue.value+"'\n"
                + form_obj.searchPictureAttribute.value+"='"+form_obj.pictureAttributeValue.value+"'\n"
                + form_obj.searchUrlAttribute.value+"='"+form_obj.urlAttributeValue.value+"'\n");
            if( form_obj.nameAttributeValue.value == "" || form_obj.categoryAttributeValue.value == "" || form_obj.addressAttributeValue.value == "" || form_obj.yearAttributeValue.value == "" || form_obj.pictureAttributeValue.value == "" || form_obj.urlAttributeValue.value == "") {
                alert("All search fields should be nonempty");
                return false;
            } else {
                return true;
            }
        }
    </script>
    <title>Browse TH</title>
    <h1 align="center">Browse TH</h1>
</head>
<body>
    <%
//searchByTHMenu("#","","#","#", "#", con);

String searchHidAttribute = request.getParameter("searchHidAttribute");
String searchNameAttribute = request.getParameter("searchNameAttribute");
String searchCategoryAttribute = request.getParameter("searchCategoryAttribute");
String searchAddressAttribute = request.getParameter("searchAddressAttribute");
String searchPhoneAttribute = request.getParameter("searchPhoneAttribute");
String searchYearAttribute = request.getParameter("searchYearAttribute");
String searchPictureAttribute = request.getParameter("searchPictureAttribute");
String searchUrlAttribute = request.getParameter("searchUrlAttribute");
if( searchHidAttribute == null || searchNameAttribute == null || searchCategoryAttribute == null || searchAddressAttribute == null || searchPhoneAttribute == null || searchYearAttribute == null || searchPictureAttribute == null || searchUrlAttribute == null)
{
    Connector connector = new Connector();
    TH th = new TH();
    %>
        <%=th%>
Search by
    <select name="th_browse" method=get onsubmit="return check_all_fields(this)" action="browseTh.jsp">
        <option name="all"> all </option>
        <option name="price"> price </option>
        <option name="address"> address </option>
        <option name="name"> name </option>
        <option name="category"> category </option>
    </select>
    <BR>
    <input type="submit">
    <BR><BR>
    <BR><a href="th.jsp"> TH Menu </a>
    <%
} else {
        String hidAttributeValue = request.getParameter("hidAttributeValue");
        Connector connector = new Connector();
        TH th = new TH();
        connector.closeConnection();
    %>
    <BR><BR><a href="browseTH.jsp"> Browse Another TH </a>
    <%
        }  // We are ending the braces for else here
    %>
</body>
