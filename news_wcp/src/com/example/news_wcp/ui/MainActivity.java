package com.example.news_wcp.ui;

import android.support.v4.app.FragmentManager;
import android.view.View;
import android.view.View.OnClickListener;

import com.example.news_wcp.R;
import com.example.news_wcp.base.BaseActivity;
import com.example.news_wcp.view.MyActionbar;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.yolanda.nohttp.NoHttp;

public class MainActivity extends BaseActivity {

	@ViewInject(R.id.ma_actionbar_main)
	public MyActionbar mMa_actionbar;
	
	private FragmentManager manager;
	private SlidingMenu slidingMenu;
	
	@Override
	public int getLayoutId() {
		return R.layout.activity_main;
	}

	@Override
	public void initView() {
		
		ViewUtils.inject(this);
		NoHttp.initialize(getApplication());
		
		slidingMenu = new SlidingMenu(this);
		slidingMenu.setMode(SlidingMenu.LEFT);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		slidingMenu.setBehindOffset(150);
		slidingMenu.setMenu(R.layout.slidingmenu_framelayout);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
		
		manager = getSupportFragmentManager();
		
		manager.beginTransaction().replace(R.id.framelayout_slidingmenu_content, new SlidingMenuFragment(this)).commit();
		manager.beginTransaction().replace(R.id.framelayout_main_content, new MainContentFragment()).commit();
	}

	@Override
	public void initData() {
		
	}

	@Override
	public void initListener() {
		mMa_actionbar.setLeftListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				slidingMenu.toggle();
			}
		});
	}
	
	public SlidingMenu getSlidingMenu(){
		
		return this.slidingMenu;
	}

}
