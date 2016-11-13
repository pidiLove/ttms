package com.xupt.ttms.idao;

import java.util.ArrayList;

import com.xupt.ttms.model.Employee;

 
public interface IEmployee {
	public boolean insert(Employee employee);
	 public boolean delete(int employeeId);
	 public boolean update(Employee employee);
	 public boolean updatePassword(String username,String password);
	 public ArrayList<Employee> findEmployeeAll();
//	 public ArrayList<Employee> findEmployeeByName(String employeeName);
//	 public Employee findEmployeeById(int employeeId);
	 public int findEmployeeByName(String employeeName,String employeePassword);
}
