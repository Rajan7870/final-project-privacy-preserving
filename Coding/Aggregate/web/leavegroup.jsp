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
     	
                   <h1 style="color: #ffffff; height: 50px"align="center" >Privacy Preserving Ranked Multi-Keyword Search for Multiple Data Owners in Cloud Computing</h1>       
	<div id="header" class="container">
            
		
        
		<div id="menu">
                    
			<ul>
				<li><a href="home.jsp" accesskey="1" title="">Homepage</a></li>
				<li><a href="uploadfile.jsp" accesskey="2" title="">UPLOAD FILE</a></li>
				<li><a href="cloudstorage.jsp" accesskey="3" title="">UPLOAD TO CLOUD</a></li>
				<li><a href="joingroup.jsp" accesskey="4" title="">JOIN GROUP</a></li>
				<li><a href="leavegroup.jsp" accesskey="5" title="">LEAVE GROUP</a></li>
                                 <li><a href="sharefile.jsp" accesskey="7" title="">SHARE FILES</a></li>
                                <li><a href="searchfile.jsp" accesskey="5" title="">SEARCH FILES</a></li>    
                                <li><a href="index.html" accesskey="6" title="">LOG OUT</a></li>
			</ul>
		</div>
	</div>
	<div id="banner" class="container">
		<div class="title">
			
		<ul class="actions">
                   <%
            Connection con=DBConnect.getCon();
            Statement st=con.createStatement();
            ResultSet rs1=st.executeQuery("select * from usergroup where username='"+session.getAttribute("username")+"'");
            if(rs1.next()){
               
               
            %>
            <form name="form1" action="Leavegroup" method="post">
            <table><tr><td style="text-align: center">Are you Sure you want to leave the Group? </td><td style="text-align: center">
                   
                       </td></tr>
                <tr><td colspan="2" style="text-align: center"><input type="submit" value="Leave Group"></td></tr>
            </table>
            
           </form>
                        <%}
            else
            {           
                        %>
                        <center>   <font style="color: red">You are not a member in any Group.</font></center>
        
        <%}%>
        </div>
		<div class="title">
			
</div>
</body>
</html>
