package com.arkui.transportation.activity.waybill;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.ButterKnife;


public class PlanPublishDetailActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_plan_publish_detail);
        setTitle("货源详情");
        setRightIcon(R.mipmap.hz_bianji);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        showActivity(EditPlanPublishDetailActivity.class);
    }
}
