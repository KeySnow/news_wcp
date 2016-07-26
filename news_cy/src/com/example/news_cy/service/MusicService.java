package com.example.news_cy.service;

import com.example.news_cy.R;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

//“Ù¿÷∑˛ŒÒ
public class MusicService extends Service {

	private MediaPlayer player;
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		player = MediaPlayer.create(this, R.raw.yy);
		player.start();
		
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		player.stop();
		player.release();
		super.onDestroy();
	}

}
