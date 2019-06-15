/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shoppingcatalog.dao.StoreDAO;
import shoppingcatalog.dto.ItemDTO;

/**
 *
 * @author Aftab
 */
public class AdminControllerServlet extends HttpServlet {

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
               HttpSession session=request.getSession();
        String username=(String)session.getAttribute("username");
        RequestDispatcher rd=null;
        System.out.println("username is "+username);
        try
        {
        if(username==null)
          {
              
              session.invalidate();
              rd=request.getRequestDispatcher("accessdenied.html");
              System.out.println("username is null");
          }
          else
        {
              String str=request.getParameter("str");
              String prodId=request.getParameter("prodId");
              if(str==null && prodId==null)
                rd=request.getRequestDispatcher("adminoptions.jsp");
              else if(str!=null)
              {
              ArrayList<Integer>itemIdList=StoreDAO.getAllProductId();
              System.out.println("Got item id list");
              request.setAttribute("itemIdList", itemIdList);
              rd=request.getRequestDispatcher("adminstore.jsp");
              }
              else
              {
              ItemDTO item=StoreDAO.getItemDetails(Integer.parseInt(prodId));
              System.out.println("Got item details");
              request.setAttribute("itemDetails", item);
              rd=request.getRequestDispatcher("senditemdetails.jsp");
              }
              
        }     
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
