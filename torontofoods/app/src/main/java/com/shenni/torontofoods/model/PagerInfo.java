package com.shenni.torontofoods.model;

import android.support.v4.app.Fragment;

/**
 * Created by Ocean on 6/22/16.
 */
public class PagerInfo {
    private Fragment fragment;
    private int titleResId;

    public Fragment getFragment() {
        return fragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }

    public int getTitleResId() {
        return titleResId;
    }

    public void setTitleResId(int titleResId) {
        this.titleResId = titleResId;
    }

    public PagerInfo(Fragment fragment, int titleResId) {
        this.fragment = fragment;
        this.titleResId = titleResId;
    }
}
