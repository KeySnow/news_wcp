package com.example.news_wcp.utils;

import com.google.gson.Gson;

public class GsonUtil {

	public static <TYPE>TYPE jsonToBean(String json, Class<TYPE> cls){
		
		Gson gson = new Gson();
		
		return gson.fromJson(json, cls);
	}
	
}
