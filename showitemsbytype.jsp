<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,shoppingcatalog.dto.*"%>

        <%
             String username=(String)session.getAttribute("username");
           
           
            if(username==null)
            {
                session.invalidate();
                response.sendRedirect("accessdenied.html");
            }
            else
            {
                StringBuffer displayBlock=new StringBuffer("<ul>");
               // System.out.println(displayBlock);
                List<ItemInfoDTO> itemList=(List<ItemInfoDTO>)request.getAttribute("itemtype");
                for(ItemInfoDTO itemType:itemList)
                {
                    displayBlock.append("<li id='"+itemType.getItemId()+"'><a href='StoreControllerServlet?itemId="+itemType.getItemId()+"'>"+itemType.getItemName()+"</a></li>");
                    
                }
                displayBlock.append("</ul");
                out.println(displayBlock);
            }
        %>