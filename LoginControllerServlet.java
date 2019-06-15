/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shoppingcatalog.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import shoppingcatalog.dao.LoginDAO;
import shoppingcatalog.dto.UserDTO;

/**
 *
 * @author Aftab
 */
public class LoginControllerServlet extends HttpServlet {

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
      String username=request.getParameter("username");
      String password=request.getParameter("password");
      String usertype=request.getParameter("usertype");
      String logout=request.getParameter("logout");
      if(logout!=null)
      {  
          HttpSession session=request.getSession();
          session.invalidate();
          response.sendRedirect("login.html");
          return;
      }
      UserDTO user=new UserDTO();
      user.setUsername(username);
      user.setPassword(password);
      user.setUsertype(usertype);
      
      RequestDispatcher rd=null;
      try
      {
          boolean result=LoginDAO.validateUser(user);
          request.setAttribute("result", result);
          request.setAttribute("username", username);
          request.setAttribute("usertype", usertype);
          System.out.println("loginresponse");
          rd=request.getRequestDispatcher("loginresponse.jsp");
          
      }
      catch(Exception ex)
      {
          request.setAttribute("exception", ex);
          rd=request.getRequestDispatcher("showexception.jsp");
          
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
