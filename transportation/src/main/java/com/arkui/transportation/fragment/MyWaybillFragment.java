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
public class MyWaybillFragment extends BaseLazyFragment {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private String[] mTitles = {"预发布", "已发布", "待装货", "运输中", "待付款", "已完成"};

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_my_waybill, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        List<BaseLazyFragment> fragmentList = new ArrayList<>();
        PublishFragment publishFragment0 = PublishFragment.getInstance(1);
        PublishFragment publishFragment1 = PublishFragment.getInstance(2);
        MyWaybillListFragment myWaybillListFragment2 = MyWaybillListFragment.getInstance(1);
        MyWaybillListFragment myWaybillListFragment3 = MyWaybillListFragment.getInstance(2);
        MyWaybillListFragment myWaybillListFragment4 = MyWaybillListFragment.getInstance(3);
        MyWaybillListFragment myWaybillListFragment5 = MyWaybillListFragment.getInstance(5);

        fragmentList.add(publishFragment0);
        fragmentList.add(publishFragment1);
        fragmentList.add(myWaybillListFragment2);
        fragmentList.add(myWaybillListFragment3);
        fragmentList.add(myWaybillListFragment4);
        fragmentList.add(myWaybillListFragment5);


        ViewPageLazyAdapter mViewPagerAdapter = new ViewPageLazyAdapter(getChildFragmentManager(), fragmentList, mTitles);
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

    }

    @Override
    protected void lazyLoadData() {

    }
    public void setCurrentPage(int page){
        mViewPager.setCurrentItem(page);
    }

}
