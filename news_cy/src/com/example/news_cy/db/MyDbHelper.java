package com.example.news_cy.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Êý¾Ý¿â
 *
 * @author wu
 *
 * 2016-6-30
 */
public class MyDbHelper extends SQLiteOpenHelper{

	public MyDbHelper(Context context){
		this(context, "info.db", null, 1);
	}
	
	public MyDbHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String newsSql = "create table news(_id integer primary key autoincrement, thumbnail_pic_s text, title text, date text, url text)";
		db.execSQL(newsSql );
		System.out.println("create news table success!");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		
	}

}
