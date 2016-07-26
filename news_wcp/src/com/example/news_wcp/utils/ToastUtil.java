package com.example.news_wcp.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastUtil {

	public static Toast toast = null;
	
	public static void showToast(Context context, String msg, int time){
		if(toast == null){
			toast = Toast.makeText(context, msg, time);
		}
		
		toast.setText(msg);
		toast.setDuration(time);
		toast.show();
		
	}
	
}
