package com.arkui.transportation_owner.activity.logistics;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_owner.R;


public class PersonageDetailActivity extends BaseActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_personage_detail);
        setTitle("张三丰");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

}
