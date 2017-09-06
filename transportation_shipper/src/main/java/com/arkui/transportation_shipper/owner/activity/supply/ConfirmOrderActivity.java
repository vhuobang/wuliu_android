package com.arkui.transportation_shipper.owner.activity.supply;

import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.dialog.SelectTypePicker;
import com.arkui.fz_tools.dialog.SuccessFullyDialog;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.LogUtil;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.api.SupplyApi;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.common.dialog.SelectDriverDialog;
import com.arkui.transportation_shipper.common.dialog.SelectVehicleDialog;
import com.arkui.transportation_shipper.common.dialog.SelectVehicleModelDialog;
import com.arkui.transportation_shipper.common.entity.DriverListEntity;
import com.arkui.transportation_shipper.common.entity.OrderEntity;
import com.arkui.transportation_shipper.common.entity.TruckListEntity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

/**
 * 货源列表抢单
 */
public class ConfirmOrderActivity extends BaseActivity implements SelectDriverDialog.OnSelectDriverListener, SelectVehicleDialog.OnSelectTruckListListener {

    @BindView(R.id.et_number)
    EditText mEtNumber;
    @BindView(R.id.tv_vehicle)
    TextView mTvVehicle;
    @BindView(R.id.tv_driver)
    TextView mTvDriver;
    @BindView(R.id.tv_complete)
    TextView mTvComplete;
    private SelectTypePicker mSelectVehicleTypePicker;
    private List<String> list;
    private List<String> list2;
    private SuccessFullyDialog mSuccessFullyDialog;
    private SupplyApi mSupplyApi;
    private String mId;
    private SelectDriverDialog mSelectDriverDialog;
    private SelectVehicleDialog mSelectVehicleDialog;
    //车辆ID 用于获取司机列表
    private String mTruckId;
    //司机ID
    private String mDriverId;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_confirm_order);
        setTitle("确认订单");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        initDialog();
    }

    private void initDialog() {
        mSelectVehicleTypePicker = new SelectTypePicker();
        list = new ArrayList<>();
        list.add("京D2455X");
        list.add("蒙S25478");
        list.add("京H78542");
        list.add("京Y57431");
        list2 = new ArrayList<>();
        list2.add("张少华");
        list2.add("陈东海");
        list2.add("成金龙");
        list2.add("森宝玉");

        //上面的代码是以前假数据用的，什么时候心情好了，我可就把他删了。
        mSuccessFullyDialog = new SuccessFullyDialog();
        //选择司机
        mSelectDriverDialog = new SelectDriverDialog();
        mSelectDriverDialog.setOnDriverListener(this);
        //选择车辆
        mSelectVehicleDialog = new SelectVehicleDialog();
        mSelectVehicleDialog.setOnTruckListListener(this);
    }

    @Override
    public void initData() {
        super.initData();
        mSupplyApi = RetrofitFactory.createRetrofit(SupplyApi.class);
        mId = getIntent().getStringExtra("id");
        getNetData();

    }


    //获取车辆列表
    public void getNetData() {
        Observable<List<TruckListEntity>> observable1 = mSupplyApi.postTruckList(App.getUserId(), mId).map(new HttpResultFunc<List<TruckListEntity>>());

        HttpMethod.getInstance().getNetData(observable1, new ProgressSubscriber<List<TruckListEntity>>(mActivity, false) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<TruckListEntity> value) {
                mSelectVehicleDialog.setTruckList(value);
            }

            @Override
            public void onApiError(ApiException e) {
                //super.onApiError(e);
                ShowToast(e.getMessage());
            }
        });

    }

    //拉取司机列表
    private void getDriverList() {

        Observable<List<DriverListEntity>> observable = mSupplyApi.postDriverList(App.getUserId(), mTruckId).map(new HttpResultFunc<List<DriverListEntity>>());

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<List<DriverListEntity>>(mActivity, false) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(List<DriverListEntity> value) {
                mSelectDriverDialog.setDriverList(value);
            }

            @Override
            public void onApiError(ApiException e) {
                //super.onApiError(e);
            }
        });
    }

    @OnClick({R.id.ll_vehicle, R.id.ll_driver})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_vehicle:
                //mSelectVehicleTypePicker.setData(list).setTitle("车辆选择").show(getSupportFragmentManager(), "vehicle");
                if (mSelectVehicleDialog.getTruckList() == null) {
                    ShowToast("没有可指派的车辆");
                    return;
                }
                mSelectVehicleDialog.showDialog(this, "driver");
                break;
            case R.id.ll_driver:
                if (mSelectDriverDialog.getDriverList() == null) {
                    ShowToast("没有可指派的司机");
                    return;
                }
                mSelectDriverDialog.showDialog(this, "driver");
                break;
        }
    }

    @OnClick(R.id.tv_complete)
    public void onClick() {
        if (mTruckId == null) {
            ShowToast("请选择指派车辆");
            return;
        }

        if (mDriverId == null) {
            ShowToast("请选择指派司机");
            return;
        }

        String number = mEtNumber.getText().toString().trim();
        if (TextUtils.isEmpty(number)) {
            ShowToast("请输入预装吨数");
        }

        //开始抢单
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", App.getUserId());
        map.put("truck_id", mTruckId);
        map.put("driver_id", mDriverId);
        map.put("carrier_num", number);
        map.put("id", mId);

        Observable<BaseHttpResult<OrderEntity>> observable = mSupplyApi.postOrder(map);

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult<OrderEntity>>(mActivity) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(final BaseHttpResult<OrderEntity> value) {
                // LogUtil.e(value.toString());
                //下单成功去支付
                mSuccessFullyDialog.show(getSupportFragmentManager(), "success");
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        mSuccessFullyDialog.dismiss();
                        // showActivity(PayMessageFeeActivity.class);
                        PayMessageFeeActivity.openActivity(mActivity, value.getData());
                        //关掉这个页面避免重复抢单问题
                        finish();
                    }
                }, 1000);
            }
        });
    }


    //选择车辆回调
    @Override
    public void selectTruck(String itemText, String itemId) {
        mTruckId = itemId;
        mTvVehicle.setText(itemText);
        //获取司机列表
        getDriverList();
        //清空之前选择的司机
        mTvDriver.setText("");
        mDriverId = null;
    }

    //选择司机回调
    @Override
    public void selectDriverModel(String itemText, String itemId) {
        mDriverId = itemId;
        mTvDriver.setText(itemText);
    }
}
