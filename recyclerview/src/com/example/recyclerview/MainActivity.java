package com.example.recyclerview;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;


public class MainActivity extends Activity {

	private RecyclerView mRecyclerView;
	private ArrayList<String> datas = new ArrayList<String>();
	private MyRecyclerViewAdapter adapter;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        for(int i = 0; i < 100; i++){
        	datas.add("图片" + i);
        }
        
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        
        GridLayoutManager layout = new GridLayoutManager(this, 4);
        layout.setOrientation(GridLayoutManager.VERTICAL);
		//设置布局
		mRecyclerView.setLayoutManager(layout );
		
		adapter = new MyRecyclerViewAdapter();
		mRecyclerView.setAdapter(adapter);
		
		adapter.setOnRecyclerViewClickListener(new OnRecyclerViewClickListener() {
			
			@Override
			public void click(int position) {
				datas.remove(position);
		    	adapter.notifyItemRemoved(position);
			}
		});
    }
    
    public void delete(View v){
    	datas.remove(0);
    	adapter.notifyItemRemoved(0);
    }
    public void add(View v){
    	datas.add(0, "new Pics");
    	adapter.notifyItemInserted(0);
    }
    
    //自定义监听器借口
    public interface OnRecyclerViewClickListener{
    	public void click(int position);
    }
    
    //RecyclerView适配器
    class MyRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder>{

    	private OnRecyclerViewClickListener listener;
    	
    	public void setOnRecyclerViewClickListener(OnRecyclerViewClickListener listener){
    		this.listener = listener;
    	}
    	
		@Override
		public int getItemCount() {
			return datas.size();
		}

		@Override
		public void onBindViewHolder(ViewHolder arg0, int arg1) {
			arg0.tv.setText(datas.get(arg1));
			final int position = arg0.getPosition();
			
			arg0.tv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					listener.click(position);
				}
			});
		}

		@Override
		public ViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
			
			View view = View.inflate(MainActivity.this, R.layout.recyclerview_item, null);
			ViewHolder holder = new ViewHolder(view);
			
			return holder;
		}
    	
    }
    
    class ViewHolder extends android.support.v7.widget.RecyclerView.ViewHolder{

    	private TextView tv;
    	
		public ViewHolder(View arg0) {
			super(arg0);
			
			tv = (TextView) arg0.findViewById(R.id.tv);
			
		}
    	
    }

}
