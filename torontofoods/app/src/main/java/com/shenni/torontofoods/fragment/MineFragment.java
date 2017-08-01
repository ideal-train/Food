package com.shenni.torontofoods.fragment;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.lzy.widget.PullZoomView;
import com.shenni.torontofoods.R;
import com.shenni.torontofoods.base.LazyFragment;

import butterknife.Bind;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 我的
 */
public class MineFragment extends LazyFragment {


    @Bind(R.id.icon_head)
    CircleImageView iconHead;
    @Bind(R.id.icon_nick)
    TextView iconNick;
    @Bind(R.id.ll1_re1_fans)
    TextView ll1Re1Fans;
    @Bind(R.id.ll1_re1_focus)
    TextView ll1Re1Focus;
    @Bind(R.id.ll1_re1_collect)
    TextView ll1Re1Collect;
    @Bind(R.id.ll2_ll1)
    LinearLayout ll2Ll1;
    @Bind(R.id.ll2_ll2)
    LinearLayout ll2Ll2;
    @Bind(R.id.ll2_ll3)
    LinearLayout ll2Ll3;
    @Bind(R.id.scrollView)
    ScrollView scrollView;
    @Bind(R.id.pzv)
    PullZoomView pzv;

    @Override
    protected int setContentView() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view) {

        pzv.setIsParallax(true);
        pzv.setIsZoomEnable(true);
        pzv.setSensitive(1.8f);
        pzv.setZoomTime(500);
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected void onBaseDestroyView() {
    }

}