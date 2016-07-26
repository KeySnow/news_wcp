package com.example.news_cy.ui;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.example.news_cy.R;
import com.example.news_cy.base.BaseActivity;
import com.example.news_cy.service.MusicService;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

/**
 * º”‘ÿ“≥
 *
 * @author wu
 *
 * 2016-6-28
 */
public class LoadingActivity extends BaseActivity {

	@ViewInject(R.id.iv_activity_loading_logo)
	private ImageView mIv_logo;
	private Intent service;
	
	@Override
	protected int getLayoutId() {
		return R.layout.activity_loading;
	}

	@Override
	protected void initView() {

		ViewUtils.inject(this);
		//º”‘ÿ∂Øª≠
		Animation animation = AnimationUtils.loadAnimation(this, R.anim.animation_logo);
		mIv_logo.setAnimation(animation);
		//∂Øª≠º‡Ã˝
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
		
		service = new Intent(this, MusicService.class);
		startService(service );
	}

	@Override
	protected void ininData() {

	}

	@Override
	protected void initListener() {

	}
	
	@Override
	protected void onDestroy() {
		stopService(service);
		super.onDestroy();
	}

}
