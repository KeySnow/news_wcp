package com.example.news_cy.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * 吐司工具类
 *
 * @author wu
 *
 * 2016-6-28
 */
public class ToastUtil {

	private static Toast toast;
	
	/**
	 * 显示吐司
	 * @param context
	 * @param text
	 * @param duration
	 */
	public static void showToast(Context context, String text, int duration){
		
		if(toast == null){
			toast = Toast.makeText(context, text, duration);
		}
		
		toast.setText(text);
		toast.setDuration(duration);
		toast.show();
	}
	
}
