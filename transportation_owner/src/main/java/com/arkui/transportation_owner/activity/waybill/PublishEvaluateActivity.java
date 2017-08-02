package com.arkui.transportation_owner.activity.waybill;

import android.os.Handler;

import com.arkui.fz_tools.dialog.SuccessFullyDialog;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_owner.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class PublishEvaluateActivity extends BaseActivity {

    private SuccessFullyDialog mSuccessFullyDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_publish_evaluate);
        setTitle("发布评价");

        mSuccessFullyDialog = new SuccessFullyDialog();
        mSuccessFullyDialog.setTitle("发布成功");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.bt_publish)
    public void onClick() {
        mSuccessFullyDialog.show(getSupportFragmentManager(),"publish");

        new Handler().postDelayed(new Runnable(){
            public void run() {
                mSuccessFullyDialog.dismiss();
                finish();
            }
        }, 1000);
    }
}
