package com.arkui.transportation.activity.waybill;

import android.view.View;
import android.widget.TextView;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration2;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation.R;
import com.arkui.transportation.api.LogisticalApi;
import com.arkui.transportation.base.App;
import com.arkui.transportation.entity.PublishDetialEntity;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class CarriageDetailActivity extends BaseActivity implements OnBindViewHolderListener<PublishDetialEntity.CarrierInfoBean>, OnRefreshListener, OnConfirmClick {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    @BindView(R.id.tv_state)
    TextView tvState;
    private CommonAdapter<PublishDetialEntity.CarrierInfoBean> mCarriageDetailAdapter;
    private CommonDialog mCommonDialog;
    private LogisticalApi logisticalApi;
    private String carGoId;
    private TextView mLoadingAddress;
    private TextView mDetailAddress;
    private TextView mUnloadingAddress;
    private TextView mDetailUnloading;
    private TextView mProductInfo;
    private TextView mSurplusNum;
    private PublishDetialEntity mPublishDetialEntity;
    private String cStatus;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_carriage_detail);
        setTitle("承运详情");
        setRight("再来一单");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        carGoId = getIntent().getStringExtra("carGoId");
        cStatus = getIntent().getStringExtra("c_status");
        tvState.setText(StrUtil.formatCStatus(cStatus));
        statuClickable();
        mRlList.setLinearLayoutManager();
        mRlList.addItemDecoration(new DividerItemDecoration2(mActivity, DividerItemDecoration2.VERTICAL_LIST));

        mCarriageDetailAdapter = new CommonAdapter<>(R.layout.item_carriage_detail, this);

        mRlList.setOnRefreshListener(this);
        mRlList.setAdapter(mCarriageDetailAdapter);

        View mCarriageHeaderView = getLayoutInflater().inflate(R.layout.layout_carriage_header, mRlList, false);
        mCarriageDetailAdapter.setHeaderView(mCarriageHeaderView);
        mLoadingAddress = (TextView) mCarriageHeaderView.findViewById(R.id.tv_loading_address);
        mDetailAddress = (TextView) mCarriageHeaderView.findViewById(R.id.tv_detail_address);
        mUnloadingAddress = (TextView) mCarriageHeaderView.findViewById(R.id.tv_unloading_address);
        mDetailUnloading = (TextView) mCarriageHeaderView.findViewById(R.id.tv_detail_unloading);
        mProductInfo = (TextView) mCarriageHeaderView.findViewById(R.id.tv_product_info);
        mSurplusNum = (TextView) mCarriageHeaderView.findViewById(R.id.surplus_num);

        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("停止抢单").setContent("确定要停止发布吗？");
        mCommonDialog.setConfirmClick(this);
        logisticalApi = RetrofitFactory.createRetrofit(LogisticalApi.class);
        onRefreshing();
    }

    private void statuClickable() {
        switch (cStatus){
            case "1": //发布中
                tvState.setClickable(true);
                break;
            case "2":// 已抢完
                tvState.setClickable(false);
                break;
            case "3"://已停止
                tvState.setClickable(false);
                break;
        }
    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        showActivity(EditPlanPublishDetailActivity.class);
    }

    /**
     * 请求数据
     */
    public void onRefreshing() {

        Observable<PublishDetialEntity> observable = logisticalApi.getPublishDetails(carGoId, App.getUserId())
                .map(new HttpResultFunc<PublishDetialEntity>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<PublishDetialEntity>(mActivity) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(PublishDetialEntity entity) {
                mPublishDetialEntity = entity;
                mRlList.refreshComplete();
                String[] loadingAddress = entity.getLoadingAddress().split(" ");
                String[] unloadingAddress = entity.getUnloadingAddress().split(" ");
                mLoadingAddress.setText(loadingAddress[0]);
                mUnloadingAddress.setText(unloadingAddress[0]);
                mDetailAddress.setText(loadingAddress[1]);
                mDetailUnloading.setText(unloadingAddress[1]);
                mProductInfo.setText(entity.getCargoName() + " " + entity.getCargoNum() + StrUtil.formatUnit(entity.getUnit()));
                mSurplusNum.setText("剩余：" + entity.getSurplusNum() + StrUtil.formatUnit(entity.getUnit()));
                List<PublishDetialEntity.CarrierInfoBean> carrierInfo = entity.getCarrierInfo();
                mCarriageDetailAdapter.setNewData(carrierInfo);
            }

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
            }
        });
    }

    @OnClick(R.id.tv_state)
    public void onClick() {
        mCommonDialog.show(getSupportFragmentManager(), "state");
    }

    @Override
    public void convert(BaseViewHolder helper, PublishDetialEntity.CarrierInfoBean item) {
        helper.setText(R.id.license_plate, item.getLicensePlate());
        helper.setText(R.id.product, mPublishDetialEntity.getCargoName());
        helper.setText(R.id.tv_product_number, item.getCarrierNum() + StrUtil.formatUnit(mPublishDetialEntity.getUnit()));
        String[] loadingAddress = mPublishDetialEntity.getLoadingAddress().split(" ");
        String[] unloadingAddress = mPublishDetialEntity.getUnloadingAddress().split(" ");
        helper.setText(R.id.tv_loading_address, loadingAddress[0]);
        helper.setText(R.id.tv_unloading_address, unloadingAddress[0]);
        helper.setText(R.id.tv_status, item.getLogistical());
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        onRefreshing();
    }

    // 确定按钮
    @Override
    public void onConfirmClick() {
        Observable<BaseHttpResult> observable = logisticalApi.upCargoStatus(carGoId);
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mActivity) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
              tvState.setText("已停止");
                tvState.setClickable(false);
            }

            @Override
            public void onApiError(ApiException e) {
                super.onApiError(e);
            }
        });
    }
}
