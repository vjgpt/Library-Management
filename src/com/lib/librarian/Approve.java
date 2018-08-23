package com.lib.librarian;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.lib.db.Database;
import com.lib.user.User;

/**
 * Servlet implementation class Approve
 */
@WebServlet("/Approve")
public class Approve extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Approve() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// TODO Auto-generated method stub
		try{	
			 String reg_no=request.getParameter("reg_id");
		Database dao = (Database)getServletContext().getAttribute("DBCPool");
		 response.setContentType("text/html");
		
		String approve=request.getParameter("approve");
		 User user = (User) request.getSession().getAttribute("LOGIN");
		
		 RequestDispatcher rd;
		 if(reg_no!=null)
			{
			 user=dao.approveMembership(user,reg_no);
			 request.getSession().setAttribute("LOGIN",user);
				rd = request.getRequestDispatcher("/lib_approve.jsp");
				rd.forward(request, response);
				
			}
		 if(approve!=null)
			{
			 if(approve.equals("app")==true )
			 { 
				 System.out.println("hello");
			
			String regd_no=user.getTempId();
		  
			 //System.out.println(regd_no);
				 user=dao.finalApproval(user,regd_no);
				 request.getSession().setAttribute("LOGIN",user);
				rd = request.getRequestDispatcher("/lib_approve.jsp");
				rd.forward(request, response);
				
			}}
	
		
			
		
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	}

}
