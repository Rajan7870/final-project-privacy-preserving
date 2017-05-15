<%@page import="java.sql.Connection"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="DBConnection.DBConnect"%>
<%@page import="java.sql.Statement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<!--
Design by TEMPLATED
http://templated.co
Released for free under the Creative Commons Attribution License

Name       : StoneWork 
Description: A two-column, fixed-width design with dark color scheme.
Version    : 1.0
Released   : 20140228

-->
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="http://fonts.googleapis.com/css?family=Didact+Gothic" rel="stylesheet" />
<link href="css/default1.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/fonts1.css" rel="stylesheet" type="text/css" media="all" />

<!--[if IE 6]><link href="default_ie6.css" rel="stylesheet" type="text/css" /><![endif]-->

</head>
<body>
<div id="header-wrapper">
      <h1 style="color: #ffffff; height: 50px"align="center" >Privacy Preserving Ranked Multi-Keyword Search for Multiple Data Owners in Cloud Computing</h1>
	<div id="header" class="container">
            
		
		<div id="menu">
			<ul>
				<li><a href="home.jsp" accesskey="1" title="">Homepage</a></li>
				<li><a href="uploadfile.jsp" accesskey="2" title="">UPLOAD FILE</a></li>
				<li><a href="cloudstorage.jsp" accesskey="3" title="">UPLOAD TO CLOUD</a></li>
<!--				<li><a href="joingroup.jsp" accesskey="4" title="">JOIN GROUP</a></li>
				<li><a href="leavegroup.jsp" accesskey="5" title="">LEAVE GROUP</a></li>-->
                                <li><a href="sharefile.jsp" accesskey="5" title="">SHARE FILES</a></li>
                                    <li><a href="searchfile.jsp" accesskey="7" title="">SEARCH FILE</a></li>
                                <li><a href="index.html" accesskey="6" title="">LOG OUT</a></li>
			</ul>
		</div>
	</div>
	<div id="banner" class="container">
		<div class="title">
                  
			<span class="byline">
                      
                            <center> <form name="form1" action="Download" method="post">
            <table border="3">
                <tr style="font-size: medium"><th>File Id</th><th>File Name</th><th>Select</th></tr>
                
                    
     <%
                             
            Statement st1;
         Connection con=DBConnect.getCon();
              st1 = con.createStatement();
              ResultSet rs1;
              System.out.println(request.getParameter("key_value"));
              rs1=st1.executeQuery("select * from upload where filecontent like '%"+request.getParameter("key_value")+"%'");
           
              while(rs1.next())
              {    
              String result=rs1.getString(4);
              Statement st2=con.createStatement();
              ResultSet rs2;
              rs2=st2.executeQuery("select fileid,filename from fileshare where filename='"+result+"' and  sharingusername='"+session.getAttribute("username")+"'");
              while(rs2.next())
              {
                  String re=rs2.getString(1);
      
                    %>
                    
                <tr><td style=" text-align: center"><input type="text" name="fileid" value="<%=re%>" readonly/> </td>
                    <td style="text-align: center"><input type="" name="filename" value="<%=rs2.getString(2)%>" readonly/></td>

                  
                    <td style="text-align: center">
                       <input type="radio" name="s1" value="<%=rs2.getString(2)%>"/></td></tr>
                       <% System.out.println(re);%>
                     
                
                <%}%>
               
                
                
                <%}%>
               
               
                
                   <tr><td style=" font-size: medium; text-align: center" colspan="3" ;> KEY FOR DECRYPT:<input type="text" name="random_key"/></td></tr>
                
                 <tr><td style="text-align: center" colspan="3"><input type="submit" value="DOWNLOAD"/></td> </tr>
            </table>
                
     <% 
String b=request.getAttribute("msg")!=null?request.getAttribute("msg").toString():"";
%><p style="font:larger;font-size:x-large ; color: crimson">
<%=b%></p>

         </form>
                            </center>
            

        <br><br><br>
       
  
                        </span>
		</div>
		<ul class="actions">

                    
		</ul>
	</div>
</div>
<div id="wrapper">
	<div id="three-column" class="container">
		<div class="title">
			
</div>
</body>
</html>
