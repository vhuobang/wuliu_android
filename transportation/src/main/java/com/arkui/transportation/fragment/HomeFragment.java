package com.arkui.transportation.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.adapter.ViewPageLazyAdapter;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.ui.BaseLazyFragment;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.home.SearchLogisticsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 基于基类的Fragment
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPage)
    ViewPager mViewPage;
    private String[] mTitles = {"待发布", "已发布"};

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);

        List<BaseLazyFragment> baseLazyFragments = new ArrayList<>();

        baseLazyFragments.add(HomePublishListFragment.getInstance(0)); // 待发布
        baseLazyFragments.add(HomePublishListFragment.getInstance(1));  // 已发布

        ViewPageLazyAdapter mViewPagerAdapter = new ViewPageLazyAdapter(getChildFragmentManager(), baseLazyFragments, mTitles);
        mViewPage.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPage);

    }

    @OnClick(R.id.iv_search)
    public void onClick() {
        showActivity(SearchLogisticsActivity.class);
    }
}
