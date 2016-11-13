package com.xupt.ttms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xupt.ttms.dao.EmployeeDAO;
import com.xupt.ttms.model.Employee;

 
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public EmployeeServlet() {
        super();
     }
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
//		 String cur ="2"/*(String)request.getParameter("cur")*/;  
//	        List<Employee> list = new ArrayList<Employee>();  
//	        list = new EmployeeDAO().findAll((Integer.parseInt(cur)));  
//	        int totalPage = new EmployeeDAO().getTotalPage();  
//	        request.setAttribute("list", list);  
//	        request.setAttribute("totalPage", totalPage);  
//	        PrintWriter out = response.getWriter();
//	       // out.print(list);
	        RequestDispatcher rd = request.getRequestDispatcher("admin/list.jsp");  
	        rd.forward(request, response);   
}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
 	}
	 
    

}
