package com.example.news_wcp.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.news_wcp.R;
import com.example.news_wcp.entities.News.Datas.Data;
import com.lidroid.xutils.BitmapUtils;

public class MyNewsAdapter extends BaseAdapter{

	private Context context;
	private ArrayList<Data> datas;
	
	public MyNewsAdapter(Context context, ArrayList<Data> datas) {
		this.context = context;
		this.datas = datas;
	}

	@Override
	public int getCount() {
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View view = null;
		ViewHolder holder = null;
		if(convertView == null){
			view = View.inflate(context, R.layout.fragment_news_listview_item, null);
			holder = new ViewHolder();
			holder.iv_icon = (ImageView) view.findViewById(R.id.iv_fragment_news_listview_item_icon);
			holder.tv_title = (TextView) view.findViewById(R.id.tv_fragment_news_listview_item_title);
			holder.tv_date = (TextView) view.findViewById(R.id.tv_fragment_news_listview_item_date);
			view.setTag(holder);
		}else{
			view = convertView;
			holder = (ViewHolder) view.getTag();
		}

		Data data = datas.get(position);
		BitmapUtils bitmap = new BitmapUtils(context);
		bitmap.display(holder.iv_icon, data.thumbnail_pic_s);
		holder.tv_title.setText(data.title);
		holder.tv_date.setText(data.date);

		return view;
	}


	class ViewHolder{
		private ImageView iv_icon;
		private TextView tv_title;
		private TextView tv_date;
	}


}
