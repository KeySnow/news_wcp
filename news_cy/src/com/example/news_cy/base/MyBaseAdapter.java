package com.example.news_cy.base;

import java.util.ArrayList;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class MyBaseAdapter<T> extends BaseAdapter {

	private ArrayList<T> datas = new ArrayList<T>();
	
	/**
	 * 设置数据
	 * @param datas
	 */
	public void setData(ArrayList<T> datas){
		this.datas = datas;
	}
	
	public void addData(T data, boolean isClear){
		if(isClear){
			datas.clear();
		}
		datas.add(data);
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
		return getItemView(position, convertView, parent);
	}
	
	/**
	 * 初始化项目视图
	 * @param position
	 * @param convertView
	 * @param parent
	 * @return
	 */
	public abstract View getItemView(int position, View convertView, ViewGroup parent);

}
