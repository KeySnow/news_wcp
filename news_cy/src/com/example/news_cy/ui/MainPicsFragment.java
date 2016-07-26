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
import com.viewpagerindicator.TabPageIndicator;
/**
 * 图片新闻
 *
 * @author wu
 *
 * 2016-7-4
 */
public class MainPicsFragment extends Fragment {

	private TabPageIndicator mIndicator;
	private ViewPager mViewPager;
	private ArrayList<Fragment> pages = new ArrayList<Fragment>();
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_main_content, container, false);
		mIndicator = (TabPageIndicator) view.findViewById(R.id.fragment_main_indicator);
		mViewPager = (ViewPager) view.findViewById(R.id.fragment_main_viewpager);
		
		String topUrl = "http://v.juhe.cn/toutiao/index?type=top&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new PicsNewsFragment(topUrl));
		String shehuiUrl = "http://v.juhe.cn/toutiao/index?type=shehui&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new PicsNewsFragment(shehuiUrl));
		String guoneiUrl = "http://v.juhe.cn/toutiao/index?type=guonei&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new PicsNewsFragment(guoneiUrl));
		String guojiUrl = "http://v.juhe.cn/toutiao/index?type=guoji&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new PicsNewsFragment(guojiUrl));
		String yuleUrl = "http://v.juhe.cn/toutiao/index?type=yule&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new PicsNewsFragment(yuleUrl));
		String tiyuUrl = "http://v.juhe.cn/toutiao/index?type=tiyu&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new PicsNewsFragment(tiyuUrl));
		String junshiUrl = "http://v.juhe.cn/toutiao/index?type=junshi&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new PicsNewsFragment(junshiUrl));
		String kejiUrl = "http://v.juhe.cn/toutiao/index?type=keji&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new PicsNewsFragment(kejiUrl));
		String caijingUrl = "http://v.juhe.cn/toutiao/index?type=caijing&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new PicsNewsFragment(caijingUrl));
		String shishangUrl = "http://v.juhe.cn/toutiao/index?type=shishang&key=728179f59512cd82f5b93ebd08fc2584";
		pages.add(new PicsNewsFragment(shishangUrl));
		
		MyPagerAdaptet adapter = new MyPagerAdaptet(getFragmentManager());
		mViewPager.setAdapter(adapter);
		mIndicator.setViewPager(mViewPager);
		
		return view;
	}
	
	class MyPagerAdaptet extends FragmentStatePagerAdapter{

		private String[] titles = {"头条","社会","国内","国际","娱乐","体育","军事","科技","财经","时尚"};
		
		public MyPagerAdaptet(FragmentManager fm) {
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
