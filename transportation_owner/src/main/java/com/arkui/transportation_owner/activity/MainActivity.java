package com.arkui.transportation_owner.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.utils.AppManager;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.activity.publish.MyDeliverActivity;
import com.arkui.transportation_owner.activity.user.LoginActivity;
import com.arkui.transportation_owner.base.App;
import com.arkui.transportation_owner.fragment.LogisticsFragment;
import com.arkui.transportation_owner.fragment.MessageFragment;
import com.arkui.transportation_owner.fragment.MyFragment;
import com.arkui.transportation_owner.fragment.WaybillFragment;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rg_root)
    RadioGroup mRgRoot;
    @BindView(R.id.rb_asset)
    RadioButton radioButtonMessage;
    @BindView(R.id.rb_msg)
    RadioButton radioButtonHome;
    private long mPressedTime = 0;
    protected BaseFragment currentSupportFragment;
    private LogisticsFragment mLogisticsFragment;
    private MessageFragment mMessageFragment;
    private WaybillFragment mWaybillFragment;
    private MyFragment mMyFragment;
    private  MainBroadcastReceiver mainBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mLogisticsFragment = new LogisticsFragment();
        mMessageFragment = new MessageFragment();
        mWaybillFragment = new WaybillFragment();
        mMyFragment = new MyFragment();

        String ts = getIntent().getStringExtra("ts");
        if (!TextUtils.isEmpty(ts) && "推送".equals(ts) ){
            radioButtonHome.setChecked(false);
            radioButtonMessage.setChecked(true);
            changeFragment(R.id.fl_content,mMessageFragment);
        }else {
            changeFragment(R.id.fl_content, mLogisticsFragment);
        }

        System.out.println(JPushInterface.getRegistrationID(this));
        AppManager.getAppManager().addActivity(this);
        initReceiver();
    }
    //注册一个广播
    private void initReceiver() {
        mainBroadcastReceiver = new MainBroadcastReceiver();
        //实例化过滤器并设置要过滤的广播
        IntentFilter intentFilter = new IntentFilter("MainActivity");
        registerReceiver(mainBroadcastReceiver, intentFilter);
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

    @OnClick({R.id.rb_msg, R.id.rb_asset, R.id.iv_publish, R.id.rb_order, R.id.rb_my})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rb_msg:
                changeFragment(R.id.fl_content, mLogisticsFragment);
                break;
            case R.id.rb_asset:
                changeFragment(R.id.fl_content, mMessageFragment);
                break;
            case R.id.iv_publish:
                if(!App.isLogin()){
                    return;
                }

               String isUserCertificate = App.getUserEntity().getIsUserCertificate();
                String isCompanyCertificate = App.getUserEntity().getIsCompanyCertificate();
                if (isUserCertificate.equals("2") || isCompanyCertificate.equals("2")){
                    Intent intent = new Intent(this, MyDeliverActivity.class);
                    startActivity(intent);
                }else{
                    //Intent intent = new Intent(this, AuthActivity.class);
                    //startActivity(intent);
                    Toast.makeText(this, "认证之后才能才能发货，快去认证吧！", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.rb_order:
                changeFragment(R.id.fl_content, mWaybillFragment);
                break;
            case R.id.rb_my:
                changeFragment(R.id.fl_content, mMyFragment);
                break;
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        int type = intent.getIntExtra("type", -1);

        switch (type) {
            case 3:
                mRgRoot.check(R.id.rb_order);
                changeFragment(R.id.fl_content, mWaybillFragment);
                mWaybillFragment.setCurrentPage(0);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
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
