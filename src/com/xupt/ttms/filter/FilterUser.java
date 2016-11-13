package com.xupt.ttms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
@WebFilter("/user/*")
public class FilterUser implements Filter {

     
    public FilterUser() {
     }

	 
	public void destroy() {
 	}

	 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		String s=(String)req.getSession().getAttribute("userflag");
		if(s!=null&&s.equalsIgnoreCase("ok")){
			chain.doFilter(request, response);

		}
		else{ 
			request.setAttribute("desc", "对不起，您无权访问！！！");
            RequestDispatcher rd = request.getRequestDispatcher("/error.jsp");
            rd.forward(request, response);
			
		}
 	}

	 
	public void init(FilterConfig fConfig) throws ServletException {
		 
	}

}
