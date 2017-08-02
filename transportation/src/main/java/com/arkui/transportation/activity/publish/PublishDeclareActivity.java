package com.arkui.transportation.activity.publish;

import com.arkui.fz_tools.dialog.ShareDialog;
import com.arkui.fz_tools.dialog.SuccessFullyShareDialog;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class PublishDeclareActivity extends BaseActivity implements OnConfirmClick {

    private SuccessFullyShareDialog mSuccessFullyDialog;
    private ShareDialog mShareDialog;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_publish_declare);
        setTitle("发布声明");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

        mSuccessFullyDialog = new SuccessFullyShareDialog();
        mSuccessFullyDialog.setOnConfirmClick(this);
        mShareDialog = new ShareDialog();
    }

    @OnClick(R.id.tv_next)
    public void onClick() {
        mSuccessFullyDialog.show(getSupportFragmentManager(),"full");
    }


    @Override
    public void onConfirmClick() {
        mSuccessFullyDialog.dismiss();
        mShareDialog.show(getSupportFragmentManager(),"share");
    }
}
