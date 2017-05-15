<%@page import="DBConnection.DBConnect"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Connection"%>
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
     	  <h1 style="color: #ffffff; height: 50px"align="center" >Enterprise Document Collaboration Suite</h1>
                        
	<div id="header" class="container">
            
		
        
		<div id="menu">
                    
			<ul>
                            <li class="active"><a href="adminhome.jsp" accesskey="1" title="">Homepage</a></li>
                                <li><a href="viewuser.jsp" accesskey="2" title="">VIEW USERS</a></li>
                                <li><a href="viewsharedfiles.jsp" accesskey="2" title="">View Shared Details</a></li>
				<!--<li><a href="admindeletegroup.jsp" accesskey="3" title="">DELETE GROUP</a></li>-->
<!--                                <li><a href="joingroup.jsp" accesskey="4" title="">JOIN GROUP</a></li>
				<li><a href="#" accesskey="5" title="">LEAVE GROUP</a></li>-->
                                <li><a href="admin.jsp" accesskey="6" title="">LOG OUT</a></li>
			</ul>
		</div>
	</div>
	<div id="banner" class="container">
		<div class="title">
			
		<ul class="actions">
              
     <center>  <h2>User Details</h2></center>
             <%
                String msg=request.getAttribute("msg")!=null?request.getAttribute("msg").toString():"";
                 Connection con=DBConnect.getCon();
                Statement st=con.createStatement();
                ResultSet rs1=st.executeQuery("select * from fileshare");
                
                
            %>
            <center> <font style="color: red"><%=msg%></font></center>
            <form name="form1" action="creategroup" method="post">
                <center> <table border="2" style="width: 50%">
                    <tr><th style="text-align: center">File_id</th><th style="text-align: center">UserShare</th><th style="text-align: center">File Name</th><th style="text-align: center">UserShare_To</th><th style="text-align: center">Status</th></tr>
                    <%while(rs1.next()){%>
                    <tr><td style="text-align: center"><%=rs1.getString(1)%></td><td style="text-align: center"><%=rs1.getString(2)%><td style="text-align: center"><%=rs1.getString(4)%></td><td style="text-align: center"><%=rs1.getString(3)%></td><td style="text-align: center"><%=rs1.getString(5)%></td></td></tr>
                    <%}%>
                    </table>  </center>  <br><br><br>
                
              
                   
                         
                         <br><br><br>
                                      
            </form>
		</ul>
	</div>
</div>
		<div class="title">
			
</div>
</body>
</html>
