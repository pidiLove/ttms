package com.xupt.ttms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 
@WebFilter("/*")
public class EncodingFilter implements Filter {

     
    public EncodingFilter() {
     }

	 
	public void destroy() {
 	}

	 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		 HttpServletRequest req=(HttpServletRequest)request;
		 HttpServletResponse resp=(HttpServletResponse)response;
		 if(req.getMethod().equalsIgnoreCase("post")){
			 req.setCharacterEncoding("utf-8");
			 
		 }
		 resp.setCharacterEncoding("utf-8");
		chain.doFilter(request, response);
	}
 
	public void init(FilterConfig fConfig) throws ServletException {
 	}

}
