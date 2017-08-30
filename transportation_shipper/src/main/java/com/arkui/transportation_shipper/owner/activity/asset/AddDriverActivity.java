package com.arkui.transportation_shipper.owner.activity.asset;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.StrUtil;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.api.AssetApi;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.common.entity.RefreshAssetListEntity;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;


public class AddDriverActivity extends BaseActivity {

    @BindView(R.id.et_driver_name)
    EditText mEtDriverName;
    @BindView(R.id.et_login_number)
    EditText mEtLoginNumber;
    @BindView(R.id.et_password)
    EditText mEtPassword;
    private AssetApi mAssetApi;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_add_driver);
        setTitle("添加司机");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mAssetApi = RetrofitFactory.createRetrofit(AssetApi.class);
    }

    @OnClick({R.id.iv_driver_name, R.id.iv_login_number, R.id.iv_password, R.id.tv_complete})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_driver_name:
                mEtDriverName.setText("");
                break;
            case R.id.iv_login_number:
                mEtLoginNumber.setText("");
                break;
            case R.id.iv_password:
                mEtPassword.setText("");
                break;
            case R.id.tv_complete:
                //添加司机
                addDriver();
                break;
        }
    }

    private void addDriver() {
        String driverName = mEtDriverName.getText().toString().trim();
        String loginNumber = mEtLoginNumber.getText().toString().trim();
        String password = mEtPassword.getText().toString().trim();

        if (TextUtils.isEmpty(driverName)) {
            ShowToast("请输入司机姓名");
            return;
        }

        if (!StrUtil.isMobileNO(loginNumber)) {
            ShowToast("请输入司机电话");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            ShowToast("请输入登录密码");
            return;
        }

        if (password.length() < 6) {
            ShowToast("登录密码长度必须大于6位");
            return;
        }

        Map<String, Object> parameter = new HashMap<>();
        parameter.put("user_id", App.getUserId());
        parameter.put("name", driverName);
        parameter.put("mobile", loginNumber);
        parameter.put("password", password);

        Observable<BaseHttpResult> observable = mAssetApi.postDriverAdd(parameter);

        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(mActivity) {
            @Override
            protected void getDisposable(Disposable d) {
                mDisposables.add(d);
            }

            @Override
            public void onNext(BaseHttpResult value) {
                //通知列表刷新数据
                ShowToast(value.getMessage());
                EventBus.getDefault().post(new RefreshAssetListEntity(2));
                finish();
            }
        });
    }
}
