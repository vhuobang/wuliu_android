package com.arkui.transportation_owner.activity.publish;

import android.content.Intent;

import com.arkui.fz_net.utils.RxBus;
import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools.dialog.ShareDialog;
import com.arkui.fz_tools.dialog.SuccessFullyShareDialog;
import com.arkui.fz_tools.entity.PublishParameterEntity;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.listener.OnDialogClick;
import com.arkui.fz_tools.mvp.PublishPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.AppManager;
import com.arkui.fz_tools.utils.LogUtil;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.activity.MainActivity;
import com.arkui.transportation_owner.entity.RefreshWaybill;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * TODO 这里还缺少分享
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
        EventBus.getDefault().register(this);
        mSuccessFullyDialog = new SuccessFullyShareDialog();
        mSuccessFullyDialog.setOnConfirmClick(this);
        mShareDialog = new ShareDialog();
        mShareDialog.setOnConfirmClick(this);
    }

    @Override
    public void initData() {
        super.initData();
        mPublishPresenter = new PublishPresenter(this, this);
        /*Disposable subscribe = RxBus.getDefault().toObservableSticky(HashMap.class).subscribe(new Consumer<HashMap>() {
            @Override
            public void accept(HashMap map) throws Exception {

                RxBus.getDefault().removeStickyEvent(HashMap.class);
            }
        });

        mDisposables.add(subscribe);*/
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
        //刷新已发布的
        RxBus.getDefault().postSticky(new RefreshWaybill(2));
    }

    @Subscribe(threadMode = ThreadMode.POSTING,sticky = true)
    public void receiveParameter(PublishParameterEntity publishParameterEntity){
        mMap = publishParameterEntity.getMap();
        LogUtil.e("成功收到参数");
        //EventBus.getDefault().removeStickyEvent(PublishParameterEntity.class);
    }

    @Override
    public void onSuccess() {
        mSuccessFullyDialog.show(getSupportFragmentManager(), "full");
    }

    @Override
    public void onFail(String message) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPublishPresenter.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
