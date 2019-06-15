<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/stylesheet.css">
        <script type="text/javascript" src="scripts/jquery.js">
            </script>
            <script type="text/javascript" src="scripts/showitems.js"></script>
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
                StringBuffer displayBlock=new StringBuffer("<h1>Admin Store</h1><p>Select a category to see its items.</p>");
               // System.out.println(displayBlock);
               // List<String> itemList=(List<String>)request.getAttribute("category");
//                for(String itemType:itemList)
//                {
                      displayBlock.append("<p id='Products'><strong><a href='#' onclick=getItemNames('Products')><span>+Products</span></a></strong></p>");
                    displayBlock.append("<p id='View'><strong><a href='#' onclick=getItemNames('View')><span>+View</span></a></strong></p>");
                      displayBlock.append("<p id='Users'><strong><a href='#' onclick=getItemNames('Users')><span>+Users</span></a></strong></p>");
//                    
//                }
                
                displayBlock.append("<h4 id='logout'><a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");
                out.println(displayBlock);
            }
        %>
    </body>
</html>
