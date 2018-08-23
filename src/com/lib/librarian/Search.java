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
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
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
		
		try{
			Database dao = (Database)getServletContext().getAttribute("DBCPool");
			 response.setContentType("text/html");
			 RequestDispatcher rd;
			 String kwd=request.getParameter("keywords");
			 String ttl=request.getParameter("title");
			 String aut=request.getParameter("author");
			 String isbn=request.getParameter("isbn");
			 String pub=request.getParameter("publisher");
			 User user=new User();
			user= dao.search(kwd,ttl,aut,isbn,pub);
			 user.setFlag(1);
			 request.getSession().setAttribute("SEARCH", user);
			 user=null;
			 rd = request.getRequestDispatcher("/Search.jsp");
			 rd.forward(request, response);
			
		}  catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
