package com.example.news_cy.utils;

import android.util.Log;

/**
 * ��־������
 *
 * @author wu
 *
 * 2016-6-28
 */
public class LogUtil {

	/**
	 * ��Ŀ����ǰ��Ϊfalse
	 */
	public static boolean isDebugMode = true;
	
	/**
	 * ������ʾ
	 * @param cls
	 * @param msg
	 */
	public static void showDebug(Object cls, String msg){
		if(isDebugMode){
			Log.i(cls.getClass().getName(), msg);
		}
	}
}
