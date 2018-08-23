<%@page import="com.lib.user.User"%>
<%@page import="com.lib.db.Database"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<title>Account</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/w3.css">

<link rel="stylesheet" href="css/abcd.css">
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
<style>
html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
</style>
<body class="w3-light-grey">
<%
User user = (User)session.getAttribute("LOGIN");
  user = (User)session.getAttribute("Account");
%>

<!-- Top container -->
 <jsp:include page="topmenu.jsp"></jsp:include>
 <!-- Menu -->
 <jsp:include page="menu.jsp"></jsp:include>

<!-- Overlay effect when opening sidenav on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px;margin-top:43px;">


  <div class="w3-container">
 
  <form class="w3-container" action="Account" method="post">
      <div class="w3-section">
        <label><h3>Login ID</h3></label><br>
        <input class="w3-input w3-border w3-margin-bottom" style="width:27%;" type="text" placeholder="Enter your Login ID" name="title" required>
        <button class="w3-btn-block w3-green w3-section w3-padding" style="width:27%;" type="submit" >Submit</button>
      </div>
    </form>
    

    	<form action="Account" method="post">
    	<% if(user.getBrecords()==true) { %>
<table class="w3-table w3-striped w3-bordered w3-border  w3-white">
		<tr>
  				<th>Book Name</th>
  				<th>Fine Amount</th>
  				
  		</tr>
  		<tr>
  		<% for(int i=0;i<user.getBlength();i++) { if(user.getFamt(i).equalsIgnoreCase("0")==false){%>
  		
  				<td><%=user.getBtitle(i)%></td>
  				<td><%=user.getFamt(i)%></td>
  				<% System.out.println(user.getBid(i));%>
  				<td> <button  class="w3-btn-block w3-green w3-section w3-padding" style="width:27%;" type="submit" name=<%=user.getBid(i) %> value=<%=user.getBid(i) %>  >Pay</button></td>
				</tr><%if(i%2==0){ %><tr style="background-color:#CCC"><%}else{ %><tr>
		<%}}}}%>  			
  			 </table>
		</form>
<% if(user.getSflag()==true) { %>
<div  >
<form action="Account" method="post">
		<input class="w3-input w3-border w3-margin-bottom" style="width:27%;" type="text" placeholder="Enter fine amount" name="fine" >
        <button class="w3-btn-block w3-green w3-section w3-padding" style="width:27%;" type="submit">Submit</button>
		</form>
    </div>
    <% } %>
    
  </div>
 <!-- Footer -->
 <jsp:include page="footer.jsp"></jsp:include>

</body>
</html>
</body>
</html>