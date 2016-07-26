package com.example.news_wcp.ui;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.example.news_wcp.R;
import com.example.news_wcp.base.BaseActivity;
import com.example.news_wcp.utils.SharedPreferenceUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
/**
 * Òýµ¼Ò³
 * @author wu
 *
 */
public class LeadingActivity extends BaseActivity {

	@ViewInject(R.id.vp_activity_leading_viewpager)
	private ViewPager mViewPager;
	@ViewInject(R.id.tv_activity_leading_enter)
	private TextView mTv_enter;
	@ViewInject(R.id.indicator1)
	private View mIndicator1;
	@ViewInject(R.id.indicator2)
	private View mIndicator2;
	@ViewInject(R.id.indicator3)
	private View mIndicator3;
	@ViewInject(R.id.indicator4)
	private View mIndicator4;

	private ArrayList<ImageView> imgArr = new ArrayList<ImageView>();
	private ArrayList<View> indicatorList = new ArrayList<View>();
	private int oldIndicator = 0;

	@Override
	public int getLayoutId() {
		return R.layout.activity_leading;
	}

	@Override
	public void initView() {

		ViewUtils.inject(this);

	}

	@Override
	public void initData() {

		if(SharedPreferenceUtil.getBoolean(LeadingActivity.this, "IS_FIRST_RUN", true)){

			ImageView iv1 = new ImageView(this);
			ImageView iv2 = new ImageView(this);
			ImageView iv3 = new ImageView(this);
			ImageView iv4 = new ImageView(this);

			iv1.setImageResource(R.drawable.welcome);
			iv1.setScaleType(ScaleType.FIT_XY);
			iv2.setImageResource(R.drawable.bd);
			iv2.setScaleType(ScaleType.FIT_XY);
			iv3.setImageResource(R.drawable.wy);
			iv3.setScaleType(ScaleType.FIT_XY);
			iv4.setImageResource(R.drawable.small);
			iv4.setScaleType(ScaleType.FIT_XY);

			imgArr.add(iv1);
			imgArr.add(iv2);
			imgArr.add(iv3);
			imgArr.add(iv4);

			indicatorList.add(mIndicator1);
			indicatorList.add(mIndicator2);
			indicatorList.add(mIndicator3);
			indicatorList.add(mIndicator4);

			indicatorList.get(oldIndicator).setBackgroundResource(R.drawable.shape_activity_leading_indicator_background_selected);

			MyPagerAdapter adapter = new MyPagerAdapter();
			mViewPager.setAdapter(adapter);
			
		}else{
			openActivity(LoadingActivity.class);
			finish();
		}
	}

	@Override
	public void initListener() {

		mTv_enter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				openActivity(LoadingActivity.class);
				finish();
				SharedPreferenceUtil.putBoolean(LeadingActivity.this, "IS_FIRST_RUN", false);
			}
		});

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				indicatorList.get(arg0).setBackgroundResource(R.drawable.shape_activity_leading_indicator_background_selected);
				indicatorList.get(oldIndicator).setBackgroundResource(R.drawable.shape_activity_leading_indicator_background_unselected);
				oldIndicator = arg0;

				if(arg0 >= imgArr.size() - 1){
					mTv_enter.setVisibility(View.VISIBLE);
				}
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	class MyPagerAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return imgArr.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {

			container.removeView(imgArr.get(position));

		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			container.addView(imgArr.get(position));

			return imgArr.get(position);
		}



	}

}
