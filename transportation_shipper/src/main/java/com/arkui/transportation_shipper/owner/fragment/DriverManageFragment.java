package com.arkui.transportation_shipper.owner.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.utils.LogUtil;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_shipper.R;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.transportation_shipper.common.api.AssetApi;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.common.entity.DriverListEntity;
import com.arkui.transportation_shipper.common.entity.RefreshAssetListEntity;
import com.arkui.transportation_shipper.owner.activity.asset.DriverDetailActivity;
import com.arkui.transportation_shipper.owner.adapter.DriverManageAdapter;
import com.arkui.transportation_shipper.owner.listener.OnBindViewHolderListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 基于基类的Fragment
 * 司机管理
 */
public class DriverManageFragment extends BaseFragment implements  BaseQuickAdapter.OnItemClickListener,OnRefreshListener {

    @BindView(R.id.rl_driver)
    PullRefreshRecyclerView mRlVehicle;
    private DriverManageAdapter mDriverManageAdapter;
    private AssetApi mAssetApi;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_driver_manage, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);

        mRlVehicle.setLinearLayoutManager();
        mDriverManageAdapter = new DriverManageAdapter();
        mRlVehicle.setAdapter(mDriverManageAdapter);

        mRlVehicle.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        mRlVehicle.setOnRefreshListener(this);

        mDriverManageAdapter.setOnItemClickListener(this);
        EventBus.getDefault().register(this);
    }


    @Override
    protected void initData() {
        super.initData();
        mAssetApi = RetrofitFactory.createRetrofit(AssetApi.class);
        getNetData();
    }

    private void getNetData() {
        Observable<List<DriverListEntity>> observable = mAssetApi.postDriverList(App.getUserId()).map(new HttpResultFunc<List<DriverListEntity>>());

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<DriverListEntity>>(mContext) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<DriverListEntity> value) {
                mDriverManageAdapter.setNewData(value);
            }
        });
    }

    /**
     * 单击事件
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        showActivity(DriverDetailActivity.class);
    }



    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        getNetData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefresh(RefreshAssetListEntity refreshAssetListEntity){
        if(refreshAssetListEntity.getType()==2){
            LogUtil.e("收到刷新指令");
            getNetData();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
