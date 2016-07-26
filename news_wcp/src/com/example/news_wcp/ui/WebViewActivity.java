package com.example.news_wcp.ui;

import android.content.Intent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.news_wcp.R;
import com.example.news_wcp.base.BaseActivity;
import com.example.news_wcp.view.MyActionbar;

public class WebViewActivity extends BaseActivity {

	private MyActionbar mMa_actionbar;
	private WebView mWebView;
	
	@Override
	public int getLayoutId() {
		return R.layout.activity_webview;
	}

	@Override
	public void initView() {

		mMa_actionbar = (MyActionbar) findViewById(R.id.ma_activity_webview_actionbar);
		mWebView = (WebView) findViewById(R.id.wv_activity_webview);
		
		mWebView.setWebChromeClient(new WebChromeClient());
		mWebView.setWebViewClient(new WebViewClient());
		WebSettings settings = mWebView.getSettings();
		settings.setJavaScriptEnabled(true);
		
		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		String title = intent.getStringExtra("title");
		mMa_actionbar.setTitle(title);
		mMa_actionbar.setRightImg(R.drawable.ic_title_share_default);
		
		mWebView.loadUrl(url);
	}

	@Override
	public void initData() {

		
	}

	@Override
	public void initListener() {

	}

}
