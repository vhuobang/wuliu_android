package com.arkui.fz_tools.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.arkui.fz_tools.ui.BaseLazyFragment;

import java.util.List;

/**
 * Created by nmliz on 2017/2/13.
 */

public class ViewPageLazyAdapter extends FragmentStatePagerAdapter {

    private List<BaseLazyFragment> mList;
    private String[] mTitles;

    public ViewPageLazyAdapter(FragmentManager fm, List<BaseLazyFragment> list, String[] titles) {
        super(fm);
        this.mList = list;
        this.mTitles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
