package com.arkui.transportation_owner.activity.waybill;

import android.view.View;
import android.widget.TextView;

import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.dialog.EndTimePicker;
import com.arkui.fz_tools.dialog.SelectTypePicker;
import com.arkui.fz_tools.listener.OnVehicleTypeClickListener;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_owner.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author nmliz
 * @time 2017/6/30 13:43
 * 编辑货源
 */
public class EditPlanPublishDetailActivity extends BaseActivity implements OnVehicleTypeClickListener {

    @BindView(R.id.tv_selected_1)
    TextView mTvSelected1;
    @BindView(R.id.tv_selected_2)
    TextView mTvSelected2;
    @BindView(R.id.tv_amount)
    TextView mTvAmount;
    @BindView(R.id.tv_density)
    TextView mTvDensity;
    @BindView(R.id.tv_freight_price)
    TextView mTvFreightPrice;
    @BindView(R.id.tv_cargo_price)
    TextView mTvCargoPrice;
    @BindView(R.id.tv_end_time)
    TextView mTvEndTime;
    @BindView(R.id.tv_payment)
    TextView mTvPayment;
    private SelectTypePicker mSelectTypePicker;
    private int mType;
    private List<String> mPaymentList;
    private List<String> mEndTimeList;
    private List<String> mStringList;
    private CommonDialog mCommonDialog;
    private EndTimePicker mEndTimePicker;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_edit_plan_publish_detail);
        setTitle("货源详情");

        mSelectTypePicker = new SelectTypePicker();
        mSelectTypePicker.setTitle("单位选择");
        mStringList = new ArrayList<>();
        mStringList.add("吨");
        mStringList.add("方");
        mStringList.add("件");
        mStringList.add("趟");
        mSelectTypePicker.setData(mStringList);

        mSelectTypePicker.setOnTypeClickListener(this);

        mPaymentList = new ArrayList<>();
        mPaymentList.add("货主网上支付");
        mPaymentList.add("物流网上支付");
        mPaymentList.add("货到现金付款");

        mEndTimeList = new ArrayList<>();
        mEndTimeList.add("立即结算");
        mEndTimeList.add("7天内结算");
        mEndTimeList.add("15天内结算");
        mEndTimeList.add("30天内结算");

        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("保存信息").setContent("发货信息已保存成功！是否前往运单查看？").setConfirmText("去订单");

        mEndTimePicker = new EndTimePicker();
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mTvSelected1.setSelected(true);
    }


    @OnClick({R.id.tv_amount, R.id.tv_density, R.id.tv_freight_price, R.id.tv_cargo_price, R.id.ll_time, R.id.ll_payment, R.id.ll_end_time, R.id.editText, R.id.tv_selected_1, R.id.tv_selected_2, R.id.tv_publish})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_amount:
                mType = 1;
                mSelectTypePicker.setData(mStringList).show(getSupportFragmentManager(), "amount");
                break;
            case R.id.tv_density:
                mType = 2;
                mSelectTypePicker.setData(mStringList).show(getSupportFragmentManager(), "density");
                break;
            case R.id.tv_freight_price:
                mType = 3;
                mSelectTypePicker.setData(mStringList).show(getSupportFragmentManager(), "freight");
                break;
            case R.id.tv_cargo_price:
                mType = 4;
                mSelectTypePicker.setData(mStringList).show(getSupportFragmentManager(), "cargo");
                break;
            case R.id.ll_time:
                mType = 5;
                mEndTimePicker.show(getSupportFragmentManager(), "time");
                break;
            case R.id.ll_payment:
                mType = 6;
                mSelectTypePicker.setData(mPaymentList).show(getSupportFragmentManager(), "payment");
                break;
            case R.id.ll_end_time:
                mType = 7;
                mSelectTypePicker.setData(mEndTimeList).show(getSupportFragmentManager(), "end");
                break;
            case R.id.editText:
                break;
            case R.id.tv_selected_1:
                mTvSelected1.setSelected(true);
                mTvSelected2.setSelected(false);
                break;
            case R.id.tv_selected_2:
                mTvSelected1.setSelected(false);
                mTvSelected2.setSelected(true);
                break;
            case R.id.tv_publish:
                mCommonDialog.show(getSupportFragmentManager(), "publish");
                break;
        }
    }

    @Override
    public void OnVehicleTypeClick(String item) {
        switch (mType) {
            case 1:
                mTvAmount.setText(item);
                break;
            case 2:
                mTvDensity.setText(item);
                break;
            case 3:
                mTvFreightPrice.setText(String.format("元/%s", item));
                break;
            case 4:
                mTvCargoPrice.setText(String.format("元/%s", item));
                break;
            case 6:
                mTvPayment.setText(item);
                break;
            case 7:
                mTvEndTime.setText(item);
                break;
        }
    }
}
