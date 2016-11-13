package com.xupt.ttms.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.xupt.ttms.idao.ISeatDAO;
import com.xupt.ttms.model.Seat;
import com.xupt.ttms.util.ConnectionManager;

/** 
 * @author wangpei 
 * @version 创建时间：2016年11月2日 下午7:26:25 
 * 类说明 
 */
public class SeatDAO implements ISeatDAO {
	
	 private static final int DATA_PER_PAGE = 5;  
	    /**
	     * 存储座位信息
	     * @return 成功与否boolean
	     */
	    @SuppressWarnings("finally")
	    public boolean insert(Seat seat)
	    {
	        boolean result = false;
	        if(seat == null)
	            return result;

	        //获取Connection
	        Connection con = ConnectionManager.getInstance().getConnection();
	        PreparedStatement pstmt = null;
	        try
	        {
	            String sql = "insert into seat(seat_id, studio_id, seat_row, seat_column, seat_status) values(?,?,?,?,?)";
	            pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, seat.getId());
	            pstmt.setInt(2, seat.getStudioId());
	            pstmt.setInt(3, seat.getRow());
	            pstmt.setInt(4, seat.getColumn());
	            pstmt.setInt(5, seat.getSeatStatus());

	           
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
	     * 删除座位(通过seatId)
	     * @return 成功与否boolean
	     */
	    @SuppressWarnings("finally")
	    public boolean delete(int seatId)
	    {
	        boolean result = false;
	        if(seatId == 0)
	            return result;

	        Connection con = ConnectionManager.getInstance().getConnection();
	        PreparedStatement pstmt = null;
	        try
	        {
	            // 删除子某个用户
	            String sql = "delete from seat where seat_id=?";
	            pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, seatId);
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
	     * 修改座位状态
	     * @return 成功与否boolean
	     */
	    @SuppressWarnings("finally")
	    public boolean update(Seat seat)
	    {
	        boolean result = false;
	        if(seat == null)
	            return result;

	        Connection con = ConnectionManager.getInstance().getConnection();
	        PreparedStatement pstmt = null;
	        try
	        {
	        	 
	            String sql = "update seat set studio_id=?,seat_row=?,seat_column=?,seat_status=? where seat_id=?";
	            pstmt = con.prepareStatement(sql);
	            pstmt.setInt(1, seat.getStudioId());
	            pstmt.setInt(2, seat.getRow());
	            pstmt.setInt(3, seat.getColumn());
	            pstmt.setInt(4, seat.getSeatStatus());
	            pstmt.setInt(5, seat.getId());
	           

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
	     * 获取所有座位信息(一般用于和界面交互)
	     * @return Seat列表
	     */
	    @SuppressWarnings("finally")/*查找所有用户信息*/
	    public ArrayList<Seat> findSeatAll()
	    {
	        ArrayList<Seat> list = new ArrayList<Seat>();
	        Seat info = null;

	        Connection con = ConnectionManager.getInstance().getConnection();
	        PreparedStatement pstmt = null;
	        ResultSet rs = null;
	        try
	        {
	            // 获取所有用户数据
	            pstmt = con.prepareStatement("select * from seat");
	            rs = pstmt.executeQuery();
	            while(rs.next())
	            {
	                info = new Seat();
	                info.setId(rs.getInt("seat_id"));
	                info.setId(rs.getInt("studio_id")); 
	                info.setId(rs.getInt("seat_row"));
	                info.setId(rs.getInt("seat_column"));
	                info.setId(rs.getInt("seat_status"));
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
	    
	  

}
