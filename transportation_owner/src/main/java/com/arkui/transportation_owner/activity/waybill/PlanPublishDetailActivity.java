package com.arkui.transportation_owner.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.widget.TextView;

import com.arkui.fz_tools._interface.ReleaseDetailInterface;
import com.arkui.fz_tools.entity.PublishParameterEntity;
import com.arkui.fz_tools.entity.ReleaseDetailsEntity;
import com.arkui.fz_tools.mvp.ReleaseDetailPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.activity.publish.SelectLogisticsActivity;
import com.arkui.transportation_owner.base.App;
import com.arkui.transportation_owner.entity.EditEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PlanPublishDetailActivity extends BaseActivity implements ReleaseDetailInterface {

    @BindView(R.id.tv_loading_address)
    TextView mTvLoadingAddress;
    @BindView(R.id.tv_loading_detail_address)
    TextView mTvLoadingDetailAddress;
    @BindView(R.id.tv_unloading_address)
    TextView mTvUnloadingAddress;
    @BindView(R.id.tv_unloading_detail_address)
    TextView mTvUnloadingDetailAddress;
    @BindView(R.id.tv_cargo_name)
    TextView mTvCargoName;
    @BindView(R.id.tv_cargo_density)
    TextView mTvCargoDensity;
    @BindView(R.id.tv_freight_price)
    TextView mTvFreightPrice;
    @BindView(R.id.tv_cargo_price)
    TextView mTvCargoPrice;
    @BindView(R.id.tv_loading_time)
    TextView mTvLoadingTime;
    @BindView(R.id.tv_payment_terms)
    TextView mTvPaymentTerms;
    @BindView(R.id.tv_settlement_time)
    TextView mTvSettlementTime;
    @BindView(R.id.tv_press_charges)
    TextView mTvPressCharges;
    @BindView(R.id.tv_truck_drawer)
    TextView mTvTruckDrawer;
    @BindView(R.id.tv_truck_tel)
    TextView mTvTruckTel;
    @BindView(R.id.tv_unloading_contact)
    TextView mTvUnloadingContact;
    @BindView(R.id.tv_unloading_tel)
    TextView mTvUnloadingTel;
    @BindView(R.id.tv_remarks)
    TextView mTvRemarks;
    private ReleaseDetailPresenter mReleaseDetailPresenter;
    ReleaseDetailsEntity mReleaseDetailsEntity;
    private String id;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_plan_publish_detail);
        setTitle("货源详情");
        setRightIcon(R.mipmap.hz_bianji);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initData() {
        super.initData();
        id = getIntent().getStringExtra("id");
        mReleaseDetailPresenter = new ReleaseDetailPresenter(this, this);
        mReleaseDetailPresenter.getReleaseDetail(id);
    }

    @Override
    protected void onRightClick() {
        super.onRightClick();
        String id = getIntent().getStringExtra("id");
        //showActivity(EditPlanPublishDetailActivity.class);
        EditPlanPublishDetailActivity.showActivity(this, id,false);
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public  void refresh(EditEvent event){
            mReleaseDetailPresenter.getReleaseDetail(id);

    }

    @Override
    public void onSuccess(ReleaseDetailsEntity releaseDetailsEntity) {
        mReleaseDetailsEntity=releaseDetailsEntity;
        String[] loadingAddress = releaseDetailsEntity.getLoadingAddress().split(" ");
        String[] unloadingAddress = releaseDetailsEntity.getUnloadingAddress().split(" ");

        mTvLoadingAddress.setText(loadingAddress.length >= 0 ? loadingAddress[0] : "");
        mTvLoadingDetailAddress.setText(loadingAddress.length >= 2 ? loadingAddress[1] : "");
        mTvUnloadingAddress.setText(unloadingAddress.length >= 0 ? unloadingAddress[0] : "");
        mTvUnloadingDetailAddress.setText(unloadingAddress.length >= 2 ? unloadingAddress[1] : "");

        mTvCargoName.setText(releaseDetailsEntity.getCargoName());
        mTvCargoDensity.setText(releaseDetailsEntity.getCargoDensity() +"方/吨");
        mTvFreightPrice.setText(releaseDetailsEntity.getFreightPrice());
        mTvCargoPrice.setText(releaseDetailsEntity.getCargoPrice());
        mTvLoadingTime.setText(releaseDetailsEntity.getLoadingTime());
        mTvPressCharges.setText(releaseDetailsEntity.getPressCharges()+ "元/天");

        mTvPaymentTerms.setText(StrUtil.formatPayMent(releaseDetailsEntity.getPaymentTerms()));
        mTvSettlementTime.setText(StrUtil.formatSettlementTime(releaseDetailsEntity.getSettlementTime()));

        mTvTruckDrawer.setText(releaseDetailsEntity.getTruckDrawer());
        mTvTruckTel.setText(releaseDetailsEntity.getTruckTel());
        mTvUnloadingContact.setText(releaseDetailsEntity.getUnloadingContact());
        mTvUnloadingTel.setText(releaseDetailsEntity.getUnloadingTel());
        mTvRemarks.setText(releaseDetailsEntity.getRemarks());
    }

    @Override
    public void onFail(String errorMessage) {

    }

    public static void showActivity(Context context, String cargo_id) {
        Intent intent = new Intent(context, PlanPublishDetailActivity.class);
        intent.putExtra("id", cargo_id);
        context.startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mReleaseDetailPresenter.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @OnClick(R.id.tv_publish)
    public void onClick() {
        postSave();
    }

    private void postSave() {

        //构造传递对象
        //--------------------------
        //你是不是想知道 这些参数都什么意思？很遗憾我没有写任何注释
        //那么多参数 我就不写注释啊 锤子费劲
        //但是你可以去 https://www.easyapi.com/dashboard/api/?code=wuliuapi&documentId=9646&categoryId=16661&apiId=66226&head=api
        //这里看
        //也可以去看看下边的Toast
        //-------------end---------

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", App.getUserId());
        map.put("op_status",  1);
        map.put("loading_address", mReleaseDetailsEntity.getLoadingAddress());
        map.put("unloading_address", mReleaseDetailsEntity.getUnloadingAddress());
        map.put("cargo_name", mReleaseDetailsEntity.getCargoName());
        map.put("cargo_num", mReleaseDetailsEntity.getCargoNum());
        map.put("cargo_density", mReleaseDetailsEntity.getCargoDensity());
        map.put("freight_price", mReleaseDetailsEntity.getFreightPrice());
        map.put("cargo_price", mReleaseDetailsEntity.getCargoPrice());
        map.put("loading_time", mReleaseDetailsEntity.getLoadingTime());
        map.put("payment_terms", mReleaseDetailsEntity.getPaymentTerms());
        map.put("settlement_time", mReleaseDetailsEntity.getSettlementTime());
        map.put("press_charges", mReleaseDetailsEntity.getPressCharges());
        map.put("truck_drawer", mReleaseDetailsEntity.getTruckDrawer());
        map.put("truck_tel", mReleaseDetailsEntity.getTruckTel());
        map.put("unloading_contact", mReleaseDetailsEntity.getUnloadingContact());
        map.put("unloading_tel", mReleaseDetailsEntity.getUnloadingTel());
        map.put("type", mReleaseDetailsEntity.getType());
        map.put("remarks", mReleaseDetailsEntity.getRemarks());
        map.put("unit", mReleaseDetailsEntity.getUnit());
        map.put("cargo_id", mReleaseDetailsEntity.getId());

        //传给后台
        //去发布
        //showActivity(SelectLogisticsActivity.class);
        /**
         *
         发现一个问题 让我思考了 40分钟人生与理想，Intent 传递map 不行哎，去百度查 还要搞一个对象装进去
         好鸡麻烦啊，于是乎我用了RxBus 粘性发射数据到下下层了，这种用法还是第一次用，会出什么问题我也不知道，
         */
        //RxBus.getDefault().postSticky(map);
        Intent intent = new Intent(mActivity, SelectLogisticsActivity.class);
        //intent.putExtra("data",map);
        showActivity(intent);
        //发送数据给下一页
        EventBus.getDefault().postSticky(new PublishParameterEntity(map));
    }

}
