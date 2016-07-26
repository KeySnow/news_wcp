package com.example.news_cy.ui;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.onekeyshare.OnekeyShare;

import com.example.news_cy.R;
import com.example.news_cy.base.BaseActivity;
import com.example.news_cy.view.MyActionbar;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class WebViewActivity extends BaseActivity {

	@ViewInject(R.id.ma_activity_webview_actionbar)
	private MyActionbar mMa_actionbar;
	@ViewInject(R.id.wv_activity_webview)
	private WebView mWv_webview;
	private String url;
	private String title;

	@Override
	protected int getLayoutId() {
		return R.layout.activity_webview;
	}

	@Override
	protected void initView() {

		ViewUtils.inject(this);

		Intent intent = getIntent();
		url = intent.getStringExtra("url");
		title = intent.getStringExtra("title");

		mWv_webview.setWebChromeClient(new WebChromeClient());
		mWv_webview.setWebViewClient(new WebViewClient());

		WebSettings settings = mWv_webview.getSettings();
		settings.setJavaScriptEnabled(true);

		mMa_actionbar.setTitle(title);
		mMa_actionbar.setRightImage(R.drawable.ic_title_share_default);
		
		mWv_webview.loadUrl(url);
	}

	@Override
	protected void ininData() {

	}

	@Override
	protected void initListener() {
		
		mMa_actionbar.setLeftListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		mMa_actionbar.setRightListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ShareSDK.initSDK(getApplicationContext());
				OnekeyShare oks = new OnekeyShare();
				//关闭sso授权
				oks.disableSSOWhenAuthorize(); 

				// 分享时Notification的图标和文字  2.5.9以后的版本不调用此方法
				//oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
				// title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
				oks.setTitle(title);
				// titleUrl是标题的网络链接，仅在人人网和QQ空间使用
				oks.setTitleUrl(url);
				// text是分享文本，所有平台都需要这个字段
				oks.setText("我是分享文本");
				// imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
				//oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
				// url仅在微信（包括好友和朋友圈）中使用
				oks.setUrl(url);
				// comment是我对这条分享的评论，仅在人人网和QQ空间使用
				oks.setComment("我是测试评论文本");
				// site是分享此内容的网站名称，仅在QQ空间使用
				oks.setSite(getString(R.string.app_name));
				// siteUrl是分享此内容的网站地址，仅在QQ空间使用
				oks.setSiteUrl(url);

				// 启动分享GUI
				oks.show(getApplicationContext());
			}
		});
	}

}
