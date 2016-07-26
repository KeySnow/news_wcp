package com.example.news_cy.utils;

import android.util.Log;

/**
 * 日志工具类
 *
 * @author wu
 *
 * 2016-6-28
 */
public class LogUtil {

	/**
	 * 项目上线前改为false
	 */
	public static boolean isDebugMode = true;
	
	/**
	 * 调试显示
	 * @param cls
	 * @param msg
	 */
	public static void showDebug(Object cls, String msg){
		if(isDebugMode){
			Log.i(cls.getClass().getName(), msg);
		}
	}
}
