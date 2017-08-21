package com.arkui.transportation_owner.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_net.utils.RxBus;
import com.arkui.fz_tools.ui.BaseLazyFragment;
import com.arkui.fz_tools.utils.LogUtil;
import com.arkui.transportation_owner.R;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.transportation_owner.adapter.ViewPageLazyAdapter;
import com.arkui.transportation_owner.entity.RefreshWaybill;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 基于基类的Fragment
 */
public class WaybillFragment extends BaseFragment {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private String[] mTitles = {"预发布", "已发布", "待装货", "运输中", "待付款", "已完成"};

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_waybill, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        // WaybillListFragment waybillListFragment1= WaybillListFragment.getInstance(1);
        //  WaybillListFragment waybillListFragment2= WaybillListFragment.getInstance(2);
        PublishListFragment publishListFragment1 = PublishListFragment.getInstance(1);
        PublishListFragment publishListFragment2 = PublishListFragment.getInstance(2);
        WaybillListFragment waybillListFragment3 = WaybillListFragment.getInstance(3);
        WaybillListFragment waybillListFragment4 = WaybillListFragment.getInstance(4);
        WaybillListFragment waybillListFragment5 = WaybillListFragment.getInstance(5);
        WaybillListFragment waybillListFragment6 = WaybillListFragment.getInstance(6);

        List<BaseLazyFragment> fragmentList = new ArrayList<>();
        fragmentList.add(publishListFragment1);
        fragmentList.add(publishListFragment2);
        fragmentList.add(waybillListFragment3);
        fragmentList.add(waybillListFragment4);
        fragmentList.add(waybillListFragment5);
        fragmentList.add(waybillListFragment6);

        ViewPageLazyAdapter mViewPagerAdapter = new ViewPageLazyAdapter(getChildFragmentManager(), fragmentList, mTitles);
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData() {
        super.initData();

    }
}
