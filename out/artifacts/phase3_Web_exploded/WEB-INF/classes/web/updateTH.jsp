<%@ page language="java" import="cs5530.*" %>
<html>
<head>
    <script LANGUAGE="javascript">

        function check_all_fields(form_obj) {
            alert(form_obj.searchHidAttribute.value+"='"+form_obj.hidAttributeValue.value+"'\n"
                + form_obj.searchNameAttribute.value+"='"+form_obj.nameAttributeValue.value+"'\n"
                + form_obj.searchCategoryAttribute.value+"='"+form_obj.categoryAttributeValue.value+"'\n"
                + form_obj.searchAddressAttribute.value+"='"+form_obj.addressAttributeValue.value+"'\n"
                + form_obj.searchYearAttribute.value+"='"+form_obj.yearAttributeValue.value+"'\n"
                + form_obj.searchPictureAttribute.value+"='"+form_obj.pictureAttributeValue.value+"'\n"
                + form_obj.searchUrlAttribute.value+"='"+form_obj.urlAttributeValue.value+"'\n");
            if(form_obj.hidAttributeValue.value == "" || form_obj.nameAttributeValue.value == "" || form_obj.categoryAttributeValue.value == "" || form_obj.addressAttributeValue.value == "" || form_obj.yearAttributeValue.value == "" || form_obj.pictureAttributeValue.value == "" || form_obj.urlAttributeValue.value == "") {
                alert("All search fields should be nonempty");
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
String searchHidAttribute = request.getParameter("searchHidAttribute");
String searchNameAttribute = request.getParameter("searchNameAttribute");
String searchCategoryAttribute = request.getParameter("searchCategoryAttribute");
String searchAddressAttribute = request.getParameter("searchAddressAttribute");
String searchPhoneAttribute = request.getParameter("searchPhoneAttribute");
String searchYearAttribute = request.getParameter("searchYearAttribute");
String searchPictureAttribute = request.getParameter("searchPictureAttribute");
String searchUrlAttribute = request.getParameter("searchUrlAttribute");
if(searchHidAttribute == null ||  searchNameAttribute == null || searchCategoryAttribute == null || searchAddressAttribute == null || searchPhoneAttribute == null || searchYearAttribute == null || searchPictureAttribute == null || searchUrlAttribute == null)
{
    Connector connector = new Connector();
    TH th = new TH();
    %>
        <%=th.getTHForLogin((String)session.getAttribute("currentUser"), connector.stmt)%>
Please enter TH information Below:
    <form name="th_update" method=get onsubmit="return check_all_fields(this)" action="updateTH.jsp">
        <input type=hidden name="searchHidAttribute" value="hid">
        <input type=text name="hidAttributeValue" length=10 placeholder="Hid">
        <br>
        <input type=hidden name="searchNameAttribute" value="hname">
        <input type=text name="nameAttributeValue" length=10 placeholder="Name">
        <br>
        <input type=hidden name="searchCategoryAttribute" value="category">
        <input type=text name="categoryAttributeValue" length=10 placeholder="Category">
        <br>
        <input type=hidden name="searchAddressAttribute" value="address">
        <input type=text name="addressAttributeValue" length=10 placeholder="Address">
        <br>
        <input type=hidden name="searchPhoneAttribute" value="phone_number">
        <input type=text name="phoneAttributeValue" length=10 placeholder="Phone Number">
        <br>
        <input type=hidden name="searchYearAttribute" value="year_built">
        <input type=text name="yearAttributeValue" length=10 placeholder="Year Built">
        <br>
        <input type=hidden name="searchPictureAttribute" value="picture">
        <input type=text name="pictureAttributeValue" length=10 placeholder="image (url)">
        <br>
        <input type=hidden name="searchUrlAttribute" value="url">
        <input type=text name="urlAttributeValue" length=10 placeholder="URL">
        <br><br>
        <input type=submit>
    </form>
    <BR><BR>
    <BR><a href="updateTH.jsp"> Reset </a></p>
    <BR><a href="th.jsp"> TH Menu </a></p>
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
        Connector connector = new Connector();
        TH th = new TH();
        if (th.isOwnerOfTH((String)session.getAttribute("currentUser"), hidAttributeValue, connector.stmt))
        {
            if (th.updateTH(hidAttributeValue, categoryAttributeValue, (String)session.getAttribute("currentUser"), nameAttributeValue, addressAttributeValue, urlAttributeValue, phoneAttributeValue, yearAttributeValue, pictureAttributeValue, connector.stmt))
            {
                    %>
                    <p><b>Successfully Update TH</b>
                    <%
            }
        }
        else
        {
                %>
                <p><b>Failed to Update TH</b>
                <%
        }
        connector.closeConnection();
    %>
    <BR><BR><a href="updateTH.jsp"> Update Another TH </a></p>
    <%
        }  // We are ending the braces for else here
    %>
</body>
