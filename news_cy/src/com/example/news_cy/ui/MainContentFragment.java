package com.example.news_cy.ui;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.news_cy.R;
import com.example.news_cy.view.MyActionbar;
import com.viewpagerindicator.TabPageIndicator;

public class MainContentFragment extends Fragment {

	private MainActivity mainActivity;
	private TabPageIndicator mIndicator;
	private ViewPager viewpager;
	private ArrayList<Fragment> pages = new ArrayList<Fragment>();
	
	public MainContentFragment() {}
	public MainContentFragment(MainActivity activity) {
		this.mainActivity = activity;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		
		View view = inflater.inflate(R.layout.fragment_main_content, null);
		
		mIndicator = (TabPageIndicator) view.findViewById(R.id.fragment_main_indicator);
		viewpager = (ViewPager) view.findViewById(R.id.fragment_main_viewpager); 
		
		String topUrl = "http://v.juhe.cn/toutiao/index?type=top&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TopNewsFragment(topUrl));
		String shehuiUrl = "http://v.juhe.cn/toutiao/index?type=shehui&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TopNewsFragment(shehuiUrl));
		String guoneiUrl = "http://v.juhe.cn/toutiao/index?type=guonei&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TopNewsFragment(guoneiUrl));
		String guojiUrl = "http://v.juhe.cn/toutiao/index?type=guoji&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TopNewsFragment(guojiUrl));
		String yuleUrl = "http://v.juhe.cn/toutiao/index?type=yule&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TopNewsFragment(yuleUrl));
		String tiyuUrl = "http://v.juhe.cn/toutiao/index?type=tiyu&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TopNewsFragment(tiyuUrl));
		String junshiUrl = "http://v.juhe.cn/toutiao/index?type=junshi&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TopNewsFragment(junshiUrl));
		String kejiUrl = "http://v.juhe.cn/toutiao/index?type=keji&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TopNewsFragment(kejiUrl));
		String caijingUrl = "http://v.juhe.cn/toutiao/index?type=caijing&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TopNewsFragment(caijingUrl));
		String shishangUrl = "http://v.juhe.cn/toutiao/index?type=shishang&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TopNewsFragment(shishangUrl));
		
		MyPagerAdapter adapter = new MyPagerAdapter(getFragmentManager());
		viewpager.setAdapter(adapter);
		mIndicator.setViewPager(viewpager);
		
		return view;
	}
	
	class MyPagerAdapter extends FragmentStatePagerAdapter{

		private String[] titles = {"头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};
		
		public MyPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		@Override
		public Fragment getItem(int arg0) {
			return pages.get(arg0);
		}

		@Override
		public int getCount() {
			return pages.size();
		}
		
		@Override
		public CharSequence getPageTitle(int position) {
			return titles[position];
		}
	}
}
