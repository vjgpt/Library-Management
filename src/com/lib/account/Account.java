package com.lib.account;

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
 * Servlet implementation class Account
 */
@WebServlet("/Account")
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Object user;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Account() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("title");
		String amt=null;
		String bid=null;
		// TODO Auto-generated method stub
		try{
			Database dao = (Database)getServletContext().getAttribute("DBCPool");
			response.setContentType("text/html;charset=UTF-8");
			User user = (User) request.getSession().getAttribute("Account");
			
			RequestDispatcher rd = null;
			if(id!=null)
			{
				user = dao.account(id);
				request.getSession().setAttribute("Account", user);
				rd = request.getRequestDispatcher("/fine_dep.jsp");
				rd.forward(request, response);
			}
			
			for(int i=0;i<user.getBlength();i++)
			{
				 bid=request.getParameter(user.getBid(i));
				 
				 if(bid!=null)
				  {
						 user= dao.amount(user,bid);
						 System.out.println("The fine given is this "+amt);
						 user.setSflag(true);
				         rd = request.getRequestDispatcher("/fine_dep.jsp");
				         rd.forward(request, response);
				  }
		      }
		amt=request.getParameter("fine");
		 System.out.println("The fine given is "+amt);
		 if(amt!=null)
		 { 
			 user= dao.updateamount(user,amt);
			 
		 request.getSession().setAttribute("Account", user);
		 rd = request.getRequestDispatcher("/fine_dep.jsp");
		 rd.forward(request, response);
		}
		
		
		     }
		     catch(Exception e)
			 {
				 System.out.println(e);
			 }
	}
}
	

	


