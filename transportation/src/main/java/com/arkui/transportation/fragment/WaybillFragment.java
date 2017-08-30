package com.arkui.transportation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.view.SegmentTabLayout;
import com.arkui.transportation.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 基于基类的Fragment
 */
public class WaybillFragment extends BaseFragment {

    @BindView(R.id.tab)
    SegmentTabLayout mTab;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private String[] mTitles = {"货主运单", "我的运单"};

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_waybill, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);

        ArrayList<Fragment> baseFragmentList = new ArrayList<>();
        OwnerWaybillFragment ownerWaybillFragment=new OwnerWaybillFragment();
        MyWaybillFragment myWaybillFragment=new MyWaybillFragment();

        baseFragmentList.add(ownerWaybillFragment);
        baseFragmentList.add(myWaybillFragment);
        mTab.setTabData(mTitles,getChildFragmentManager(),R.id.fl_content,baseFragmentList);

        mTab.setCurrentTab(position);

    }
    int position;
   // 显示  0表示货主运单  1表示 我的运单
    public void setFragmentPosition(int position){
        this.position=position;
    }

}
