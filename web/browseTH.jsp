<%@ page language="java" import="cs5530.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<html>
<head>
    <title>Browse TH</title>
    <h1 align="center">Browse TH</h1>
</head>
<body>
<a href="browseTH.jsp"> New Search </a><BR>
    <%
        String minPriceAttributeValue = request.getParameter("minPriceAttributeValue");
        String maxPriceAttributeValue = request.getParameter("maxPriceAttributeValue");
        String addressAttributeValue = request.getParameter("addressAttributeValue");
        String nameAttributeValue = request.getParameter("nameAttributeValue");
        String categoryAttributeValue = request.getParameter("categoryAttributeValue");

        String submitButtonLabelText = "Complete Search / Sort By";

        String orderBy = request.getParameter("orderBy");

        String whereQueryAttributeValue = request.getParameter("whereQueryAttributeValue");
        if(whereQueryAttributeValue == null)
        {
            whereQueryAttributeValue = "";
        }
        else
        {
            if(!whereQueryAttributeValue.isEmpty())
            {
                if((!minPriceAttributeValue.isEmpty() && !maxPriceAttributeValue.isEmpty()) || !addressAttributeValue.isEmpty() || !nameAttributeValue.isEmpty() || !categoryAttributeValue.isEmpty())
                {
                    whereQueryAttributeValue += "OR ";
                }
            }
            boolean andRequired = false;
            if(!minPriceAttributeValue.isEmpty() && !maxPriceAttributeValue.isEmpty()) {
                if(andRequired)
                {
                    whereQueryAttributeValue += "AND ";
                }
                whereQueryAttributeValue += "(price >= "+minPriceAttributeValue+" AND price <= "+maxPriceAttributeValue+") ";
                andRequired = true;
            }
            if(!addressAttributeValue.isEmpty()) {
                if(andRequired)
                {
                    whereQueryAttributeValue += "AND ";
                }
                whereQueryAttributeValue += "address LIKE '%"+addressAttributeValue+"%' ";
                andRequired = true;
            }
            if(!nameAttributeValue.isEmpty()) {
                if(andRequired)
                {
                    whereQueryAttributeValue += "AND ";
                }
                whereQueryAttributeValue += "hname LIKE '%"+nameAttributeValue+"%' ";
                andRequired = true;
            }
            if(!categoryAttributeValue.isEmpty()) {
                if(andRequired)
                {
                    whereQueryAttributeValue += "AND ";
                }
                whereQueryAttributeValue += "category LIKE '%"+categoryAttributeValue+"%' ";
                andRequired = true;
            }
            if(!whereQueryAttributeValue.isEmpty())
            {
                submitButtonLabelText = "Sort / Add To Search (Using OR)";
            }
        }
        Connector connector = new Connector();
        Application app = new Application();
%>
<p>(Note: Search fields act as ANDs to use OR functionality complete the search)</p>
<form id="searchBy"  method=get action="browseTH.jsp">
    <h4>Search:</h4>
    <input type="hidden" name="whereQueryAttributeValue" value="<%=whereQueryAttributeValue%>">
    <input type="hidden" name="orActiveAttributeValue" value="orActive">
    Price Range (optional):<BR>
    <input type="text" name="minPriceAttributeValue" length=10 placeholder="min">
    <input type="text" name="maxPriceAttributeValue" length=10 placeholder="max"><BR>
    Address (optional):<BR>
    <input type="text" name="addressAttributeValue" length=10 placeholder="address"><BR>
    Name (optional):<BR>
    <input type="text" name="nameAttributeValue" length=10 placeholder="hname"><BR>
    Category (optional):<BR>
    <input type="text" name="categoryAttributeValue" length=10 placeholder="category"><BR>
    <h4>Sort by:</h4>
    <input type="radio" name="orderBy" value="hid" checked> hid <BR>
    <input type="radio" name="orderBy" value="average price"> price <BR>
    <input type="radio" name="orderBy" value="average fbscore"> average fbscore <BR>
    <input type="radio" name="orderBy" value="average fbscore of the trusted user feedbacks"> average fbscore of trusted user feedbacks <BR>
    <BR>
    <input type="submit" value="<%=submitButtonLabelText%>">
</form>
    <%

        %>
        Current Search: <%= whereQueryAttributeValue.isEmpty() ? "All" : whereQueryAttributeValue%> <BR>
        Sorted By: <%=orderBy == null ? "hid" : orderBy %>
        <%
        if(orderBy != null)
        {
            if(orderBy.equals("average price"))
            {
                out.println(app.browsingTH(whereQueryAttributeValue, true, false, false, connector.stmt));
            }
            else if (orderBy.equals("average fbscore"))
            {
                out.println(app.browsingTH(whereQueryAttributeValue, false, true, false, connector.stmt));
            }
            else if (orderBy.equals("average fbscore of the trusted user feedbacks"))
            {
                out.println(app.browsingTH(whereQueryAttributeValue, false, true, true, connector.stmt));
            }
            else // orderBy.equals("hid")
            {
                out.println(app.browsingTH(whereQueryAttributeValue, false, false, false, connector.stmt));
            }
        }
        else
        {
            out.println(app.browsingTH(whereQueryAttributeValue, false, false, false, connector.stmt));
        }
    %>
<BR><a href="th.jsp"> TH Menu </a>

</body>
