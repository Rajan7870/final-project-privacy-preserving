/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.cloud;

import DBConnection.DBConnect;
import com.upload.SendMail;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class FileSharing extends HttpServlet {

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
             Statement st;
             Statement st1;
             Connection con;
             ResultSet rs;
            try {
                con = DBConnect.getCon();
                st = con.createStatement();
                String username;
                HttpSession session=request.getSession();
                username=(String)session.getAttribute("username");
                int fileid=Integer.parseInt(request.getParameter("file"));
                String sharingusername=request.getParameter("shareusername");
                int time=Integer.parseInt(request.getParameter("time"));
        session.setAttribute("sharingusername",sharingusername);
                
//                String path1="D:/Self Destructing/"+sharingusername+"/Binary Files/";
//                String path2="D:/Self Destructing/"+sharingusername+"/Encrypted Files/";
                String path3="D:/Self Destructing/"+sharingusername+"/Downloaded Files/";
                 
//                File f1=new File(path1);
//                File f2=new File(path2);
                File f3=new File(path3);
        
//                if(!f1.exists()){
//                f1.mkdirs();
//                }
//         
//                if(!f2.exists()){
//                f2.mkdirs();
//                }
                
//           
                if(!f3.exists()){
                f3.mkdirs();
                }
                                     
               // SendMail ob=new SendMail(username,sharingusername,filena);
               
                int i=st.executeUpdate("insert into filesharing (username,fileid,sharingusername,filecontent) values('"+username+"','"+fileid+"','"+sharingusername+"','"+ session.getAttribute("filecontent")+"')");
//                TimeHandler obj=new TimeHandler(time,username,fileid);
                if(i>0)
                {
                    RequestDispatcher rd=request.getRequestDispatcher("userhome.jsp");
                    request.setAttribute("msg","File Shared Successfully!!!");
                    rd.forward(request, response);
                }
                
            } catch (Exception ex) {
                Logger.getLogger(FileSharing.class.getName()).log(Level.SEVERE, null, ex);
                       RequestDispatcher rd=request.getRequestDispatcher("errorpage.jsp");
                             request.setAttribute("msg","Sorry!!! Error Occured");
                             rd.forward(request, response);
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
