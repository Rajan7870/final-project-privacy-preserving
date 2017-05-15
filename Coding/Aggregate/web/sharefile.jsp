<%@page import="java.sql.PreparedStatement"%>
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
    <script type="text/javascript" src="assets/js/jquery.min.js"></script>
    <script type="text/javascript" src="js1/commonscript.js"></script>
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
                    <center>
			
		<ul class="actions">
                    <br/><font size="5px;" style="color: #ffffff;font-family: serif"><strong>Select And Share Files To Users :</strong></font><br/><br/>
                            <table cellspacing="0" style="width:500px; ">
                                <tr><th>File Name</th><th>Choose</th></tr>

                           

                            <%
                                Connection con=null;
                                ResultSet resultSet11=null;
                                try
                                {
                                 con =DBConnection.DBConnect.getCon();
                                
                                PreparedStatement ps;
                                ps = con.prepareStatement("select * from upload where status1='yes' and username='"+session.getAttribute("username")+"'");
                                 resultSet11 = ps.executeQuery();

                                if (resultSet11 != null) {
                                    while (resultSet11.next()) {
                                        String filename = resultSet11.getString("filename");


                            %>

                            <tr><td><%=filename%></td><td>

                                    <div class='controls'>

                                        <label class="checkbox">
                                            <input type="checkbox" name="my_match[]" value="<%=filename%>"></label>
                                    </div>
                                </td>

                            </tr>

                            <%    }
                                }
                                }
                                catch(Exception e)
                                {
                                    e.printStackTrace();
                                }
                                finally
                                {resultSet11.close();
                                    con.close();
                                }
                                 
                                
                            %>


                           
                        </table>
                    </center>
          
                            
                            <!--Files list Table -->
                            
                            
                            <br/><br/>
                            <!--User's Table -->
                            <center>
                         <br/><font size="5px;" style="color: #ffffff;font-family: serif"><strong>Share To Friends :</strong></font><br/><br/>
                            <table cellspacing="0" style="width:500px; ">
                                <tr><th>UserName</th><th>Choose</th></tr>


                            <%
                             Connection con1=null;
                             ResultSet resultSet1=null;
                                try
                                {
                                 con1=DBConnection.DBConnect.getCon();
                                PreparedStatement ps1;
                                ps1 = con1.prepareStatement("select name from user where name!='"+session.getAttribute("username")+"'");
                                System.out.println(session.getAttribute("username"));
                                resultSet1 = ps1.executeQuery();

                                if (resultSet1 != null) {
                                    while (resultSet1.next()) {
                                        String usrname = resultSet1.getString("name");


                            %>

                            <tr><td><%=usrname%></td><td>

                                    <div class='controls'>

                                        <label class="checkbox">
                                            <input type="checkbox" name="my_match1[]" value="<%=usrname%>"></label>
                                    </div>
                                </td>

                            </tr>

                            <%    }
                                }
                                }
                                catch(Exception e)
                                {
                                    e.printStackTrace();
                                }
                                finally
                                {
                                    resultSet1.close();
                                
                                 con1.close();
                                }
                            %>



                        </table>
                            </center>
                            <!--User's Table-->
                            
                            <br/><img style="float: right; margin-right: 230px;" src="images/sharefiles.png" class="imgclass"></img>
                        <br/>

                    </div>


                    
                           
       
              
              
              
    
        </div>
		<div class="title">
			
</div>
</body>
</html>
