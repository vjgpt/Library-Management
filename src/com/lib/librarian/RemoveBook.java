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
 * Servlet implementation class RemoveBook
 */
@WebServlet("/RemoveBook")
public class RemoveBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RemoveBook() {
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
	try{	Database dao = (Database)getServletContext().getAttribute("DBCPool");
		
		 response.setContentType("text/html");
		 String isbn=request.getParameter("isbn");
		 User user = (User) request.getSession().getAttribute("LOGIN");
		 user=dao.removeBook(isbn);
		 RequestDispatcher rd;
		 if(user!=null)
			{
				
				request.getSession().setAttribute("LOGIN", user);
				rd = request.getRequestDispatcher("/lib_remove_book.jsp");
				request.setAttribute("Successful!", "Book has been added succesfully!");
			}
			else{
				request.setAttribute("Sorry!", "Book wasn't added!");
				rd = request.getRequestDispatcher("/lib_remove_book.jsp");
			}
			rd.forward(request, response);
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	
	}
	

}
