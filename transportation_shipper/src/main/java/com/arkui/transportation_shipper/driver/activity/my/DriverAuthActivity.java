package com.arkui.transportation_shipper.driver.activity.my;

import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class DriverAuthActivity extends BaseActivity {

    private CommonDialog mCommonDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_driver_auth);
        setTitle("用户认证");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("个人认证").setContent("个人认证信息已提交到后台进行审核，请耐心等待！").setNoCancel();
    }

    @OnClick(R.id.bt_submit)
    public void onClick() {
        mCommonDialog.show(getSupportFragmentManager(),"auth");
    }
}
