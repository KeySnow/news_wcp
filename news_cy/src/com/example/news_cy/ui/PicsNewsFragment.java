package com.example.news_cy.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news_cy.R;
import com.example.news_cy.entities.News;
import com.example.news_cy.entities.News.Datas.Data;
import com.example.news_cy.utils.GsonUtil;
import com.lidroid.xutils.BitmapUtils;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.OnResponseListener;
import com.yolanda.nohttp.rest.Request;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;

public class PicsNewsFragment extends Fragment {

	private String url;
	private RecyclerView mRecyclerView;
	private ArrayList<Data> datas;

	public PicsNewsFragment(String url) {
		this.url = url;
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		View view = inflater.inflate(R.layout.fragment_picsnews_recyclerview, container, false);
		mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_fragment_picsnews_recyclerview);
		
		initData();
		
		
		StaggeredGridLayoutManager layout = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
		mRecyclerView.setLayoutManager(layout);
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		
		return view;
	}
	
	private void initData() {

		RequestQueue queue = NoHttp.newRequestQueue();
		Request<String> request = NoHttp.createStringRequest(url, RequestMethod.GET);
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
		} ;
		queue.add(1, request, responseListener);
	}

	protected void parseData(String result) {

		GsonUtil gson = new GsonUtil();
		News news = gson.JsonToBean(result, News.class);
		datas = news.result.data;
		
		MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter();
		mRecyclerView.setAdapter(adapter);
	}

	class MyRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder>{

		@Override
		public int getItemCount() {
			return datas.size();
		}

		@Override
		public void onBindViewHolder(ViewHolder arg0, final int arg1) {
			BitmapUtils bitmap = new BitmapUtils(getActivity());
			bitmap.display(arg0.iv_icon, datas.get(arg1).thumbnail_pic_s);
			arg0.tv_title.setText(datas.get(arg1).title);
			
			arg0.iv_icon.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(getActivity(), WebViewActivity.class);
					intent.putExtra("url", datas.get(arg1).url);
					intent.putExtra("title", datas.get(arg1).title);
					startActivity(intent );
				}
			});
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
			
			View view = View.inflate(getActivity(), R.layout.fragment_picsnews_recyclerview_item, null);
			ViewHolder holder = new ViewHolder(view);
			
			return holder;
		}
		
	}
	
	class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder{

		private ImageView iv_icon;
		private TextView tv_title;
		
		public ViewHolder(View arg0) {
			super(arg0);
			
			iv_icon = (ImageView) arg0.findViewById(R.id.iv_fragment_picsnews_recyclerview_item_icon);
			tv_title = (TextView) arg0.findViewById(R.id.tv_fragment_picsnews_recyclerview_item_title);
		}
		
	}
	
	
}
