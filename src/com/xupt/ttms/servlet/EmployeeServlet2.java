package com.xupt.ttms.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
import com.xupt.ttms.dao.EmployeeDAO;
import com.xupt.ttms.model.Employee;

 
@WebServlet("/EmployeeServlet2")
public class EmployeeServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
     
    public EmployeeServlet2() {
        super();
     }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		request.setCharacterEncoding("utf-8");
		String cur="2"/*request.getParameter("cur")*/;
		 List<Employee> list = new ArrayList<Employee>();  
		 list = new EmployeeDAO().findAll((Integer.parseInt(cur)));  
		Map<String, Object> reMap = new HashMap<String, Object>();
		//int totalPage = new StudioDAO().getTotalPage();
		reMap.put("total", 1);  
		reMap.put("pageSize",5);  
		reMap.put("pageNo", cur);  
		reMap.put("rows", list);  
		PrintWriter out = response.getWriter();
		out.print(reMap);
		out.flush();
		out.close();
 	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 doGet(request,response);
	}
	public static void main(String[] args) throws ServletException, IOException {
		HttpServletRequest request=null;
		HttpServletResponse response=null;
		new EmployeeServlet2().doGet(request, response);
		
	}

}
