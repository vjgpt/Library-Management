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
<script src="js/my.js"></script>
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">

<style>
html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
</style>
<title>Library</title>
</head>
<body class="w3-light-grey">
<%
User user = (User)session.getAttribute("TRANSACTION");
%>

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
    <h5><b>Transaction</b></h5>
  </header>
</head>
<div class="w3-container">
  <form class="w3-container" action="Transaction" method="post">
      <div class="w3-section">
		<label><b>Login Id</b></label><br>
        <input class="w3-input w3-border w3-margin-bottom" style="width:27%;" type="text" placeholder="Enter the registration number" name="regd_no" required>
        
        
        <button class="w3-btn-block w3-green w3-padding" style="width:27%;" name="submit" type="submit">Submit</button>
      </div>
    </form>

      
     
       <% if(user.getSflag()==true) { %>
      
	 <div class="w3-section">
        <button class="w3-btn-block w3-green w3-padding" style="width:27%;margin-left:150px;margin-top:70px;" type="submit" value="issue" name="issue" onclick="divHide()">ISSUE</button>
        <button class="w3-btn-block w3-green w3-padding" style="width:27%;margin-left:250px;margin-top:70px;position:relative" name="return" value="return" type="submit" onclick="divHide2()" >RETURN</button>
      </div>
          <% } %>
   

    
       <div class="w3-section">
   <div id="div01" style="display:none">
    <form action="Transaction" method="post">
   

   <fieldset style="width:40%;">
   <legend><b>Issue</b></legend>
        <label>Book Title</label><br>
        <input class="w3-input w3-border w3-margin-bottom" style="width:65%;" type="text" name="ttl" required>
        
        <button class="w3-btn-block w3-green w3-padding" style="width:65%;" type="submit">SEARCH</button>
        </fieldset>
</div>
    </form>
    
     <div id="div02" style="display:none">
    <form action="Transaction" method="post">
   <fieldset style="width:40%;">
   <legend><b>Return</b></legend>
        <label>Book Title</label><br>
        <input class="w3-input w3-border w3-margin-bottom" style="width:65%;" type="text" name="ttl1" required>
        
        <button class="w3-btn-block w3-green w3-padding" style="width:65%;" type="submit">SEARCH</button>
        </fieldset>
</div>
    </form>
    </div>
    
      
    
    </div>
    <% if(user.getBrecords()==true && user.getSflag()==true) { %>
    <%if(user.getTflag()==1  ){ %>
    <form action="Transaction"  method="post">
<table class="w3-table w3-striped w3-bordered w3-border  w3-white">
		<tr>
  				<th>Book Name</th>
  				<th>Author Name</th>
  				<th>Publisher Name</th>
  				<th>Edition</th>
  				<th>Availability</th>
  				<th>Book Now</th>
  		</tr><tr>
  		<% for(int i=user.getLength();i<user.getBlength();i++) {%>
  		   		<td><%=user.getBtitle(i)%></td>
  				<td><%=user.getBauthor(i)%></td>
  				<td><%=user.getBpublisher(i)%></td>
  				<td><%=user.getBedition(i)%></td>
  				<td><%=user.getAvail(i) %></td>
  				<td><button class="w3-btn-block w3-green w3-section w3-padding" style="width:55%;" type="submit" name="<%=user.getBid(i)%>" value="<%=user.getBid(i)%>">Issue</button></td>
		</tr><%if(i%2==0){ %><tr style="background-color:#CCC"><%}else{ %><tr>
		<%}}} else if(user.getTflag()==2) {%><h3>Book has already being issued </h3><%} %>
</table>
<%} else if(user.getRtn()==2){ %><h3>No such books is being issued!!!!</h3><%} %>
    </form>
    </div>

  </div>
  <hr>
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

