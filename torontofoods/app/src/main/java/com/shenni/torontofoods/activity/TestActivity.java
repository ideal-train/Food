package com.shenni.torontofoods.activity;

import com.shenni.torontofoods.R;
import com.shenni.torontofoods.base.BaseTooBarActivity;

/**
 * Created by lijiafu on 2017/12/4.
 * 功能：
 */

public class TestActivity extends BaseTooBarActivity {

    @Override
    public int setContentViewID() {
        return R.layout.fragment_mine;
    }

    @Override
    public void initView() {
//        默认形式的自定义TooBar
        setTitle("测试");
//        添加的自定义TooBar
//        getLayoutInflater().inflate(R.layout.item_near, getBaseToolBar());
    }
}
