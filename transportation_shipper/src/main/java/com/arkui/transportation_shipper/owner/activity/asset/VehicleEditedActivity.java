package com.arkui.transportation_shipper.owner.activity.asset;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkui.fz_tools.dialog.SelectPicturePicker;
import com.arkui.fz_tools.dialog.SelectTypePicker;
import com.arkui.fz_tools.listener.OnVehicleTypeClickListener;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class VehicleEditedActivity extends BaseActivity implements OnVehicleTypeClickListener {

    @BindView(R.id.tv_vehicle_model)
    TextView mTvVehicleModel;
    @BindView(R.id.iv_front)
    ImageView mIvFront;
    @BindView(R.id.iv_driving_license)
    ImageView mIvDrivingLicense;
    private SelectPicturePicker mSelectPicturePicker;
    private SelectTypePicker mSelectVehicleTypePicker;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_vehicle_edited);
        setTitle("车辆编辑");
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

    @OnClick({R.id.tv_vehicle_model, R.id.iv_front, R.id.iv_driving_license, R.id.tv_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_vehicle_model:
                mSelectVehicleTypePicker.show(getSupportFragmentManager(), "model");
                break;
            case R.id.iv_front:
                mSelectPicturePicker.show(getSupportFragmentManager(), "front");
                break;
            case R.id.iv_driving_license:
                mSelectPicturePicker.show(getSupportFragmentManager(), "license");
                break;
            case R.id.tv_complete:
                break;
        }
    }


    /**
     * 车型选择回调
     *
     * @param item
     */
    @Override
    public void OnVehicleTypeClick(String item) {
        mTvVehicleModel.setText(item);
    }
}
