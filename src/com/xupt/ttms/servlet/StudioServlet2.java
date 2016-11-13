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

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xupt.ttms.dao.EmployeeDAO;
import com.xupt.ttms.dao.StudioDAO;
import com.xupt.ttms.model.Employee;

 
@WebServlet("/StudioServlet2")
public class StudioServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public StudioServlet2() {
        super();
     }

	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
 	}

	 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 
		String cur= request.getParameter("page");
		JsonElement list = new StudioDAO().findStudioAll(1);  
		Map<String, Object> reMap = new HashMap<String, Object>();
		int totalPage = new StudioDAO().getTotalPage();
		reMap.put("total", totalPage);  
		reMap.put("pageSize",5);  
		reMap.put("pageNo", cur);  
		reMap.put("rows", list);  

 		JsonObject ob= new JsonObject();
 		 ob.addProperty("totalPage",totalPage);
		 ob.addProperty("pageSize",5);
		 ob.addProperty("pageNo",cur);
		 ob.add("rows", list);
  		PrintWriter out = response.getWriter();
		
		out.print(ob);
		out.flush();
		out.close();
 	}

}
