package com.arkui.transportation.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.adapter.ViewPageLazyAdapter;
import com.arkui.fz_tools.ui.BaseLazyFragment;
import com.arkui.transportation.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基于基类的Fragment
 */
public class OwnerWaybillFragment extends BaseLazyFragment {


    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private String[] mTitles = {"待装货", "运输中", "待付款", "待收款", "已完成"};


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_owner_waybill, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        List<BaseLazyFragment> fragmentList = new ArrayList<>();

        for (int i = 0; i < mTitles.length; i++) {

            fragmentList.add(OwnerWaybillListFragment.getInstance(i));

        }

        ViewPageLazyAdapter mViewPagerAdapter = new ViewPageLazyAdapter(getChildFragmentManager(), fragmentList, mTitles);
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void lazyLoadData() {

    }
}
