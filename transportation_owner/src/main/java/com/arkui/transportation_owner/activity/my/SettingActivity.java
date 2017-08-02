package com.arkui.transportation_owner.activity.my;

import android.view.View;

import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_owner.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class SettingActivity extends BaseActivity {
    private CommonDialog mOutDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_setting);
        setTitle("设置");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mOutDialog = new CommonDialog();
        mOutDialog.setTitle("退出登录").setContent("确定退出当前账户吗？");
    }

    @OnClick({R.id.tl_about, R.id.tr_feedback, R.id.bt_out})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tl_about:
                showActivity(AboutActivity.class);
                break;
            case R.id.tr_feedback:
                showActivity(FeedbackActivity.class);
                break;
            case R.id.bt_out:
                mOutDialog.show(getSupportFragmentManager(),"out");
                break;
        }
    }
}
