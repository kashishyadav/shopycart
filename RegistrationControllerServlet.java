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
import shoppingcatalog.dao.RegistrationDAO;
import shoppingcatalog.dto.UserDTO;

/**
 *
 * @author Aftab
 */
public class RegistrationControllerServlet extends HttpServlet {

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
        RequestDispatcher rd=null;
              try
              {
                  boolean userFound=RegistrationDAO.searchUser(username);
                  boolean result=false;
                  if(!userFound)
                  {
                      UserDTO user=new UserDTO();
                      user.setUsername(username);
                      user.setPassword(password);
                      user.setUsertype("CUSTOMER");
                      result=RegistrationDAO.registerUser(user);
                  }
                  request.setAttribute("result", result);
                  request.setAttribute("userFound", userFound);
                  System.out.println("Result is:"+result+"  "+userFound);
                  rd=request.getRequestDispatcher("registrationresponse.jsp");
                  
                  
              }
              catch(Exception ex)
              {
                  System.out.println("Exception in RCS"+ex);
                  ex.printStackTrace();
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
