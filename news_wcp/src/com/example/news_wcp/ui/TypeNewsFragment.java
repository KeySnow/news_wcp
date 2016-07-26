package com.example.news_wcp.ui;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

import com.example.news_wcp.adapter.MyNewsAdapter;
import com.example.news_wcp.base.BaseNewsFragment;
import com.example.news_wcp.entities.News;
import com.example.news_wcp.utils.GsonUtil;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

public class TypeNewsFragment extends BaseNewsFragment {

	private String url;
	
	public TypeNewsFragment(String url) {
		this.url = url;
	}
	
	@Override
	public void initData() {
		RequestQueue queue = NoHttp.newRequestQueue();
		Request<String> request = NoHttp.createStringRequest(url, RequestMethod.GET);
		OnResponseListener<String> responseListener = new OnResponseListener<String>() {
			
			@Override
			public void onSucceed(int what, Response<String> response) {
				String result = response.get();
				peasData(result);
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
	
	protected void peasData(String result) {

		News news = GsonUtil.jsonToBean(result, News.class);
		datas = news.result.data;
		
		adapter = new MyNewsAdapter(getActivity(), datas);
		mXListView.setAdapter(adapter);
		
	}

	@Override
	public void initListener() {
		mXListView.setOnItemClickListener(new OnItemClickListener() {

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
	

}





