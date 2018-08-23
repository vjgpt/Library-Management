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
 * Servlet implementation class Transaction
 */
@WebServlet("/Transaction")
public class Transaction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transaction() {
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
			Database dao = (Database)getServletContext().getAttribute("DBCPool");
			 response.setContentType("text/html");
			 String ttl=request.getParameter("ttl");
			String ttl1=request.getParameter("ttl1");
			 String regd_no=request.getParameter("regd_no");
			  User user=(User)request.getSession().getAttribute("TRANSACTION");
			 			
   			 RequestDispatcher rd=null,rd1;
			 if(regd_no!=null){
				 
			  user=dao.tralogin(regd_no);
			 request.getSession().setAttribute("TRANSACTION", user);
				  System.out.println("Tralogin ");
			  rd = request.getRequestDispatcher("/lib_transaction.jsp");
				 rd.forward(request, response);
			 }
			 if(ttl!=null){
				 System.out.println("title-issue"+ttl);
			 user= dao.issue(user,ttl);
			 System.out.println("issue k baad");
			 request.getSession().setAttribute("TRANSACTION", user);	
			 rd1 = request.getRequestDispatcher("/lib_transaction.jsp");
			 rd1.forward(request, response);
			 }
			 
			 if(user.getIssue()==1){
				 System.out.println("get issue meh");
				 for(int i=user.getLength();i<user.getBlength();i++)
				 { 
					 	String bid=request.getParameter(user.getBid(i));
					 	if(bid!=null)
					 	{
					 			user=dao.finalIssue(user, bid);
					 			 System.out.println("final issue");
					 			 request.getSession().setAttribute("TRANSACTION", user);
					 			 
					 			rd = request.getRequestDispatcher("/lib_transaction.jsp");
					 			rd.forward(request, response);
					 	}
				 }
			 }
			if(ttl1!=null){
				 System.out.println("title-return"+ttl1);
				user=(User) request.getSession().getAttribute("TRANSACTION");
				 user= dao.returnbook(user,ttl1);
				 System.out.println("return book k baad");
				 rd1 = request.getRequestDispatcher("/lib_transaction.jsp");
				 rd1.forward(request, response);
				 System.out.println("return forward");
				 }	 
		if(user.getRtn()==1){
			 System.out.println("get return meh");
			 for(int i=user.getLength();i<user.getBlength();i++)
			 {
				String bid=request.getParameter(user.getBid(i));
				 System.out.println("return"+bid);
				if(bid!=null)
				{
					user=dao.finalReturn(user, bid);
					System.out.println("Final return k baad");
					 user=dao.fine(user,bid);
					 rd = request.getRequestDispatcher("/lib_transaction.jsp");
					 rd.forward(request, response);
				}
			}
		 }
	}catch(Exception e)
		{
		System.out.println(e);
	}

}}
