package com.arkui.transportation_shipper.driver.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.ui.BaseLazyFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.api.DriverApi;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.common.entity.DriverOrderListEntity;
import com.arkui.transportation_shipper.driver.activity.waybill.DriverWaybillDetailActivity;
import com.arkui.transportation_shipper.owner.adapter.CommonAdapter;
import com.arkui.transportation_shipper.owner.listener.OnBindViewHolderListener;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 基于基类的Fragment
 *
 * 司机端运单列表
 */

public class DriverWaybillListFragment extends BaseLazyFragment implements OnBindViewHolderListener<DriverOrderListEntity>,OnRefreshListener {

    @BindView(R.id.rl_waybill)
    PullRefreshRecyclerView mRlWaybill;
    private CommonAdapter<DriverOrderListEntity> mWaybillListAdapter;
    private DriverApi driverApi;
    private int type;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_waybill_list, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        mWaybillListAdapter = CommonAdapter.getInstance(R.layout.item_driver_waybill, this);
        mRlWaybill.setOnRefreshListener(this);
        mRlWaybill.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        mRlWaybill.setLinearLayoutManager();
        mRlWaybill.setAdapter(mWaybillListAdapter);
        type = getArguments().getInt("type");

        driverApi = RetrofitFactory.createRetrofit(DriverApi.class);

        mWaybillListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                DriverOrderListEntity item = (DriverOrderListEntity) adapter.getItem(position);
                DriverWaybillDetailActivity.openActivity(getActivity(),type,item.getId());
            }
        });

    }

    @Override
    public void convert(BaseViewHolder helper, DriverOrderListEntity item) {
        String[] loading_address = item.getLoadingAddress().split(" ");
        String[] unloadingAddress = item.getUnloadingAddress().split(" ");

        if (type==1){
            helper.setText(R.id.cargo_info,item.getLicensePlate()+ " " + item.getCargoName() + "预装"+item.getCarrierNum()+ "吨");
        }else {
            helper.setText(R.id.cargo_info,item.getLicensePlate()+ " " + item.getCargoName() );
        }
        helper.setText(R.id.loading_address,loading_address[0]);
        helper.setText(R.id.unloading_address,unloadingAddress[0]);

    }

    public void onRefreshing() {
        Observable<List<DriverOrderListEntity>> observable = driverApi.getCargoList(App.getUserId(), String.valueOf(type)).map(new HttpResultFunc<List<DriverOrderListEntity>>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<DriverOrderListEntity>>(getActivity(),false) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<DriverOrderListEntity> value) {
                mRlWaybill.refreshComplete();
                mWaybillListAdapter.setNewData(value);
            }

            @Override
            public void onApiError(ApiException e) {
              //  super.onApiError(e);
                mRlWaybill.refreshComplete();
                mRlWaybill.loadFail();
            }
        });
    }

    @Override
    protected void lazyLoadData() {
        onRefreshing();
    }

    public static DriverWaybillListFragment getInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        DriverWaybillListFragment waybillListFragment = new DriverWaybillListFragment();
        waybillListFragment.setArguments(bundle);
        return waybillListFragment;
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        onRefreshing();
    }
}
