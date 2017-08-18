package com.arkui.transportation_owner.activity.publish;

import android.content.Intent;
import android.view.View;

import com.arkui.fz_net.utils.RxBus;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.ui.BaseListActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.utils.LogUtil;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.adapter.CollectLogisticsAdapter;
import com.arkui.transportation_owner.adapter.LogisticsAdapter;
import com.arkui.transportation_owner.entity.LogisticalListEntity;
import com.arkui.transportation_owner.entity.RefreshLogistics;
import com.arkui.transportation_owner.mvp.LogisticsPresenter;
import com.arkui.transportation_owner.view.LogisticsView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;


public class SelectLogisticsActivity extends BaseActivity implements LogisticsView, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener, OnRefreshListener {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private CollectLogisticsAdapter mCollectLogisticsAdapter;
    private LogisticsPresenter mLogisticsPresenter;
    private int mPage = 1;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_select_logistics);
        setTitle("选择物流公司");
        setRightIcon(R.mipmap.go_collect);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mCollectLogisticsAdapter = new CollectLogisticsAdapter();
        mRlList.setLinearLayoutManager();
        mRlList.setAdapter(mCollectLogisticsAdapter);
        mRlList.setOnRefreshListener(this);
        mRlList.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));
        mCollectLogisticsAdapter.setOnItemChildClickListener(this);
        mCollectLogisticsAdapter.setOnLoadMoreListener(this, mRlList.getRecyclerView());

        mRlList.getRecyclerView().getItemAnimator().setChangeDuration(0);
    }

    @Override
    public void initData() {
        super.initData();
        mLogisticsPresenter = new LogisticsPresenter(this, this);
        //获取我的收藏的物流公司
        getNetData();

        Disposable subscribe = RxBus.getDefault().toObservable(RefreshLogistics.class).subscribe(new Consumer<RefreshLogistics>() {
            @Override
            public void accept(RefreshLogistics refreshLogistics) throws Exception {
                LogUtil.e("收到刷新命令");
                if(refreshLogistics.getType()==101){
                    mRlList.starLoad();
                    mPage = 1;
                    mLogisticsPresenter.postCollectionLogisticsList(mPage);

                }
            }
        });

        mDisposables.add(subscribe);
    }

    private void getNetData() {
        mLogisticsPresenter.postCollectionLogisticsList(mPage);
    }

    @OnClick(R.id.tv_publish)
    public void onClick() {
        StringBuilder ids = new StringBuilder();
        for (LogisticalListEntity logisticalListEntity : mCollectLogisticsAdapter.getData()) {
            if (logisticalListEntity.isCheck()) {
                ids.append(logisticalListEntity.getId()).append(",");
            }
            if (ids.length() > 0) {
                ids.deleteCharAt(ids.length() - 1);
            }
        }

        if(ids.length()==0){
            ShowToast("请选择物流公司");
            return;
        }

        Intent intent=new Intent(mActivity,PublishDeclareActivity.class);
        intent.putExtra("ids",ids.toString());
        startActivity(intent);
    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        showActivity(LogisticsListActivity.class);
    }

    @Override
    public void onSucceed(List<LogisticalListEntity> logisticalList) {
        if (mPage == 1) {
            mCollectLogisticsAdapter.setNewData(logisticalList);
            mRlList.refreshComplete();
            mCollectLogisticsAdapter.setEnableLoadMore(logisticalList.size() == 20);
        } else {
            mCollectLogisticsAdapter.addData(logisticalList);
            mCollectLogisticsAdapter.loadMoreComplete();
        }
        mPage += 1;
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        mCollectLogisticsAdapter.getItem(position).setCheck(!mCollectLogisticsAdapter.getItem(position).isCheck());
        mCollectLogisticsAdapter.notifyItemChanged(position);
    }

    @Override
    public void onLoadMoreRequested() {
        mLogisticsPresenter.postCollectionLogisticsList(mPage);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPage = 1;
        mLogisticsPresenter.postCollectionLogisticsList(mPage);
    }

    @Override
    public void onError() {
        if (mPage == 1) {
            mRlList.loadFail();
        } else {
            mCollectLogisticsAdapter.loadMoreEnd();
        }
        mRlList.refreshComplete();
    }

    @Override
    public void onSucceed(LogisticalListEntity logisticalDetails) {

    }

    @Override
    public void onSucceed(int position) {

    }
}
