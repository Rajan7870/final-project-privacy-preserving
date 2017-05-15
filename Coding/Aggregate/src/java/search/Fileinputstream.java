/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package search;

import DBConnection.DBConnect;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ANDROID2
 */
public class Fileinputstream
{
    private String full_path;
     String b=null;
   
    public String fun1(String s)
    {
    
    
     File ob = new File(s.trim());
//            System.out.print(ob.toString());
           // System.out.println("hello sir");
            FileInputStream fis = null;
            try 
            {
                
            
            fis = new FileInputStream(ob);
            String onn = fis.toString();
            String on = onn;
            int i;
            StringBuilder sb = new StringBuilder();
            System.out.println(fis.toString());
            
            while ((i = fis.read()) != -1) {

                System.out.print((char) i);
                sb.append((char) i);

                System.out.print(sb.toString());
            }
            fis.close();
            
            System.out.println(ob.toString());
         b = sb.toString();
         
        
             
            
         
            }
            catch(Exception e)
            {
                
            }
      return b;        
    }
}
    
     
    