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
				//�ر�sso��Ȩ
				oks.disableSSOWhenAuthorize(); 

				// ����ʱNotification��ͼ�������  2.5.9�Ժ�İ汾�����ô˷���
				//oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
				// title���⣬ӡ��ʼǡ����䡢��Ϣ��΢�š���������QQ�ռ�ʹ��
				oks.setTitle(title);
				// titleUrl�Ǳ�����������ӣ�������������QQ�ռ�ʹ��
				oks.setTitleUrl(url);
				// text�Ƿ����ı�������ƽ̨����Ҫ����ֶ�
				oks.setText("���Ƿ����ı�");
				// imagePath��ͼƬ�ı���·����Linked-In�����ƽ̨��֧�ִ˲���
				//oks.setImagePath("/sdcard/test.jpg");//ȷ��SDcard������ڴ���ͼƬ
				// url����΢�ţ��������Ѻ�����Ȧ����ʹ��
				oks.setUrl(url);
				// comment���Ҷ�������������ۣ�������������QQ�ռ�ʹ��
				oks.setComment("���ǲ��������ı�");
				// site�Ƿ�������ݵ���վ���ƣ�����QQ�ռ�ʹ��
				oks.setSite(getString(R.string.app_name));
				// siteUrl�Ƿ�������ݵ���վ��ַ������QQ�ռ�ʹ��
				oks.setSiteUrl(url);

				// ��������GUI
				oks.show(getApplicationContext());
			}
		});
	}

}
