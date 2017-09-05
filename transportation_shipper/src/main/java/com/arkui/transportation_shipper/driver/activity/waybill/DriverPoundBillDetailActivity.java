package com.arkui.transportation_shipper.driver.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.api.DriverApi;
import com.arkui.transportation_shipper.common.entity.PoundListDetail;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class DriverPoundBillDetailActivity extends BaseActivity {


    @BindView(R.id.loading_time)
    TextView mLoadingTime;
    @BindView(R.id.loading_number)
    TextView mLoadingNumber;
    @BindView(R.id.iv_loading_pound)
    ImageView mIvLoadingPound;
    @BindView(R.id.unloading_time)
    TextView mUnloadingTime;
    @BindView(R.id.tv_unloading_weight)
    TextView mTvUnloadingWeight;
    @BindView(R.id.iv_unloading_pound)
    ImageView mIvUnloadingPound;
    private String orderId;
    private DriverApi driverApi;

    public static void openActivity(Context context, String orderId) {
        Intent intent = new Intent(context, DriverPoundBillDetailActivity.class);
        intent.putExtra("orderId", orderId);
        context.startActivity(intent);
    }


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_driver_pound_bill_detail);
        setTitle("磅单详情");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        orderId = getIntent().getStringExtra("orderId");
        driverApi = RetrofitFactory.createRetrofit(DriverApi.class);
    }

    @Override
    public void initData() {
        Observable<PoundListDetail> observable = driverApi.loadingListDetail(orderId).map(new HttpResultFunc<PoundListDetail>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<PoundListDetail>(this) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(PoundListDetail value) {
             mLoadingTime.setText(value.getLoadingTime());
                mLoadingNumber.setText(value.getLoadingWeight() + "吨");
                GlideUtils.getInstance().loadRound(mActivity,value.getLoadingPhoto(),mIvLoadingPound);
                mUnloadingTime.setText(value.getUnloadingTime());
                mTvUnloadingWeight.setText(value.getUnloadingWeight()+ "吨");
                GlideUtils.getInstance().loadRound(mActivity,value.getUnloadingPhoto(),mIvUnloadingPound);
            }

        });
    }

}
