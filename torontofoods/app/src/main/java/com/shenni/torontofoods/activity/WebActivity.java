package com.shenni.torontofoods.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.shenni.torontofoods.R;
import com.shenni.torontofoods.base.BaseActivity;
import com.shenni.torontofoods.utils.DialogUtil;
import com.shenni.torontofoods.utils.StatusBarUtil;
import com.shenni.torontofoods.utils.StringUtil;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebActivity extends BaseActivity {

    @Bind(R.id.wv)
    WebView wv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        ButterKnife.bind(this);
        StatusBarUtil.setColor(WebActivity.this, getResources().getColor(R.color.colorPrimary), 0);
        leftDefault();
        init();
    }

    public void init() {
        final String url = getIntent().getStringExtra("url");
        Log.e("url", url);
        if (!StringUtil.isNullOrEmpty(url)) {
//            MyLog.d("WebActivity", "加载:");
//            DialogUtil.showMyDialog(this, true);
        }
        wv.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);

        wv.getSettings().setJavaScriptEnabled(true);  //支持javascript
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        wv.getSettings().setUseWideViewPort(true);
        //wv.getSettings().setSupportZoom(true);   //支持缩放
        wv.getSettings().setCacheMode(wv.getSettings().LOAD_CACHE_ELSE_NETWORK); //优先使用缓存
        wv.getSettings().setLoadWithOverviewMode(true);
        wv.loadUrl(url);
        wv.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                DialogUtil.showMyDialog(WebActivity.this, true);
            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                super.onPageFinished(view, url);
//                DialogUtil.closeMyDialog();
//            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//                view.loadUrl(url);
                return true;


            }
        });
        wv.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {

                if (newProgress == 50) {
                    //加载完成
                    DialogUtil.closeMyDialog();
                }
            }

        });

    }


}

