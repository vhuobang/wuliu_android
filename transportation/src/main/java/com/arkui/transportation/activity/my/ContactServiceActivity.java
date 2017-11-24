package com.arkui.transportation.activity.my;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ContactServiceActivity extends BaseActivity {

    @BindView(R.id.tv_qq_number)
    TextView mTvQqNumber;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_contact_service);
        setTitle("联系客服");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }


    @OnClick({R.id.tv_qq_number, R.id.tv_phone})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_qq_number:
                String qqNum = mTvQqNumber.getText().toString().trim();
                if (checkApkExist(this, "com.tencent.mobileqq")){
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin="+qqNum+"&version=1")));
                }else{
                    Toast.makeText(this,"本机未安装QQ应用",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_phone:
                String phoneNumber = mTvPhone.getText().toString().trim();
                Intent intent2 = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent2);
                break;
        }
    }

    public boolean checkApkExist(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(packageName,
                    PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }
}
