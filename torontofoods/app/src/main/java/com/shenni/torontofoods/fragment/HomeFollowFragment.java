package com.shenni.torontofoods.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.flyco.tablayout.SlidingTabLayout;
import com.shenni.torontofoods.R;
import com.shenni.torontofoods.adapter.ViewPagerAdapter;
import com.shenni.torontofoods.base.LazyFragment;
import com.shenni.torontofoods.model.PagerInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class HomeFollowFragment extends LazyFragment {


    @Bind(R.id.sli_tl)
    SlidingTabLayout sliTl;
    @Bind(R.id.vp)
    ViewPager vp;

    private ViewPagerAdapter mAdapter;
    @Override
    protected int setContentView() {
        return R.layout.fragment_follw_home;
    }

    @Override
    protected void initView(View view) {

        List<PagerInfo> pagerList = new ArrayList<PagerInfo>();

        PagerInfo A = new PagerInfo(new FollowMerFragment(), R.string.follow_home_page1);
        PagerInfo B = new PagerInfo(new FollowUsrFragment(), R.string.follow_home_page2);

        pagerList.add(A);
        pagerList.add(B);

        mAdapter = new ViewPagerAdapter(getChildFragmentManager(), pagerList);
        vp.setAdapter(mAdapter);

        sliTl.setViewPager(vp);
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
