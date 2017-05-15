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
	<div id="header" class="container">
            
		<div id="logo">
                    <h1 align="left" ><a href="#">Key-Aggregate</a></h1>	<br></br>
		</div>
		<div id="menu">
			<ul>
				<li><a href="home.jsp" accesskey="1" title="">Homepage</a></li>
				
                                <li><a href="index.html" accesskey="6" title="">LOG OUT</a></li>
			</ul>
		</div>
	</div>
	<div id="banner" class="container">
		<div class="title">
                  
			<span class="byline">
                      
                           <form style="animation: normal;text-align: right" name="form1" action="searchfile.jsp" method="post">
                        File search:
                                <input type="text" name="key_value"/>
                                <input type="submit" name="submit"/>
                            </form>
                    
     <%
                            String t=request.getParameter("key_value");    
            Statement st;
            Connection con=DBConnect.getCon();
              st = con.createStatement();
              ResultSet rs;
              rs=st.executeQuery("select * from upload where fileid='"+session.getAttribute("file_id")+"'");
              
              
            %>

        <br><br><br>
       
    <center><form name="form1" action="Download1" method="post">
            <table border="3">
                <tr><th>File Id</th><th>File Name</th><th>Select</th></tr>
                <%while(rs.next()){%>
                <tr><td style=" text-align: center"><input type="text" name="fileid" value="<%=rs.getInt(1)%>" </td>
                    <td style="text-align: center"><%=rs.getString(1)%></td>               
                    <td style="text-align: center">
                        <input type="radio" name="select" value="<%=rs.getInt(1)%>"></td></tr>
                
                <%}%>
                <tr><td style="text-align: center" colspan="3"><input type="submit" value="DOWNLOAD"/></td> </tr>
                
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
