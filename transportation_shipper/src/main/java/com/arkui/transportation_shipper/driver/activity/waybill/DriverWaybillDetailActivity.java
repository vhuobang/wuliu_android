package com.arkui.transportation_shipper.driver.activity.waybill;

import android.view.View;
import android.widget.TextView;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class DriverWaybillDetailActivity extends BaseActivity {

    @BindView(R.id.tv_submit)
    TextView mTvSubmit;
    private int mType;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_driver_waybill_detail);
        //setTitle("DriverWaybillDetailActivity");

    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mType = getIntent().getIntExtra("type", 0);

        switch (mType) {
            case 1:
                setTitle("待装货详情");
                break;
            case 2:
                setTitle("运输中详情");
                setRight("查看磅单");
                mTvSubmit.setText("提交卸货磅单");
                break;
            case 3:
                setTitle("已卸货详情");
                setRight("查看磅单");
                mTvSubmit.setVisibility(View.GONE);
                break;
        }
    }

    @OnClick(R.id.tv_submit)
    public void onClick() {
        if(mType==1){
            showActivity(LoadingBillActivity.class);
        }else{
            showActivity(UnloadBillActivity.class);
        }

    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        showActivity(DriverPoundBillDetailActivity.class);
    }
}
