/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package store;



import DBConnection.DBConnect;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;



/**
 *
 * @author jemi java
 */
public class Tocloud extends HttpServlet {

    public static String full_path;
    private String fileName;
    File f1;

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();
//            String name = (String) session.getAttribute("username");
//            String userid = (String) session.getAttribute("fileid");
//            String FName = (String) request.getAttribute("FName");
            String filename=null;
            Date now = new Date();
//            String r = (String) request.getParameter("s");
            Connection con=DBConnect.getCon();
            
            
            int fileid=Integer.parseInt(request.getParameter("select"));
            
         
            
            String sa = "select fileid,filepath,filename from upload where fileid='"+fileid+"'";
            
            PreparedStatement pr = con.prepareStatement(sa);
            
            ResultSet rs = pr.executeQuery();
         
            if (rs.next()) {
                full_path = rs.getString(2);
                filename=rs.getString(3);
            }
            con.close();
            File file = new File(full_path);
            fileName = (String) full_path;
            Cloudmanupulation.main();
            FileOutputStream fos1 = null;
       
            try {


                //Getting the chunked contents
                String[] getContents = Crushingfile1.function(file);
                System.out.println("File Contents");
                System.out.println(getContents[0]);

                //Uploading file and Create temp file
                 f1 = new File(file.getAbsolutePath());
                
                System.out.println("The Path is"+file.getAbsolutePath());
              
                fos1 = new FileOutputStream(f1);
               
                try {
                    fos1.write(getContents[0].getBytes());
                    Cloudmanupulation.upload(f1, "aggri");
                               
                    
//                    try {
//                        //select  
//                      UUID uuid = UUID.randomUUID();
//                      String key=uuid.toString();
//                      Statement st=null;
//                        Connection con1=DBConnect.getCon();
//                       st= con1.createStatement();
//                      int i=st.executeUpdate("insert into cloudupload (fileid,filename,fileowner,filekey) values ('"+fileid+"','"+filename+"','"+session.getAttribute("username")+"','"+key+"')");
//                  
//
//                    } catch (Exception ee) {
//                        System.out.println("Cannot insert"+ee);
//                               RequestDispatcher rd=request.getRequestDispatcher("errorpage.jsp");
//                             request.setAttribute("msg","Sorry!!! Error Occured");
//                             rd.forward(request, response);
//                    }
                 
                    fos1.close();
                    RequestDispatcher rd=request.getRequestDispatcher("cloudstorage.jsp");
                    request.setAttribute("msg","File Successfully Uploaded in Cloud!!!!");
                    con=DBConnect.getCon();
                    Statement st1=con.createStatement();
               
                   int i=st1.executeUpdate("update upload  set status1='yes' where fileid='"+fileid+"'");
                   
                    rd.forward(request, response);
                    

                } catch (IOException ex) {
                    System.out.println(ex);
                    ex.printStackTrace();

                }
                
                catch (Exception ex1) {
                           RequestDispatcher rd=request.getRequestDispatcher("cloudstorage.jsp");
                             request.setAttribute("msg","Sorry!!! Error Occured");
                             rd.forward(request, response);
                }
                
            }
            
            catch(Exception e)
            {
                System.out.println(e);
                       RequestDispatcher rd=request.getRequestDispatcher("cloudstorage.jsp");
                             request.setAttribute("msg","Sorry!!! Error Occured");
                             rd.forward(request, response);
            }
            
            finally {
            }

          


            /* TODO output your page here*
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DataStorage</title>");  
            out.println("</head>");
            out.println("<body>");
            
            out.println("USER NAME :"+name);
            out.println("File Name: "+FName);
            out.println("Date: "+now);
            
            // out.println("Array List"+a);
            //out.println("Index:"+i);
            out.println("Value "+radio1);
            out.println("<h1>Servlet DataStorage at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");*/

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Tocloud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Tocloud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);





        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(Tocloud.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Tocloud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
