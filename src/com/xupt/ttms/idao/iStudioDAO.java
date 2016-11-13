package com.xupt.ttms.idao;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.xupt.ttms.model.Studio;

/**
 * @author wangpei
 * @version 创建时间：2016年11月1日 下午8:06:05 类说明
 */
public interface iStudioDAO {
	public boolean insert(Studio studio);

	public boolean delete(int studioId);

	public boolean update(Studio studio);

	public JsonElement findStudioAll(int cur);

	public ArrayList<Studio> findStudioByName(String studioName);

	public Studio findStudioById(int studioId);
}
