package com.shenni.torontofoods.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.shenni.torontofoods.R;
import com.shenni.torontofoods.adapter.FirstNearkAdapter;
import com.shenni.torontofoods.base.LazyFragment;
import com.shenni.torontofoods.utils.GlideImageLoader;
import com.shenni.torontofoods.widget.FullyLinearLayoutManager;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

public class FirstHotFragment extends LazyFragment {

    @Bind(R.id.head_banner)
    Banner headBanner;
    @Bind(R.id.recy_hot_list)
    RecyclerView recycleview;
    @Bind(R.id.trl_refresh_list)
    TwinklingRefreshLayout mRefreshlist;


    private FirstNearkAdapter mAdapter;
    private List<String> list;

    @Override
    protected int setContentView() {
        return R.layout.fragment_first_hot;
    }

    @Override
    protected void initView(View view) {
        setBander();
        setRefreshLayout();

        recycleview.setFocusable(false);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        FullyLinearLayoutManager layoutManager = new FullyLinearLayoutManager(getActivity());
//      设置布局管理器
        recycleview.setLayoutManager(layoutManager);
        recycleview.setNestedScrollingEnabled(false);
//       设置为垂直布局，这也是默认的
//        layoutManager.setOrientation(OrientationHelper.VERTICAL);


        list = new ArrayList<>();
        mAdapter = new FirstNearkAdapter(getActivity(), list);
        recycleview.setAdapter(mAdapter);


        setDatas();

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

    private void setDatas() {


        List<String> bannerlist = new ArrayList<>();
        bannerlist.add("http://img11.360buyimg.com/cms/jfs/t2437/194/1459556207/167646/bbc95cdc/56a582a6N037c4a1c.jpg");
        bannerlist.add("http://pic66.nipic.com/file/20150514/20186208_152955102407_2.jpg");
        bannerlist.add("http://pic.58pic.com/58pic/17/70/35/557adcd2ab3d4_1024.jpg");
        headBanner.setImages(bannerlist);
        headBanner.start();


        List<String> itemlist = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            itemlist.add(String.valueOf(i));
        }
        list.addAll(itemlist);
        mAdapter.notifyDataSetChanged();
    }

    private void setBander() {

        List<Integer> images = new ArrayList<>();
        //设置banner样式
        headBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
        headBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片加载器
        headBanner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        headBanner.setImages(images);
        //设置banner动画效果
        headBanner.setBannerAnimation(Transformer.Default);
//        headBanner.setBannerAnimation(Transformer.CubeOut);
        //设置标题集合（当banner样式有显示title时）
//        banner.setBannerTitles("当banner样式有显示title时");
        //设置自动轮播，默认为true
        headBanner.isAutoPlay(true);
        //设置轮播时间
        headBanner.setDelayTime(2000);
        //设置指示器位置（当banner模式中有指示器时）
        headBanner.setIndicatorGravity(BannerConfig.CENTER);
        //banner设置方法全部调用完毕时最后调用
        headBanner.start();

        //点击事件
        headBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
//                String url = list.get(position).getLink();
//                if (StringUtil.isNullOrEmpty(url)) {
////                    toast("暂无活动链接");
//                } else {
//                    Intent intent = new Intent(getActivity().this, WebActivity.class);
//                    intent.putExtra("url", url);
//                    startActivity(intent);
//                }
            }
        });
    }


    private void setRefreshLayout() {
        SinaRefreshView headerView = new SinaRefreshView(getActivity());
        headerView.setArrowResource(R.drawable.ic_arrow);
        headerView.setTextColor(getResources().getColor(R.color.colorAccent));
        LoadingView loadingView = new LoadingView(getActivity());
        ProgressLayout header = new ProgressLayout(getActivity());
        mRefreshlist.setHeaderView(header);
//        切换到像SwipeRefreshLayout一样的悬浮刷新模式了
        mRefreshlist.setFloatRefresh(true);
//        禁止下拉
//        mRefreshlist.setEnableRefresh(false);
//        禁止回弹
        mRefreshlist.setEnableOverScroll(false);
        mRefreshlist.setBottomView(loadingView);
//        mRefreshlist.startRefresh();
//        mRefreshlist.setTargetView(recycleview);
//        mRefreshlist.setOverScrollRefreshShow(false);
        mRefreshlist.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                page = 1;
//                getDataList();
//                initData();
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                page++;
//                if (null != mList)
//                    getDataList();
            }
        });

    }
}
