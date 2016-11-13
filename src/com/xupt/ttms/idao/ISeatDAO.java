package com.xupt.ttms.idao;

import java.util.ArrayList;

import com.xupt.ttms.model.Seat;

/** 
 * @author wangpei 
 * @version 创建时间：2016年11月1日 上午7:29:43 
 * 类说明 
 */
public interface ISeatDAO {
	 public boolean insert(Seat seat);
	 public boolean delete(int seatId);
	 public boolean update(Seat seat);
	 public ArrayList<Seat> findSeatAll();

}
