package com.arkui.transportation_shipper.owner.activity.supply;

import android.os.Handler;
import android.view.View;

import com.arkui.fz_tools.dialog.SelectTypePicker;
import com.arkui.fz_tools.dialog.SuccessFullyDialog;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class ConfirmOrderActivity extends BaseActivity {

    private SelectTypePicker mSelectVehicleTypePicker;
    private List<String> list;
    private List<String> list2;
    private SuccessFullyDialog mSuccessFullyDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_confirm_order);
        setTitle("确认订单");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        initDialog();
    }

    private void initDialog() {
        mSelectVehicleTypePicker = new SelectTypePicker();
        list = new ArrayList<>();
        list.add("京D2455X");
        list.add("蒙S25478");
        list.add("京H78542");
        list.add("京Y57431");
        list2 = new ArrayList<>();
        list2.add("张少华");
        list2.add("陈东海");
        list2.add("成金龙");
        list2.add("森宝玉");

        mSuccessFullyDialog = new SuccessFullyDialog();
    }

    @OnClick({R.id.ll_vehicle, R.id.ll_driver})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_vehicle:
                mSelectVehicleTypePicker.setData(list).setTitle("车辆选择").show(getSupportFragmentManager(), "vehicle");
                break;
            case R.id.ll_driver:
                mSelectVehicleTypePicker.setData(list2).setTitle("司机选择").show(getSupportFragmentManager(), "driver");
                break;
        }
    }

    @OnClick(R.id.tv_complete)
    public void onClick() {
        mSuccessFullyDialog.show(getSupportFragmentManager(),"success");
        new Handler().postDelayed(new Runnable(){
            public void run() {
                mSuccessFullyDialog.dismiss();
                showActivity(PayMessageFeeActivity.class);
            }
        }, 1000);
    }
}
