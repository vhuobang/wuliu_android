package com.arkui.transportation_owner.activity.user;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_owner.R;

import butterknife.ButterKnife;


public class ClauseActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_clause);
        setTitle("免责声明");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

}
