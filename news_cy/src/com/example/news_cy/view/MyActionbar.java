package com.example.news_cy.view;

import com.example.news_cy.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * 自定义活动条
 *
 * @author wu
 *
 * 2016-6-28
 */
public class MyActionbar extends LinearLayout {

	private ImageView mIv_left;
	private ImageView mIv_right;
	private TextView mTv_title;
	
	public MyActionbar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initView(context);
	}


	public MyActionbar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public MyActionbar(Context context) {
		this(context, null);
	}

	private void initView(Context context) {

		View view = View.inflate(context, R.layout.actionbar_layout, this);
		mIv_left = (ImageView) view.findViewById(R.id.actionbar_left);
		mIv_right = (ImageView) view.findViewById(R.id.actionbar_right);
		mTv_title = (TextView) view.findViewById(R.id.actionbar_title);
	}
	
	public void setTitle(String text){
		mTv_title.setText(text);
	}
	
	public void setLeftImage(int resId){
		mIv_left.setImageResource(resId);
	}
	
	public void setRightImage(int resId){
		mIv_right.setImageResource(resId);
	}
	
	public void setLeftListener(OnClickListener listenet){
		mIv_left.setOnClickListener(listenet);
	}
	
	public void setRightListener(OnClickListener listener){
		mIv_right.setOnClickListener(listener);
	}
	
}
