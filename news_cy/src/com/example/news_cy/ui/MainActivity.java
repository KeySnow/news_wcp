package com.example.news_cy.ui;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.news_cy.R;
import com.example.news_cy.base.BaseActivity;
import com.example.news_cy.view.MyActionbar;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.NoHttp;

/**
 * 主页
 *
 * @author wu
 *
 * 2016-6-28
 */
public class MainActivity extends BaseActivity{

	@ViewInject(R.id.ma_activity_main_actionbar)
	public MyActionbar mMa_actionbar;
	private SlidingMenu slidingMenu;
	private FragmentManager manager;
	
	@Override
	protected int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	protected void initView() {
		NoHttp.initialize(getApplication());
		ViewUtils.inject(this);
		
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		slidingMenu.setBehindOffset(80);
		slidingMenu.setMenu(R.layout.slidingmenu_framelayout);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
	
		manager = getSupportFragmentManager();
		manager.beginTransaction().replace(R.id.slidingmenu_left_framelayout, new SlidingMenuFragmnet(this)).commit();
		manager.beginTransaction().replace(R.id.main_content_framelayout, new MainContentFragment(this)).commit();
	}
	
	public SlidingMenu getSlidingMenu(){
		return this.slidingMenu;
	}

	@Override
	protected void ininData() {
		mMa_actionbar.setTitle("新闻资讯");
	}

	@Override
	protected void initListener() {
		mMa_actionbar.setLeftListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				slidingMenu.toggle();
			}
		});
	}

}
