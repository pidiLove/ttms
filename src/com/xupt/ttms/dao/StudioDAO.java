package com.xupt.ttms.dao;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.xupt.ttms.idao.iStudioDAO;
import com.xupt.ttms.model.Studio;
import com.xupt.ttms.util.ConnectionManager;

 /*演出厅管理
  * */
public class StudioDAO implements iStudioDAO
{ private static final int DATA_PER_PAGE = 5; 
    /**
     * 存储演出厅信息
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean insert(Studio studio)
    {
        boolean result = false;
        if(studio == null)
            return result;

        // 获取Connection
        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            String sql = "insert into studio(studio_name, studio_row_count, studio_col_count, studio_introduction, studio_flag) values(?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, studio.getName());
            pstmt.setInt(2, studio.getRowCount());
            pstmt.setInt(3, studio.getColCount());
            pstmt.setString(4, studio.getIntroduction());
            pstmt.setInt(5, studio.getStudioFlag());

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
     * 删除演出厅(通过studioId)
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean delete(int studioId)
    {
        boolean result = false;
        if(studioId == 0)
            return result;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            // 删除子某个演出厅
            String sql = "delete from studio where studio_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1,studioId);
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
     * 修改演出厅信息
     * @return 成功与否boolean
     */
    @SuppressWarnings("finally")
    public boolean update(Studio studio)
    {
        boolean result = false;
        if(studio == null)
            return result;
         Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        try
        {
            String sql = "update studio set studio_name=?,studio_row_count=?,studio_col_count=?,studio_introduction=?,studio_flag=? where studio_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, studio.getName());
            pstmt.setInt(2, studio.getRowCount());
            pstmt.setInt(3, studio.getColCount());
            pstmt.setString(4, studio.getIntroduction());
            pstmt.setInt(5, studio.getStudioFlag());
            pstmt.setInt(6, studio.getID());

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
     * 获取所有演出厅信息(一般用于和界面交互)
     * 以json格式的字符串显示出来
     * @return 
     * @return Studio列表
     */
    @SuppressWarnings("finally")
    public JsonElement findStudioAll(int cur)
    {
        ArrayList<Map<String,Studio>> list = new ArrayList<Map<String,Studio>>();
        Map<String,Studio> map=new HashMap<String,Studio>();
        Studio info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        
        JsonArray array=null;
        JsonElement s=null;
        try
        {
        	 String sql = "select * from studio where 1 limit ?,?";  
             pstmt = con.prepareStatement(sql);  
            pstmt.setInt(1, (cur - 1) * DATA_PER_PAGE);  
            pstmt.setInt(2, DATA_PER_PAGE);
             
            rs = pstmt.executeQuery();
             array = new JsonArray();  
   
            while (rs.next()) {  
  
                JsonObject ob = new JsonObject();  
  
                ob.addProperty("studio_id", rs.getInt("studio_id"));  
                ob.addProperty("studio_name", rs.getString("studio_name"));  
                ob.addProperty("studio_row_count", rs.getInt("studio_row_count"));  
                ob.addProperty("studio_col_count", rs.getInt("studio_col_count"));  
                ob.addProperty("studio_introduction", rs.getString("studio_introduction"));  
                ob.addProperty("studio_flag", rs.getInt("studio_flag"));  
            
               array.add(ob);  
  
            } 
            } catch(Exception e){
            	System.out.println("1处出错了");
            	e.printStackTrace();
            }
  
 //           object.add("News", array);  
            
         //    out.print(array.toString());  
            return array;
    
           
          
    }
        public int getTotalPage(){  
            Connection conn = null;  
            PreparedStatement pstmt = null;  
            ResultSet rs = null;  
            String sql = "";  
            int count = 0;  
            try {  
                sql = "select count(*) from studio";  
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

    /**
     * 根据演出厅名获取演出厅信息(一般用于和界面交互)
     * @return Employee列表
     */
    @SuppressWarnings("finally")
    public ArrayList<Studio> findStudioByName(String studioName)
    {
        if(studioName == null || studioName.equals(""))
            return null;

        ArrayList<Studio> list = new ArrayList<Studio>();
        Studio info = null;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据:模糊查询
            pstmt = con.prepareStatement("select * from studio where studio_name like ?");
            pstmt.setString(1, "%" + studioName + "%");// 拼接模糊查询串
            rs = pstmt.executeQuery();
            while(rs.next())
            {
                // 如果有值的话再实例化
                info = new Studio();
                info.setID(rs.getInt("studio_id"));
                info.setName(rs.getString("studio_name"));
                info.setRowCount(rs.getInt("studio_row_count"));
                info.setColCount(rs.getInt("studio_col_count"));
                info.setIntroduction(rs.getString("studio_introduction"));
                info.setStudioFlag(rs.getInt("studio_flag"));
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

    /**
     * 根据studio_id获取演出厅信息(一般用于数据内部关联操作)
     * @return 演出厅
     */
    @SuppressWarnings("finally")
    public Studio findStudioById(int studioId)
    {
        Studio info = null;
        if(studioId == 0)
            return info;

        Connection con = ConnectionManager.getInstance().getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try
        {
            // 获取所有用户数据
            pstmt = con.prepareStatement("select * from studio where studio_id=?");
            pstmt.setInt(1, studioId);
            rs = pstmt.executeQuery();
            if(rs.next())
            {
                // 如果有值的话再实例化
                info = new Studio();
                info.setID(rs.getInt("studio_id"));
                info.setName(rs.getString("studio_name"));
                //studio_row_count=?,studio_col_count=?,studio_introduction=?,studio_flag
                info.setRowCount(rs.getInt("studio_row_count"));
                info.setColCount(rs.getInt("studio_col_count"));
                info.setIntroduction(rs.getString("studio_introduction"));
                info.setStudioFlag(rs.getInt("studio_flag"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionManager.close(rs, pstmt, con);
            return info;
        }
    
    }
    public static void main(String[] args) {
		StudioDAO d=new StudioDAO();
		System.out.println(d.findStudioAll(1)); 
	}

}