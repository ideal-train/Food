package com.shenni.torontofoods.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.shenni.torontofoods.R;
import com.shenni.torontofoods.utils.ToastUtil;
import com.zhy.autolayout.AutoLayoutActivity;

import java.util.concurrent.ConcurrentLinkedQueue;


/**
 * 1、集成ButterKnife View 注入框架
 * <p/>
 * 2、集成EventBus 事件总线框架
 * <p/>
 */
public abstract class BaseActivity extends AutoLayoutActivity {

    protected Context mContext;


    RelativeLayout rlLeft;
    RelativeLayout rlRight;
    public ActionBar actionBar;
    public TextView tvTitle;
    public ImageView ivLeft;
    public TextView tvRight;

    public LinearLayout llBar;

    public String uid = "";

    /**
     * 页数
     */
    public int page = 1;
    /**
     * 显示的数据条数
     */
    public int num = 12;

    /**
     * 所有已存在的Activity
     */
    protected static ConcurrentLinkedQueue<Activity> allActivity = new ConcurrentLinkedQueue<Activity>();



    //状态栏颜色
    TextView tvStatusBar;
    //toobar 是否可隐藏
    RelativeLayout mToobarView;
    //左侧 点击区域
    RelativeLayout leftView;
    //左侧 图片
    ImageView leftimg;
    //左侧 文字
    TextView leftTv;
    //标题部分
    TextView mToolTarTitle;
    //右侧 点击区域
    RelativeLayout rightView;
    //    右侧 图片
    ImageView rightimg;
    //右侧文字
    TextView righTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(setLayoutResID());
//		unbinder=ButterKnife.bind(this);
//        ButterKnife.inject(this);
        allActivity.add(this);
        mContext = this;
//        initActionbar();
        // Logger.init().hideThreadInfo().setLogLevel(LogLevel.FULL);
    }

    private void initActionbar() {
        View view = getLayoutInflater().inflate(R.layout.toolbar_layout, null);
        llBar = (LinearLayout) view.findViewById(R.id.toolbar_main);
        tvTitle = (TextView) view.findViewById(R.id.toolbar_title);
        ivLeft = (ImageView) view.findViewById(R.id.toolbar_left_iv);
        rlLeft = (RelativeLayout) view.findViewById(R.id.left_view);
        rlRight = (RelativeLayout) view.findViewById(R.id.right_view);
        tvRight = (TextView) view.findViewById(R.id.toolbar_right_tv);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT, Gravity.LEFT);
        actionBar = getSupportActionBar();
        assert actionBar != null;
        if (actionBar == null)
            return;
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(view, params);
    }

//
//    //有左侧返回 中间标题
//    public void setTitleBar(int title) {
//        ivLeft.setVisibility(View.VISIBLE);
//        tvTitle.setText(getString(title));
//        mBackCClick();
//    }
//    //有左侧返回 中间标题
//
//    public void setTitleBar(String title) {
//        ivLeft.setVisibility(View.VISIBLE);
//        tvTitle.setText(title);
//        mBackCClick();
//    }
//
//    //修改左侧图片 中间标题
//    public void setLeftImgTitleBar(int title, int lImg) {
//        ivLeft.setVisibility(View.VISIBLE);
//        ivLeft.setImageResource(lImg);
//        tvTitle.setText(getString(title));
//        mBackCClick();
//    }
//    //修改左侧图片 中间标题
//
//    public void setLeftImgTitleBar(String title, int lImg) {
//        ivLeft.setVisibility(View.VISIBLE);
//        ivLeft.setImageResource(lImg);
//        tvTitle.setText(title);
//        mBackCClick();
//    }
//
//
//    //左侧返回 中间标题 右侧文字
//    public void setRightTxtTitleBar(int title, int rTxt) {
//        tvTitle.setVisibility(View.VISIBLE);
//        tvRight.setVisibility(View.VISIBLE);
//        ivLeft.setVisibility(View.VISIBLE);
//        tvTitle.setText(getString(title));
//        tvRight.setText(getString(rTxt));
//        mBackCClick();
//    }//左侧返回 中间标题 右侧文字
//
//    public void setRightTxtTitleBar(String title, String rTxt) {
//        tvTitle.setVisibility(View.VISIBLE);
//        tvRight.setVisibility(View.VISIBLE);
//        ivLeft.setVisibility(View.VISIBLE);
//        tvTitle.setText(title);
//        tvRight.setText(rTxt);
//        mBackCClick();
//        mRightClick();
//    }
//
//
//    //返回事件
//    private void mBackCClick() {
//        rlLeft.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                leftAction();
//            }
//        });
//    }
//
//    private void mRightClick() {
//        rlRight.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                rightAction();
//            }
//        });
//    }
//
//    public void rightAction() {
//
//    }
//
//    //只需要返回的情况下不需要覆写
//    public void leftAction() {
//        finish();
//    }
//
//    public void noActionBar() {
//        if (actionBar == null)
//            return;
//        actionBar.hide();
//    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//		unbinder.unbind();
    }


    /**
     * 结束所有activity
     */
    public static void finishAll() {
        // 结束Activity
        for (Activity act : allActivity) {
            act.finish();
        }
    }


