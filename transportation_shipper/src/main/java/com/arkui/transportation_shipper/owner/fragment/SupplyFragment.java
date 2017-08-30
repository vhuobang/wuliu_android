package com.arkui.transportation_shipper.owner.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.dialog.AddressPicker;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.entity.City;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.api.SupplyApi;
import com.arkui.transportation_shipper.common.entity.SupplyListEntity;
import com.arkui.transportation_shipper.owner.activity.supply.WaybillDetailActivity;
import com.arkui.transportation_shipper.owner.adapter.CommonAdapter;
import com.arkui.transportation_shipper.owner.adapter.SupplyAdapter;
import com.arkui.transportation_shipper.owner.listener.OnBindViewHolderListener;
import com.arkui.transportation_shipper.owner.utils.LoadCityData;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
/**
 * 基于基类的Fragment
 * TODO 货源页面
 */
public class SupplyFragment extends BaseFragment implements OnBindViewHolderListener<String>, BaseQuickAdapter.OnItemClickListener, OnRefreshListener {

    @BindView(R.id.rl_supply)
    PullRefreshRecyclerView mRlSupply;
    private SupplyAdapter mSupplyAdapter;
    private CommonDialog mCommonDialog;
    private AddressPicker mAddressPicker;
    private List<City> mCities;
    private Disposable mDisposable;
    private int mPage=1;
    private SupplyApi mSupplyApi;
    private String mLoadingAddress="";
    private String mUnloadingAddress="";
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_supply, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        mSupplyAdapter = new SupplyAdapter();
        mRlSupply.setLinearLayoutManager();
        mRlSupply.setAdapter(mSupplyAdapter);
        mRlSupply.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        mRlSupply.setOnRefreshListener(this);
        mSupplyAdapter.setOnItemClickListener(this);
        initDialog();
    }

    private void initDialog() {
        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("支付提醒").setContent("您的账户还有未支付信息费用的订单，请先完成支付！").setConfirmText("立即支付").setNoCancel().setIsCanceledOnTouch(false);
        mCommonDialog.show(getChildFragmentManager(), "supply");
        mAddressPicker = new AddressPicker();
    }

    @Override
    public void convert(BaseViewHolder helper, String item) {

    }

    @Override
    protected void initData() {
        super.initData();
        //初始化其数据
        mDisposable = LoadCityData.initData(mContext, new Consumer<List<City>>() {
            @Override
            public void accept(List<City> cityList) throws Exception {
                mAddressPicker.setCities(cityList);
            }

        });

        mSupplyApi = RetrofitFactory.createRetrofit(SupplyApi.class);
        //获取货源数据
        getNetData();
    }

    private void getNetData() {
        Map<String,Object> parameter=new HashMap<>();
        parameter.put("loading_address",mLoadingAddress);
        parameter.put("unloading_address",mUnloadingAddress);
        parameter.put("page",mPage);
        parameter.put("pagesize",20);
        Observable<List<SupplyListEntity>> observable = mSupplyApi.postSupplyList(parameter).map(new HttpResultFunc<List<SupplyListEntity>>());

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<SupplyListEntity>>(mContext,false) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<SupplyListEntity> value) {
                mSupplyAdapter.setNewData(value);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null)
            mDisposable.dispose();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("fz", "onHiddenChanged" + hidden);
        if (!hidden) {
            if (mCommonDialog != null) {
                mCommonDialog.show(getChildFragmentManager(), "supply");
                //mAddressPicker.show(getChildFragmentManager(),"city");
            }
        }
    }

    @OnClick({R.id.tv_start, R.id.tv_end})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_start:
                mAddressPicker.show(getChildFragmentManager(), "start");
                break;
            case R.id.tv_end:
                mAddressPicker.show(getChildFragmentManager(), "end");
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        showActivity(WaybillDetailActivity.class);
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {

    }
}
