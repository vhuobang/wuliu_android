package com.arkui.transportation_shipper.owner.activity.my;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;


public class FeedbackActivity extends BaseActivity {



    @Override
    public void setRootView() {
        setContentView(R.layout.activity_feedback);
        setTitle("意见反馈");
        setRight("提交");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        finish();
    }
}
