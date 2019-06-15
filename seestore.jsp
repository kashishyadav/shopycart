<%-- 
    Document   : seestore
    Created on : 6 Jan, 2019, 10:11:10 AM
    Author     : Aftab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/stylesheet.css">
        <script type="text/javascript" src="scripts/jquery.js">
            </script>
            <script type="text/javascript" src="scripts/showitems.js?v=1"></script>
        <title>Store Items</title>
    </head>
    <body>
        <%@include file="logo.html"  %>
        <%
             String username=(String)session.getAttribute("username");
           
           
            if(username==null)
            {
                session.invalidate();
                response.sendRedirect("accessdenied.html");
            }
            else
            {
                StringBuffer displayBlock=new StringBuffer("<h1>My Categories</h1><p>Select a category to see its items.</p>");
               // System.out.println(displayBlock);
                List<String> itemList=(List<String>)request.getAttribute("category");
                for(String itemType:itemList)
                {
                    displayBlock.append("<p id='"+itemType+"'><strong><a href='#' onclick=getItemNames('"+itemType+"')><span>+"+itemType+"</span></a></strong></p>");
                    
                }
                
                displayBlock.append("<h4 id='logout'><a href='myorders.jsp'>My Orders</a>&nbsp;&nbsp;&nbsp;<a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");
                out.println(displayBlock);
            }
        %>
    </body>
</html>
