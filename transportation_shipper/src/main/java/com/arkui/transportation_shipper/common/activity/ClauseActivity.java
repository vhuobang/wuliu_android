package com.arkui.transportation_shipper.common.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;


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
