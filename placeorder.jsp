<%-- 
    Document   : placeorder
    Created on : 9 Jan, 2019, 9:33:36 AM
    Author     : Aftab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,shoppingcatalog.*,shoppingcatalog.dto.*"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script type="text/javascript" src="scripts/jquery.js">
            </script>
            <script type="text/javascript" src="scripts/removecart.js"></script>
        <title>Place Order Page</title>
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
             // int itemId=(Integer)session.getAttribute("itemid");
              StringBuffer displayBlock=new StringBuffer("<h1>My Store-Order Details</h1> ");
              displayBlock.append("<div style='float:left;'>");
              Enumeration en=session.getAttributeNames();
              displayBlock.append("<table border='1'>");
              displayBlock.append("<tr><th>Item Name</th><th>Item Price</th><th>Remove</th></tr>");
              double totalAmount=0.0;
              while(en.hasMoreElements())
              {
                  Object o=en.nextElement();
                  if(o.equals("username")==false)
                  {
                    //  if(!request.getParameter("itemName").equalsIgnoreCase("username"))
                      //{
                        // session.removeAttribute(request.getParameter("itemName"));
                      //}
                      //else
                      //{
                      
                      ItemDTO item=(ItemDTO)session.getAttribute(o.toString());
                      //String str=item.getItemName();
                   //  str=str.replace(" ","%20");
                       displayBlock.append("<tr  id='"+item.getItemId()+"'><td>"+item.getItemName()+"</td><td>"+item.getItemPrice()+"</td><td ><a href='#' onclick=removeRow('"+item.getItemId()+"')>Remove</a></td><tr>");
                       System.out.println(displayBlock);
                       totalAmount+=item.getItemPrice();
                      //}
                  }
              }
              displayBlock.append("</table>");
              displayBlock.append("<p><strong>Total Amount To Pay:</strong>"+totalAmount+"</p>");
              displayBlock.append("<p><a href='StoreControllerServlet'>Continue Shopping</a>&nbsp;&nbsp;&nbsp;&nbsp;");
              displayBlock.append("<a href='checkout.jsp?totalAmount="+totalAmount+"'>CheckOut</a></p></div>");
               displayBlock.append("<h4 id='logout'><a href='myorders.jsp'>My Orders</a>&nbsp;&nbsp;&nbsp;<a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");
                out.println(displayBlock);
             
          }
        %>
    </body>
</html>
