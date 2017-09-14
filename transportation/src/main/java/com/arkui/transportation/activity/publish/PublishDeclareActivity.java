package com.arkui.transportation.activity.publish;

import android.content.Intent;
import android.text.TextUtils;

import com.arkui.fz_net.utils.RxBus;
import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools.dialog.ShareDialog;
import com.arkui.fz_tools.dialog.SuccessFullyShareDialog;
import com.arkui.fz_tools.entity.ReleaseDetailsEntity;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.listener.OnDialogClick;
import com.arkui.fz_tools.mvp.PublishPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.AppManager;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.MainActivity;
import com.arkui.transportation.base.App;
import com.arkui.transportation.entity.RefreshWaybill;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class PublishDeclareActivity extends BaseActivity implements OnConfirmClick, OnDialogClick, PublicInterface {

    private SuccessFullyShareDialog mSuccessFullyDialog;
    private ShareDialog mShareDialog;
    private ReleaseDetailsEntity releaseInfo;
    private PublishPresenter mPublishPresenter;
    private Map<String,Object> map;

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
        releaseInfo = (ReleaseDetailsEntity) getIntent().getSerializableExtra("releaseInfo");
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
    public void onSuccess() {
        mSuccessFullyDialog.show(getSupportFragmentManager(),"full");
    }

    @Override
    public void onFail(String message) {

    }
}
