package com.arkui.transportation_shipper.driver.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.ui.BaseLazyFragment;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.owner.adapter.ViewPageLazyrAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基于基类的Fragment
 */
public class DriverWaybillFragment extends BaseFragment {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private String[] mTitles = {"待装货", "运输中","已卸货"};

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_driver_order, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);

        DriverWaybillListFragment waybillListFragment1= DriverWaybillListFragment.getInstance(1);

        DriverWaybillListFragment waybillListFragment2= DriverWaybillListFragment.getInstance(2);

        DriverWaybillListFragment waybillListFragment3= DriverWaybillListFragment.getInstance(3);

        List<BaseLazyFragment> fragmentList = new ArrayList<>();
        fragmentList.add(waybillListFragment1);
        fragmentList.add(waybillListFragment2);
        fragmentList.add(waybillListFragment3);

        ViewPageLazyrAdapter mViewPagerAdapter = new ViewPageLazyrAdapter(getChildFragmentManager(), fragmentList, mTitles);
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }
}
