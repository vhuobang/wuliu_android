package com.arkui.transportation_shipper.owner.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.ui.BaseLazyFragment;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.owner.adapter.ViewPageLazyrAdapter;
import com.arkui.transportation_shipper.owner.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基于基类的Fragment
 * 运单页面
 */
public class WaybillFragment extends BaseFragment {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private String[] mTitles = {"待装货", "运输中","待收款","已完成"};
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_waybill, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);

        WaybillListFragment waybillListFragment1= WaybillListFragment.getInstance(1);

        WaybillListFragment waybillListFragment2= WaybillListFragment.getInstance(2);

        WaybillListFragment waybillListFragment3= WaybillListFragment.getInstance(3);

        WaybillListFragment waybillListFragment4= WaybillListFragment.getInstance(4);

        List<BaseLazyFragment> fragmentList = new ArrayList<>();
        fragmentList.add(waybillListFragment1);
        fragmentList.add(waybillListFragment2);
        fragmentList.add(waybillListFragment3);
        fragmentList.add(waybillListFragment4);

        ViewPageLazyrAdapter mViewPagerAdapter = new ViewPageLazyrAdapter(getChildFragmentManager(), fragmentList, mTitles);
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }


}
