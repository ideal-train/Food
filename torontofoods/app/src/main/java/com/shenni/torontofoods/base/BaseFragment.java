package com.shenni.torontofoods.base;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shenni.torontofoods.MyApplication;
import com.shenni.torontofoods.utils.ToastUtil;


/**
 * Fragment 基类
 */
public abstract class BaseFragment extends Fragment {

    protected View mView;
    private Context mContext;

    public BaseFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mContext = getActivity();
        return mView;
    }
//    public abstract void createPresenter();

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        unbinder.unbind();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }


    public void toast(String msg) {
        ToastUtil.shortToast(MyApplication.getContext(), msg);
    }

    public void toast(int resId) {
        ToastUtil.shortToast(MyApplication.getContext(), resId);
    }


}
