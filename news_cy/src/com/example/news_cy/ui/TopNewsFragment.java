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
import com.example.news_cy.entities.News;
import com.example.news_cy.utils.GsonUtil;
import com.example.news_cy.utils.SharedPreferenceUtil;
import com.example.news_cy.utils.ToastUtil;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;
/**
 * 新闻资讯
 *
 * @author wu
 *
 * 2016-7-1
 */
public class TopNewsFragment extends MyBaseNewsFragment{

	private String url;

	public TopNewsFragment(String url) {
		this.url = url;
	}

	protected void parseData(String result) {

		News news = GsonUtil.JsonToBean(result, News.class);
		datas = news.result.data;

		adapter = new MyNewsAdapter(getActivity(), datas);
		mLv_content.setAdapter(adapter);


	}

	@Override
	protected void initListener() {
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

		mLv_content.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					final int position, long id) {

				Builder builder = new Builder(getActivity());
				builder.setTitle("收藏新闻");
				builder.setMessage(datas.get(position-1).title);
				builder.setPositiveButton("是", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						if(!SharedPreferenceUtil.getString(getActivity(), "readed_news").contains(datas.get(position-1).url)){

							NewsDbManager manager = new NewsDbManager(getActivity());
							manager.insertNews(datas.get(position-1));
							ToastUtil.showToast(getActivity(), "已收藏:" + datas.get(position-1).title, 0);
							
							String oldString = SharedPreferenceUtil.getString(getActivity(), "readed_news");
							String urlString = datas.get(position-1).url + "," +oldString;
							SharedPreferenceUtil.putString(getActivity(), "readed_news", urlString);
							
						}else{
							ToastUtil.showToast(getActivity(), "该新闻已存在收藏列表，不需要重复收藏", 0);
						}
					}
				});

				builder.setNegativeButton("否", new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						ToastUtil.showToast(getActivity(), "取消收藏", 0);
					}
				});
				builder.show();
				return true;
			}
		});
	}

	@Override
	protected void initData() {
		RequestQueue queue = NoHttp.newRequestQueue();
		Request<String> request = NoHttp.createStringRequest(url , RequestMethod.GET);
		OnResponseListener<String> responseListener = new OnResponseListener<String>() {

			@Override
			public void onSucceed(int what, Response<String> response) {
				String result = response.get();
				parseData(result);
			}

			@Override
			public void onStart(int what) {

			}

			@Override
			public void onFinish(int what) {

			}

			@Override
			public void onFailed(int what, String url, Object tag, Exception exception,
					int responseCode, long networkMillis) {

			}
		};
		queue.add(1, request, responseListener);

	}


}
