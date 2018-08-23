package com.lib.staff;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lib.db.Database;
import com.lib.user.User;


@WebServlet("/Booking")
public class Booking extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static int i=0;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			Database dao = (Database)getServletContext().getAttribute("DBCPool");
			 response.setContentType("text/html");
			 String ttl=request.getParameter("title");
			 RequestDispatcher rd;
			 if(ttl!=null){
			 User user= dao.booking(ttl);
			 user.setFlag(1);
			 request.getSession().setAttribute("BOOKING", user);
			 user=null;
			 rd = request.getRequestDispatcher("/booking.jsp");
			 rd.forward(request, response);
			 }
			 User user=(User) request.getSession().getAttribute("BOOKING");
			 for(i=0;i<user.getBlength();i++) {
			 String bid=request.getParameter(user.getBid(i));
			 if(bid!=null) {
				 User user1=(User) request.getSession().getAttribute("LOGIN");
				 user= dao.updateBooking(user1,bid);
				 user.setFlag(2);
				 request.getSession().setAttribute("BOOKING", user);
				 user1=null;
				 rd = request.getRequestDispatcher("/booking.jsp");
				 rd.forward(request, response);
			 }
			 }
			 
			 } catch(Exception e)
		{
			System.out.println(e);
		}
	}
}