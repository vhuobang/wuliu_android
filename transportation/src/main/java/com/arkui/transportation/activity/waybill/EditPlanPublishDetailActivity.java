package com.arkui.transportation.activity.waybill;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arkui.fz_net.utils.RxBus;
import com.arkui.fz_tools._interface.PublishInterface;
import com.arkui.fz_tools._interface.ReleaseDetailInterface;
import com.arkui.fz_tools._interface.RemarkInterface;
import com.arkui.fz_tools.adapter.FlowLayoutAdapter;
import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.dialog.EndTimePicker;
import com.arkui.fz_tools.dialog.SelectTypePicker;
import com.arkui.fz_tools.entity.PublishBean;
import com.arkui.fz_tools.entity.ReleaseDetailsEntity;
import com.arkui.fz_tools.entity.RemarkEntity;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.listener.OnVehicleTypeClickListener;
import com.arkui.fz_tools.mvp.PublishPresenter;
import com.arkui.fz_tools.mvp.ReleaseDetailPresenter;
import com.arkui.fz_tools.mvp.RemarkPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.view.ShapeEditText;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.MainActivity;
import com.arkui.transportation.activity.publish.PublishCompleteInfoActivity;
import com.arkui.transportation.activity.publish.SelectAddressActivity;
import com.arkui.transportation.base.App;
import com.arkui.transportation.entity.EditEvent;
import com.arkui.transportation.entity.RefreshWaybill;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.arkui.transportation.R.id.tv_publish;

/**
 * @author nmliz
 * @time 2017/6/30 13:43
 * 编辑货源
 */
public class EditPlanPublishDetailActivity extends BaseActivity implements OnVehicleTypeClickListener, ReleaseDetailInterface, OnConfirmClick, EndTimePicker.OnEnsureListener, PublishInterface, RemarkInterface {
    @BindView(R.id.tv_send)
    TextView mTvSend;
    @BindView(R.id.tv_receive)
    TextView mTvReceive;
    @BindView(R.id.et_cargo_name)
    EditText mEtCargoName;
    @BindView(R.id.et_number)
    EditText mEtNumber;
    @BindView(R.id.tv_amount)
    TextView mTvAmount;
    @BindView(R.id.et_cargo_density)
    EditText mEtCargoDensity;
    @BindView(R.id.tv_density)
    TextView mTvDensity;
    @BindView(R.id.et_freight_price)
    EditText mEtFreightPrice;
    @BindView(R.id.tv_freight_price)
    TextView mTvFreightPrice;
    @BindView(R.id.et_cargo_price)
    EditText mEtCargoPrice;
    @BindView(R.id.tv_cargo_price)
    TextView mTvCargoPrice;
    @BindView(R.id.tv_loading_time)
    TextView mTvLoadingTime;
    @BindView(R.id.tv_payment_terms)
    TextView mTvPaymentTerms;
    @BindView(R.id.tv_end_time)
    TextView mTvEndTime;
    @BindView(R.id.et_press_charges)
    EditText mEtPressCharges;
    @BindView(R.id.et_truck_drawer)
    EditText mEtTruckDrawer;
    @BindView(R.id.et_truck_tel)
    EditText mEtTruckTel;
    @BindView(R.id.et_unloading_contact)
    EditText mEtUnloadingContact;
    @BindView(R.id.et_unloading_tel)
    EditText mEtUnloadingTel;
    @BindView(R.id.tv_selected_1)
    TextView mTvSelected1;
    @BindView(R.id.tv_selected_2)
    TextView mTvSelected2;
    @BindView(R.id.et_remarks)
    ShapeEditText mEtRemarks;
    @BindView(R.id.flow_layout)
    TagFlowLayout mFlowLayout;
    @BindView(R.id.tv_publish)
    TextView tvPublish;
    private SelectTypePicker mSelectTypePicker;
    private int mType;
    private List<String> mPaymentList;
    private List<String> mEndTimeList;
    private List<String> mStringList;
    private CommonDialog mCommonDialog;
    private EndTimePicker mEndTimePicker;
    //1、吨；2、方；3、件；4、趟
    private int mUnit = 1;
    private String  mPublishType ;
    private int mSettlementTime = -1;
    private int mPaymentTerms = -1;

