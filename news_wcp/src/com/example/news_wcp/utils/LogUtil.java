package com.example.news_wcp.utils;

import android.util.Log;
/**
 * 日志工具类
 * @author wu
 *
 */
public class LogUtil {

	public static boolean isDebugMode = true;
	
	public static void showLog(Object cls, String msg){
		if(isDebugMode){
			Log.i(cls.getClass().getName(), msg);
		}
	}
}
