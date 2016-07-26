package com.example.news_cy.db;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.news_cy.entities.News.Datas.Data;
/**
 * 数据库管理
 *
 * @author wu
 *
 * 2016-6-30
 */
public class NewsDbManager {

	private MyDbHelper helper;
	private Context context;
	
	public NewsDbManager(Context context) {
		this.context = context;
		helper = new MyDbHelper(context);
	}

	public void insertNews(Data data){
		SQLiteDatabase db = helper.getWritableDatabase();
		
		ContentValues values = new ContentValues();
		values.put("thumbnail_pic_s", data.thumbnail_pic_s);
		values.put("title", data.title);
		values.put("date", data.date);
		values.put("url", data.url);
		
		db.insert("news", null, values);
		db.close();
		System.out.println("insert news success!");
	}
	
	public ArrayList<Data> getNewsList(){
		
		ArrayList<Data> newsList = new ArrayList<Data>();
		SQLiteDatabase db = helper.getReadableDatabase();
		String selectSql = "select * from news";
		Cursor cursor = db.rawQuery(selectSql, null);
		
		while(cursor.moveToNext()){
			
			Data data = new Data();
			String icon = cursor.getString(cursor.getColumnIndex("thumbnail_pic_s"));
			String title = cursor.getString(cursor.getColumnIndex("title"));
			String date = cursor.getString(cursor.getColumnIndex("date"));
			String url = cursor.getString(cursor.getColumnIndex("url"));
			
			data.thumbnail_pic_s = icon;
			data.title = title;
			data.date = date;
			data.url = url;
			
			newsList.add(data);
		}
		db.close();
		System.out.println("select sql success!");
		return newsList;
		
	}
	
	public void deleteNews(String title){
		SQLiteDatabase db = helper.getWritableDatabase();
		db.delete("news", "title = ?", new String[]{title});
		
		db.close();
		System.out.println("delete news success!");
	}
}





