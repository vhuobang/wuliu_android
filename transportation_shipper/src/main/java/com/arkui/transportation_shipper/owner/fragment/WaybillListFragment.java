package com.arkui.transportation_shipper.owner.fragment;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.WaybillListInterface;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.entity.LogWayBIllListEntity;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.mvp.WaybillListPresenter;
import com.arkui.fz_tools.ui.BaseLazyFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.driver.event.LoadEvent;
import com.arkui.transportation_shipper.owner.activity.waybill.DriverLocationActivity;
import com.arkui.transportation_shipper.owner.activity.waybill.WaybillListDetailActivity;
import com.arkui.transportation_shipper.owner.adapter.CommonAdapter;
import com.arkui.transportation_shipper.owner.api.LogisticalApi;
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
 * 基于基类的Fragment]
 * 车主订单列表
 */

public class WaybillListFragment extends BaseLazyFragment implements OnBindViewHolderListener<LogWayBIllListEntity>, OnRefreshListener, WaybillListInterface, OnConfirmClick {

    @BindView(R.id.rl_waybill)
    PullRefreshRecyclerView mRlWaybill;
    private CommonAdapter<LogWayBIllListEntity> mWaybillListAdapter;
    private int mType;
    WaybillListPresenter waybillListPresenter;
    private LogisticalApi logisticalApi;
    private CommonDialog commonDialog;
    private LogWayBIllListEntity item;

    // 提供实例
    public static WaybillListFragment getInstance(int type) {
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        WaybillListFragment waybillListFragment = new WaybillListFragment();
        waybillListFragment.setArguments(bundle);
        return waybillListFragment;
    }


    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_waybill_list, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        commonDialog = new CommonDialog();
        commonDialog.setTitle("删除订单");
        commonDialog.setContent("信息费将不会退还！确定删除订单？");
        commonDialog.setConfirmClick(this);
        mWaybillListAdapter = CommonAdapter.getInstance(R.layout.item_waybill, this);
        mRlWaybill.setOnRefreshListener(this);
        mRlWaybill.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));
        mRlWaybill.setLinearLayoutManager();
        mRlWaybill.setAdapter(mWaybillListAdapter);
        waybillListPresenter = new WaybillListPresenter(this, getActivity());
        logisticalApi = RetrofitFactory.createRetrofit(LogisticalApi.class);
        mType = getArguments().getInt("type");

        mWaybillListAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                LogWayBIllListEntity item = (LogWayBIllListEntity) adapter.getItem(position);
                WaybillListDetailActivity.openActivity(getActivity(), item.getOwnerStatus(), item.getId());
            }
        });

        //待装货删除订单
        if (mType==1){
            mWaybillListAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                    commonDialog.showDialog(getActivity(),"order");
                    item = (LogWayBIllListEntity) adapter.getItem(position);

                    return true;
                }
            });
        }

        mWaybillListAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                LogWayBIllListEntity item = (LogWayBIllListEntity) adapter.getItem(position);
                if(TextUtils.isEmpty(item.getLog())){
                    Toast.makeText(mContext, "暂无司机位置", Toast.LENGTH_SHORT).show();
                    return;
                }
                DriverLocationActivity.openActivity(getActivity(), item.getLog(), item.getLat());
            }
        });

        EventBus.getDefault().register(this);
    }
    //删除一单
    private void deleteOrder(LogWayBIllListEntity item) {

        Observable<BaseHttpResult> observable = logisticalApi.getChargeBack(item.getId());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(getActivity()) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
                  //删除成功
                onRefreshing();
            }

            @Override
            public void onApiError(ApiException e) {

            }
        });
    }

    @Override
    public void convert(BaseViewHolder helper, LogWayBIllListEntity item) {
        helper.setText(R.id.tv_cargo_info, item.getLicensePlate() + "   " +item.getTruckName()+ " " + item.getCargoName());
        String[] loadingAddress = item.getLoadingAddress().split(" ");
        String[] unloadingAddress = item.getUnloadingAddress().split(" ");
        helper.setVisible(R.id.pay_name,false);
        helper.setText(R.id.tv_loading_address, loadingAddress[0]);
        helper.setText(R.id.tv_unloading_address, unloadingAddress[0]);
        helper.setText(R.id.tv_company, item.getLogName());
        switch (mType) {
            case 1:
                helper.setText(R.id.tv_cargo_info, item.getLicensePlate() + "   " + item.getTruckName() + " " + item.getCargoName()
                        + "  预装" + item.getCarrierNum() + StrUtil.formatUnit(item.getUnit()));

                break;
            case 4:
                helper.setVisible(R.id.ll_location, false);
                helper.setVisible(R.id.pay_name,true);
                helper.setText(R.id.pay_name,"付款方："+item.getPayeeName());
                break;
            case 5:
                helper.setVisible(R.id.ll_location, false);
               ;
                break;
        }
        helper.addOnClickListener(R.id.ll_location);
    }

    /**
     * 加载数据
     */
    public void onRefreshing() {
        waybillListPresenter.getWaybillList(App.getUserId(), String.valueOf(mType));
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mType==1){
            onRefreshing();
        }
    }

    @Override
    protected void lazyLoadData() {
        onRefreshing();
    }

    //  下拉刷新
    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        onRefreshing();
    }

    //请求数据成功
    @Override
    public void onSuccess(List<LogWayBIllListEntity> logWayBIllListEntityList) {
        mWaybillListAdapter.setNewData(logWayBIllListEntityList);
        mRlWaybill.refreshComplete();
    }

    // 请求数据失败
    @Override
    public void onError(String errorMessage) {
        mRlWaybill.refreshComplete();
        mRlWaybill.loadFail();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshList(LoadEvent loadEvent){
        onRefreshing();
    }

    @Override
    public void onConfirmClick() {
        deleteOrder(item);
    }
}
