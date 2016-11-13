package com.xupt.ttms.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xupt.ttms.dao.EmployeeDAO;

 
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
    public ChangePassword() {
        super();
     }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username=(String) request.getSession().getAttribute("username");
		String newPassword=request.getParameter("password");
		System.out.println(username);
		System.out.println(newPassword);
		new EmployeeDAO().updatePassword(username,newPassword);
		RequestDispatcher rd = request.getRequestDispatcher("zhuye.jsp");  
	    rd.forward(request, response);   
 	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
 	}

}
