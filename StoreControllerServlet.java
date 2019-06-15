/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shoppingcatalog.dao.StoreDAO;
import shoppingcatalog.dto.ItemDTO;
import shoppingcatalog.dto.ItemInfoDTO;

/**
 *
 * @author Aftab
 */
public class StoreControllerServlet extends HttpServlet {

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
          HttpSession session=request.getSession();
       
        try
        {
            String username=(String)session.getAttribute("username");
            if(username==null)
            {
                session.invalidate();
                response.sendRedirect("accessdenied.html");
            }
             else
            {
             
            String itemtype=(String)request.getParameter("itemtype");
            String itemid=(String)request.getParameter("itemId");
            if(itemtype==null&&itemid==null)
            {
               // System.out.println("if item itemid");
                List<String> category=StoreDAO.getItemType();
                request.setAttribute("category",category);
               //response.sendRedirect("seestore.jsp");
               rd=request.getRequestDispatcher("seestore.jsp");
            }
             else if(itemid!=null)
                 {
                // System.out.println("if itemid");
                ItemDTO details=StoreDAO.getItemDetails(Integer.parseInt(itemid));
                request.setAttribute("itemdetails", details);
                rd=request.getRequestDispatcher("showitemdetails.jsp");
                
                
                }
           
             else if(itemtype!=null)
            {
                
               
                
                // System.out.println("if itemtype");
                List<ItemInfoDTO> items=StoreDAO.getItemByType(itemtype);
                request.setAttribute("itemtype", items);
                rd=request.getRequestDispatcher("showitemsbytype.jsp");
                 
                 }
            
            
            }
        }
        catch(Exception ex)
        {
            System.out.println("Exception in SCS:"+ex);
            rd=request.getRequestDispatcher("showexception.jsp");
            ex.printStackTrace();
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
