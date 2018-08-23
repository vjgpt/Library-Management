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
 * Servlet implementation class Regd
 */
@WebServlet("/Regd")
public class Regd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	int r=0;
    public Regd() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		{		Database dao = (Database)getServletContext().getAttribute("DBCPool");
		 User user = (User) request.getSession().getAttribute("LOGIN");
		response.setContentType("text/html;charset=UTF-8");
		String id = request.getParameter("usrname");
		String name = request.getParameter("name");
		int dept = Integer.parseInt(request.getParameter("dept"));
		String stu_course = request.getParameter("course");
		String role = request.getParameter("vehicle");
		String address=request.getParameter("address");
		String mobile = request.getParameter("mobile");
		String psw=request.getParameter("psw");
		
		if(request.getParameter("staff")!=null)
		{
			stu_course="";
		user= dao.roles(role);
		r=user.getRole();
		System.out.println("role="+r);
		user = dao.reg(id,name,dept,r,stu_course,address,mobile,psw);
		RequestDispatcher rd;
		if(id!=null)
		{
			request.setAttribute("VALID", "Registered Successfully");
			rd = request.getRequestDispatcher("/index.jsp");
		}
		else{
			request.setAttribute("INVALID", "Userid or password is incorrect");
			rd = request.getRequestDispatcher("/index.jsp");
		}
		rd.forward(request, response);
}
		else if(request.getParameter("stud")!=null){
			r=3;
			user = dao.reg(id,name,dept,r,stu_course,address,mobile,psw);
			RequestDispatcher rd;
			if(id!=null)
			{
				request.setAttribute("VALID", "Registered Successfully");
				rd = request.getRequestDispatcher("/index.jsp");
			}
			else{
				request.setAttribute("INVALID", "Userid or password is incorrect");
				rd = request.getRequestDispatcher("/index.jsp");
			}
			rd.forward(request, response);
			
		}
		else
		{
			request.setAttribute("invalid","Incorrect !");
		}
}


	}

}
