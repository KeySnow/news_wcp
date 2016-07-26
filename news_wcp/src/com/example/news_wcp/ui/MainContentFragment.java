package com.example.news_wcp.ui;

import java.util.ArrayList;

import com.example.news_wcp.R;
import com.example.news_wcp.view.MyActionbar;
import com.viewpagerindicator.TabPageIndicator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MainContentFragment extends Fragment {

	private ViewPager mViewPager;
	private TabPageIndicator mIndicator;
	private ArrayList<Fragment> pages = new ArrayList<Fragment>();
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_main_content, container, false);
		mIndicator = (TabPageIndicator) view.findViewById(R.id.indicator_fragment_main_content);
		mViewPager = (ViewPager) view.findViewById(R.id.viewpager_fragment_main_content);
		
		String topUrl = "http://v.juhe.cn/toutiao/index?type=top&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TypeNewsFragment(topUrl));
		String shehuiUrl = "http://v.juhe.cn/toutiao/index?type=shehui&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TypeNewsFragment(shehuiUrl));
		String guoneiUrl = "http://v.juhe.cn/toutiao/index?type=guonei&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TypeNewsFragment(guoneiUrl));
		String guojiUrl = "http://v.juhe.cn/toutiao/index?type=guoji&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TypeNewsFragment(guojiUrl));
		String yuleUrl = "http://v.juhe.cn/toutiao/index?type=yule&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TypeNewsFragment(yuleUrl));
		String tiyuUrl = "http://v.juhe.cn/toutiao/index?type=tiyu&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TypeNewsFragment(tiyuUrl));
		String junshiUrl = "http://v.juhe.cn/toutiao/index?type=junshi&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TypeNewsFragment(junshiUrl));
		String kejiUrl = "http://v.juhe.cn/toutiao/index?type=keji&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TypeNewsFragment(kejiUrl));
		String caijingUrl = "http://v.juhe.cn/toutiao/index?type=caijing&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TypeNewsFragment(caijingUrl));
		String shishangUrl = "http://v.juhe.cn/toutiao/index?type=shishang&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new TypeNewsFragment(shishangUrl));
		
		MyPagerAdapter adapter = new MyPagerAdapter(getFragmentManager());
		mViewPager.setAdapter(adapter);
		mIndicator.setViewPager(mViewPager);
		
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
