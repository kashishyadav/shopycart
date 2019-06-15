<%-- 
    Document   : myorders
    Created on : 11 Jan, 2019, 9:47:54 AM
    Author     : Aftab
--%>

<%@page import="shoppingcatalog.dao.StoreDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,shoppingcatalog.dto.OrderDTO,java.text.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/stylesheet.css">
        <script type="text/javascript" src="scripts/jquery.js">
            </script>
            <script type="text/javascript" src="scripts/orderdetails.js"></script>
        <title>My Orders Page</title>
    </head>
    <body>
        <%@include file='logo.html' %>
       <%
           String username=(String)session.getAttribute("username");
           if(username==null)
            {
                response.sendRedirect("accessdenied.html");
            }
           else
           {
               StringBuffer displayBlock=new StringBuffer("<h1>My Store-My Orders</h1>");
               displayBlock.append("<div style=float:left;>");
               ArrayList<OrderDTO> orders=StoreDAO.getOrdersByCustomer(username);
               if(orders.size()==0)
               {
                   displayBlock.append("<p><strong>You have not placed any order yet</strong></p>");
                  
               }
               else
               {
                    displayBlock.append("<table id='t1' border=1>");
                    SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
                   displayBlock.append("<tr><th>Order Id</th><th>Order Amount</th><th>Order Date</th></tr>");
                   for(OrderDTO orderList:orders)
                   {
                       String datestr=sdf.format(orderList.getOrderDate());
                    displayBlock.append("<tr><td><a href='#' onclick=getdetails('"+orderList.getOrderId()+"')>"+orderList.getOrderId()+"</a></td><td>"+orderList.getOrderAmount()+"</td><td>"+datestr+"</td></tr>");
                    // displayBlock.append("<tr><td>naman</td></tr>");
                      
                        
                   }
                   displayBlock.append("</table>");
                   
                      // System.out.println(displayBlock);
                   
               }
               displayBlock.append("<p><a href='StoreControllerServlet'>Show Categories</a></p><div>");
                displayBlock.append("<h4 id='logout'><a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");
                
                
                
                out.println(displayBlock);
               
           }
       %>
    </body>
</html>
