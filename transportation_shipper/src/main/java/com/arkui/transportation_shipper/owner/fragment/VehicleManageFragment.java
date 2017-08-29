package com.arkui.transportation_shipper.owner.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.utils.LogUtil;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.api.AssetApi;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.common.entity.RefreshAssetListEntity;
import com.arkui.transportation_shipper.common.entity.TruckListEntity;
import com.arkui.transportation_shipper.owner.activity.asset.VehicleDetailsActivity;
import com.arkui.transportation_shipper.owner.adapter.VehicleManageAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
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
 * 车辆管理
 */
public class VehicleManageFragment extends BaseFragment implements  BaseQuickAdapter.OnItemClickListener, OnRefreshListener {

    @BindView(R.id.rl_vehicle)
    PullRefreshRecyclerView mRlVehicle;
    private VehicleManageAdapter mVehicleManageAdapter;
    private AssetApi mAssetApi;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_vehicle_manage, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        mRlVehicle.setLinearLayoutManager();
        mVehicleManageAdapter = new VehicleManageAdapter();
        mRlVehicle.setAdapter(mVehicleManageAdapter);
        mVehicleManageAdapter.setOnItemClickListener(this);

        mRlVehicle.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        mRlVehicle.setOnRefreshListener(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void initData() {
        super.initData();
        mAssetApi = RetrofitFactory.createRetrofit(AssetApi.class);
        getNetData();
    }

    private void getNetData() {
        Observable<List<TruckListEntity>> observable = mAssetApi.postTruckList(App.getUserId()).map(new HttpResultFunc<List<TruckListEntity>>());

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<TruckListEntity>>(mContext,false) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<TruckListEntity> value) {
                mVehicleManageAdapter.setNewData(value);
                mRlVehicle.refreshComplete();
            }

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
                mRlVehicle.loadFail(e.getMessage());
                mRlVehicle.refreshComplete();
            }
        });
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        showActivity(VehicleDetailsActivity.class);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        //onRefreshing();
        getNetData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onRefresh(RefreshAssetListEntity refreshAssetListEntity){
        if(refreshAssetListEntity.getType()==1){
            LogUtil.e("收到刷新指令");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
