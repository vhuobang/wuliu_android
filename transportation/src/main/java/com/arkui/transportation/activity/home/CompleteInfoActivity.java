package com.arkui.transportation.activity.home;

import android.os.Handler;
import android.view.View;

import com.arkui.fz_tools.dialog.SelectTypePicker;
import com.arkui.fz_tools.dialog.SuccessFullyDialog;
import com.arkui.fz_tools.listener.OnVehicleTypeClickListener;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class CompleteInfoActivity extends BaseActivity implements OnVehicleTypeClickListener {

    private SelectTypePicker mSelectTypePicker;
    private SuccessFullyDialog mSuccessFullyDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_complete_info);
        setTitle("完善信息");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

        List<String> mEndTimeList = new ArrayList<>();
        mEndTimeList.add("立即结算");
        mEndTimeList.add("7天内结算");
        mEndTimeList.add("15天内结算");
        mEndTimeList.add("30天内结算");
        mSelectTypePicker = new SelectTypePicker();
        mSelectTypePicker.setTitle("结算时间").setData(mEndTimeList);
        mSelectTypePicker.setOnTypeClickListener(this);

        mSuccessFullyDialog = new SuccessFullyDialog();
        mSuccessFullyDialog.setTitle("发布成功");
    }

    @Override
    public void OnVehicleTypeClick(String item) {

    }

    @OnClick({R.id.tv_time, R.id.bt_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_time:
                mSelectTypePicker.show(getSupportFragmentManager(),"time");
                break;
            case R.id.bt_confirm:
                mSuccessFullyDialog.show(getSupportFragmentManager(),"full");

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSuccessFullyDialog.dismiss();
                        finish();
                    }
                },1000);
                break;
        }
    }
}
