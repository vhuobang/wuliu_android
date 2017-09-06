package com.arkui.transportation_shipper.owner.activity.asset;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.AppManager;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.api.AssetApi;
import com.arkui.transportation_shipper.common.entity.DriverListDetailEntity;
import com.arkui.transportation_shipper.common.entity.RefreshAssetListEntity;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class DriverEditActivity extends BaseActivity {

    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    private DriverListDetailEntity mDriverDetail;
    private AssetApi mAssetApi;
    private String mId;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_driver_edit);
        setTitle("编辑司机");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        super.initData();
        mDriverDetail = getIntent().getParcelableExtra("data");
        mId = getIntent().getStringExtra("id");

        mEtPhone.setText(mDriverDetail.getMobile());
        mEtPassword.setText(mDriverDetail.getOrigin_password());
        mEtName.setText(mDriverDetail.getName());

        mAssetApi = RetrofitFactory.createRetrofit(AssetApi.class);
    }

    @OnClick({R.id.iv_name, R.id.iv_phone, R.id.iv_password, R.id.tv_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_name:
                mEtName.setText("");
                break;
            case R.id.iv_phone:
                mEtPhone.setText("");
                break;
            case R.id.iv_password:
                mEtPassword.setText("");
                break;
            case R.id.tv_complete:
                //修改司机账号
                editDriver();
                break;
        }
    }

    private void editDriver() {
        Map<String, Object> map = new HashMap<>();

        String name = mEtName.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();
        String phone = mEtPhone.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            ShowToast("请输入司机姓名");
            return;
        }

        if (TextUtils.isEmpty(phone)) {
            ShowToast("请输入手机号码");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            ShowToast("请输入登录密码");
            return;
        }

        if (password.length() < 6) {
            ShowToast("密码长度小于6位");
            return;
        }

        map.put("driver_id", mId);
        map.put("name", name);
        map.put("mobile", phone);
        map.put("password", password);

        Observable<BaseHttpResult> observable = mAssetApi.postEditDriver(map);

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mActivity) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
                ShowToast(value.getMessage());
                AppManager.getAppManager().finishActivity(DriverDetailActivity.class);
                EventBus.getDefault().post(new RefreshAssetListEntity(2));
                finish();
            }
        });
    }
}
