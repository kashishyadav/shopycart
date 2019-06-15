<%
    boolean userFound=(Boolean)request.getAttribute("userFound");
    boolean result=(Boolean)request.getAttribute("result");
    if(userFound==true)
    {
        out.println("uap");
        
    }
    else if(result==false)
    {
        out.println("failure");
    }
    else
    {
        out.println("success");
    }
%>
