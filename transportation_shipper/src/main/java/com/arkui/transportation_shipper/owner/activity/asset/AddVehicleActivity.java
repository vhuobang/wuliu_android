package com.arkui.transportation_shipper.owner.activity.asset;

import android.view.View;

import com.arkui.fz_tools.dialog.SelectPicturePicker;
import com.arkui.fz_tools.dialog.SelectTypePicker;
import com.arkui.fz_tools.listener.OnVehicleTypeClickListener;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class AddVehicleActivity extends BaseActivity implements OnVehicleTypeClickListener {

    private SelectPicturePicker mSelectPicturePicker;
    private SelectTypePicker mSelectVehicleTypePicker;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_add_vehicle);
        setTitle("添加车辆");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        initDialog();
    }

    private void initDialog() {
        mSelectPicturePicker = new SelectPicturePicker();
        mSelectVehicleTypePicker = new SelectTypePicker();
        List<String> list = new ArrayList<>();
        list.add("车型一");
        list.add("车型二");
        list.add("车型三");
        list.add("车型四");
        list.add("车型五");
        mSelectVehicleTypePicker.setData(list).setTitle("车辆选择");
        mSelectVehicleTypePicker.setOnTypeClickListener(this);
    }

    @OnClick({R.id.tv_vehicle_model, R.id.tv_complete,R.id.iv_pic,R.id.iv_pic2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_vehicle_model:
                mSelectVehicleTypePicker.show(getSupportFragmentManager(),"model");
                break;
            case R.id.tv_complete:

                break;
            case R.id.iv_pic:
            case R.id.iv_pic2:
                mSelectPicturePicker.show(getSupportFragmentManager(),"vehicle");
                break;
        }
    }

    @Override
    public void OnVehicleTypeClick(String item) {

    }
}
