/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Registerandlogin;

import DBConnection.DBConnect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ANDROID2
 */
@WebServlet(name = "Register", urlPatterns = {"/Register"})
public class Register extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Register</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Register at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
      Statement st=null;
      String username=request.getParameter("name");
      String email=request.getParameter("email");
      String password=request.getParameter("password");
      String con_password=request.getParameter("repassword");
      String gender=request.getParameter("gender");
      String phone=request.getParameter("phone");
      
      
      
      if(password.equals(con_password))
      {
          try
          {
              Connection con=DBConnect.getCon();
              st=con.createStatement();
              int add=st.executeUpdate("insert into user values ('"+username+"','"+email+"','"+password+"','"+gender+"','"+phone+"')");
              if(add>0)
              {
                  request.setAttribute("msg","Sucessfully Registered");
                  RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
                  rd.forward(request, response);
              }
              else
              {
                  request.setAttribute("msg","Register failed! try again");
                  RequestDispatcher rd=request.getRequestDispatcher("register.jsp");
                  rd.forward(request,response);
          }
          }
          catch(Exception e)
          {
              e.printStackTrace();
              System.out.println(e);
              
          }
      }
      else
          {
           
                  request.setAttribute("msg","ur password and confirm password does not match !");
                  RequestDispatcher rd=request.getRequestDispatcher("register.jsp");
                  rd.forward(request,response);
                  
                  }
                  }
          
      
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>
//
//}
