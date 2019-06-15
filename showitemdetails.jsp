<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,shoppingcatalog.dto.ItemDTO"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="styles/stylesheet.css">
        <script type="text/javascript" src="script/jquery.js">
            </script>
            <script type="text/javascript" src="script/showItem.js"></script>
        <title>Store Items</title>
    </head>
    <body>
        <%
             String username=(String)session.getAttribute("username");
           
           
            if(username==null)
            {
                session.invalidate();
                response.sendRedirect("accessdenied.html");
            }
            else
            {
                StringBuffer displayBlock=new StringBuffer("<h1>My store-item Details</h1><p><em>You are viewing:</em><br/>");
               // System.out.println(displayBlock);
               
                ItemDTO details=(ItemDTO)request.getAttribute("itemdetails");
               
                    displayBlock.append("<strong><a href='StoreControllerServlet'>"+details.getItemType()+"&gt;</a>"+details.getItemName()+"</strong></p>");
                    displayBlock.append("<div style='float:left;'>");
                    displayBlock.append("<img src=\'images/"+details.getItemImage()+"'></div>");
                    displayBlock.append("<div style='float:left;padding-left:12px'>");
                    
                    displayBlock.append("<p><strong>Description:</strong><br/>"+details.getItemDesc()+"</p>");
                    displayBlock.append("<p><strong>Price:</strong>Rs"+details.getItemPrice()+"</p>");
                    displayBlock.append("<p><a href='addtocart.jsp?itemId="+details.getItemId()+"'>Add To Cart</a></p></div>");
                     displayBlock.append("<h4 id='logout'><a href='myorders.jsp'>My Orders</a>&nbsp;&nbsp;&nbsp;<a href='LoginControllerServlet?logout=logout'>Logout</a></h4>");
                out.println(displayBlock);
            }
        %>
    </body>
</html>