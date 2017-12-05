package com.shenni.torontofoods.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.util.Util;
import com.shenni.torontofoods.R;
import com.shenni.torontofoods.utils.StatusBarUtil;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

/**
 */
public abstract class BaseTooBarActivity extends AutoLayoutActivity {


    private LinearLayout rootLayout;
    private Toolbar mToolbar;
    private TextView mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base_transparent_bar);
        // 初始化toolbar
        initBaseToolbar();
        setContentView(View.inflate(this, setContentViewID(), null));
        initView();
        //初始化状态栏透明
        setStatusBar();
        ButterKnife.bind(this);
    }

    public abstract
    @LayoutRes
    int setContentViewID();

    public abstract void initView();


    protected void setStatusBar() {
//        有颜色全透状态栏
        StatusBarUtil.setColor(this, getResources().getColor(R.color.black), 0);
//        浸入式状态栏
//      StatusBarUtil.setTranslucentForImageView(this, 0, null);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        if (Util.isOnMainThread() && !this.isFinishing()) {
            Glide.with(this).pauseRequests();
        }
    }
    @Override
    public void setContentView(View view) {
//        子布局加入到父布局中
        rootLayout = (LinearLayout) findViewById(R.id.activity_base_too_bar);
        if (rootLayout != null) {
            rootLayout.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            initBaseToolbar();
        }
    }

    /**
     * 初始化toolbar
     */
    private void initBaseToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.base_toolbar);
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
        }
    }


    /**
     * 加入默认toobar
     */
    private void initBaseTooBarContent() {
        View view = getLayoutInflater().inflate(R.layout.toolbar_layout, mToolbar);
        mTitle = view.findViewById(R.id.toolbar_title);
    }

    /**
     * 获取toolbar
     *
     * @return
     */
    public Toolbar getBaseToolBar() {
        return mToolbar;
    }

    public void setTitle(String title) {
        initBaseTooBarContent();
        if (mTitle == null)
            return;
        mTitle.setText(title);
    }
}
