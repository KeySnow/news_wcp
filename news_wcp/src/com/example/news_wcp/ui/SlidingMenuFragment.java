package com.example.news_wcp.ui;

import com.example.news_wcp.R;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SlidingMenuFragment extends Fragment {

	private MainActivity mainActivity;

	private RelativeLayout rl1;
	private RelativeLayout rl2;
	private RelativeLayout rl3;
	private RelativeLayout rl4;

	public SlidingMenuFragment(MainActivity activity){
		this.mainActivity = activity;
	}

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = initView(inflater, container);
		initListener();
		return view;
	}

	private void initListener() {

		MyOnClickListener listener = new MyOnClickListener();
		
		rl1.setOnClickListener(listener);
		rl2.setOnClickListener(listener);
		rl3.setOnClickListener(listener);
		rl4.setOnClickListener(listener);
	}

	private View initView(LayoutInflater inflater, ViewGroup container) {
		View view = inflater.inflate(R.layout.slidingmenu_left_content, container, false);
		rl1 = (RelativeLayout) view.findViewById(R.id.slidingmenu_item1);
		rl2 = (RelativeLayout) view.findViewById(R.id.slidingmenu_item2);
		rl3 = (RelativeLayout) view.findViewById(R.id.slidingmenu_item3);
		rl4 = (RelativeLayout) view.findViewById(R.id.slidingmenu_item4);
		return view;
	}

	class MyOnClickListener implements OnClickListener{

		@Override
		public void onClick(View v) {

			rl1.setBackgroundColor(Color.TRANSPARENT);
			rl2.setBackgroundColor(Color.TRANSPARENT);
			rl3.setBackgroundColor(Color.TRANSPARENT);
			rl4.setBackgroundColor(Color.TRANSPARENT);

			switch (v.getId()) {
			case R.id.slidingmenu_item1:
				rl1.setBackgroundColor(0x558c5555);
				mainActivity.getSlidingMenu().toggle();
				mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_main_content, new MainContentFragment()).commit();
				mainActivity.mMa_actionbar.setTitle("新闻资讯");
				break;
			case R.id.slidingmenu_item2:
				rl1.setBackgroundColor(0x558c5555);
				mainActivity.getSlidingMenu().toggle();
				mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_main_content, new MainReadFragment()).commit();
				mainActivity.mMa_actionbar.setTitle("新闻收藏");
				break;
			case R.id.slidingmenu_item3:
				rl1.setBackgroundColor(0x558c5555);
				mainActivity.getSlidingMenu().toggle();
				mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_main_content, new MainPicsFragment()).commit();
				mainActivity.mMa_actionbar.setTitle("图片新闻");
				break;
			case R.id.slidingmenu_item4:
				rl1.setBackgroundColor(0x558c5555);
				mainActivity.getSlidingMenu().toggle();
				mainActivity.getSupportFragmentManager().beginTransaction().replace(R.id.framelayout_main_content, new MainChatFragment()).commit();
				mainActivity.mMa_actionbar.setTitle("聊天小助手");
				break;

			default:
				break;
			}
		}
	}

}
