package com.shenni.torontofoods;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.shenni.torontofoods.base.BaseActivity;
import com.shenni.torontofoods.fragment.CollectFragment;
import com.shenni.torontofoods.fragment.HomeFirstFragment;
import com.shenni.torontofoods.fragment.HomeFollowFragment;
import com.shenni.torontofoods.fragment.MineFragment;
import com.shenni.torontofoods.utils.StatusBarUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.fl_home)
    FrameLayout flHome;
    @Bind(R.id.fake_status_bar)
    View fakeStatusBar;


    private long exitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
//        StatusBarUtil.setColor(MainActivity.this, getResources().getColor(R.color.app_bg), 0);
        StatusBarUtil.setTranslucentForImageView(MainActivity.this, 0, null);
        ButterKnife.bind(this);
        fakeStatusBar.setBackgroundColor(getResources().getColor(R.color.app_bg));
        switchFragment(new HomeFirstFragment(), null);
    }

    @OnClick({R.id.rb_home, R.id.rb_follow, R.id.rb_collection, R.id.rb_user, R.id.bottom_conter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_home:
                switchFragment(new HomeFirstFragment(), null);
                break;
            case R.id.rb_follow:
                switchFragment(new HomeFollowFragment(), null);
                break;
            case R.id.rb_collection:
                switchFragment(new CollectFragment(), null);
                break;
            case R.id.rb_user:
                switchFragment(new MineFragment(), null);
                fakeStatusBar.setVisibility(View.GONE);
                break;
            case R.id.bottom_conter:
                toast(R.string.loading);
                break;
        }
    }

    public void switchFragment(Fragment fragment, Bundle args) {
        try {
            fakeStatusBar.setVisibility(View.VISIBLE);
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.fl_home, fragment);
            ft.commit();
        } catch (Exception exceptione) {
        }
    }



    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            exitTime = System.currentTimeMillis();
            toast("再按一次退出程序");
        } else {
            finishAll();
        }

    }

}


