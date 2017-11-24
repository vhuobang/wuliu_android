package com.arkui.transportation_shipper.owner.activity;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.arkui.fz_net.entity.BaseHttpResult;
import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.http.HttpMethod;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.utils.AppManager;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.activity.LoginActivity;
import com.arkui.transportation_shipper.common.api.DriverApi;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.owner.fragment.AssetFragment;
import com.arkui.transportation_shipper.owner.fragment.MessageFragment;
import com.arkui.transportation_shipper.owner.fragment.MyFragment;
import com.arkui.transportation_shipper.owner.fragment.SupplyFragment;
import com.arkui.transportation_shipper.owner.fragment.WaybillFragment;
import com.arkui.transportation_shipper.service.MonitorService;
import com.arkui.transportation_shipper.service.WakeLockService;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity implements AMapLocationListener {

    @BindView(R.id.rg_root)
    RadioGroup mRgRoot;
    private long mPressedTime = 0;
    protected BaseFragment currentSupportFragment;
    private MessageFragment mMessageFragment;
    private AssetFragment mAssetFragment;
    private SupplyFragment mSupplyFragment;
    private WaybillFragment mWaybillFragment;
    private MyFragment mMyFragment;
    private RxPermissions rxPermissions;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private DriverApi driverApi;
    private MainBroadcastReceiver mainBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        rxPermissions = new RxPermissions(this);
        driverApi = RetrofitFactory.createRetrofit(DriverApi.class);
        AppManager.getAppManager().addActivity(this);
        initData();
        initReceiver();
    }

    private void initReceiver() {
        mainBroadcastReceiver = new MainBroadcastReceiver();
        //实例化过滤器并设置要过滤的广播
        IntentFilter intentFilter = new IntentFilter("MainActivity");
        registerReceiver(mainBroadcastReceiver, intentFilter);
    }

    private void initData() {
        mMessageFragment = new MessageFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("authType",1); // 代表车主
        mMessageFragment.setArguments(bundle);
        mAssetFragment = new AssetFragment();
        mSupplyFragment = new SupplyFragment();
        mWaybillFragment = new WaybillFragment();
        mMyFragment = new MyFragment();
        changeFragment(R.id.fl_content, mMessageFragment);
        initLocation();
    }

    private void initLocation() {
        rxPermissions.request(Manifest.permission.ACCESS_FINE_LOCATION).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if (!aBoolean) {
                    Toast.makeText(MainActivity.this, "没有权限，无法定位，建议你允许！", Toast.LENGTH_SHORT).show();
                } else {
                    //改成开启服务
                    beginService();
                  // getLocation();
                }
            }
        });
    }
    private Intent monitorService;
    private void beginService() {
        startService(new Intent(MainActivity.this, WakeLockService.class));
        if (!MonitorService.isRunning) {
            // 开启监听service
            MonitorService.isCheck = true;
            MonitorService.isRunning = true;
            monitorService = new Intent(MainActivity.this, MonitorService.class);
            startService(monitorService);
        }
    }

    /**
     * 获取定位
     */
    private void getLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        //初始化AMapLocationClientOption对象
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setOnceLocation(true);
        //mLocationOption.setInterval(2000);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);
        //启动定位
        mLocationClient.startLocation();

    }


    public void changeFragment(int resView, BaseFragment targetFragment) {
        if (targetFragment.equals(currentSupportFragment)) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager()
                .beginTransaction();
        if (!targetFragment.isAdded()) {
            transaction.add(resView, targetFragment, targetFragment.getClass()
                    .getName());
        }
        if (targetFragment.isHidden()) {
            transaction.show(targetFragment);
            targetFragment.onChange();
        }
        if (currentSupportFragment != null
                && currentSupportFragment.isVisible()) {
            transaction.hide(currentSupportFragment);
        }
        currentSupportFragment = targetFragment;
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        long mNowTime = System.currentTimeMillis();
        //获取第一次按键时间
        if ((mNowTime - mPressedTime) > 2000) {//比较两次按键时间差
            Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            mPressedTime = mNowTime;
        } else {//退出程序
            this.finish();
        }
    }

    @OnClick({R.id.rb_msg, R.id.rb_asset, R.id.iv_publish, R.id.rb_order, R.id.rb_my})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_msg:
                changeFragment(R.id.fl_content, mMessageFragment);
                break;
            case R.id.rb_asset:
                changeFragment(R.id.fl_content, mAssetFragment);
                break;
            case R.id.iv_publish:
                String isUserCertificate = App.getUserEntity().getIsUserCertificate();
                String isCompanyCertificate = App.getUserEntity().getIsCompanyCertificate();
                if ( !(isUserCertificate.equals("2") || isCompanyCertificate.equals("2"))){
                    Toast.makeText(MainActivity.this,"请先去认证",Toast.LENGTH_SHORT).show();
                    return;
                }
                mRgRoot.clearCheck();
                changeFragment(R.id.fl_content, mSupplyFragment);
                break;
            case R.id.rb_order:
                changeFragment(R.id.fl_content, mWaybillFragment);
                break;
            case R.id.rb_my:
                changeFragment(R.id.fl_content, mMyFragment);
                break;
        }
    }

    //得到经纬度
    private double latitude;
    private double longitude;
    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null
                && aMapLocation.getErrorCode() == 0) {
            latitude = aMapLocation.getLatitude();
            longitude = aMapLocation.getLongitude();
            Log.d("gaode", "latitude:  " + latitude + "longitude:  " + longitude + aMapLocation.getAddress());
            upLocation(latitude,longitude);
        } else {
            String errText = "定位失败," + aMapLocation.getErrorCode() + ": " + aMapLocation.getErrorInfo();
            Log.e("AmapErr", errText);
            //  Toast.makeText(RouteSentActivity.this, errText, Toast.LENGTH_LONG).show();
        }
    }
      Disposable mdisposable;
    private void upLocation(double latitude, double longitude) {
        Observable<BaseHttpResult> observable = driverApi.upDriverPosition(String.valueOf(longitude), String.valueOf(latitude),App.getUserId());
        HttpMethod.getInstance().getNetData(observable, new ProgressSubscriber<BaseHttpResult>(this) {
            @Override
            protected void getDisposable(Disposable d) {

             //   mDisposables.add(d);
                mdisposable =d;
            }

            @Override
            public void onNext(BaseHttpResult value) {
              //  Toast.makeText(MainActivity.this,value.getMessage(),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApiError(ApiException e) {
           //     Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * 销毁定位
     */
    private void destroyLocation() {
        if (null != mLocationClient) {
            mLocationClient.stopLocation();
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            mLocationClient.onDestroy();
            mLocationClient = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        destroyLocation();
       // mdisposable.dispose();
        AppManager.getAppManager().removeActivity(this);
        unregisterReceiver(mainBroadcastReceiver);
    }

    public class MainBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(final Context context, Intent intent) {
            //利用Intent判断是否有自定义消息
            String message = intent.getStringExtra("MessageContent");
            if(message != null && message.equals("异地登陆")){
                //如果有，则弹出对话框，提示用户下线
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder .setTitle("系统提示").
                        setMessage("请注意，您的账号异地登陆！").setCancelable(false).
                        setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //在这里可清除本地的用户信息
                                App.getInstance().deleteUserInfo();
                                AppManager.getAppManager().finishAllActivity();
                                JPushInterface.setAlias(MainActivity.this, "", new TagAliasCallback() {
                                    @Override
                                    public void gotResult(int i, String s, Set<String> set) {
                                    }
                                });
                            }
                        }).setNegativeButton("重新登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //再次执行登录操作
                        App.getInstance().deleteUserInfo();
                        AppManager.getAppManager().finishAllActivity();
                        MainActivity.this.startActivity(new Intent(MainActivity.this,LoginActivity.class));
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.getWindow().setType(WindowManager.LayoutParams.TYPE_TOAST);
                alertDialog.show();
            }
        }
    }

}
