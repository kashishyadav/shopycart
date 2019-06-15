<%-- 
    Document   : checkout
    Created on : 9 Jan, 2019, 10:41:54 AM
    Author     : Aftab
--%>

<%@page import="shoppingcatalog.dao.StoreDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,shoppingcatalog.dto.*"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="styles/stylesheet.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CheckOut Page</title>
    </head>
    <body>
        <%@include file="logo.html" %>
        <%
            String username=(String)session.getAttribute("username");
            if(username==null)
            {
                response.sendRedirect("accessdenied.jsp");
            }
            else
            {
                String totalAmount=request.getParameter("totalAmount");
                StringBuffer displayBlock=new StringBuffer("<h1>My Store-CheckOut Page</h1>");
                displayBlock.append("<div style='float:left;'>");
                displayBlock.append("<p><strong>Thank You For Shopping With Us!!<strong></p>");
                displayBlock.append("<p><strong>Your Payment Of Rs "+totalAmount+"is under processing!!</strong></p>");
                
                Enumeration en=session.getAttributeNames();
                ArrayList<ItemDTO> itemList=new ArrayList<ItemDTO>();
                
                while(en.hasMoreElements())
                {
                    Object o=en.nextElement();
                    if(o.equals("username")==false)
                    {
                        ItemDTO item=(ItemDTO)session.getAttribute(o.toString());
                        itemList.add(item);
                        session.removeAttribute(o.toString());
                    }
                }
                try
                {
                    StoreDAO.addOrder(username, itemList, Double.parseDouble(totalAmount));
                    displayBlock.append("<p><strong>Order saved in the database:</strong></p>");
                    
                }
                catch(Exception ex)
                {
                    System.out.println("Exception from storeModel.addOrder:"+ex);
                    ex.printStackTrace();
                }
                
                displayBlock.append("<p><a href='StoreControllerServlet'>Shop Again</a>&nbsp;&nbsp;&nbsp;&nbsp;");
                displayBlock.append("<a href='myorders.jsp'>My Orders</a></p></div>");
                 displayBlock.append("<h4 id='logout'><a href='myorders.jsp'>My Orders</a>&nbsp;&nbsp;&nbsp;<a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");
                out.println(displayBlock);
                
            }
        %>
    </body>
</html>
