/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package store;

import DBConnection.DBConnect;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Sq1
 */
public class Joingroup extends HttpServlet {

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
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet joingroup</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet joingroup at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>")
            
            try{
                Connection con=DBConnect.getCon();
                Statement st=con.createStatement();
                String groupid=request.getParameter("group");
                if(groupid.equals("0")){
                    request.setAttribute("msg","Select Groupname!!!");
                }
                else{
                    HttpSession session=request.getSession();
                String username=(String)session.getAttribute("username");
                int i=st.executeUpdate("insert into usergroup (groupid,username) values ('"+groupid+"','"+username+"')");
                if(i>0){
                    request.setAttribute("msg","Joined Group Successfully!!!");
                }
                else{
                    request.setAttribute("msg","Failed!!!");
                }
                    
                }
                
                RequestDispatcher rd=request.getRequestDispatcher("joingroup.jsp");
                rd.forward(request, response);
                
                
            }
            catch(Exception e){
                System.out.println(e);
            }
            
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
