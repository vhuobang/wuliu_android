package com.arkui.transportation.activity.home;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.dialog.SelectTypePicker;
import com.arkui.fz_tools.dialog.SuccessFullyDialog;
import com.arkui.fz_tools.listener.OnVehicleTypeClickListener;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.transportation.R;
import com.arkui.transportation.api.LogisticalApi;
import com.arkui.transportation.base.App;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class CompleteInfoActivity extends BaseActivity implements OnVehicleTypeClickListener {

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.et_free)
    EditText etFree;
    @BindView(R.id.bt_confirm)
    ShapeButton btConfirm;
    private SelectTypePicker mSelectTypePicker;
    private SuccessFullyDialog mSuccessFullyDialog;
    private String cargoId; // 货源id
    private String payMoneyTime = "立即结算";
    private LogisticalApi mLogisticalApi;


    @Override
    public void setRootView() {
        setContentView(R.layout.activity_complete_info);
        setTitle("完善信息");
    }

    public  static void openCompleteInfoActivity(Context context,String cargo_id){
        Intent intent = new Intent(context,CompleteInfoActivity.class);
        intent.putExtra("cargoId",cargo_id);
        context.startActivity(intent);
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        cargoId = getIntent().getStringExtra("cargoId");
        mLogisticalApi = RetrofitFactory.createRetrofit(LogisticalApi.class);

        List<String> mEndTimeList = new ArrayList<>();
        mEndTimeList.add("立即结算");
        mEndTimeList.add("7天内结算");
        mEndTimeList.add("15天内结算");
        mEndTimeList.add("30天内结算");
        mSelectTypePicker = new SelectTypePicker();
        mSelectTypePicker.setTitle("结算时间").setData(mEndTimeList);
        mSelectTypePicker.setOnTypeClickListener(this);

        mSuccessFullyDialog = new SuccessFullyDialog();
        mSuccessFullyDialog.setTitle("发布成功");
    }

    @Override
    public void OnVehicleTypeClick(String item,int pos) {
        tvTime.setText(item);
        payMoneyTime= item;//结算时间
    }

    @OnClick({R.id.tv_time, R.id.bt_confirm})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_time:
                mSelectTypePicker.show(getSupportFragmentManager(), "time");
                break;
            case R.id.bt_confirm:
                //请求网络 确认发布
                HashMap<String,Object>  hashMap = new HashMap<>();
                String name = etName.getText().toString().trim();
                if (TextUtils.isEmpty(name)){
                    Toast.makeText(CompleteInfoActivity.this,"请输入物流联系人",Toast.LENGTH_SHORT).show();
                    return;
                }
                String phone = etPhone.getText().toString().trim();
                if (!StrUtil.isMobileNO(phone)) {
                    Toast.makeText(CompleteInfoActivity.this, "手机号输入不正确", Toast.LENGTH_SHORT).show();
                    return;
                }
                String free = etFree.getText().toString().trim();
                hashMap.put("user_id", App.getUserId());
                hashMap.put("cargo_id",cargoId);
                hashMap.put("log_settlement_time",payMoneyTime);
                hashMap.put("infomation_fee",free);
                hashMap.put("log_contact_name",name);
                hashMap.put("log_contact_tel",phone);
                Observable<BaseHttpResult> observable= mLogisticalApi.getLogisticalForward(hashMap);
                HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(CompleteInfoActivity.this) {
                    @Override
                    protected void getDisposable(Disposable d) {
                        mDisposables.add(d);
                    }

                    @Override
                    public void onNext(BaseHttpResult value) {
                        mSuccessFullyDialog.show(getSupportFragmentManager(), "full");
                        new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSuccessFullyDialog.dismiss();
                        finish();
                    }
                }, 1000);
                    }
                    @Override
                    public void onApiError(ApiException e) {
                        super.onApiError(e);
                    }
                });

//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        mSuccessFullyDialog.dismiss();
//                        finish();
//                    }
//                }, 1000);
                break;
        }
    }

}
