package com.example.news_wcp.ui;

import com.example.news_wcp.adapter.MyNewsAdapter;
import com.example.news_wcp.base.BaseNewsFragment;

import android.support.v4.app.Fragment;

public class MainReadFragment extends BaseNewsFragment{

	@Override
	public void initData() {
		adapter = new MyNewsAdapter(getActivity(), datas);
		mXListView.setAdapter(adapter);
	}

	@Override
	public void initListener() {
		
	}

}
