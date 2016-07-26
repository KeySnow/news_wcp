package com.example.news_wcp.base;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.news_wcp.R;
import com.example.news_wcp.adapter.MyNewsAdapter;
import com.example.news_wcp.entities.News.Datas.Data;
import com.markmao.pulltorefresh.widget.XListView;
import com.markmao.pulltorefresh.widget.XListView.IXListViewListener;

public abstract class BaseNewsFragment extends Fragment {

	protected XListView mXListView;
	protected ArrayList<Data> datas;
	protected MyNewsAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_news_listview, container, false);
		initListView(view);

		initData();
		initListener();

		return view;
	}


	private void initListView(View view) {
		mXListView = (XListView) view.findViewById(R.id.lv_fragment_news_listview);
		mXListView.setPullLoadEnable(true);
		mXListView.setPullRefreshEnable(true);
		mXListView.setXListViewListener(new IXListViewListener() {

			@Override
			public void onRefresh() {
				mXListView.stopRefresh();
			}

			@Override
			public void onLoadMore() {

				mXListView.stopLoadMore();
			}
		});
	}

	public abstract void initData();
	public abstract void initListener();

}
