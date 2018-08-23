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
 * Servlet implementation class AddBook
 */
@WebServlet("/AddBook")
public class AddBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddBook() {
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
		
			 String title= request.getParameter("title");
				int price=Integer.parseInt( request.getParameter("price"));
				String edition= request.getParameter("edition");
				String author= request.getParameter("author");
				String isbn= request.getParameter("isbn");
				String publisher= request.getParameter("publisher");
				String publisher_place= request.getParameter("publisher_place");
				int quantity= Integer.parseInt(request.getParameter("quantity"));
			   // String add=request.getParameter("add");
			 User user = (User) request.getSession().getAttribute("LOGIN");
			 user=dao.addBook(title,price,edition,author,isbn,publisher,publisher_place,quantity);
			 RequestDispatcher rd;
				if(user!=null)
				{
					user.setSflag(true);
					request.getSession().setAttribute("LOGIN", user);
					rd = request.getRequestDispatcher("/lib_add_book.jsp");
					
				}
				else{
					request.setAttribute("Sorry!", "Book wasn't added!");
					rd = request.getRequestDispatcher("/lib_add_book.jsp");
				}
				rd.forward(request, response);
			
			
			 
			 } catch(Exception e)
		{
			System.out.println(e);
		}
	}

}
