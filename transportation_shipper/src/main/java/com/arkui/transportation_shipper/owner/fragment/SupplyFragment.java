package com.arkui.transportation_shipper.owner.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.dialog.AddressPicker;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.entity.City;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.owner.activity.supply.WaybillDetailActivity;
import com.arkui.transportation_shipper.owner.adapter.CommonAdapter;
import com.arkui.transportation_shipper.owner.listener.OnBindViewHolderListener;
import com.arkui.transportation_shipper.owner.utils.LoadCityData;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
/**
 * 基于基类的Fragment
 */
public class SupplyFragment extends BaseFragment implements OnBindViewHolderListener<String>, BaseQuickAdapter.OnItemClickListener, OnRefreshListener {

    @BindView(R.id.rl_supply)
    PullRefreshRecyclerView mRlSupply;
    private CommonAdapter<String> mSupplyAdapter;
    private CommonDialog mCommonDialog;
    private AddressPicker mAddressPicker;
    private List<City> mCities;
    private Disposable mDisposable;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_supply, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        mSupplyAdapter = CommonAdapter.getInstance(R.layout.item_supply, this);
        mRlSupply.setLinearLayoutManager();
        mRlSupply.setAdapter(mSupplyAdapter);
        mRlSupply.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        onRefreshing();
        mRlSupply.setOnRefreshListener(this);
        mSupplyAdapter.setOnItemClickListener(this);
        initDialog();
    }

    private void initDialog() {
        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("支付提醒").setContent("您的账户还有未支付信息费用的订单，请先完成支付！").setConfirmText("立即支付").setNoCancel().setIsCanceledOnTouch(false);
        mCommonDialog.show(getChildFragmentManager(), "suppply");
        mAddressPicker = new AddressPicker();
    }

    @Override
    public void convert(BaseViewHolder helper, String item) {

    }

    @Override
    protected void initData() {
        super.initData();
        /*mSubscribe = Observable.just("cities.txt").map(new Func1<String, List<City>>() {
            @Override
            public List<City> call(String name) {
                //读取asset 里面的数据
                String string = FileUtil.readAssets(mContext, "cities.txt");
                mCities = JSON.parseArray(string, City.class);
                return mCities;
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<List<City>>(getActivity(), false) {
                    @Override
                    public void onNext(List<City> cityList) {
                        mAddressPicker.setCities(cityList);
                    }
                });*/

        //初始化其数据
        mDisposable = LoadCityData.initData(mContext, new Consumer<List<City>>() {
            @Override
            public void accept(List<City> cityList) throws Exception {
                mAddressPicker.setCities(cityList);
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
                mCommonDialog.show(getChildFragmentManager(), "suppply");
                //mAddressPicker.show(getChildFragmentManager(),"city");
            }
        }
    }

    public void onRefreshing() {
        new Handler().postDelayed(new Runnable() {
            public void run() {
                mSupplyAdapter.addData("车辆已装货");
                mSupplyAdapter.addData("车辆已装货");
                mRlSupply.refreshComplete();
            }
        }, 1000);
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
        onRefreshing();
    }
}
