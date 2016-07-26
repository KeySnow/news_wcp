package com.example.news_wcp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.example.news_wcp.utils.LogUtil;

public abstract class BaseActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtil.showLog(this, "onCreate...");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(getLayoutId());
		
		initView();
		initData();
		initListener();
	}
	
	@Override
	protected void onStart() {
		super.onStart();
		LogUtil.showLog(this, "onStart...");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		LogUtil.showLog(this, "onRestart...");
	}

	@Override
	protected void onResume() {
		super.onResume();
		LogUtil.showLog(this, "onCreate...");
	}

	@Override
	protected void onPause() {
		super.onPause();
		LogUtil.showLog(this, "onPause...");
	}

	@Override
	protected void onStop() {
		super.onStop();
		LogUtil.showLog(this, "onStop...");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		LogUtil.showLog(this, "onDestroy...");
	}
	
	/**
	 * 普通跳转页面
	 * @param context
	 * @param cls
	 */
	public void openActivity(Class cls){
		openActivity(cls, null);
	}
	/**
	 * 带数据跳转页面
	 * @param context
	 * @param cls
	 * @param bundle
	 */
	public void openActivity(Class cls, Bundle bundle){
		Intent intent = new Intent(this, cls);
		if(bundle != null){
			intent.putExtras(bundle);
		}
		startActivity(intent );
	}

	public abstract int getLayoutId();
	public abstract void initView();
	public abstract void initData();
	public abstract void initListener();

	
}
