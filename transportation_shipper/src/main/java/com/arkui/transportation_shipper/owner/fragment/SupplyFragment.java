package com.arkui.transportation_shipper.owner.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.dialog.AddressPicker;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.entity.City;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.api.SupplyApi;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.common.entity.OrderEntity;
import com.arkui.transportation_shipper.common.entity.RefreshSupplyListEntity;
import com.arkui.transportation_shipper.common.entity.SupplyListEntity;
import com.arkui.transportation_shipper.owner.activity.supply.PayMessageFeeActivity;
import com.arkui.transportation_shipper.owner.activity.supply.WaybillDetailActivity;
import com.arkui.transportation_shipper.owner.adapter.SupplyAdapter;
import com.arkui.transportation_shipper.owner.utils.LoadCityData;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
public class SupplyFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, OnRefreshListener, AddressPicker.OnEnsureClickListener, OnConfirmClick, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.rl_supply)
    PullRefreshRecyclerView mRlSupply;
    @BindView(R.id.tv_start)
    TextView mTvStart;
    @BindView(R.id.tv_end)
    TextView mTvEnd;
    private SupplyAdapter mSupplyAdapter;
    private CommonDialog mCommonDialog;
    private AddressPicker mAddressPicker;
    private List<City> mCities;
    private Disposable mDisposable;
    private int mPage = 1;
    private SupplyApi mSupplyApi;
    private String mLoadingAddress = "";
    private String mUnloadingAddress = "";
    private int mType;
    private OrderEntity mOrderEntity;

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
        mSupplyAdapter.setOnLoadMoreListener(this,mRlSupply.getRecyclerView());
        initDialog();
        EventBus.getDefault().register(this);
    }

    private void initDialog() {
        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("支付提醒").setContent("您的账户还有未支付信息费用的订单，请先完成支付！").setConfirmText("立即支付");
        mCommonDialog.setConfirmClick(this);
        mAddressPicker = new AddressPicker();

        mAddressPicker.setOnEnsureClickListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        //获取有没有未支付信息费
        if (!isHidden()){
            isPayFee();
        }

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

       // isPayFee();
    }

    private void getNetData() {
        Map<String, Object> parameter = new HashMap<>();
        parameter.put("loading_address", mLoadingAddress);
        parameter.put("unloading_address", mUnloadingAddress);
        parameter.put("page", mPage);
        parameter.put("pagesize", 20);
        Observable<List<SupplyListEntity>> observable = mSupplyApi.postSupplyList(parameter).map(new HttpResultFunc<List<SupplyListEntity>>());

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<SupplyListEntity>>(mContext, false) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<SupplyListEntity> value) {
                if (mPage == 1) {
                    mSupplyAdapter.setNewData(value);
                    mRlSupply.refreshComplete();
                    mSupplyAdapter.setEnableLoadMore(value.size() == 20);
                } else {
                    mSupplyAdapter.addData(value);
                    mSupplyAdapter.loadMoreComplete();
                }
                mPage += 1;
            }

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
                if (mPage == 1) {
                    mRlSupply.refreshComplete();
                    mRlSupply.loadFail();
                } else {
                    mSupplyAdapter.loadMoreEnd();
                }
            }
        });
    }

    private void isPayFee() {

        Observable<List<OrderEntity>> observable = mSupplyApi.postIsSettle(App.getUserId()).map(new HttpResultFunc<List<OrderEntity>>());

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<OrderEntity>>(mContext,false) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<OrderEntity> value) {
                if (!value.isEmpty()) {
                    mOrderEntity = value.get(0);
                    mCommonDialog.showDialog(getActivity(), "supply");
                }
            }

            @Override
            public void onApiError(ApiException e) {
                //super.onApiError(e);
                mOrderEntity=null;
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mDisposable != null)
            mDisposable.dispose();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        Log.e("fz", "onHiddenChanged" + hidden);
        if (!hidden) {
            if (mCommonDialog != null) {
                /*if(mOrderEntity!=null){
                    mCommonDialog.showDialog(getActivity(), "supply");
                }*/
                isPayFee();
                //mAddressPicker.show(getChildFragmentManager(),"city");
            }
        }
    }

    @OnClick({R.id.tv_start, R.id.tv_end})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_start:
                mType = 1;

                mAddressPicker.show(getChildFragmentManager(), "start");
               // mAddressPicker.tv_reset.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_end:
                mType = 2;
                mAddressPicker.show(getChildFragmentManager(), "end");
              //  mAddressPicker.tv_reset.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        if (mOrderEntity==null){
            SupplyListEntity item = (SupplyListEntity) adapter.getItem(position);
            WaybillDetailActivity.openActivity(getActivity(), item.getId());
        }else {
            mCommonDialog.showDialog(getActivity(), "supply");
        }

    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        mPage = 1;
        getNetData();
    }
  // TODO 全国的处理
    @Override
    public void onCityClick(String city) {
        if(city.equals("全国")){
            if (mType == 1) {
                mLoadingAddress = "";
                mTvStart.setText("全国");
            } else {
                mUnloadingAddress = "";
                mTvEnd.setText("全国");
            }
            mPage = 1;
            getNetData();
        }else {
//            String[] split = city.split("-");
//            if (split.length < 2) {
//                //ShowToast("请选择");
//                Toast.makeText(mContext, "请选择正确地址", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            String cityStr = null;
//
//            if ("北京".equals(split[0]) || "上海".equals(split[0]) || "天津".equals(split[0]) || "重庆".equals(split[0])) {
//                cityStr = split[0];
//            } else {
//                cityStr = split[1];
//            }

            if (mType == 1) {
                mLoadingAddress = city;
                mTvStart.setText(city);
            } else {
                mUnloadingAddress = city;
                mTvEnd.setText(city);
            }
            mPage = 1;
            getNetData();
        }

    }

    @Override
    public void onConfirmClick() {
        if (mOrderEntity == null)
            return;
        PayMessageFeeActivity.openActivity(getActivity(), mOrderEntity);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshSupplyList(RefreshSupplyListEntity refreshSupplyListEntity){
        mPage=1;
        getNetData();
        isPayFee();
    }

    @Override
    public void onLoadMoreRequested() {
        mPage++;
        getNetData();
    }
}
