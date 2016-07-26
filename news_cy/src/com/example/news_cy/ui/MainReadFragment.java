package com.example.news_cy.ui;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;

import com.example.news_cy.adapter.MyNewsAdapter;
import com.example.news_cy.base.MyBaseNewsFragment;
import com.example.news_cy.db.NewsDbManager;
import com.example.news_cy.utils.SharedPreferenceUtil;
import com.example.news_cy.utils.ToastUtil;

public class MainReadFragment extends MyBaseNewsFragment{

	private NewsDbManager manager;

	@Override
	protected void initListener() {
		mLv_content.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {
				Builder builder = new Builder(getActivity());
				builder.setTitle("删除新闻");
				builder.setMessage(datas.get(position-1).title);
				builder.setPositiveButton("是", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						
						manager.deleteNews(datas.get(position-1).title);
						
						String oldUrl = SharedPreferenceUtil.getString(getActivity(), "readed_news");
						String targetUrl = datas.get(position-1).url;
						//1.定位1
						int targetUrlLen = targetUrl.length();
						int firstIndex = oldUrl.indexOf(targetUrl );
						String subUrl1 = oldUrl.substring(0, firstIndex);
						//2.定位2
						int secondIndex = firstIndex + targetUrlLen + 1;
						String subUrl2 = oldUrl.substring(secondIndex );
						//3.拼接url
						String newUrl = subUrl1 + subUrl2;
						//4.替换到xml
						SharedPreferenceUtil.putString(getActivity(), "readed_news", newUrl);
						
						ToastUtil.showToast(getActivity(), "已删除:" + datas.get(position-1).title, 0);
						datas.remove(datas.get(position-1));
						adapter.notifyDataSetChanged();
					}
				});
				builder.setNegativeButton("否", new OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						ToastUtil.showToast(getActivity(), "取消删除", 0);
					}
				});
				builder.show();
				return true;
			}
		});
		
		mLv_content.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent(getActivity(), WebViewActivity.class);
				intent.putExtra("url", datas.get(position-1).url);
				intent.putExtra("title", datas.get(position-1).title);
				startActivity(intent );
			}
		});
	}

	@Override
	protected void initData() {
		manager = new NewsDbManager(getActivity());
		datas = manager.getNewsList();
		adapter = new MyNewsAdapter(getActivity(), datas);
		mLv_content.setAdapter(adapter);
	}

}
