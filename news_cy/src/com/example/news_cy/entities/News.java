package com.example.news_cy.entities;

import java.util.ArrayList;

public class News {

	public String reason;
	public Datas result;
	public String error_code;
	
	public static class Datas{
		
		public String stat;
		public ArrayList<Data> data;
		
		public static class Data{
			
			public String title;
			public String date;
			public String category;;
			public String author_name;;
			public String thumbnail_pic_s;;
			public String url;;
		}
	}
}
