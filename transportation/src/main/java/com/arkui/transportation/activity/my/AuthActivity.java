package com.arkui.transportation.activity.my;

import android.support.v4.view.ViewPager;

import com.arkui.fz_tools.adapter.ViewPagerAdapter;
import com.arkui.fz_tools.listener.OnTabSelectListener;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.view.SegmentTabLayout;
import com.arkui.transportation.R;
import com.arkui.transportation.fragment.CompanyAuthFragment;
import com.arkui.transportation.fragment.PersonalAuthFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class AuthActivity extends BaseActivity {

    @BindView(R.id.tab)
    SegmentTabLayout mTab;
    @BindView(R.id.viewPager)
    ViewPager mViewPager;
    private String[] mTitles = {"个人认证", "公司认证"};
    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_auth);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

        List<BaseFragment> baseFragmentList = new ArrayList<>();
        baseFragmentList.add(new PersonalAuthFragment());
        baseFragmentList.add(new CompanyAuthFragment());

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), baseFragmentList, mTitles);

        mViewPager.setAdapter(viewPagerAdapter);
        mTab.setTabData(mTitles);
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

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }
}
