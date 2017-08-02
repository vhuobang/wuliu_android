package com.arkui.transportation.activity.my;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.ButterKnife;


public class PointWithdrawActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_point_withdraw);
        setTitle("申请提现");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

}
