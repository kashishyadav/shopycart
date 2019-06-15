<%-- 
    Document   : orderdetails
    Created on : 17 Jan, 2019, 4:56:55 AM
    Author     : Aftab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,shoppingcatalog.dto.*,java.text.*"%>
    <%
             String username=(String)session.getAttribute("username");
           
           
            if(username==null)
            {
                session.invalidate();
                response.sendRedirect("accessdenied.html");
            }
            else
            {
                StringBuffer displayBlock=new StringBuffer("<table border=1>");
              ArrayList<OrderDetailsDTO> details=(ArrayList<OrderDetailsDTO>)request.getAttribute("orderdetails");
              displayBlock.append("<tr><th>Order Id</th><th>Item Name</th><th>Item Price</th><th>Order Date</th></tr>");
              SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-yyyy");
                for(OrderDetailsDTO ode:details)
                {
                    String str=sdf.format(ode.getOrderDate());
                    displayBlock.append("<tr><td>"+ode.getOrdId()+"</td><td>"+ode.getItemName()+"</td><td>"+ode.getItemPrice()+"</td><td>"+str+"</td></tr>");
                    
                }
                displayBlock.append("</table");
                out.println(displayBlock);
            }
        %>
