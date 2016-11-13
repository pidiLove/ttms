package com.xupt.ttms.idao;

import com.xupt.ttms.dao.EmployeeDAO;
import com.xupt.ttms.dao.SeatDAO;
import com.xupt.ttms.dao.StudioDAO;
 
public class DAOFactory {
	public static iStudioDAO creatStudioDAO(){
		return new StudioDAO();
	}
	
	

	public static ISeatDAO creatSeatDAO(){
		return new SeatDAO();
	}

	
	public static IEmployee creatEmployeeDAO() {
		return new EmployeeDAO();
	}
 }
