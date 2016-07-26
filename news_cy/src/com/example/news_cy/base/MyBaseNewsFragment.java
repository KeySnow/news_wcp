package com.example.news_cy.base;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.news_cy.R;
import com.example.news_cy.adapter.MyNewsAdapter;
import com.example.news_cy.entities.News.Datas.Data;
import com.markmao.pulltorefresh.widget.XListView;
import com.markmao.pulltorefresh.widget.XListView.IXListViewListener;

public abstract class MyBaseNewsFragment extends Fragment{

	protected XListView mLv_content;
	protected ArrayList<Data> datas;
	protected MyNewsAdapter adapter;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_news_listview, null);
		//初始化ListView
		initListView(view);
		//请求网络数据
		initData();
		initListener();
		
		return view;
	}

	private void initListView(View view) {

		mLv_content = (XListView) view.findViewById(R.id.lv_fragment_news_content);
		mLv_content.setPullLoadEnable(true);
		mLv_content.setPullRefreshEnable(true);
		mLv_content.setXListViewListener(new IXListViewListener() {
			
			@Override
			public void onRefresh() {
				mLv_content.stopRefresh();
			}
			
			@Override
			public void onLoadMore() {
				
				mLv_content.stopLoadMore();
			}
		});
	}

	protected abstract void initListener();
	
	protected abstract void initData();

}
