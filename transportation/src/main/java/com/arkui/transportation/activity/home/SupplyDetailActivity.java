package com.arkui.transportation.activity.home;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.transportation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SupplyDetailActivity extends BaseActivity {

    @BindView(R.id.bt_start)
    ShapeButton mBtStart;
    private int mType;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_supply_detail);
        setTitle("货源详情");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mType = getIntent().getIntExtra("type", -1);

        if (mType == 1) {
            mBtStart.setText("立即发布");
        }else {
            mBtStart.setText("承运详情");
        }
    }

    @OnClick(R.id.bt_start)
    public void onClick() {
        if(mType==1){
            showActivity(CompleteInfoActivity.class);
        }else{
            showActivity(CarriageListActivity.class);
        }
    }
}
