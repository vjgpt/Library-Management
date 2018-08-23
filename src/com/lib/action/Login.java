package com.lib.action;

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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	String sql;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		{				
				Database dao = (Database)getServletContext().getAttribute("DBCPool");
				response.setContentType("text/html;charset=UTF-8");
				String email = request.getParameter("usrname");
				String pass = request.getParameter("psw");
				RequestDispatcher rd=null;
				User user = dao.checkLogin(email,pass);
				if(user!=null){
				request.getSession().setAttribute("BOOKING", user);
				request.getSession().setAttribute("SEARCH", user);
				request.getSession().setAttribute("TRANSACTION", user);
				request.getSession().setAttribute("Account", user);
				user=dao.finepro(user);
				request.getSession().setAttribute("FINE", user);
				user=dao.checkLogin(email, pass);
				
				if(user!=null)
				{
					user = dao.roleAssign(user);
					user = dao.userDetails(user);
					user = dao.bookDetails(user);
					request.getSession().setAttribute("LOGIN", user);
					rd = request.getRequestDispatcher("/profile.jsp");
				}
				else{
					request.setAttribute("INVALID", "Userid or password is incorrect");
					rd = request.getRequestDispatcher("/index.jsp");
				}
				
		} else{
			request.setAttribute("NOTAPPROVE", "You need to get approved by the librarian.Please contact librarian");
			rd = request.getRequestDispatcher("/index.jsp");
		}
	rd.forward(request, response);
	}
	}
}
