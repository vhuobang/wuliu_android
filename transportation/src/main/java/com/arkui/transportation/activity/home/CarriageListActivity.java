package com.arkui.transportation.activity.home;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.ui.BaseListActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.waybill.WaybillDetailActivity;
import com.arkui.transportation.api.LogisticalApi;
import com.arkui.transportation.entity.CargoCarrierListEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class CarriageListActivity extends BaseListActivity<CargoCarrierListEntity> {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private CommonAdapter<CargoCarrierListEntity> mCommonAdapter;
    private String cargoId;
    private LogisticalApi mLogisticalApi;

    public static void openCarriageListActivity(Context context, String cargoId) {
        Intent intent = new Intent(context, CarriageListActivity.class);
        intent.putExtra("cargoId", cargoId);
        context.startActivity(intent);
    }

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_carriage_list);
        setTitle("承运详情");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        cargoId = getIntent().getStringExtra("cargoId");
        mLogisticalApi = RetrofitFactory.createRetrofit(LogisticalApi.class);
        mCommonAdapter = initAdapter(mRlList, R.layout.item_carriage_list);
        mRlList.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));
        mCommonAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CargoCarrierListEntity item = (CargoCarrierListEntity) adapter.getItem(position);
                int logisticalStatus =Integer.valueOf(item.getLogisticalStatus());
                WaybillDetailActivity.openActivity(CarriageListActivity.this,logisticalStatus,false,item.getId());
            }
        });
    }

    @Override
    public void initData() {
        Observable<List<CargoCarrierListEntity>> observable = mLogisticalApi.getCargoCarrierList(cargoId).map(new HttpResultFunc<List<CargoCarrierListEntity>>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<CargoCarrierListEntity>>(CarriageListActivity.this) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<CargoCarrierListEntity> carrierListEntityList) {
                mCommonAdapter.setNewData(carrierListEntityList);
                mRlList.refreshComplete();
            }

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
                mRlList.loadFail();
                mRlList.refreshComplete();
            }
        });
    }

    @Override
    public void convert(BaseViewHolder helper, CargoCarrierListEntity item) {
        helper.setText(R.id.tv_info, item.getLicensePlate() + item.getCargoName() +" "+
                item.getCarrierNum()+ StrUtil.formatUnit(item.getUnit()));
        helper.setText(R.id.tv_loading_address,StrUtil.splitAddress(item.getLoadingAddress()) );
        helper.setText(R.id.tv_unloading_address, StrUtil.splitAddress(item.getUnloadingAddress()));

        String logisticalStatus = item.getLogisticalStatus();
        // 1、待装货；2、运输中；3、待付款；4、待收款；5、已完成
        if ("1".equals(logisticalStatus)) {
            helper.setText(R.id.tv_type, "待装货");
        } else if ("2".equals(logisticalStatus)) {
            helper.setText(R.id.tv_type, "运输中");
        } else if ("3".equals(logisticalStatus)) {
            helper.setText(R.id.tv_type, "待付款");
        } else if ("4".equals(logisticalStatus)) {
            helper.setText(R.id.tv_type, "待收款");
        } else if ("5".equals(logisticalStatus)) {
            helper.setText(R.id.tv_type, "已完成");
        }

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        initData();
    }


}
