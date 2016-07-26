package com.example.news_wcp.ui;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.news_wcp.R;
import com.example.news_wcp.base.BaseActivity;
import com.example.news_wcp.service.MusicService;

public class LoadingActivity extends BaseActivity {

	private ImageView mIv_load;
	private Intent service;
	
	@Override
	public int getLayoutId() {
		return R.layout.activity_loading;
	}

	@Override
	public void initView() {

		mIv_load = (ImageView) findViewById(R.id.iv_activity_loading);
		
		service = new Intent(this, MusicService.class);
		startService(service );
		
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha_loading_logo);
		mIv_load.setAnimation(animation);
		
		animation.setAnimationListener(new AnimationListener() {
			
			@Override
			public void onAnimationStart(Animation animation) {
				
			}
			
			@Override
			public void onAnimationRepeat(Animation animation) {
				
			}
			
			@Override
			public void onAnimationEnd(Animation animation) {
				openActivity(MainActivity.class);
				finish();
			}
		});
	}

	@Override
	public void initData() {

	}

	@Override
	public void initListener() {

	}
	
	@Override
	protected void onDestroy() {
		stopService(service);
		super.onDestroy();
	}

}
