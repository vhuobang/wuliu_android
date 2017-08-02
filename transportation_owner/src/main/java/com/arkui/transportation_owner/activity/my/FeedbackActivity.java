package com.arkui.transportation_owner.activity.my;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_owner.R;

import butterknife.ButterKnife;


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
