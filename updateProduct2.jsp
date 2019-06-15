<%-- 
    Document   : updateProduct2
    Created on : 3 Feb, 2019, 7:44:37 PM
    Author     : Aftab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="org.json.JSONObject,shoppingcatalog.dto.*"%>

<%
    JSONObject json=new JSONObject();
    ItemDTO item=(ItemDTO)request.getAttribute("itemDetails");
    json.put("pname", item.getItemName());
    json.put("ptype", item.getItemType());
    json.put("pprice",item.getItemPrice());
    json.put("pdesc", item.getItemDesc());
    json.put("pimage", item.getItemImage());
    //json=JSON.stringify(json);
    out.println(json);
%>
