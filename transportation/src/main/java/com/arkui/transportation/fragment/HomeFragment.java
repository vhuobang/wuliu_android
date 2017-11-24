package com.arkui.transportation.fragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.adapter.ViewPageLazyAdapter;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.ui.BaseLazyFragment;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.home.SearchLogisticsActivity;
import com.arkui.transportation.api.LogisticalApi;
import com.arkui.transportation.entity.SliderMessageEntity;
import com.arkui.transportation.view.VerticalScrolledListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 基于基类的Fragment
 */
public class HomeFragment extends BaseFragment {

    @BindView(R.id.tabLayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewPage)
    ViewPager mViewPage;

    @BindView(R.id.vertical_scrolled_list)
    VerticalScrolledListView textListView;

    private String[] mTitles = {"待发布", "已发布"};
    private LogisticalApi logisticalApi;
    private List<String> textList;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);

        logisticalApi = RetrofitFactory.createRetrofit(LogisticalApi.class);
        List<BaseLazyFragment> baseLazyFragments = new ArrayList<>();

        baseLazyFragments.add(HomePublishListFragment.getInstance(1)); // 待发布
        baseLazyFragments.add(HomePublishListFragment.getInstance(2));  // 已发布

        ViewPageLazyAdapter mViewPagerAdapter = new ViewPageLazyAdapter(getChildFragmentManager(), baseLazyFragments, mTitles);
        mViewPage.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPage);
    }

    @OnClick(R.id.iv_search)
    public void onClick() {
        showActivity(SearchLogisticsActivity.class);
    }

    @Override
    public void onPause() {
        super.onPause();
        textListView.onDestroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadMessageData();
    }

    private void loadMessageData() {
        Observable<List<SliderMessageEntity>> observable = logisticalApi.getSliderMessage().map(new HttpResultFunc<List<SliderMessageEntity>>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<SliderMessageEntity>>(mContext,false) {

            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<SliderMessageEntity> list) {
                textListView.onDestroy();
                if (textList==null){
                    textList = new ArrayList<>();
                }
                textList.clear();
                for (int i =0;i<list.size();i++){
                    textList.add(list.get(i).getContent());
                }
                textListView.setData(textList);
                textListView.startTimer();
            }

            @Override
            public void onApiError(ApiException e) {
//                super.onApiError(e);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        textListView.onDestroy();
    }
}