//    public abstract int setLayoutResID();

//    public abstract void InitView();

    public void toast(String msg) {
        ToastUtil.shortToast(getApplication(), msg);
    }

    public void toast(int resId) {
        ToastUtil.shortToast(getApplication(), resId);
    }


    /**
     * 初始化titlebar
     */
    private void initToolbar() {
        tvStatusBar = (TextView) findViewById(R.id.tv_status_bar);

        mToobarView = (RelativeLayout) findViewById(R.id.toobar_view);

        leftView = (RelativeLayout) findViewById(R.id.left_view);
        leftimg = (ImageView) findViewById(R.id.toolbar_left_iv);
        leftTv = (TextView) findViewById(R.id.toolbar_left_tv);

        mToolTarTitle = (TextView) findViewById(R.id.toolbar_title);

        rightView = (RelativeLayout) findViewById(R.id.right_view);
        rightimg = (ImageView) findViewById(R.id.toolbar_right_iv);
        righTv = (TextView) findViewById(R.id.toolbar_right_tv);
    }


    //左侧返回
    public void leftDefault() {
        initToolbar();
        mLeftClick();
    }

    // 中间标题
    public void justSetTitleBar(int title) {
        initToolbar();
        leftimg.setVisibility(View.GONE);
        mToolTarTitle.setText(getString(title));
    }
    // 中间标题

    public void justSetTitleBar(String title) {
        initToolbar();
        leftimg.setVisibility(View.GONE);
        mToolTarTitle.setText(title);
    }

    //左侧返回 中间标题
    public void setTitleBar(int title) {
        initToolbar();
        mToolTarTitle.setText(getString(title));
        mLeftClick();
    }
    //左侧返回 中间标题

    public void setTitleBar(String title) {
        initToolbar();
        mToolTarTitle.setText(title);
        mLeftClick();
    }

    //修改左侧图片 中间标题
    public void setLeftImgTitleBar(int title, int lImg) {
        initToolbar();
        leftTv.setVisibility(View.GONE);
        leftimg.setVisibility(View.VISIBLE);
        leftimg.setImageResource(lImg);
        mToolTarTitle.setText(getString(title));
        mLeftClick();
    }

    //修改左侧图片 中间标题
    public void setLeftImgTitleBar(String title, int lImg) {
        initToolbar();
        leftTv.setVisibility(View.GONE);
        leftimg.setVisibility(View.VISIBLE);
        leftimg.setImageResource(lImg);
        mToolTarTitle.setText(title);
        mLeftClick();
    }

    //左侧文字 中间标题
    public void setLeftTextTitleBar(String left, String title) {
        initToolbar();
        leftimg.setVisibility(View.GONE);
        leftTv.setVisibility(View.VISIBLE);
        leftTv.setText(left);
        mToolTarTitle.setText(title);
        mLeftClick();
    }


    //左侧返回 中间标题 右侧图片
    public void setRightTxtTitleBar(int title, int lImg) {
        initToolbar();
        leftTv.setVisibility(View.GONE);
        rightimg.setVisibility(View.VISIBLE);
        mToolTarTitle.setText(getString(title));
        rightimg.setImageResource(lImg);
        mLeftClick();
        mRightClick();
    }

    //左侧返回 中间标题 右侧文字
    public void setRightTxtTitleBar(String title, String lImg) {
        initToolbar();

        mToolTarTitle.setText(title);

        rightimg.setVisibility(View.GONE);
        righTv.setVisibility(View.VISIBLE);
        righTv.setText(lImg);
        mLeftClick();
        mRightClick();
    }


    //左侧文字 中间标题 右侧文字
    public void setAllTitleBar(String left, String title, String right) {
        initToolbar();
        leftimg.setVisibility(View.GONE);
        leftTv.setVisibility(View.VISIBLE);
        leftTv.setText(left);

        mToolTarTitle.setText(title);

        rightimg.setVisibility(View.GONE);
        righTv.setVisibility(View.VISIBLE);
        righTv.setText(right);
        mLeftClick();
        mRightClick();
    }

    //左侧无 中间标题 右侧图片
    public void justSetRightTxtTitleBar(int title, int Img) {
        initToolbar();
        leftimg.setVisibility(View.GONE);
        rightimg.setVisibility(View.VISIBLE);
        mToolTarTitle.setText(getString(title));
        rightimg.setImageResource(Img);
        mRightClick();
    }

    //左侧无 中间标题 右侧文字
    public void JustSetRightTxtTitleBar(String title, int Img) {
        initToolbar();
        leftimg.setVisibility(View.GONE);
        rightimg.setVisibility(View.VISIBLE);
        mToolTarTitle.setText(title);
        rightimg.setImageResource(Img);
        mRightClick();
    }

    //返回事件
    private void mLeftClick() {
        leftView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leftAction();
            }
        });
    }

    private void mRightClick() {
        rightView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightAction();
            }
        });
    }

    public void rightAction() {

    }

    //只需要返回的情况下不需要覆写
    public void leftAction() {
        finish();
    }

    public void noToolBar() {
        initToolbar();
        mToobarView.setVisibility(View.GONE);
    }

    public void noActionBar() {
    }


}
