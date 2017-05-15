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
				<li><a href="adminhome.jsp" accesskey="1" title="">Homepage</a></li>
				<li><a href="admincreategroup.jsp" accesskey="2" title="">CREATE GROUP</a></li>
				<li class="active"><a href="admindeletegroup.jsp" accesskey="3" title="">DELETE GROUP</a></li>
<!--                                <li><a href="joingroup.jsp" accesskey="4" title="">JOIN GROUP</a></li>
				<li><a href="#" accesskey="5" title="">LEAVE GROUP</a></li>-->
                                <li><a href="admin.jsp" accesskey="6" title="">LOG OUT</a></li>
			</ul>
		</div>
	</div>
	<div id="banner" class="container">
		<div class="title">
			
		<ul class="actions">
                <%
                String msg=request.getAttribute("msg")!=null?request.getAttribute("msg").toString():"";
            %>
            <center> <font style="color: red"><%=msg%></font></center>
            <%
            Connection con=DBConnect.getCon();
            Statement st=con.createStatement();
            ResultSet rs=st.executeQuery("select groupid,groupname from groups");
             
            %>

                 <h2>Delete Group</h2>
        <br><br><br>
       
      <form name="form1" action="Deletegroup" method="post">
                <table>
                    <tr><td>Select the Group to Delete :</td><td><select name="group">
                                <option value="0">--Select Group--</option>
                                <%while(rs.next()){%>
                                <option value="<%=rs.getInt(1)%>"><%=rs.getString(2)%></option>
                                        <%}%>
                            </select></td></tr>
                    <tr><td colspan="2" style="text-align: center"><input type="submit" value="Delete"></td></tr>
                    
                </table>               
            </form> 
		</ul>
	</div>
</div>
		<div class="title">
			
</div>
</body>
</html>
