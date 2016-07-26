package com.example.chat;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {

	private Button btn;
	private TextView tv;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				tv.setText((String) msg.obj);
				break;

			default:
				break;
			}
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btn = (Button) findViewById(R.id.btn);
		tv = (TextView) findViewById(R.id.tv);
	}

	public void request(View v){

		new Thread(new Runnable() {

			@Override
			public void run() {

				DefaultHttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost("http://www.tuling123.com/openapi/api");
				try {
					StringEntity entity = new StringEntity("{\"key\":\"3604e18ffd2d1788bb911cb62735e31f\",\"info\":\"½²¸öÐ¦»°\"}", "utf8");
					post.setEntity(entity);
					HttpResponse execute = client.execute(post);
					HttpEntity responseEntity = execute.getEntity();
					String string = EntityUtils.toString(responseEntity, "utf8");

					Message msg = new Message();
					msg.what = 1;
					msg.obj = string;
					handler.sendMessage(msg );
				} catch (Exception e) {
					e.printStackTrace();
				} 

			}
		}).start();
	}

}
