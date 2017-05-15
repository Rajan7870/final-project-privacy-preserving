<%-- 
    Document   : search12
    Created on : Oct 13, 2015, 4:11:13 PM
    Author     : ANDROID2
--%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DBConnection.DBConnect"%>
<%@page import="java.sql.Statement"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
                          <%
                            String t=request.getParameter("key_value");    
            Statement st;
            Connection con=DBConnect.getCon();
              st = con.createStatement();
              ResultSet rs;
              rs=st.executeQuery("select * from upload where filecontent like '%"+t+"%'");
              
              
            %>

                 <h2>Select Files to Store in Cloud</h2>
        <br><br><br>
       
    <center><form name="form1" action="Tocloud" method="post">
            <table border="3">
                <tr><th>File Id</th><th>File Name</th><th>Select</th></tr>
                <%while(rs.next()){%>
                <tr><td style=" text-align: center"><%=rs.getInt(1)%></td>
                    <td style="text-align: center"><%=rs.getString(4)%></td>
<!--                    <td style="text-align: center"><%=rs.getString(2)%></td>-->
                    <td style="text-align: center">
                        <input type="radio" name="select" value="<%=rs.getInt(1)%>"></td></tr>
                
                <%}%>
                <tr><td style="text-align: center" colspan="3"><input type="submit" value="submit"/></td> </tr>
                
                <% 
                String msg=request.getAttribute("msg")!=null?request.getAttribute("msg").toString():"";
                %>
                <%=msg%>
                
                
                
                
                
            </table>
                
                
                
        
                  

           <% 
String b=request.getAttribute("message")!=null?request.getAttribute("message").toString():"";
%><p style="font:larger;font-size:x-large ; color: crimson">
<%=b%></p>
         </form>
        <h1>Hello World!</h1>
    </body>
</html>
