package com.example.news_cy.base;

import com.example.news_cy.utils.LogUtil;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
/**
 * 活动基类
 *
 * @author wu
 *
 * 2016-6-28
 */
public abstract class BaseActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LogUtil.showDebug(this, "onCreate...");
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(getLayoutId());
		initView();
		ininData();
		initListener();
	}



	@Override
	protected void onStart() {
		super.onStart();
		LogUtil.showDebug(this, "onStart...");
	}

	@Override
	protected void onRestart() {
		super.onRestart();
		LogUtil.showDebug(this, "onRestart...");
	}

	@Override
	protected void onResume() {
		super.onResume();
		LogUtil.showDebug(this, "onResume...");
	}

	@Override
	protected void onPause() {
		super.onPause();
		LogUtil.showDebug(this, "onPause...");
	}

	@Override
	protected void onStop() {
		super.onStop();
		LogUtil.showDebug(this, "onStop...");
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		LogUtil.showDebug(this, "onDestroy...");
	}

	/**
	 * 活动跳转
	 * @param targetActivity
	 */
	protected void openActivity(Class targetActivity){
		openActivity(targetActivity, null);
	}
	
	/**
	 * 带数据捆的活动跳转
	 * @param targetActivity
	 * @param bundle
	 */
	protected void openActivity(Class targetActivity, Bundle bundle){
		Intent intent = new Intent(this, targetActivity);
		if(bundle != null){
			intent.putExtras(bundle);
		}
		startActivity(intent );
	}
	
	//子类必须实现的抽象方法
	protected abstract int getLayoutId();
	protected abstract void initView();
	protected abstract void ininData();
	protected abstract void initListener();
}
