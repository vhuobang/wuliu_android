package com.arkui.transportation_shipper.owner.activity.supply;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.widget.TextView;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.dialog.SuccessFullyDialog;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.api.SupplyApi;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.common.entity.OrderEntity;
import com.arkui.transportation_shipper.common.entity.RefreshSupplyListEntity;
import com.arkui.transportation_shipper.owner.activity.my.AccountRechargeActivity;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class PayMessageFeeActivity extends BaseActivity {

    @BindView(R.id.tv_money)
    TextView mTvMoney;
    @BindView(R.id.tv_name)
    TextView mTvName;
    private CommonDialog mCommonDialog;
    private SupplyApi mSupplyApi;
    private OrderEntity mOrderEntity;
    private SuccessFullyDialog mSuccessFullyDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_pay_message_fee);
        setTitle("支付信息费");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        initDialog();
    }

    @Override
    public void initData() {
        super.initData();
        mOrderEntity = getIntent().getParcelableExtra("data");
        mTvMoney.setText(String.format("￥%s", mOrderEntity.getInfomation_fee()));
        //TODO 2017年9月5日 后台没有返回收款方 待完善
        mTvName.setText(mOrderEntity.getLogistical_name());
        mSupplyApi = RetrofitFactory.createRetrofit(SupplyApi.class);
    }

    private void initDialog() {
        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("余额不足").setContent("您的余额不足,请先充值在支付!").setConfirmText("立即充值").setNoCancel();
        mCommonDialog.setConfirmClick(new OnConfirmClick() {
            @Override
            public void onConfirmClick() {
                // ShowToast("待完善！");
                showActivity(AccountRechargeActivity.class);
            }
        });

        mSuccessFullyDialog = new SuccessFullyDialog().setTitle("抢单成功");
    }

    @OnClick(R.id.bt_start)
    public void onClick() {
        //
        //拉起支付
        if (mOrderEntity==null){
            return;
        }else {
            String infomation_fee = mOrderEntity.getInfomation_fee();
            String balance = App.getUserEntity().getBalance();
            if (Double.parseDouble(balance) < Double.parseDouble(infomation_fee)){
                mCommonDialog.show(getSupportFragmentManager(), "pay");
            }else {
              CommonDialog  mCommonDialog  = new CommonDialog();
                mCommonDialog.setTitle("确认支付");
                mCommonDialog.setContent("确认信息费无误，立即支付").setConfirmText("立即支付");
                mCommonDialog.showDialog(PayMessageFeeActivity.this,"pay");
                mCommonDialog.setConfirmClick(new OnConfirmClick() {
                    @Override
                    public void onConfirmClick() {
                        pay();
                    }
                });

            }
        }

    }

    private void pay() {
        if (mOrderEntity == null) {
            return;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", App.getUserId());
        map.put("order_number", mOrderEntity.getOrder_number());
        map.put("money", mOrderEntity.getInfomation_fee());
        map.put("fee_status", "pay_message_free");
        Observable<BaseHttpResult> observable = mSupplyApi.postPay(map);

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mActivity) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
                ShowToast(value.getMessage());
                mSuccessFullyDialog.showDialog(PayMessageFeeActivity.this, "success");
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mSuccessFullyDialog.dismiss();
                        //通知抢单列表页面刷新
                        EventBus.getDefault().post(new RefreshSupplyListEntity());
                        finish();
                    }
                }, 1000);
            }

            @Override
            public void onApiError(ApiException e) {
                //super.onApiError(e);
                mCommonDialog.showDialog(PayMessageFeeActivity.this, "pay");
            }
        });
    }

    public static void openActivity(Activity activity, OrderEntity orderEntity) {
        Intent intent = new Intent(activity, PayMessageFeeActivity.class);
        intent.putExtra("data", orderEntity);
        activity.startActivity(intent);
    }
}
