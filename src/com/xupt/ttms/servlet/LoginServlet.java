package com.xupt.ttms.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xupt.ttms.dao.EmployeeDAO;

 
@WebServlet("/UserJudge")
public class LoginServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
     
    public LoginServlet() {
        super();
     }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user=request.getParameter("user");
		String pass=request.getParameter("pass");
		String login=request.getParameter("loginflag");//
		String page="/login.jsp";
		String desc="用户密码或用户名错误";
		request.getSession().setAttribute("loginflag", null);//作用：清除session
		request.getSession().setAttribute("adminflag", null);
		request.getSession().setAttribute("userflag", null);
		int flag=new EmployeeDAO().findEmployeeByName(user, pass);
		System.out.println(flag);
		if(flag==1){
			request.getSession().setAttribute("loginflag","yes");
			request.getSession().setAttribute("adminflag", "ok");
			request.getSession().setAttribute("username", user);
			page="/zhuye.jsp";
			desc="管理员登陆";
			
		}
		else if(flag==2)
		{
			request.getSession().setAttribute("loginflag","yes");
			request.getSession().setAttribute("userflag", "ok");
			request.getSession().setAttribute("username", user);
			page="/zhuye.jsp";
			desc="用户"+user+"登陆";
			
		}
		else{
			
			
		}
		
		request.setAttribute("desc", desc);
	    RequestDispatcher rd=request.getRequestDispatcher(page);
        rd.forward(request, response);
 	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
