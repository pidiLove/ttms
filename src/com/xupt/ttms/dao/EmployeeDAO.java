package com.xupt.ttms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.xupt.ttms.idao.IEmployee;
import com.xupt.ttms.model.Employee;
import com.xupt.ttms.util.ConnectionManager;

 
public class EmployeeDAO implements IEmployee
{   private static final int DATA_PER_PAGE = 5;  
    /**
     * 存储用户信息
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean insert(Employee employee)
    {
        boolean result = false;
        if(employee == null)
            return result;

        // 获取Connection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            String sql = "insert into Employee(emp_no, emp_name, emp_password, emp_tel_num, emp_addr, emp_email,emp_flag) values(?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, employee.getEmp_no());
            pstmt.setString(2, employee.getEmp_name());
            pstmt.setString(3, employee.getEmp_password());
            pstmt.setString(4, employee.getEmp_tel_num());
            pstmt.setString(5, employee.getEmp_addr());
            pstmt.setString(6, employee.getEmp_email());
            pstmt.setString(7, employee.getEmp_flag());
            pstmt.executeUpdate();
            result = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    /**
     * 删除用户(通过employeeId)
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean delete(int employeeId)
    {
        boolean result = false;
        if(employeeId == 0)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            // 删除子某个用户
            String sql = "delete from employee where emp_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, employeeId);
            pstmt.executeUpdate();
            ConnectionManager.close(null, pstmt, con);
            result = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    /**
     * 修改用户信息
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean update(Employee employee)
    {
        boolean result = false;
        if(employee == null)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            String sql = "update employee set emp_no=?,emp_name=?,emp_password=?,emp_tel_num=?,emp_addr=?,emp_email=?,emp_flag=? where emp_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, employee.getEmp_no());
            pstmt.setString(2, employee.getEmp_name());
            pstmt.setString(3, employee.getEmp_password());
            pstmt.setString(4, employee.getEmp_tel_num());
            pstmt.setString(5, employee.getEmp_addr());
            pstmt.setString(6, employee.getEmp_email());
            pstmt.setInt(7, employee.getEmp_id());
            pstmt.setString(8, employee.getEmp_flag());
            pstmt.executeUpdate();
            result = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    /**
     * 通过用户名称修改用户密码
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean updatePassword(String username,String password)
    {
        boolean result = false;
        if(username == null)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            String sql = "update employee set emp_password=? where emp_name=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, password);
            pstmt.setString(2, username);
            
            pstmt.executeUpdate();
            result = true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            // 关闭连接
            ConnectionManager.close(null, pstmt, con);
            return result;
        }
    }

    /**
     * 获取所有用户信息(一般用于和界面交互)
     * @return Employee列表
     */
    @SuppressWarnings("finally")/*查找所有用户信息*/
    public ArrayList<Employee> findEmployeeAll()
    {
        ArrayList<Employee> list = new ArrayList<Employee>();
        Employee info = null;
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from employee");
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                info = new Employee();

                info.setEmp_id(rs.getInt("emp_id"));
                info.setEmp_no(rs.getString("emp_no"));
                info.setEmp_name(rs.getString("emp_name"));
                info.setEmp_name(rs.getString("emp_password"));
                info.setEmp_tel_num(rs.getString("emp_tel_num"));
                info.setEmp_addr(rs.getString("emp_addr"));
                info.setEmp_email(rs.getString("emp_email"));
                info.setEmp_email(rs.getString("emp_flag"));
                // 加入列表
                list.add(info);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, con);
            return list;
        }
    }
    
    @SuppressWarnings("finally")/*查找所有用户信息*/
    public List<Employee>findAll(int cur){
    	 List<Employee> list = new ArrayList<Employee>();  
         
         Connection conn = null;  
         PreparedStatement pstmt = null;  
         ResultSet rs = null;  
         String sql = "";  
         try {  
             sql = "select * from employee where 1 limit ?,?";  
             conn = ConnectionManager.getInstance().getConnection();
             pstmt = conn.prepareStatement(sql);  
             pstmt.setInt(1, (cur - 1) * DATA_PER_PAGE);  
             pstmt.setInt(2, DATA_PER_PAGE);  
               
             rs = pstmt.executeQuery();  
               
             while(rs.next()){  
                 Employee test = new Employee();  
                 test.setEmp_id(rs.getInt("emp_id"));
                 test.setEmp_no(rs.getString("emp_no"));
                 test.setEmp_name(rs.getString("emp_name"));
                 test.setEmp_password(rs.getString("emp_password"));
                 test.setEmp_tel_num(rs.getString("emp_tel_num"));
                 test.setEmp_addr(rs.getString("emp_addr"));
                 test.setEmp_email(rs.getString("emp_email"));
                 test.setEmp_email(rs.getString("emp_flag"));
                 // 加入列表
                 list.add(test); 
             }  
               
         } catch (Exception e) {  
             e.printStackTrace();  
         }finally{  
               
        	  ConnectionManager.close(rs, pstmt, conn);
              return list;
         }  
        
    }
 public int getTotalPage(){  
        Connection conn = null;  
        PreparedStatement pstmt = null;  
        ResultSet rs = null;  
        String sql = "";  
        int count = 0;  
        try {  
            sql = "select count(*) from employee";  
            conn = ConnectionManager.getInstance().getConnection();  
            pstmt = conn.prepareStatement(sql);  
              
            rs = pstmt.executeQuery();  
              
            while(rs.next()){  
                count = rs.getInt(1);  
            }  
              
            count = (int)Math.ceil((count + 1.0 - 1.0) / DATA_PER_PAGE);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{
       	  ConnectionManager.close(rs, pstmt, conn);
       	  
        } 
        return count;
    }  
      

//    /**
//     * 根据用户名获取用户信息(一般用于和界面交互)
//     * @return Employee列表
//     */
//    @SuppressWarnings("finally")
//    public ArrayList<Employee> findEmployeeByName(String employeeName)
//    {
//        if(employeeName == null || employeeName.equals(""))
//            return null;
//
//        ArrayList<Employee> list = new ArrayList<Employee>();
//        Employee info = null;
//
//        Connection con = ConnectionManager.getInstance().getConnection();
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try
//        {
//            // 获取所有用户数据:模糊查询
//            pstmt = con.prepareStatement("select * from employee where emp_name like ?");
//            pstmt.setString(1, "%" + employeeName + "%");// 拼接模糊查询串
//            rs = pstmt.executeQuery();
//            while(rs.next())
//            {
//                // 如果有值的话再实例化
//                info = new Employee();
//                info.setEmp_id(rs.getInt("emp_id"));
//                info.setEmp_no(rs.getString("emp_no"));
//                info.setEmp_name(rs.getString("emp_name"));
//                info.setEmp_tel_num(rs.getString("emp_tel_num"));
//                info.setEmp_addr(rs.getString("emp_addr"));
//                info.setEmp_email(rs.getString("emp_email"));
//                // 加入列表
//                list.add(info);
//            }
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            ConnectionManager.close(rs, pstmt, con);
//            return list;
//        }
//    }
//
//    /**
//     * 根据employee_id获取用户信息(一般用于数据内部关联操作)
//     * @return 用户
//     */
//    @SuppressWarnings("finally")
//    public Employee findEmployeeById(int employeeId)
//    {
//        Employee info = null;
//        if(employeeId == 0)
//            return info;
//
//        Connection con = ConnectionManager.getInstance().getConnection();
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//        try
//        {
//            // 获取所有用户数据
//            pstmt = con.prepareStatement("select * from employee where emp_id=?");
//            pstmt.setInt(1, employeeId);
//            rs = pstmt.executeQuery();
//            if(rs.next())
//            {
//                // 如果有值的话再实例化
//                info = new Employee();
//                info.setEmp_id(employeeId);
//                info.setEmp_no(rs.getString("emp_no"));
//                info.setEmp_name(rs.getString("emp_name"));
//                info.setEmp_tel_num(rs.getString("emp_tel_num"));
//                info.setEmp_addr(rs.getString("emp_addr"));
//                info.setEmp_email(rs.getString("emp_email"));
//            }
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
//        finally
//        {
//            ConnectionManager.close(rs, pstmt, con);
//            return info;
//        }
//    }
 /**
 /**
     * 根据用户名密码判断用户是否合法
     * @return Employee列表
     */
    @SuppressWarnings("finally")
    public int findEmployeeByName(String employeeName,String employeePassword)
    {
        if(employeeName == null || employeeName.equals("")||employeePassword == null || employeePassword.equals(""))
            return -1;
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int flag = -1;
        try
        {
            // 获取所有用户数据:模糊查询
        	pstmt = con.prepareStatement("select emp_flag from employee where emp_name=? and emp_password=?");
            pstmt.setString(1,employeeName); 
            pstmt.setString(2,employeePassword); 
            rs = pstmt.executeQuery();
            while(rs.next()){ 
            flag=rs.getInt(1);//flag=-1用户名，密码错误 flag=1:管理员flag=2:用户
            }
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {    
            ConnectionManager.close(rs, pstmt, con);
            return flag;
         }
    }
    
    public static void main(String[] args) {
		EmployeeDAO d=new EmployeeDAO();
	//	System.out.println(d.findAll(2).get(1).getEmp_email());
		 
	}

}