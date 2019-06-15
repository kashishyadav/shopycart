<%@page import="java.util.*"%>
<%
    ArrayList<String> id=(ArrayList)request.getAttribute("users");
    StringBuffer displayBlock=new StringBuffer("");
    for(int i=0;i<id.size();i++)
    {
        displayBlock.append("<option id='"+id.get(i)+"'>"+id.get(i)+"</option>");
    }
   // displayBlock.append("</select>");
   System.out.println(displayBlock);
    out.println(displayBlock);
%>