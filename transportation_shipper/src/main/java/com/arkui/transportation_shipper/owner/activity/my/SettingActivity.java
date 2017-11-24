package com.arkui.transportation_shipper.owner.activity.my;

import android.view.View;
import android.widget.TextView;

import com.arkui.fz_tools.dialog.CommonDialog;
import com.arkui.fz_tools.listener.OnConfirmClick;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.AppManager;
import com.arkui.fz_tools.utils.FileUtil;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.activity.LoginActivity;
import com.arkui.transportation_shipper.common.base.App;

import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;


public class SettingActivity extends BaseActivity implements OnConfirmClick {
    private CommonDialog mOutDialog;
    @BindView(R.id.tv_version)
    TextView tvVersion;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_setting);
        setTitle("设置");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mOutDialog = new CommonDialog();
        mOutDialog.setTitle("退出登录").setContent("确定退出当前账户吗？");
        mOutDialog.setConfirmClick(this);
        String appVersionName = FileUtil.getAppVersionName(mActivity);
        tvVersion.setText(appVersionName);
    }

    @OnClick({R.id.tl_about, R.id.tr_feedback, R.id.bt_out,R.id.tr_soft_des})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tl_about:
                showActivity(AboutActivity.class);
                break;
            case R.id.tr_feedback:
                showActivity(FeedbackActivity.class);
                break;
            case R.id.bt_out:
                mOutDialog.show(getSupportFragmentManager(),"out");
                break;
            case R.id.tr_soft_des:
                showActivity(SoftWareDescriptionActivity.class);
                break;
        }
    }
   // 退出登陆
    @Override
    public void onConfirmClick() {
        App.getInstance().deleteUserInfo();
        JPushInterface.setAlias(mActivity, "", new TagAliasCallback() {
            @Override
            public void gotResult(int i, String s, Set<String> set) {

            }
        });
        AppManager.getAppManager().finishAllActivity();
        showActivity(LoginActivity.class);
    }
}
