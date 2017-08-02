package com.arkui.transportation.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.adapter.ViewPagerAdapter;
import com.arkui.fz_tools.listener.OnTabSelectListener;
import com.arkui.fz_tools.view.SegmentTabLayout;
import com.arkui.transportation.R;
import com.arkui.fz_tools.ui.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基于基类的Fragment
 */
public class MessageFragment extends BaseFragment {

    @BindView(R.id.tab)
    SegmentTabLayout mTab;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private String[] mTitles = {"运单消息", "系统消息"};

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_message, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        mTab.setTabData(mTitles);

        List<BaseFragment> baseFragmentList = new ArrayList<>();
        OrderFragment orderFragment = new OrderFragment();
        SystemFragment systemFragment = new SystemFragment();


        baseFragmentList.add(orderFragment);
        baseFragmentList.add(systemFragment);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), baseFragmentList, mTitles);

        mViewPager.setAdapter(viewPagerAdapter);

        mTab.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTab.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mViewPager.setCurrentItem(0);
    }
}
