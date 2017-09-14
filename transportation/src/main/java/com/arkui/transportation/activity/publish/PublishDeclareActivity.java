package com.arkui.transportation.activity.publish;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.text.TextUtils;
import android.widget.Toast;

import com.arkui.fz_net.utils.RxBus;
import com.arkui.fz_tools._interface.PublishInterface;
import com.arkui.fz_tools.dialog.ShareDialog;
import com.arkui.fz_tools.dialog.SuccessFullyShareDialog;
import com.arkui.fz_tools.entity.PublishBean;
import com.arkui.fz_tools.entity.ReleaseDetailsEntity;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.listener.OnDialogClick;
import com.arkui.fz_tools.model.NetConstants;
import com.arkui.fz_tools.mvp.PublishPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.AppManager;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.MainActivity;
import com.arkui.transportation.base.App;
import com.arkui.transportation.entity.RefreshWaybill;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.SocializeUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;


public class PublishDeclareActivity extends BaseActivity implements OnConfirmClick, OnDialogClick, PublishInterface {

    private SuccessFullyShareDialog mSuccessFullyDialog;
    private ShareDialog mShareDialog;
    private ReleaseDetailsEntity releaseInfo;
    private PublishPresenter mPublishPresenter;
    private Map<String,Object> map;
    private ProgressDialog mDialog;
    private String url;

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
        releaseInfo = (ReleaseDetailsEntity) getIntent().getSerializableExtra("releaseInfo");
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
    }

    @OnClick(R.id.tv_next)
    public void onClick() {
         map= new HashMap<>();
        map.put("user_id", App.getUserId());
        map.put("op_status",1);
        map.put("loading_address",releaseInfo.getLoadingAddress());
        map.put("unloading_address",releaseInfo.getUnloadingAddress());
        map.put("cargo_name",releaseInfo.getCargoName());
        map.put("cargo_num",releaseInfo.getCargoNum());
        map.put("cargo_density",releaseInfo.getCargoDensity());
        map.put("freight_price",releaseInfo.getFreightPrice());
        map.put("cargo_price",releaseInfo.getCargoPrice());
        map.put("loading_time",releaseInfo.getLoadingTime());
        map.put("payment_terms",releaseInfo.getPaymentTerms());
        map.put("settlement_time",releaseInfo.getSettlementTime());
        map.put("press_charges",releaseInfo.getPressCharges());
        map.put("truck_drawer",releaseInfo.getTruckDrawer());
        map.put("truck_tel",releaseInfo.getTruckTel());
        map.put("unloading_contact",releaseInfo.getUnloadingContact());
        map.put("unloading_tel",releaseInfo.getUnloadingTel());
        map.put("type",releaseInfo.getType());
        map.put("remarks",releaseInfo.getRemarks());
        map.put("unit",releaseInfo.getUnit());
       //物流id
        map.put("logistical_ids", App.getUserId());
        map.put("owner_name", releaseInfo.getOwner_name());
        map.put("owner_tel", releaseInfo.getOwner_tel());
        map.put("infomation_fee", releaseInfo.getInfomation_fee());
        map.put("log_settlement_time",releaseInfo.getLog_settlement_time());
        map.put("log_contact_name", releaseInfo.getLog_contact_name());
        map.put("log_contact_tel", releaseInfo.getLog_contact_tel());
        if (!TextUtils.isEmpty(releaseInfo.getId())){
            map.put("cargo_id",releaseInfo.getId());
        }
        mPublishPresenter.postSave(map);
    }
    @Override
    public void onConfirmClick() {
        mSuccessFullyDialog.dismiss();
        mShareDialog.show(getSupportFragmentManager(),"share");
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

    @Override
    public void onCancelClick() {
        AppManager.getAppManager().finishActivity(PublishCompleteInfoActivity.class);
        AppManager.getAppManager().finishActivity(MyDeliverActivity.class);
        Intent intent = new Intent(mActivity, MainActivity.class);
        intent.putExtra("type", 3);
        startActivity(intent);
        //发送刷新命令
        RxBus.getDefault().postSticky(new RefreshWaybill(0));
    }

    //发布成
    @Override
    public void onSuccess(PublishBean publishBean) {
        url= url+ publishBean.getId();
        mSuccessFullyDialog.show(getSupportFragmentManager(),"full");
    }

    @Override
    public void onFail(String message) {

    }
    private UMWeb mWeb;
    private RxPermissions mRxPermissions;
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
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
}
