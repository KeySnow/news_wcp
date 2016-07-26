package com.example.news_cy.ui;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.TextView;

import com.example.news_cy.R;
import com.example.news_cy.base.BaseActivity;
import com.example.news_cy.utils.SharedPreferenceUtil;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
/**
 * Òýµ¼Ò³
 *
 * @author wu
 *
 * 2016-6-28
 */
public class LeadingActivity extends BaseActivity {

	private List<View> viewList = new ArrayList<View>();
	private List<ImageView> mIndicatorList = new ArrayList<ImageView>();
	private int indicator_oldIndex = 0;

	@ViewInject(R.id.vp_activity_leading_viewpager)
	private ViewPager mVp_ad;
	@ViewInject(R.id.iv_activity_leading_indicator1)
	private ImageView mIndicator1;
	@ViewInject(R.id.iv_activity_leading_indicator2)
	private ImageView mIndicator2;
	@ViewInject(R.id.iv_activity_leading_indicator3)
	private ImageView mIndicator3;
	@ViewInject(R.id.iv_activity_leading_indicator4)
	private ImageView mIndicator4;
	@ViewInject(R.id.tv_activity_leading_enter)
	private TextView mTv_enter;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_leading;
	}

	@Override
	protected void initView() {
		ViewUtils.inject(this);
	}

	@Override
	protected void ininData() {

		if(SharedPreferenceUtil.getBoolean(this, "IS_FIRST_RUN", true)){

			ImageView iv1 = new ImageView(this);
			ImageView iv2 = new ImageView(this);
			ImageView iv3 = new ImageView(this);
			ImageView iv4 = new ImageView(this);

			iv1.setImageResource(R.drawable.welcome);
			iv1.setScaleType(ScaleType.FIT_XY);
			iv2.setImageResource(R.drawable.wy);
			iv2.setScaleType(ScaleType.FIT_XY);
			iv3.setImageResource(R.drawable.small);
			iv3.setScaleType(ScaleType.FIT_XY);
			iv4.setImageResource(R.drawable.bd);
			iv4.setScaleType(ScaleType.FIT_XY);

			viewList.add(iv1);
			viewList.add(iv2);
			viewList.add(iv3);
			viewList.add(iv4);

			mIndicatorList.add(mIndicator1);
			mIndicatorList.add(mIndicator2);
			mIndicatorList.add(mIndicator3);
			mIndicatorList.add(mIndicator4);

			mIndicatorList.get(indicator_oldIndex).setImageResource(R.drawable.shape_leading_ad_indicator_selected);

			mVp_ad.setAdapter(new MyAdapter());
	
		}else{
			openActivity(LoadingActivity.class);
			finish();
		}
	}

	@Override
	protected void initListener() {
		//½øÈë¼àÌý
		mTv_enter.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				openActivity(LoadingActivity.class);
				finish();
				SharedPreferenceUtil.putBoolean(LeadingActivity.this, "IS_FIRST_RUN", false);
			}
		});

		//Í¼Æ¬ÂÖ²¥¼àÌý
		mVp_ad.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				mIndicatorList.get(arg0).setImageResource(R.drawable.shape_leading_ad_indicator_selected);
				mIndicatorList.get(indicator_oldIndex).setImageResource(R.drawable.shape_leading_ad_indicator_unselected);
				indicator_oldIndex = arg0;

				if(arg0 >= viewList.size() - 1){
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

	class MyAdapter extends PagerAdapter{

		@Override
		public int getCount() {
			return viewList.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView(viewList.get(position));
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {

			container.addView(viewList.get(position));
			return viewList.get(position);
		}

	}

}
