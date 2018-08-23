<%@page import="com.lib.user.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<div class="w3-container">
		<%User user = (User)session.getAttribute("LOGIN"); %>
    <h5>MENU</h5>
  </div>
  <a href="#" class="w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>  Close Menu</a>
  <a id="a1" href="profile.jsp" onclick="addClass(this.id)" class="w3-padding"> Profile</a>
  <a id="a2" href="Search.jsp" onclick="addClass(this.id)" class="w3-padding">  Search</a>
  <%if(user.getRole()==1){ %>
   <a id="a3" href="lib_add_book.jsp" onclick="addClass(this.id)" class="w3-padding">  Add Book</a>
    <a id="a4" href="lib_remove_book.jsp" onclick="addClass(this.id)" class="w3-padding">  Remove Book</a>
  <a id="a5" href="lib_approve.jsp" onclick="addClass(this.id)" class="w3-padding">  Approval</a>
   <a id="a9" href="lib_transaction.jsp" onclick="addClass(this.id)" class="w3-padding">  Transaction</a>
  <%}else if(user.getRole()==2 || user.getRole()==3 ){ %>
   <a id="a6" href="booking.jsp" onclick="addClass(this.id)" class="w3-padding"> Booking</a>
<a id="a7" href="fine.jsp" onclick="addClass(this.id)" class="w3-padding">  Fine</a><br><br>
  <%} else if(user.getRole()==4){ %>
  <a id="a8" href="fine_dep.jsp" onclick="addClass(this.id)" class="w3-padding">  Fine Deposit</a><br><br>
  <%} %>
</nav>