package com.arkui.transportation_owner.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.widget.CheckBox;
import android.widget.TextView;

import com.arkui.fz_tools._interface.PrePayInterface;
import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.entity.PrePayEntity;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.mvp.BankPresenter;
import com.arkui.fz_tools.mvp.PrePayPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.base.App;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.arkui.transportation_owner.R.id.freight_price;


public class PaymentFreightActivity extends BaseActivity implements OnConfirmClick, PrePayInterface, PublicInterface {

    public String payType = "pay_freight";
    @BindView(R.id.cargo_wight)
    TextView mCargoWight;
    @BindView(R.id.cargo_price)
    TextView mCargoPrice;
    @BindView(freight_price)
    TextView mFreightPrice;
    @BindView(R.id.loss)
    TextView mLoss;
    @BindView(R.id.freight)
    TextView mFreight;
    @BindView(R.id.loss_money)
    TextView mLossMoney;
    @BindView(R.id.total_money)
    TextView mTotalMoney;
    @BindView(R.id.bt_pay)
    ShapeButton mBtPay;
    @BindView(R.id.is_checked)
    CheckBox mIsChecked;
    private CommonDialog mCommonDialog;
    private PrePayPresenter prePayPresenter;
    private String orderId;
    private PrePayEntity mPrePayEntity;
    private String orderNumber;
    private BankPresenter bankPresenter;
    private String money;

    public static void openActivity(Context context, String orderId, String orderNumber) {
        Intent intent = new Intent(context, PaymentFreightActivity.class);
        intent.putExtra("orderId", orderId);
        intent.putExtra("orderNumber", orderNumber);
        context.startActivity(intent);
    }

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_payment_freight);
        setTitle("支付运费");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        orderId = getIntent().getStringExtra("orderId");
        orderNumber = getIntent().getStringExtra("orderNumber");
        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("支付运费").setContent("运费信息确认无误，立即支付运费");
        mCommonDialog.setConfirmClick(this);
        prePayPresenter = new PrePayPresenter(this, this);
        bankPresenter = new BankPresenter(this, this);
    }

    @Override
    public void initData() {
        prePayPresenter.getPrePay(orderId);
    }

    @OnClick(R.id.bt_pay)
    public void onClick() {
        mCommonDialog.show(getSupportFragmentManager(), "pay");

    }

    @Override
    public void onConfirmClick() {
        // 选中不扣损耗
        if (mIsChecked.isChecked()) {
            money = mPrePayEntity.getFreight();
        } else {
            money = mPrePayEntity.getTotalMoney();
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("user_id", App.getUserId());
        map.put("order_number", orderNumber);
        map.put("money", money);
        map.put("fee_status", payType);
        bankPresenter.pay(map);
    }

    //请求数据成功
    @Override
    public void onSuccess(PrePayEntity entity) {
        mPrePayEntity = entity;
        mCargoWight.setText(entity.getCarrierNum() + StrUtil.formatUnit(entity.getUnit()));
        mCargoPrice.setText(entity.getCargoPrice() + StrUtil.formatMoneyUnit(entity.getUnit()));
        mFreightPrice.setText(entity.getFreightPrice() + StrUtil.formatMoneyUnit(entity.getUnit()));
        mLoss.setText(entity.getLoss() + StrUtil.formatUnit(entity.getUnit()));
        mFreight.setText(entity.getFreight() + "元");
        mLossMoney.setText(entity.getLossMoney() + "元");
        mTotalMoney.setText(entity.getTotalMoney() + "元");
    }

    // 支付成功
    @Override
    public void onSuccess() {

        FreightPaymentSucceedActivity.openActivity(mActivity, money);
        finish();
    }

    // 请求数据失败
    @Override
    public void onFail(String errMessage) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (prePayPresenter != null) {
            prePayPresenter.onDestroy();
        }
    }
}
