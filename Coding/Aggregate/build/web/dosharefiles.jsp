<%-- 
    Document   : readWriteUpdateInDB
    Created on : Jul 24, 2014, 5:31:38 PM
    Author     : ANDROID2
--%>
<%@page import="com.upload.SendMail"%>
<%@page import="com.cloud.FileSharing"%>
<%@page import="java.util.Random"%>
<%@page import="DBConnection.DBConnect"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.util.Arrays"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.util.ArrayList"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>



        <%!
            ArrayList<String> filesListArrayList = new ArrayList<String>();
            ArrayList<String> userListArrayList = new ArrayList<String>();

            public int generateRandomNumber() {
                //int nextRandom = 0;
                Random randomGenerator = new Random();
                int randomInt = randomGenerator.nextInt(10060);

                return randomInt;
            }

            public String returnFilePath(String fileName) throws Exception {
                String filePath = "";
                Connection connection1 = DBConnect.getCon();

                PreparedStatement ps;

                ps = connection1.prepareStatement("select * from upload where filename=?");

                ps.setString(1, fileName);

                ResultSet resultSet = ps.executeQuery();

                while (resultSet.next()) {
                    filePath = resultSet.getString("filepath");
                }

                return filePath;
            }

        %>
        <%
            Connection connection = DBConnect.getCon();
            Statement st=connection.createStatement();
            PreparedStatement ps1 = null;
            try {
                String filesListArray = request.getParameter("filesListArray");
                String userListArray = request.getParameter("userListArray");

                System.out.println("-----------------------");
                boolean isTrue = filesListArray.isEmpty();
                if (!isTrue) {

                    filesListArrayList = new ArrayList<String>(Arrays.asList(filesListArray.split(",")));

                }
                boolean isTrue1 = userListArray.isEmpty();
                if (!isTrue1) {
                    userListArrayList = new ArrayList<String>(Arrays.asList(userListArray.split(",")));

                }

                if (userListArrayList != null) {
                          Random r = new Random();
              int i=r.nextInt();
                    for (String filesValue : filesListArrayList) {
                         ResultSet rs2;
              int j=st.executeUpdate("update upload set randomkey="+i+" where filename='"+filesValue+"'");
                        
                        if (filesListArrayList != null) {

                            for (String usersValue : userListArrayList) {
                                String sql;

                                System.out.println("filesValue: " + filesValue);

                                String path = returnFilePath(filesValue);
                                 
                                
                                sql = "insert into fileshare(filename,sharingusername,username) values(?,?,?)";
                                
                                ps1 = connection.prepareStatement(sql);
                             

                                ps1.setString(2, usersValue);
                                session.setAttribute("sharingusername",usersValue);
                                
                                ps1.setString(1, filesValue);
                                ps1.setString(3, (String)session.getAttribute("username"));
                           
                                
                                
                                int update = ps1.executeUpdate();
                                Connection con1;
                                ResultSet rs=null;
                               // Statement st=con.createStatement();
                           // int i=st.executeUpdate("insert into fileshare (filecontent) select filecontent from upload filename='"+filesValue+"'");
                               
                                if (update > 0) {
                          
                                    System.out.println("inserted into db..");
                                }

                            }
                           
                        }
                    }
              
             
                     for (String usersValue : userListArrayList) {
                    SendMail ob=new SendMail((String)session.getAttribute("username"),usersValue,i);
                     }
                }

                out.println("success");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                filesListArrayList.clear();
                userListArrayList.clear();
                

            }

        %>
    </body>
</html>
