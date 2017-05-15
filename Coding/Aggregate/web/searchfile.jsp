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
                      
                           
                        <center><form style="animation: normal;text-align: right; align:center;" name="form1" action="searchfile1.jsp" method="get">
                        File search:
                                <input type="text" name="key_value"/>
                                <input type="submit" name="submit"/>
                                </form>
            </center>
                            
                             <%
                            String t=request.getParameter("key_value");
                            System.out.println(t);
                           
                            
            Statement st;
            Connection con=DBConnect.getCon();
              st = con.createStatement();
              ResultSet rs;
           
              rs=st.executeQuery("select * from fileshare where sharingusername='"+session.getAttribute("username")+"'");
              
              
            %>
         
            <center>
                            <form name="form1" action="searchfile1.jsp" method="post">
            <table border="3">
                <tr><th>File Id</th><th>File Name</th></tr>
                <%while(rs.next()){%>
                <tr><td style=" text-align: center"><input type="text" name="fileid" value="<%=rs.getInt(1)%>" readonly/> </td>
                    <td style="text-align: center"><input type="" name="filename" value="<%=rs.getString(4)%>" readonly/></td>
<!--                    <td style="text-align: center"><%=rs.getString(2)%></td>-->
                   </tr>
                
                <%}%>
            
                
                <% 
                String msg=request.getAttribute("msg")!=null?request.getAttribute("msg").toString():"";
                %>
                <%=msg%>
                
                
                
                
                
            </table>
                            </form>
            </center>
                
                
                
        
                  

           <% 
String b=request.getAttribute("message")!=null?request.getAttribute("message").toString():"";
%><p style="font:larger;font-size:x-large ; color: crimson">
<%=b%></p>
        
            

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
