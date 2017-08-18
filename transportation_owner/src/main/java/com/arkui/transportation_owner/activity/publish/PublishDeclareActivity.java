package com.arkui.transportation_owner.activity.publish;

import android.content.Intent;

import com.arkui.fz_net.utils.RxBus;
import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools.dialog.ShareDialog;
import com.arkui.fz_tools.dialog.SuccessFullyDialog;
import com.arkui.fz_tools.dialog.SuccessFullyShareDialog;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.listener.OnDialogClick;
import com.arkui.fz_tools.mvp.PublishPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.AppManager;
import com.arkui.fz_tools.utils.LogUtil;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.activity.MainActivity;
import com.arkui.transportation_owner.entity.RefreshWaybill;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * TODO 这里还却分享
 */
public class PublishDeclareActivity extends BaseActivity implements OnConfirmClick, PublicInterface, OnDialogClick {

    private SuccessFullyShareDialog mSuccessFullyDialog;
    private ShareDialog mShareDialog;
    private PublishPresenter mPublishPresenter;
    private Map<String,Object> mMap;

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
        mShareDialog.setOnConfirmClick(this);
    }

    @Override
    public void initData() {
        super.initData();
        mPublishPresenter = new PublishPresenter(this, this);
        RxBus.getDefault().toObservableSticky(HashMap.class).subscribe(new Consumer<HashMap>() {
            @Override
            public void accept(HashMap map) throws Exception {
                mMap=map;
                LogUtil.e("收到参数");
                RxBus.getDefault().removeStickyEvent(HashMap.class);
            }
        });
    }

    @OnClick(R.id.tv_next)
    public void onClick() {
        if(mMap==null){
            return;
        }
        String ids = getIntent().getStringExtra("ids");
        mMap.put("logistical_ids", ids);
        mPublishPresenter.postSave(mMap);
    }


    @Override
    public void onConfirmClick() {
        mSuccessFullyDialog.dismiss();
        mShareDialog.show(getSupportFragmentManager(), "share");
    }

    @Override
    public void onCancelClick() {
        AppManager.getAppManager().finishActivity(SelectLogisticsActivity.class);
        AppManager.getAppManager().finishActivity(MyDeliverActivity.class);

        Intent intent = new Intent(mActivity, MainActivity.class);
        intent.putExtra("type", 3);
        startActivity(intent);
        //发送刷新命令
        RxBus.getDefault().postSticky(new RefreshWaybill(0));
    }

    @Override
    public void onSuccess() {
        mSuccessFullyDialog.show(getSupportFragmentManager(), "full");
    }

    @Override
    public void onFail(String message) {

    }
}
