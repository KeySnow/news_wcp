package com.example.news_cy.utils;

import com.google.gson.Gson;
/**
 * Gaonπ§æﬂ¿‡
 *
 * @author wu
 *
 * 2016-6-29
 */
public class GsonUtil {

	public static <TYPE>TYPE JsonToBean(String json, Class<TYPE> cls){
		Gson gson = new Gson();
		return gson.fromJson(json, cls);
	}
}
