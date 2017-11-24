package com.arkui.transportation.activity.home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.transportation.R;
import com.arkui.transportation.api.LogisticalApi;
import com.arkui.transportation.entity.LogisticalDetailEntity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class SupplyDetailActivity extends BaseActivity implements OnConfirmClick {

    @BindView(R.id.bt_start)
    ShapeButton mBtStart;
    @BindView(R.id.tv_start_address)
    TextView tvStartAddress;
    @BindView(R.id.tv_start_detail_address)
    TextView tvStartDetailAddress;
    @BindView(R.id.tv_destination)
    TextView tvDestination;
    @BindView(R.id.tv_detail_destination)
    TextView tvDetailDestination;
    @BindView(R.id.goods_info)
    TextView goodsInfo;
    @BindView(R.id.cargo_density)
    TextView cargoDensity;
    @BindView(R.id.freight_price)
    TextView freightPrice;
    @BindView(R.id.cargo_price)
    TextView cargoPrice;
    @BindView(R.id.loading_time)
    TextView loadingTime;
    @BindView(R.id.payment_terms)
    TextView paymentTerms;
    @BindView(R.id.settlement_time)
    TextView settlementTime;
    @BindView(R.id.press_charges)
    TextView pressCharges;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.truck_drawer)
    TextView truckDrawer;
    @BindView(R.id.truck_tel)
    TextView truckTel;
    @BindView(R.id.unloading_contact)
    TextView unloadingContact;
    @BindView(R.id.unloading_tel)
    TextView unloadingTel;
    @BindView(R.id.remarks)
    TextView remarks;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_user_info)
    TextView tvUserInfo;
    @BindView(R.id.tl_ratingBar)
    RatingBar tlRatingBar;
    @BindView(R.id.iv_phone)
    ImageView ivPhone;
    private int mType;
    private String cargo_id;
    private LogisticalApi logisticalApi;
    private CommonDialog commonDialog;
    private LogisticalDetailEntity entity;
    private String paymentTerms1;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_supply_detail);
        setTitle("货源详情");
    }
  public static  void openActivity(Context context,int type,String  cargo_id){
      Intent intent = new Intent(context, SupplyDetailActivity.class);
      intent.putExtra("type",type);
      intent.putExtra("cargo_id",cargo_id);
      context.startActivity(intent);
  }
    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
         mType = getIntent().getIntExtra("type", -1);
         cargo_id = getIntent().getStringExtra("cargo_id");
        logisticalApi = RetrofitFactory.createRetrofit(LogisticalApi.class);
        if (mType == 1) {
            mBtStart.setText("立即发布");
        } else {
            mBtStart.setText("承运详情");
        }
        commonDialog = new CommonDialog();
        commonDialog.setTitle("拨打电话");
        commonDialog.setConfirmText("打电话");
        commonDialog.setConfirmClick(this);
    }

    // 请求数据
    @Override
    public void initData() {
        loadData();
    }

    private void loadData() {

        Observable<LogisticalDetailEntity> observable = logisticalApi.getLogisticalDetails(cargo_id).map(new HttpResultFunc<LogisticalDetailEntity>());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<LogisticalDetailEntity>(mActivity) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(LogisticalDetailEntity logisticalDetailEntity) {
                setUiData(logisticalDetailEntity);
            }

            @Override
            public void onApiError(ApiException e) {
            //   super.onApiError(e);
            }
        });
    }

    /**
     * 设置ui数据
     *
     * @param logisticalDetailEntity
     */
    private void setUiData(LogisticalDetailEntity logisticalDetailEntity) {
        entity = logisticalDetailEntity;
        String[] loadAddress = logisticalDetailEntity.getLoadingAddress().split(" ");
        String[] unloadingAddress = logisticalDetailEntity.getUnloadingAddress().split(" ");
        tvStartAddress.setText(loadAddress[0]);
        tvDestination.setText(unloadingAddress[0]);
        if (loadAddress.length>1){
            tvStartDetailAddress.setText(loadAddress[1]);
        }
        if (unloadingAddress.length>1){
            tvDetailDestination.setText(unloadingAddress[1]);
        }
        GlideUtils.getInstance().loadRound(this,entity.getLogo(),ivHeader);
        goodsInfo.setText(logisticalDetailEntity.getCargoName()+"/"+logisticalDetailEntity.getCargoNum()+ StrUtil.formatUnit( logisticalDetailEntity.getUnit())+"/剩余"+
        logisticalDetailEntity.getSurplusNum() + StrUtil.formatUnit(logisticalDetailEntity.getUnit()));
        cargoDensity.setText(logisticalDetailEntity.getCargoDensity() + "方/吨");
        freightPrice.setText(logisticalDetailEntity.getFreightPrice());
        cargoPrice.setText(logisticalDetailEntity.getCargoPrice());
        loadingTime.setText(logisticalDetailEntity.getLoadingTime());
        // 1.货主2。物流3.货到付款
        paymentTerms1 = logisticalDetailEntity.getPaymentTerms();
        if (paymentTerms1.equals("1")){
            this.paymentTerms.setText("货主网上支付");
        }else if (paymentTerms1.equals("2")){
            this.paymentTerms.setText("物流网上支付");
        }else if (paymentTerms1.equals("3")){
            this.paymentTerms.setText("货到付款");
        }
        String settlementTime = logisticalDetailEntity.getSettlementTime();
        if (settlementTime.equals("1")){
            this.settlementTime.setText("立即支付");
        }else if (settlementTime.equals("2")){
            this.settlementTime.setText("7天内支付");
        }else if (settlementTime.equals("3")){
            this.settlementTime.setText("15天内支付");
        }else {
            this.settlementTime.setText("30天内支付");
        }
        pressCharges.setText(logisticalDetailEntity.getPressCharges()+"元");
        truckDrawer.setText(logisticalDetailEntity.getTruckDrawer());
        truckTel.setText(logisticalDetailEntity.getTruckTel());
        unloadingContact.setText(logisticalDetailEntity.getUnloadingContact());
        unloadingTel.setText(logisticalDetailEntity.getUnloadingTel());
        remarks.setText(logisticalDetailEntity.getRemarks());
        tvName.setText(logisticalDetailEntity.getName());

        tvUserInfo.setText("注册"+logisticalDetailEntity.getRegisterYear()+"年"+ "  " + "发货"+logisticalDetailEntity.getSendNum()+"次");
        tlRatingBar.setRating(Float.parseFloat(logisticalDetailEntity.getStarRating()));

    }

    @OnClick({R.id.bt_start,R.id.iv_phone})
    public void onClick(View view) {
      switch (view.getId()){
          case R.id.bt_start:
              if (mType == 1) {
                  CompleteInfoActivity.openCompleteInfoActivity(SupplyDetailActivity.this,cargo_id,paymentTerms1);
              } else {
                  CarriageListActivity.openCarriageListActivity(SupplyDetailActivity.this,cargo_id);
              }
              break;
          case R.id.iv_phone:
              commonDialog.setContent(entity.getTel());
              commonDialog.showDialog(SupplyDetailActivity.this,"phone");
              break;
      }

    }

    @Override
    public void onConfirmClick() {
        String phoneNumber = commonDialog.getContent();
        Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent2);
    }
}
