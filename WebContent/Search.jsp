<%@page import="com.lib.user.User"%>
<%@page import="com.lib.db.Database"%>
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
<%
User user = (User)session.getAttribute("SEARCH");
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
    <h5><b>Advanced Search</b></h5>
  </header>
 <div class="w3-container">
  <form class="w3-container" action="Search" method="post">
      <div class="w3-section">
		<label><b>Keywords</b></label>
        <input class="w3-input w3-border w3-margin-bottom" style="width:27%;" type="text" placeholder="Enter keywords" name="keywords" >
		<label><b>Title</b></label>
        <input class="w3-input w3-border w3-margin-bottom" style="width:27%;" type="text" placeholder="Enter title name" name="title" >
		<label><b>Author</b></label>
        <input class="w3-input w3-border w3-margin-bottom" style="width:27%;" type="text" placeholder="Enter author name" name="author">
		<label><b>ISBN</b></label>
        <input class="w3-input w3-border w3-margin-bottom" style="width:27%;" type="text" placeholder="Enter ISBN name" name="isbn" >
		<label><b>Publisher</b></label>
        <input class="w3-input w3-border w3-margin-bottom" style="width:27%;" type="text" placeholder="Enter publisher name" name="publisher" >
        <button class="w3-btn-block w3-green w3-padding" style="width:27%;" type="submit">Submit</button>
      </div>
    </form>
     <% if(user.getBrecords()==true && user.getFlag()!=0) { 
     %>
<table class="w3-table w3-striped w3-bordered w3-border  w3-white">
		<tr>
  				<th>Book Name</th>
  				<th>Author Name</th>
  				<th>Publisher Name</th>
  				<th>Edition</th>
  				<th>Availability</th>
  			
  		</tr><tr>
  	
  		<% for(int i=0;i<user.getBlength();i++) {%>
  				<td><%=user.getBtitle(i)%></td>
  				<td><%=user.getBauthor(i)%></td>
  				<td><%=user.getBpublisher(i)%></td>
  				<td><%=user.getBedition(i)%></td>
  				<td><%=user.getAvail(i) %></td>
  				</tr><%if(i%2==0){ %><tr style="background-color:#CCC"><%}else{ %><tr>
		<%}}} else{%>
		<%if(user.getBrecords()==true){ %>
		<h3>No Books Found</h3><%}} %>
</table>
  </div>
  <hr>
   </div>
  </div>
   <!-- Footer -->
 <jsp:include page="footer.jsp"></jsp:include>
</body>
</html>