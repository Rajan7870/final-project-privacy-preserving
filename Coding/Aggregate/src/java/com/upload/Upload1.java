/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.upload;


import DBConnection.DBConnect;
// import com.databaseConnection.DBConnection;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import search.Fileinputstream;
/**
 *
 * @author jemi java
 */
public class Upload1 extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
   public static String path;
   public static String fname;
   public static String full_path;
   public static String key_value;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
          try {
            /* TODO output your page here
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Log</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Log at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
             */
        } finally {            
            out.close();
        }
    }
 //Folder changes 1%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%   
   // private final String UPLOAD_DIRECTORY = "C:/Users/jemi java/Desktop/upload";
    private final String UPLOAD_DIRECTORY = "D:/Keyaggregate/Original/";
//Folder changes 1%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%    
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
        processRequest(request, response);
        
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
       
        HttpSession session=request.getSession();
         key_value=(String)session.getAttribute("key");
        String name = null;
        try
        {
            Connection con=DBConnect.getCon();
            
        }
        catch(Exception e)
        {
            
        }
    
       
        
 //Modification 1       
//        HttpSession session=request.getSession();
      
 //Modification 1  END     
             
        //process only if its multipart content
        if(ServletFileUpload.isMultipartContent(request)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
               for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        name = new File(item.getName()).getName();
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                        String path_name=UPLOAD_DIRECTORY+name;
                        ServletContext s=getServletContext();
                        String FSepa=(String)File.separator;
                        s.setAttribute("FPath", "UPLOAD_DIRECTORY");
                 
                        s.setAttribute("FName", "name");
                        s.setAttribute("Owner", "Admin");
                        Java_Encrypt1 j=new Java_Encrypt1();
                        path=UPLOAD_DIRECTORY;
                        fname=name;
                         Connection con=DBConnect.getCon();
                         Statement st1=con.createStatement();
                         ResultSet rs1;
                         rs1=st1.executeQuery("select filename from upload where filename='"+fname+"'");
                        String result="";
                         while(rs1.next())
                         {
                             result=rs1.getString(1);
                             
                             
                             
                         }
                         if(result.equals(fname))
                         {
                              request.setAttribute("message", " sorry! File name already exist");
                         }
                         else
                         {
                        
                          full_path=UPLOAD_DIRECTORY+name;
                    
                        String uname=(String) session.getAttribute("username"); 
                        session.setAttribute("fname", fname);
                        j.fun(path,fname,full_path,uname,key_value);
                        session.setAttribute("full_path","full_path");
                             Fileinputstream fi=new Fileinputstream();
                         String j1=fi.fun1(full_path);
                         
                         session.setAttribute("filecontent","j1");
                        
                       
                        Statement st=con.createStatement();
                        String username=(String)session.getAttribute("username");
                        int i=st.executeUpdate("insert into upload (filename,username ,filepath,filekey,filecontent) values ('"+fname+"','"+username+"','"+full_path+"','"+key_value+"','"+j1+"')");
                        session.setAttribute("filecontent", "filecontent");
                         request.setAttribute("message", "File Uploaded Successfully");
                    
                }
                    }
           
               //File uploaded successfully
              
               }
            } catch (Exception ex) 
            {
               request.setAttribute("message", "File Upload Failed due to " + ex);
            }          
         
        }else{
            request.setAttribute("message","Sorry this Servlet only handles file upload request");
        }
    
   //     request.getRequestDispatcher("UploadList.jsp").forward(request, response);
   request.getRequestDispatcher("uploadfile.jsp").forward(request, response);
     ///Insert commands:
 /*        try {
                String s="hai";
                DbConnection d=new DbConnection();
                PreparedStatement ps=(PreparedStatement) d.con.prepareStatement("insert into File(userid,FileName)values('"+s+"','"+name+"')");
                int k = ps.executeUpdate();        
        /*           
               PreparedStatement ps=con.prepareStatement("insert into Reg(userid,name,password,city,mail,time,date)values(?,?,?,?,?,?,?)");
            //PreparedStatement ps = con.prepareStatement("insert into Reg values ('a','a','a','a','a','a','a')");
            	ps.setString(1, id);
            ps.setString(2,names);
            ps.setString(3,pwds);
            ps.setString(4, city);
            ps.setString(5,mids);
            ps.setString(6, dates);
            ps.setString(7, times);
            int k = ps.executeUpdate();         
                */
                
                
 /*       } catch (ClassNotFoundException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Log.class.getName()).log(Level.SEVERE, null, ex);
        }
*/            
    ///Inser (End)    
        
        
        processRequest(request, response);
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
