<%-- 
    Document   : addtocart
    Created on : 8 Jan, 2019, 11:05:11 PM
    Author     : Aftab
--%>


<%@page contentType="text/html" pageEncoding="UTF-8" import="shoppingcatalog.dto.ItemDTO,shoppingcatalog.dao.StoreDAO,java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add To Cart Page</title>
    </head>
    <body>
        <%@include file='logo.html' %>
       <%
          String username=(String)session.getAttribute("username");
          if(username==null)
          {
              session.invalidate();
              response.sendRedirect("accessdenied.html");
          }
          else
          {
          int itemid=Integer.parseInt((String)request.getParameter("itemId"));
          ItemDTO item=StoreDAO.getItemDetails(itemid);
          session.setAttribute(String.valueOf(item.getItemId()), item);
          StringBuffer displayBlock=new StringBuffer("<h1>My store-shopping cart</h1>");
          displayBlock.append("<div style='float:left;'>");
          displayBlock.append("<p><strong>Item Added Successfully!</strong></p>");
          displayBlock.append("<p><strong>ItemId:</strong>"+item.getItemId()+"</p>");
          displayBlock.append("<p><strong>ItemName:</strong>"+item.getItemName()+"</p>");
          Enumeration en=session.getAttributeNames();
          int count=0;
          while(en.hasMoreElements())
          {
              ++count;
              en.nextElement();
          }
          displayBlock.append("<p><strong>Total Items In Cart:</strong>"+(count-1)+"</p>");
          displayBlock.append("<p><a href='StoreControllerServlet'>Continue Shopping</a>&nbsp;&nbsp;&nbsp;&nbsp;");
          displayBlock.append("<a href='placeorder.jsp'>Place Order</a></p></div>");
           displayBlock.append("<h4 id='logout'><a href='myorders.jsp'>My Orders</a>&nbsp;&nbsp;&nbsp;<a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");
                out.println(displayBlock);
          }
           
           
       
       %>
    </body>
</html>
