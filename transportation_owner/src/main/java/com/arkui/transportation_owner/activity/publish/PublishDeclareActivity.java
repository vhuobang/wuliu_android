package com.arkui.transportation_owner.activity.publish;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import com.arkui.fz_net.utils.RxBus;
import com.arkui.fz_tools._interface.PublishInterface;
import com.arkui.fz_tools.dialog.ShareDialog;
import com.arkui.fz_tools.dialog.SuccessFullyShareDialog;
import com.arkui.fz_tools.entity.PublishBean;
import com.arkui.fz_tools.entity.PublishParameterEntity;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.listener.OnDialogClick;
import com.arkui.fz_tools.model.NetConstants;
import com.arkui.fz_tools.mvp.PublishPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.AppManager;
import com.arkui.fz_tools.utils.LogUtil;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.activity.MainActivity;
import com.arkui.transportation_owner.entity.RefreshWaybill;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.SocializeUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * TODO 这里还缺少分享
 */
public class PublishDeclareActivity extends BaseActivity implements OnConfirmClick, OnDialogClick, PublishInterface {

    private SuccessFullyShareDialog mSuccessFullyDialog;
    private ShareDialog mShareDialog;
    private PublishPresenter mPublishPresenter;
    private Map<String,Object> mMap;
    private ProgressDialog mDialog;
    private String url;
    private UMWeb mWeb;
    private RxPermissions mRxPermissions;
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

        mRxPermissions=new RxPermissions(mActivity);
        mDialog = new ProgressDialog(this);

        url = NetConstants.SHARE+"?cargo_id=" ;

        // TODO 没权限 不能分享QQ 也不知道为什么哦
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mRxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean aBoolean) throws Exception {
                    if(!aBoolean){
                        Toast.makeText(mActivity, "没有存储权限，QQ无法正常分享哦，建议你允许！", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
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
    public void onConfirmClick(String type) {
        mWeb = new UMWeb(url);
        mWeb.setTitle("危货帮");
        mWeb.setThumb(new UMImage(this, com.arkui.fz_tools.R.mipmap.about_logo));
        mWeb.setDescription("一个好用的app");
        switch (type){
            case "wx":
                showShare("wx");
                break;
            case "qq":
                showShare("qq");
                break;
            case "pyq":
                showShare("pyq");
                break;
        }
    }
    public void showShare(String type){

        switch (type){
            case "qq":
                new ShareAction(mActivity)
                        .withText("this is title")
                        .withMedia(mWeb)
                        .setPlatform(SHARE_MEDIA.QQ)
                        .setCallback(mShareListener).share();
                break;
            case "wx":
                new ShareAction(mActivity)
                        .withText("this is title")
                        .withMedia(mWeb)
                        .setPlatform(SHARE_MEDIA.WEIXIN)
                        .setCallback(mShareListener).share();
                break;
            case "pyq":
                new ShareAction(mActivity)
                        .withText("this is title")
                        .withMedia(mWeb)
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setCallback(mShareListener).share();
                break;
        }
    }

    private UMShareListener mShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(mDialog);
        }
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(mActivity, "成功了", Toast.LENGTH_LONG).show();
            SocializeUtils.safeCloseDialog(mDialog);
        }
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            SocializeUtils.safeCloseDialog(mDialog);
            Toast.makeText(mActivity, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            SocializeUtils.safeCloseDialog(mDialog);
            Toast.makeText(mActivity, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
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
    public void onSuccess(PublishBean publishBean) {

        url= url+ publishBean.getId();
        mSuccessFullyDialog.show(getSupportFragmentManager(),"full");
    }

    @Override
    public void onFail(String message) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPublishPresenter.onDestroy();
        EventBus.getDefault().unregister(this);
        UMShareAPI.get(this).release();
    }
}
