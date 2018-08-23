<%@page import="com.lib.user.User" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/w3.css">
<link rel="stylesheet" href="css/fonts.css">
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">

<style>
html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
</style>
<title>Library</title>
</head>
<body class="w3-light-grey">

<!-- Top container -->
 <jsp:include page="topmenu.jsp"></jsp:include>
 <!-- Menu -->
 <jsp:include page="menu.jsp"></jsp:include>

<!-- Overlay effect when opening sidenav on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px;margin-top:43px;">

  <!-- Header -->
  <header class="w3-container" style="padding-top:22px">
    <h5><b>Approval</b></h5>
  </header>

<div class="w3-container">
  <form class="w3-container" action="Approve" method="post">
      <div class="w3-section">
		<label><b>Login Id</b></label><br><br>
        <input class="w3-input w3-border w3-margin-bottom" style="width:27%;" type="text" placeholder="Enter the registration number" name="reg_id" required>
        
       <br/>
        <button class="w3-btn-block w3-green w3-padding" style="width:27%;" type="submit">Submit</button>
      </div>
    </form>
    <%
User user = (User)session.getAttribute("LOGIN");

%>
        	<form action="Approve" method="post">
     <% if(user.isMem()!=false) { %>
<table class="w3-table w3-striped w3-bordered w3-border  w3-white">
		<tr>
  				<th>Id</th>
  				<th>Name</th>
  				<th>Mobile</th>
  				<th>Photo</th>
  		</tr><tr>
  	
  	
  				<td><%=user.getTempId()%></td>
  				<td><%=user.getTempName()%></td>
  				<td><%=user.getTempMobile()%></td>
  			</tr>	
  			<tr>
  				<td><button class="w3-btn-block w3-green w3-section w3-padding" style="width:30%;" type="submit" name="approve" value="app">Approve</button></td>
		</tr></tr>
		</table>
		<% }  %>
		<% if(user.getSflag()==true && user.isMem()==false) { %>
		<h5> Membership has been approved successfuly </h5>  
		<% } %>
		
		</form>
		

  </div>
  <hr>
  

  
    </div>
 
  
     <!-- Footer -->
 <jsp:include page="footer.jsp"></jsp:include>

  <!-- End page content -->
 
<script>
// Get the Sidenav
var mySidenav = document.getElementById("mySidenav");

// Get the DIV with overlay effect
var overlayBg = document.getElementById("myOverlay");

// Toggle between showing and hiding the sidenav, and add overlay effect
function w3_open() {
    if (mySidenav.style.display === 'block') {
        mySidenav.style.display = 'none';
        overlayBg.style.display = "none";
    } else {
        mySidenav.style.display = 'block';
        overlayBg.style.display = "block";
    }
}

// Close the sidenav with the close button
function w3_close() {
    mySidenav.style.display = "none";
    overlayBg.style.display = "none";
}
</script>

</body>
</html>

