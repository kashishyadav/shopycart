<%-- 
    Document   : showorders
    Created on : 12 Feb, 2019, 2:42:28 PM
    Author     : Aftab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,shoppingcatalog.dto.*,java.text.*"%>
<%
    ArrayList<OrderDTO> orders=(ArrayList)request.getAttribute("orders");
    StringBuffer displayBlock=new StringBuffer("<table border=2>");
    SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
    displayBlock.append("<tr><th>Order Id</th><th>Order Amount</th><th>Order Date</th><th>View Details</th></tr>");
    for(OrderDTO o:orders)
    {
        String date=sdf.format(o.getOrderDate());
        displayBlock.append("<tr><td>"+o.getOrderId()+"</td><td>"+o.getOrderAmount()+"</td><td>"+date+"</td><td><a href='#'  id='"+o.getOrderId()+"' onclick='viewOrderDetails()'>Click Here</a></td></tr>");
    }
    displayBlock.append("</table>");
    System.out.println(displayBlock);
    out.println(displayBlock);
%>
