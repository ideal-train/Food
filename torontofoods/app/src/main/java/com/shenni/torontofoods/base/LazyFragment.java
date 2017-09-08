package com.shenni.torontofoods.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 *  设置Fragment模式为懒加载模式
 */
public abstract class LazyFragment extends BaseFragment {

    private boolean isInit;                  //初始化是否完成
    protected boolean isLoad = false;
    /**
     * 页数
     */
    public int page = 1;
    /**
     * 显示的数据条数
     */
    public int num = 10;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(setContentView(), container, false);
        ButterKnife.bind(this, view);
        initView(view);
        isInit = true;
        initPrepare();
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    /**
     * 视图是否已经对用户可见，系统的方法
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //先判断映射关系再判断 是否可见
        initPrepare();
    }


    @Override
    public void onDestroyView() {
        isInit = false;
        isLoad = false;
        onBaseDestroyView();
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    //同步锁
    private synchronized void initPrepare() {
        if (!isInit) {
            return;
        }
        if (getUserVisibleHint()) {
            onUserVisible();
            isLoad = true;
        } else {
            //不可见时执行操作
            if (isLoad) {
                onUserInvisible();
            }
        }
    }


    /**
     * 设置Fragment要显示的布局
     *
     * @return 布局的layoutId
     */
    protected abstract int setContentView();

    /**
     * 初始化控件操作
     *
     * @param view
     */
    protected abstract void initView(View view);


    /**
     * 当视图初始化
     * 并且对用户可见
     * 时候去真正的加载数据
     */
    protected abstract void onUserVisible();

    /**
     * 页面不可见时操作
     */
    protected abstract void onUserInvisible();

    protected abstract void onBaseDestroyView();
}
