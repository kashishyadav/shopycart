package shoppingcatalog.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Aftab
 */
public class RemoveCartControllerServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        RequestDispatcher rd=null;
        System.out.println("rcs");
       HttpSession session=request.getSession();
       int itemId=Integer.parseInt((String)request.getParameter("itemName"));
       try
       {
           System.out.println("itemName is:"+itemId);
           Enumeration en=session.getAttributeNames();
           while(en.hasMoreElements())
           {
               Object o=en.nextElement();
               System.out.println(o);
                  if(o.equals("username")==false)
                  {
                    // if(!request.getParameter("itemName").equalsIgnoreCase("username"))
                      //{
                         session.removeAttribute(request.getParameter("itemName"));
                  }
           }
           
           request.setAttribute("itemId", String.valueOf(itemId));
           System.out.println("set attribute in servlet");
           rd=request.getRequestDispatcher("removecart.jsp");
       }
       catch(Exception e)
        {
            request.setAttribute("exception", e);
            rd=request.getRequestDispatcher("showexception.jsp");
            System.out.println("Exception from AdminController:"+e);
            e.printStackTrace();
        }
        finally
        {
          rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
