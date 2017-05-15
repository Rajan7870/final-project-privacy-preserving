/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.upload;


import DBConnection.DBConnect;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.InternetAddress;
import javax.net.ssl.*;

/**
 *
 * @author Sq1
 */
public class SendMail {
    String email;
    String key;
    String toemail;
    public SendMail(String fileowner,String username,int key){
        
        try{
              System.out.println("a"+fileowner);
            System.out.println("b"+username);
          //    System.out.println("c"+filename);
            
              Statement st,st1;
              Connection con=DBConnect.getCon();
              st = con.createStatement();
              ResultSet rs;
              rs=st.executeQuery("select email from user where name='"+username+"'");
            
              rs.next();
              email=rs.getString(1);
                System.out.println(email);
              st1=con.createStatement();
              ResultSet rs1;
//              rs1=st.executeQuery("select filekey from upload where filename='"+filename+"'");
//              rs1.next();
//              key=rs1.getString(1);
              String subject="File Shared";
              
            
              
              
              String message="Your Friend "+fileowner+" shared a file. To Access, use this Private Key: "+key;
              mail(email,subject,message);
            }
        catch(Exception e){
            System.out.println(e);
            
        }
              
       
    }
    
    
    

 public SendMail(String username, String filename)
 {
     try{
         Statement s;
          Connection con1=DBConnect.getCon();
              s = con1.createStatement();
              ResultSet rs2;
              rs2=s.executeQuery("select email from users where username='"+username+"'");
              rs2.next();
              toemail=rs2.getString(1);
              String subject="The File has been Deleted";
              String message="The File you have shared has been deleted due to unauthorized Access";
              mail(toemail,subject,message);
                      
     }
     catch(Exception e)
     {
         System.out.println("sqlexcep"+e);
     }
 }
 
 public SendMail(String username){
  try{
         Statement s;
          Connection con1=DBConnect.getCon();
              s = con1.createStatement();
              ResultSet rs2;
              rs2=s.executeQuery("select email from user where username='"+username+"'");
              rs2.next();
              toemail=rs2.getString(1);
              String subject="The File has been Deleted";
              String message="The File you have shared has been deleted due to time Expired. Your friend not downloaded the file.";
              mail(toemail,subject,message);
                      
     }
     catch(Exception e)
     {
         System.out.println("sqlexcep"+e);
     }
 }
 
 
 
 
 public void mail(String to,String subject,String messageText)
 {
       try{
                String host = "smtp.gmail.com";
                //String to=email;
                String from="rajan_sankarprasad@srmuniv.edu.in";  // write your email address means from email address.
                //String subject = "Private Key";
                //String messageText = "Your Friend "+fileowner+" shared a file. To Access, use this Private Key: "+key;
                boolean sessionDebug = true;
                Properties props = System.getProperties();
                props.put("mail.smtp.starttls.enable","true");
                props.setProperty("mail.transport.protocol","smtp");
                props.setProperty("mail.host",host);
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.port", "587");    //port is 587 for TLS  if you use SSL then port is 465
                props.put("mail.debug", "true");
                props.put("mail.smtp.socketFactory.port", "465");
                props.put("mail.smtp.socketFactory.fallback", "false");
                props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

                Session mailSession = Session.getInstance(props, new javax.mail.Authenticator() 
                {
                    protected PasswordAuthentication getPasswordAuthentication()
                    {
                        return new PasswordAuthentication("rajan_sankarprasad@srmuniv.edu.in", "kishan7870");
                    }
                });
                mailSession.setDebug(sessionDebug);
                Message msg = new MimeMessage(mailSession);
                msg.setFrom(new InternetAddress(from));
                InternetAddress[] address = {new InternetAddress(to)};
                msg.setRecipients(Message.RecipientType.TO, address);
                msg.setSubject(subject);
                msg.setSentDate(new Date());
                msg.setText(messageText);
                Transport transport = mailSession.getTransport("smtp");
                transport.connect(host, "rajan_sankarprasad@srmuniv.edu.in", "kishan7870");
                transport.sendMessage(msg, msg.getAllRecipients());
                
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
 }
}
