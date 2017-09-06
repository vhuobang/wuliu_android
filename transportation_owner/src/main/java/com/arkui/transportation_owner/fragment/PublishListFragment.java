package com.arkui.transportation_owner.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_net.utils.RxBus;
import com.arkui.fz_tools._interface.CarGoListInterface;
import com.arkui.fz_tools.entity.CarGoListEntity;
import com.arkui.fz_tools.mvp.CarGoListPresenter;
import com.arkui.fz_tools.ui.BaseLazyFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.utils.LogUtil;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.activity.waybill.CarriageDetailActivity;
import com.arkui.transportation_owner.activity.waybill.PlanPublishDetailActivity;
import com.arkui.transportation_owner.adapter.PublishAdapter;
import com.arkui.transportation_owner.base.App;
import com.arkui.transportation_owner.entity.RefreshWaybill;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 基于基类的Fragment
 * 预发布 已发布
 */
public class PublishListFragment extends BaseLazyFragment implements OnRefreshListener, CarGoListInterface, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private int mType;
    private CarGoListPresenter mCargoListPresenter;
    private int mPage = 1;
    private PublishAdapter mPublishAdapter;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_waybill_list, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        mRlList.setLinearLayoutManager();
        mRlList.setOnRefreshListener(this);
        mType = getArguments().getInt("type");
        initPublishAdapter();
        mRlList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
    }

    private void initPublishAdapter() {
        mPublishAdapter = new PublishAdapter();
        mRlList.setAdapter(mPublishAdapter);
        mPublishAdapter.setOnLoadMoreListener(this, mRlList.getRecyclerView());
        mPublishAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                String id = mPublishAdapter.getItem(position).getId();

                String cStatus = mPublishAdapter.getItem(position).getCStatus();
                switch (mType) {
                    case 1:
                        PlanPublishDetailActivity.showActivity(mActivity, id);
                        break;
                    case 2:
                        CarriageDetailActivity.showActivity(mContext, id,cStatus);
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mCargoListPresenter = new CarGoListPresenter(this, getActivity());
        Disposable subscribe = RxBus.getDefault().toObservableSticky(RefreshWaybill.class).subscribe(new Consumer<RefreshWaybill>() {
            @Override
            public void accept(RefreshWaybill refreshWaybill) throws Exception {
                LogUtil.e("收到刷新指令！");
                mPage = 1;
                /*switch (refreshWaybill.getType()) {
                    case 1:
                        mCargoListPresenter.getCarGoList(App.getUserId(), "0", mPage, 20);
                        break;
                    case 2:
                        mCargoListPresenter.getCarGoList(App.getUserId(), "1", mPage, 20);
                        break;
                }*/
                mCargoListPresenter.getCarGoList(App.getUserId(), getType(), mPage, 20);
                RxBus.getDefault().removeStickyEvent(RefreshWaybill.class);
            }
        });

        mDisposables.add(subscribe);
    }

    @Override
    protected void lazyLoadData() {
        mCargoListPresenter.getCarGoList(App.getUserId(), getType(), mPage, 20);
    }

    /**
     * @param type 决定已发布还是未发布
     * @return
     */
    public static PublishListFragment getInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        PublishListFragment waybillListFragment = new PublishListFragment();
        waybillListFragment.setArguments(bundle);
        return waybillListFragment;
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPage = 1;
        mCargoListPresenter.getCarGoList(App.getUserId(), getType(), mPage, 20);
    }

    @Override
    public void onCarGoListSuccess(List<CarGoListEntity> carGoListEntityList) {
        if (mPage == 1) {
            mPublishAdapter.setNewData(carGoListEntityList);
            mRlList.refreshComplete();
            mPublishAdapter.setEnableLoadMore(carGoListEntityList.size() == 20);
        } else {
            mPublishAdapter.addData(carGoListEntityList);
            mPublishAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onCarGoListFail(String errorMessage) {
        if (mPage == 1) {
            mRlList.loadFail();
        } else {
            mPublishAdapter.loadMoreEnd();
        }
        mRlList.refreshComplete();
    }

    //请求未发布和已发布
    private String getType() {
        return mType == 1 ? "0" : "1";
    }

    @Override
    public void onLoadMoreRequested() {
        mCargoListPresenter.getCarGoList(App.getUserId(), getType(), mPage, 20);
    }
}
