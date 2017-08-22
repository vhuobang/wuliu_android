package com.arkui.transportation.activity.waybill;

import android.view.View;
import android.widget.TextView;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
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


public class CarriageDetailActivity extends BaseActivity implements  OnBindViewHolderListener<PublishDetialEntity.CarrierInfoBean>,OnRefreshListener {

    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
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
        mRlList.setLinearLayoutManager();
        mRlList.addItemDecoration(new DividerItemDecoration2(mActivity, DividerItemDecoration2.VERTICAL_LIST));

        mCarriageDetailAdapter = new CommonAdapter<>(R.layout.item_carriage_detail, this);

        mRlList.setOnRefreshListener(this);
        mRlList.setAdapter(mCarriageDetailAdapter);

        View mCarriageHeaderView = getLayoutInflater().inflate(R.layout.layout_carriage_header, mRlList, false);
        mCarriageDetailAdapter.setHeaderView(mCarriageHeaderView);
        mLoadingAddress = (TextView) mCarriageHeaderView.findViewById(R.id.tv_loading_address);
        mDetailAddress = (TextView)   mCarriageHeaderView.findViewById(R.id.tv_detail_address);
        mUnloadingAddress = (TextView)   mCarriageHeaderView.findViewById(R.id.tv_unloading_address);
        mDetailUnloading = (TextView)   mCarriageHeaderView.findViewById(R.id.tv_detail_unloading);
        mProductInfo = (TextView)   mCarriageHeaderView.findViewById(R.id.tv_product_info);
        mSurplusNum = (TextView)   mCarriageHeaderView.findViewById(R.id.surplus_num);

        onRefreshing();

        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("停止抢单").setContent("该运单还剩20吨没有被抢，确定要停止发布吗？");

        logisticalApi = RetrofitFactory.createRetrofit(LogisticalApi.class);
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
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                mCarriageDetailAdapter.addData(ListData.getTestData(""));
//                mRlList.refreshComplete();
//            }
//        }, 1000);
        Observable<PublishDetialEntity> observable = logisticalApi.getPublishDetails(carGoId, App.getUserId())
                .map(new HttpResultFunc<PublishDetialEntity>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<PublishDetialEntity>(mActivity) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(PublishDetialEntity entity) {
                String[] loadingAddress = entity.getLoadingAddress().split(" ");
                String[] unloadingAddress = entity.getUnloadingAddress().split(" ");
                mLoadingAddress.setText(loadingAddress[0]);
                mUnloadingAddress.setText(unloadingAddress[0]);
               mDetailAddress.setText(loadingAddress[1]);
                mDetailUnloading.setText(unloadingAddress[1]);
                mProductInfo.setText(entity.getCargoName()+ " " + entity.getCargoNum()+ StrUtil.formatUnit(entity.getUnit()));
                mSurplusNum.setText("剩余："+entity.getSurplusNum()+ StrUtil.formatUnit(entity.getUnit()));
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
        mCommonDialog.show(getSupportFragmentManager(),"state");
    }

    @Override
    public void convert(BaseViewHolder helper, PublishDetialEntity.CarrierInfoBean item) {
          // helper.setText(R.id.tv_logistics_name,item.getLogistical())
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        onRefreshing();
    }
}
