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
import java.sql.ResultSet;
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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

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
            out.println("<title>Servlet Login</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
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
        ResultSet rs=null;
        
        String username=request.getParameter("name");
        String password=request.getParameter("password");
      
        try{
            Connection con=DBConnect.getCon();
            st=con.createStatement();
              
            rs=st.executeQuery("select * from user where name='"+username+"' and password='"+password+"'");
            
            if(rs.next())
            {
                
                RequestDispatcher rd=request.getRequestDispatcher("home.jsp");
                HttpSession session=request.getSession();
                session.setAttribute("username",username);
                
                rd.forward(request, response);
        }
            else
            {
                RequestDispatcher rd=request.getRequestDispatcher("login.jsp");
                request.setAttribute("msg","Username or password incorrect !");
                rd.forward(request,response);
               
            }
        }
        catch(Exception e)
        {
        }
        }
        
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
  
  

