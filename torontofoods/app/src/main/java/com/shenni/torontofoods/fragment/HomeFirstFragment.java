package com.shenni.torontofoods.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.shenni.torontofoods.R;
import com.shenni.torontofoods.adapter.ViewPagerAdapter;
import com.shenni.torontofoods.base.LazyFragment;
import com.shenni.torontofoods.model.PagerInfo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class HomeFirstFragment extends LazyFragment {


    @Bind(R.id.tc_city)
    TextView tcCity;
    @Bind(R.id.tl_5)
    SlidingTabLayout tl5;
    @Bind(R.id.vp)
    ViewPager vp;

    private ViewPagerAdapter mAdapter;

    @Override
    protected int setContentView() {
        return R.layout.fragment_first_home;
    }

    @Override
    protected void initView(View view) {
        View decorView = getActivity().getWindow().getDecorView();

        List<PagerInfo> pagerList = new ArrayList<PagerInfo>();

        PagerInfo A = new PagerInfo(new FirstHotFragment(), R.string.first_home_page1);
        PagerInfo B = new PagerInfo(new FirstNearFragment(), R.string.first_home_page2);
        PagerInfo C = new PagerInfo(new FirstFindFragment(), R.string.first_home_page3);

        pagerList.add(A);
        pagerList.add(B);
        pagerList.add(C);

//        ViewPager vp = ViewFindUtils.find(decorView, R.id.vp);
        mAdapter = new ViewPagerAdapter(getChildFragmentManager(), pagerList);
        vp.setAdapter(mAdapter);
        tl5.setViewPager(vp);
        vp.setCurrentItem(1);
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
