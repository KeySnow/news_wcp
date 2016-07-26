package com.example.news_cy.ui;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.news_cy.R;
import com.example.news_cy.entities.Chat;
import com.example.news_cy.entities.Message;
import com.example.news_cy.utils.GsonUtil;
/**
 * 聊天小助手
 *
 * @author wu
 *
 * 2016-7-4
 */
public class MainChatFragment extends Fragment {

	private ListView mLv_chat;
	private EditText mEt_import;
	private Button mBtn_send;
	private TextView mTv_hint;
	private ArrayList<Message> datas = new ArrayList<Message>();
	private MyAdapter adapter;
	private Handler handler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				Message message = new Message(1, (String)msg.obj);
				datas.add(message);
				adapter.notifyDataSetChanged();
				break;
			case 2:
				mTv_hint.setVisibility(View.INVISIBLE);
				break;

			default:
				break;
			}
		};
	};

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_chat_listview, container, false);

		initView(view);
		initListener();

		return view;
	}

	private void initView(View view) {

		mLv_chat = (ListView) view.findViewById(R.id.lv_fragment_chat_listview);
		mEt_import = (EditText) view.findViewById(R.id.et_fragment_chat_listview_import);
		mBtn_send = (Button) view.findViewById(R.id.btn_fragment_chat_listview_send);
		mTv_hint = (TextView) view.findViewById(R.id.tv_fragment_chat_listview_hint);

		datas.add(new Message(1, "和傻强来聊聊吧~"));

		adapter = new MyAdapter();
		mLv_chat.setAdapter(adapter );
	}

	private void initListener() {
		mBtn_send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(!mEt_import.getText().toString().equals("")){

					String text = mEt_import.getText().toString();
					mEt_import.setText(null);
					Message message = new Message(0, text);
					datas.add(message);
					adapter.notifyDataSetChanged();

					requestData(text);
				}else{
					mTv_hint.setVisibility(View.VISIBLE);
					
					android.os.Message msg = new android.os.Message();
					msg.what = 2;
					handler.sendMessageDelayed(msg, 3000);
				}
			}

		});
	}

	private void requestData(final String text) {
		new Thread(new Runnable() {

			@Override
			public void run() {

				DefaultHttpClient client = new DefaultHttpClient();
				HttpPost post = new HttpPost("http://www.tuling123.com/openapi/api");
				try {
					StringEntity entity = new StringEntity("{\"key\":\"3604e18ffd2d1788bb911cb62735e31f\",\"info\":\"" + text + "\"}", "utf8");
					post.setEntity(entity );
					HttpResponse execute = client.execute(post);
					HttpEntity responseEntity = execute.getEntity();
					String string = EntityUtils.toString(responseEntity, "utf8");

					GsonUtil gson = new GsonUtil();
					Chat chat = gson.JsonToBean(string, Chat.class);
					String text2 = chat.getText();

					android.os.Message msg = new android.os.Message();
					msg.what = 1;
					msg.obj = text2;
					handler.sendMessage(msg);
				} catch (Exception e) {
					e.printStackTrace();
				} 

			}
		}).start();
	}

	class MyAdapter extends BaseAdapter{

		private TextView tv_send_name;
		private TextView tv_send_text;
		private TextView tv_come_name;
		private TextView tv_come_text;

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

			switch (getItemViewType(position)) {
			case 0:
				view = View.inflate(getActivity(), R.layout.fragment_chat_listview_send_item, null);
				tv_send_name = (TextView) view.findViewById(R.id.tv_fragment_chat_listview_send_name);
				tv_send_text = (TextView) view.findViewById(R.id.tv_fragment_chat_listview_send_text);
				tv_send_text.setText(datas.get(position).getMsg());
				break;
			case 1:
				view = View.inflate(getActivity(), R.layout.fragment_chat_listview_come_item, null);
				tv_come_name = (TextView) view.findViewById(R.id.tv_fragment_chat_listview_come_name);
				tv_come_text = (TextView) view.findViewById(R.id.tv_fragment_chat_listview_come_text);
				tv_come_text.setText(datas.get(position).getMsg());
				break;

			default:
				break;
			}

			return view;
		}

		@Override
		public int getViewTypeCount() {
			return 2;
		}

		@Override
		public int getItemViewType(int position) {
			return datas.get(position).getType();
		}

	}

}
