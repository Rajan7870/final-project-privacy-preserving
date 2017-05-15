package search;



import DBConnection.DBConnect;
import com.mysql.jdbc.Connection;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 *
 * @author jemi java
 */
public class Download extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    public static int process=0;
    int flag=0;
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
         PrintWriter out = response.getWriter();
         Statement st;
         int key=0;
         String filekey=null;
//     int fileid=Integer.parseInt(request.getParameter("select"));
       
         int randomkey1=Integer.parseInt(request.getParameter("random_key"));
          String filename = request.getParameter("s1");
        
        HttpSession session=request.getSession();
        
        try{
         java.sql.Connection con=DBConnect.getCon();
              st = con.createStatement();
              ResultSet rs;
            
              rs=st.executeQuery("select filename,randomkey from upload where filename='"+filename+"'");
           while(rs.next())
           {
              
               key=rs.getInt(2);
           }  
              if(key == randomkey1)
              {     ResultSet rs1;
              Statement st1=con.createStatement();
              
                  rs1=st1.executeQuery("select * from upload where filename='"+filename+"' and randomkey='"+randomkey1+"'");
              if(rs1.next())
              
              {
                   filekey=""+rs1.getString(5);
              
                    String path1="D:/Keyaggregate/Encrypt1/"+rs1.getString(4);          
                    String path2="D:/Keyaggregate/Downloaded/"+rs1.getString(4);
                      Filedecryption obj=new Filedecryption(); 
                      obj.fun2(path1, path2,filekey);   
              
                      
                      
                           Statement s3;
                            s3=con.createStatement();
                            s3.executeUpdate("update fileshare set status='Downloaded' where filename='"+filename+"' and sharingusername='"+session.getAttribute("username")+"'");
                     
                      RequestDispatcher rd=request.getRequestDispatcher("searchfile1.jsp");
                      request.setAttribute("msg","File Successfully Downloaded in your Download Folder!!!");
                      rd.forward(request, response);
                      
                  
              }
              else
              {
                  RequestDispatcher rd=request.getRequestDispatcher("searchfile1.jsp");
                  request.setAttribute("msg","Download Failed! Try again...");
                  rd.forward(request, response);
              }
              }
              else
              {
                  RequestDispatcher rd=request.getRequestDispatcher("searchfile1.jsp");
                  request.setAttribute("msg","Download Failed! Wrong Key...");
                  rd.forward(request, response);
              
                      
              }      
              
        }
        catch(Exception e)
        {
            System.out.println(e);
              RequestDispatcher rd=request.getRequestDispatcher("searchfile1.jsp");
                             request.setAttribute("msg","Sorry!!! Error Occured");
                             rd.forward(request, response);
        }
                       
       // HttpSession session=request.getSession();
   
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