    private PublishPresenter mPublishPresenter;
    private String mId;
    private com.arkui.fz_tools.mvp.ReleaseDetailPresenter mReleaseDetailPresenter;
    private Boolean isTwice;
    private FlowLayoutAdapter flowLayoutAdapter;
    private List<RemarkEntity> remarkEntityList;
    private String keyWord;
    private String cargo_density;
    private String press_charges;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_edit_plan_publish_detail);
        setTitle("货源详情");

        mSelectTypePicker = new SelectTypePicker();
        mSelectTypePicker.setTitle("单位选择");
        mStringList = new ArrayList<>();
        mStringList.add("吨");
        mStringList.add("方");
        mStringList.add("升");
        mStringList.add("趟");

        mSelectTypePicker.setData(mStringList);

        mSelectTypePicker.setOnTypeClickListener(this);

        mPaymentList = new ArrayList<>();
//        mPaymentList.add("货主网上支付");
        mPaymentList.add("物流网上支付");
        mPaymentList.add("货到现金付款");

        mEndTimeList = new ArrayList<>();
        mEndTimeList.add("立即结算");
        mEndTimeList.add("7天内结算");
        mEndTimeList.add("15天内结算");
        mEndTimeList.add("30天内结算");

        mCommonDialog = new CommonDialog();
        mCommonDialog.setTitle("保存信息").setContent("发货信息已保存成功！是否前往运单查看？").setConfirmText("去订单");
        mCommonDialog.setConfirmClick(this);
        mEndTimePicker = new EndTimePicker();
        mEndTimePicker.setOnEnsureListener(this);

    }
    // FlowLayout
    private void initFlowLayout() {
        RemarkPresenter remarkPresenter = new RemarkPresenter(this, this);
        remarkPresenter.getRemarks();
        flowLayoutAdapter = new FlowLayoutAdapter(remarkEntityList, this);
        mFlowLayout.setAdapter(flowLayoutAdapter);

        mFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                Iterator<Integer> iterator = selectPosSet.iterator();
                keyWord = "";
                mPublishType="";
                while (iterator.hasNext()){
                    Integer next = iterator.next();
                    keyWord += remarkEntityList.get(next).getContent()+";";
                    mPublishType += next+",";
                }
                mEtRemarks.setText(keyWord);
                Log.e("fz", "onSelected: "+ keyWord  + " ---------" + mPublishType);

            }
        });
    }
    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        remarkEntityList= new ArrayList<>();
        initFlowLayout();
    }

    @Override
    public void initData() {
        super.initData();
        mId = getIntent().getStringExtra("id");
        isTwice = getIntent().getBooleanExtra("isTwice", false);
        mReleaseDetailPresenter = new ReleaseDetailPresenter(this, this);
        mReleaseDetailPresenter.getReleaseDetail(mId);
        mPublishPresenter = new PublishPresenter(this, this);
        if (isTwice){
            tvPublish.setVisibility(View.VISIBLE);
        }else {
            tvPublish.setVisibility(View.GONE);
        }
    }

    @OnClick({R.id.tv_amount, R.id.tv_density, R.id.tv_freight_price, R.id.tv_cargo_price, R.id.ll_time, R.id.ll_payment, R.id.ll_end_time, R.id.tv_selected_1, R.id.tv_selected_2, R.id.tv_publish, R.id.tv_save, R.id.tv_send, R.id.tv_receive
            , R.id.iv_cargo_name, R.id.iv_truck_drawer, R.id.iv_truck_tel, R.id.iv_unloading_contact, R.id.iv_unloading_tel})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_amount:
                mType = 1;
                mSelectTypePicker.setData(mStringList).show(getSupportFragmentManager(), "amount");
                break;
            case R.id.tv_density:
                //mType = 2;
                // mSelectTypePicker.setData(mStringList).show(getSupportFragmentManager(), "density");
                break;
            case R.id.tv_freight_price:
                mType = 3;
                mSelectTypePicker.setData(mStringList).show(getSupportFragmentManager(), "freight");
                break;
            case R.id.tv_cargo_price:
                mType = 4;
                mSelectTypePicker.setData(mStringList).show(getSupportFragmentManager(), "cargo");
                break;
            case R.id.ll_time:
                mType = 5;
                mEndTimePicker.show(getSupportFragmentManager(), "time");
                break;
            case R.id.ll_payment:
                mType = 6;
                mSelectTypePicker.setData(mPaymentList).show(getSupportFragmentManager(), "payment");
                break;
            case R.id.ll_end_time:
                mType = 7;
                mSelectTypePicker.setData(mEndTimeList).show(getSupportFragmentManager(), "end");
                break;
            case R.id.tv_selected_1: // 没有了
              //  mPublishType = 1;
                mTvSelected1.setSelected(true);
                mTvSelected2.setSelected(false);
                break;
            case R.id.tv_selected_2://没有了
              //  mPublishType = 2;
                mTvSelected1.setSelected(false);
                mTvSelected2.setSelected(true);
                break;
            case tv_publish:
                //mCommonDialog.show(getSupportFragmentManager(), "publish");
                //跳转到选择物流页面
                postSave(false);
                break;
            case R.id.iv_cargo_name:
                mEtCargoName.setText("");
                break;
            case R.id.iv_truck_drawer:
                mEtTruckDrawer.setText("");
                break;
            case R.id.iv_truck_tel:
                mEtTruckTel.setText("");
                break;
            case R.id.iv_unloading_contact:
                mEtUnloadingContact.setText("");
                break;
            case R.id.iv_unloading_tel:
                mEtUnloadingTel.setText("");
                break;
            case R.id.tv_save:
                postSave(true);
                break;
            case R.id.tv_send:
                SelectAddressActivity.openActivity(mActivity, 1);
                break;
            case R.id.tv_receive:
                SelectAddressActivity.openActivity(mActivity, 2);
                break;
        }
    }

    @Override
    public void OnVehicleTypeClick(String item, int pos) {
        switch (mType) {
            case 1:
            case 3:
            case 4:
                mTvAmount.setText(item);
                mTvFreightPrice.setText(String.format("元/%s", item));
                mTvCargoPrice.setText(String.format("元/%s", item));
                mUnit = pos + 1;
                break;
            case 6:
                //1、货主网上支付；2、物流网上支付；3货到付款
                mPaymentTerms = pos + 2;
                mTvPaymentTerms.setText(item);
                break;
            case 7:
                //结算时间
                mSettlementTime = pos + 1;
                mTvEndTime.setText(item);
                break;
        }
    }

    private void postSave(boolean isSave) {

        //构造传递对象
        //你是不是想知道 这些参数都什么意思？很遗憾我没有写任何注释
        //但是你可以去 https://www.easyapi.com/dashboard/api/?code=wuliuapi&documentId=9646&categoryId=16661&apiId=66226&head=api
        //这里看
        //也可以去看看下边的Toast
        //end 一个比较皮的code
        String loading_address = mTvSend.getText().toString().trim();
        String unloading_address = mTvReceive.getText().toString().trim();
        String cargo_name = mEtCargoName.getText().toString().trim();
        String cargo_num = mEtNumber.getText().toString().trim();
        cargo_density = mEtCargoDensity.getText().toString().trim();
        String freight_price = mEtFreightPrice.getText().toString().trim();
        String cargo_price = mEtCargoPrice.getText().toString().trim();
        String loading_time = mTvLoadingTime.getText().toString().trim();
        //String payment_terms = mTvPayment.getText().toString().trim();
        press_charges = mEtPressCharges.getText().toString().trim();
        String truck_drawer = mEtTruckDrawer.getText().toString().trim();
        String truck_tel = mEtTruckTel.getText().toString().trim();
        String unloading_contact = mEtUnloadingContact.getText().toString().trim();
        String unloading_tel = mEtUnloadingTel.getText().toString();
        String remarks = mEtRemarks.getText().toString().trim();

        if (TextUtils.isEmpty(loading_address)) {
            ShowToast("请输入装货地址");
            return;
        }
        if (TextUtils.isEmpty(unloading_address)) {
            ShowToast("请输入卸货地址");
            return;
        }
        if (TextUtils.isEmpty(cargo_name)) {
            ShowToast("请输入货物信息");
            return;
        }
        if (TextUtils.isEmpty(cargo_num)) {
            ShowToast("请输入货物数量");
            return;
        }
        if (TextUtils.isEmpty(cargo_density)) {
          //  ShowToast("请输入货物密度");
            cargo_density ="0";

        }
        if (TextUtils.isEmpty(freight_price)) {
            ShowToast("请输入运费单价");
            return;
        }
        if (TextUtils.isEmpty(cargo_price)) {
            ShowToast("请输入货物单价");
            return;
        }
        if (TextUtils.isEmpty(loading_time)) {
            ShowToast("请选择截至时间");
            return;
        }
        if (mPaymentTerms == -1) {
            ShowToast("请选择运费支付方");
            return;
        }
        if (mSettlementTime == -1) {
            ShowToast("请选择结算时间");
            return;
        }
        if (TextUtils.isEmpty(press_charges)) {
       //     ShowToast("请输入压车费");
            press_charges ="0";

        }
        if (TextUtils.isEmpty(truck_drawer)) {
            ShowToast("请输入提货公司名称");
            return;
        }

//        if (!StrUtil.isMobileNO(truck_tel)) {
//            ShowToast("请输入装车联系电话");
//            return;
//        }
//        if (TextUtils.isEmpty(unloading_contact)) {
//            ShowToast("请输入卸车开票人");
//            return;
//        }
//        if (!StrUtil.isMobileNO(unloading_tel)) {
//            ShowToast("请输入卸车联系电话");
//            return;
//        }

        Map<String, Object> map = new HashMap<>();
        map.put("user_id", App.getUserId());
        map.put("op_status", isSave ? 0 : 1);
        map.put("loading_address", loading_address);
        map.put("unloading_address", unloading_address);
        map.put("cargo_name", cargo_name);
        map.put("cargo_num", cargo_num);
        map.put("cargo_density", cargo_density);
        map.put("freight_price", freight_price);
        map.put("cargo_price", cargo_price);
        map.put("loading_time", loading_time);
        map.put("payment_terms", mPaymentTerms);
        map.put("settlement_time", mSettlementTime);
        map.put("press_charges", press_charges);
        map.put("truck_drawer", truck_drawer);
        map.put("truck_tel", truck_tel);
        map.put("unloading_contact", unloading_contact);
        map.put("unloading_tel", unloading_tel);
        map.put("type", mPublishType);
        map.put("remarks", remarks);
        map.put("unit", mUnit);
        String[] loadaddress = loading_address.split(" ");
        String[] unloadAddress = unloading_address.split(" ");
        String loadaddres = loadaddress[0];
        String[] split = loadaddres.split("-");
        String unloadAddres = unloadAddress[0];
        String[] split1 = unloadAddres.split("-");

        String loading_province = split[0];
        String loading_city = split[1];
        String unloading_province = split1[0];
        String unloading_city = split1[1];
        boolean isHave = loading_city.contains("全");
        boolean isHas =unloading_city.contains("全");
        if (isHave){
            loading_city=  loading_city.replace("全", "");
        }
        if (isHas){
            unloading_city=  unloading_city.replace("全", "");
        }
        map.put("loading_province",loading_province);
        map.put("loading_city",loading_city);
        map.put("unloading_province",unloading_province);
        map.put("unloading_city",unloading_city);
        if (!isTwice) {
            map.put("cargo_id", mId);
        }

        //传给后台
        if (isSave) {
            if (isTwice) {
                mPublishPresenter.postSave(map);
            } else {
                mPublishPresenter.postEdit(map);
            }

        } else {
            //去发布
            //showActivity(SelectLogisticsActivity.class);
            /**
             *
             发现一个问题 让我思考了 40分钟人生与理想，Intent 传递map 不行哎，去百度查 还要搞一个对象装进去
             好鸡麻烦啊，于是乎我用了RxBus 粘性发射数据到下下层了，这种用法还是第一次用，会出什么问题我也不知道，
             */
            ReleaseDetailsEntity entity = new ReleaseDetailsEntity();
            entity.setCargoDensity(cargo_density);
            entity.setCargoName(cargo_name);
            entity.setCargoNum(cargo_num);
            entity.setCargoPrice(cargo_price);
            entity.setFreightPrice(freight_price);
            entity.setLoadingAddress(loading_address);
            entity.setLoadingTime(loading_time);
            entity.setPaymentTerms(mPaymentTerms + "");
            entity.setPressCharges(press_charges);
            entity.setRemarks(remarks);
            entity.setSettlementTime(mSettlementTime + "");
            entity.setTruckDrawer(truck_drawer);
            entity.setTruckTel(truck_tel);
            entity.setUnloadingAddress(unloading_address);
            entity.setLoadingTime(loading_time);
            entity.setUnit(mUnit + "");
            entity.setUnloadingContact(unloading_contact);
            entity.setUnloadingTel(unloading_tel);
            entity.setType(mPublishType + "");
            if (!isTwice) {
                entity.setId(mId);
            }

            Intent intent = new Intent(mActivity, PublishCompleteInfoActivity.class);
            intent.putExtra("releaseDetails", entity);
            showActivity(intent);
        }

    }

    public static void showActivity(Context context, String cargo_id) {
        Intent intent = new Intent(context, EditPlanPublishDetailActivity.class);
        intent.putExtra("id", cargo_id);
        context.startActivity(intent);
    }

    @Override
    public void onSuccess(ReleaseDetailsEntity releaseDetailsEntity) {
        //  String[] loadingAddress = releaseDetailsEntity.getLoadingAddress().split(" ");
        //String[] unloadingAddress = releaseDetailsEntity.getUnloadingAddress().split(" ");

        //mEtLoadingAddress.setText(loadingAddress.length >= 0 ? loadingAddress[0] : "");
        // mTvLoadingDetailAddress.setText(loadingAddress.length >= 2 ? loadingAddress[1] : "");
        mTvSend.setText(releaseDetailsEntity.getLoadingAddress());
        mTvReceive.setText(releaseDetailsEntity.getUnloadingAddress());

        mEtCargoName.setText(releaseDetailsEntity.getCargoName());
        mEtCargoDensity.setText(releaseDetailsEntity.getCargoDensity());
        mEtFreightPrice.setText(releaseDetailsEntity.getFreightPrice());
        mEtCargoPrice.setText(releaseDetailsEntity.getCargoPrice());
        mTvLoadingTime.setText(releaseDetailsEntity.getLoadingTime());
        mEtPressCharges.setText(releaseDetailsEntity.getPressCharges());

        mTvPaymentTerms.setText(StrUtil.formatPayMent(releaseDetailsEntity.getPaymentTerms()));
        mTvEndTime.setText(StrUtil.formatSettlementTime(releaseDetailsEntity.getSettlementTime()));

        mEtTruckDrawer.setText(releaseDetailsEntity.getTruckDrawer());
        mEtTruckTel.setText(releaseDetailsEntity.getTruckTel());
        mEtUnloadingContact.setText(releaseDetailsEntity.getUnloadingContact());
        mEtUnloadingTel.setText(releaseDetailsEntity.getUnloadingTel());
        mEtRemarks.setText(releaseDetailsEntity.getRemarks());
        mEtNumber.setText(releaseDetailsEntity.getCargoNum());

        mTvAmount.setText(StrUtil.formatUnit(releaseDetailsEntity.getUnit()));
        mTvFreightPrice.setText(String.format("元/%s", StrUtil.formatUnit(releaseDetailsEntity.getUnit())));
        mTvCargoPrice.setText(String.format("元/%s", StrUtil.formatUnit(releaseDetailsEntity.getUnit())));

        mTvSelected1.setSelected("1".equals(releaseDetailsEntity.getType()));
        mTvSelected2.setSelected("2".equals(releaseDetailsEntity.getType()));

        mPublishType =releaseDetailsEntity.getType();
        mPaymentTerms = Integer.parseInt((releaseDetailsEntity.getPaymentTerms()));
        String settlementTime = releaseDetailsEntity.getSettlementTime();
        if (!TextUtils.isEmpty(settlementTime)){
            mSettlementTime = Integer.parseInt(settlementTime);
        }

    }

    @Override
    public void onConfirmClick() {
        Intent intent = new Intent(mActivity, MainActivity.class);
        intent.putExtra("type", 3);
        startActivity(intent);
        //发送刷新命令
        //刷新保存那个界面
        RxBus.getDefault().postSticky(new RefreshWaybill(0));
        finish();
    }

    @Override
    public void onSuccess(PublishBean publishBean) {

        if (isTwice){
            mCommonDialog.setTitle("再来一单").setContent("再来一单成功").setConfirmText("去列表");
           mCommonDialog.show(getSupportFragmentManager(), "publish");
        }else {
            EventBus.getDefault().post(new EditEvent());
            finish();
        }


    }

    @Override
    public void onFail(String errorMessage) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            String address = data.getStringExtra("address");
            mTvSend.setText(address);
        } else if (requestCode == 2 && resultCode == Activity.RESULT_OK) {
            String address = data.getStringExtra("address");
            mTvReceive.setText(address);
        }
    }

    @Override
    public void onEnsureClick(String time) {
        mTvLoadingTime.setText(time);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPublishPresenter.onDestroy();
        mReleaseDetailPresenter.onDestroy();
    }

    @Override
    public void remarkList(List<RemarkEntity> remarkEntities) {
        remarkEntityList.clear();
        remarkEntityList.addAll(remarkEntities);
        flowLayoutAdapter.notifyDataChanged();
    }

    @Override
    public void noRemark(String message) {
        Toast.makeText(mActivity,"暂无备注",Toast.LENGTH_SHORT).show();
    }
}
