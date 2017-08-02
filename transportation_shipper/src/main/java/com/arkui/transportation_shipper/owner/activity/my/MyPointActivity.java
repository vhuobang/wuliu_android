package com.arkui.transportation_shipper.owner.activity.my;

import android.view.View;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.owner.activity.my.PointWithdrawActivity;
import com.arkui.transportation_shipper.owner.activity.my.WithdrawRecordActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyPointActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_my_point);
       /* setTitle("我的积分");
        setRightIcon(R.mipmap.jifen_tixian);*/
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.bt_withdraw, R.id.iv_back,R.id.iv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_withdraw:
                showActivity(PointWithdrawActivity.class);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_right:
                showActivity(WithdrawRecordActivity.class);
                break;
        }

    }

   /* @Override
    protected void onRightClick() {
        super.onRightClick();
        showActivity(WithdrawRecordActivity.class);
    }*/
}
