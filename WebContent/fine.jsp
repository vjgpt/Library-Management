<%@page import="com.lib.user.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fine</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/w3.css">
<link rel="stylesheet" href="css/fonts.css">
<link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
<%@ page isELIgnored="false" %>
<style>
html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
</style>
</head>
<body class="w3-light-grey">
		<%

		User user1 = (User)session.getAttribute("FINE");
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
    <h5><b>FINE</b></h5>
  </header>


  <div class="w3-container">
<br>


<br>
<BR>
<BR>
		 <% if(user1.getRecords()==true) { %>
<table class="w3-table w3-striped w3-bordered w3-border  w3-white">
		<tr>
  				<th>Book Name</th>
  				<th>Author name</th>
  				<th>Publisher name</th>
  				<th>Book edition</th>
  				<th>Date of issue</th>
  				<th>Date of return</th>
  				<th>Total days</th>
   				<th>Fine</th>
  		</tr><tr>
  		<% for(int i=0;i<user1.getBlength();i++) {%>
  				<td><%=user1.getTitle(i)%></td>
  				<td><%=user1.getBauthor(i)%></td>
  				<td><%=user1.getBpublisher(i)%></td>
  				<td><%=user1.getBedition(i)%></td>
  				<td><%=user1.getDoi(i) %></td>
  				<td><%=user1.getDor(i)%></td>
  				<td ><%=user1.getAmt(i) %></td>
  				<td ><%=user1.getFamt(i) %></td>
		</tr><%if(i%2==0){ %><tr style="background-color:#CCC"><%}else{ %><tr>
		<%}}} else{%><h3>No Books Issued</h3><%} %>
</table>
<br>
<br>
<br>

   
  </div>
  <hr>
    </div>
  </div>

  <!-- Footer -->
 <jsp:include page="footer.jsp"></jsp:include>

</body>
</html>