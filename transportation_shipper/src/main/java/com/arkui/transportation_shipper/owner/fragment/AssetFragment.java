package com.arkui.transportation_shipper.owner.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.arkui.fz_tools.pop.BubblePopupWindow;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.utils.ToastUtil;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.owner.activity.asset.AddDriverActivity;
import com.arkui.transportation_shipper.owner.activity.asset.AddVehicleActivity;
import com.arkui.transportation_shipper.owner.adapter.ViewPagerAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 基于基类的Fragment
 */
public class AssetFragment extends BaseFragment implements View.OnClickListener {

    @BindView(R.id.iv_add)
    ImageView mIvAdd;
    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;

    private String[] mTitles = {"车辆管理", "司机管理"};
    private BubblePopupWindow mRightPopupWindow;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_asset, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);

        VehicleManageFragment vehicleManageFragment = new VehicleManageFragment();
        DriverManageFragment driverManageFragment = new DriverManageFragment();

        List<BaseFragment> fragmentList = new ArrayList<>();
        fragmentList.add(vehicleManageFragment);
        fragmentList.add(driverManageFragment);

        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), fragmentList, mTitles);

        mViewPager.setAdapter(mViewPagerAdapter);

        mTabLayout.setupWithViewPager(mViewPager);

        initPopWindows();
    }

    private void initPopWindows() {
        mRightPopupWindow = new BubblePopupWindow(mContext);
        View popWindowView = LayoutInflater.from(mContext).inflate(R.layout.layout_asset_add, null);
        popWindowView.findViewById(R.id.tv_add_vehicle).setOnClickListener(this);
        popWindowView.findViewById(R.id.tv_add_driver).setOnClickListener(this);
        mRightPopupWindow.setBubbleView(popWindowView);
    }

    @OnClick(R.id.iv_add)
    public void onClick() {
        mRightPopupWindow.show(mIvAdd, Gravity.BOTTOM, mRightPopupWindow.getMeasuredWidth() - ToastUtil.dp2px(mContext, 25));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_add_vehicle:
                showActivity(AddVehicleActivity.class);
                break;
            case R.id.tv_add_driver:
                showActivity(AddDriverActivity.class);
                break;
        }
        mRightPopupWindow.dismiss();
    }
}
