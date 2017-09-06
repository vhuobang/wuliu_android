package com.arkui.transportation_shipper.driver.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkui.fz_tools._interface.UserInterface;
import com.arkui.fz_tools.dialog.ShareDialog;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.mvp.UserPresenter;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.activity.DriverLoginActivity;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.driver.activity.my.DriverAuthActivity;
import com.arkui.transportation_shipper.owner.activity.my.ContactServiceActivity;
import com.arkui.transportation_shipper.owner.activity.my.InviteFriendActivity;
import com.arkui.transportation_shipper.owner.activity.my.MyBalanceActivity;
import com.arkui.transportation_shipper.owner.activity.my.MyPointActivity;
import com.arkui.transportation_shipper.owner.activity.my.MyProfileActivity;
import com.arkui.transportation_shipper.owner.activity.my.SettingActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 基于基类的Fragment
 */
public class DriverMyFragment extends BaseFragment implements UserInterface {
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.integral)
    TextView mIntegral;
    @BindView(R.id.balance)
    TextView mBalance;

    private ShareDialog mShareDialog;
    private UserPresenter userPresenter;
    private String isUserCertificate;
    @BindView(R.id.tv_auth)
    TextView tvAuth;
    private UserEntity mUserEntity;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_driver_my, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        mShareDialog = new ShareDialog();
        userPresenter= new UserPresenter(this,getActivity());
    }

    @OnClick({R.id.ll_balance, R.id.ll_point, R.id.ll_share, R.id.ll_service, R.id.ll_driver_login, R.id.iv_setting, R.id.iv_head, R.id.ll_auth})
    public void onClick(View view) {

        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.ll_balance:

               // bundle.putString("balance",mUserEntity.getBalance());
                showActivity(MyBalanceActivity.class);
                break;
            case R.id.ll_point:
               // bundle.putString("integral",mUserEntity.getIntegral());
                showActivity(MyPointActivity.class);
                break;
            case R.id.ll_share:
                //mShareDialog.show(getChildFragmentManager(), "share");
                showActivity(InviteFriendActivity.class);
                break;
            case R.id.ll_service:
                showActivity(ContactServiceActivity.class);
                break;
            case R.id.ll_driver_login:
                showActivity(DriverLoginActivity.class);
                getActivity().finish();
                break;
            case R.id.iv_setting:
                showActivity(SettingActivity.class);
                break;
            case R.id.iv_head:
                showActivity(MyProfileActivity.class);
                break;
            case R.id.ll_auth:
                showActivity(DriverAuthActivity.class);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        userPresenter.getUserInfo(App.getUserId());
    }

    @Override
    public void onSucceed() {

    }

    @Override
    public void loginSucceed(UserEntity userEntity) {
        mUserEntity = userEntity;
        GlideUtils.getInstance().loadRound(getActivity(), userEntity.getAvatar(), ivHead);
        tvName.setText(userEntity.getNickname());

        isUserCertificate = userEntity.getIsUserCertificate();
          mIntegral.setText(userEntity.getIntegral());
          mBalance.setText("￥" + userEntity.getBalance());
        if (isUserCertificate.equals("0") ) {
            tvAuth.setText("未认证 立即认证");
        }
        if (isUserCertificate.equals("1")) {
            tvAuth.setText("审核中");
        }
        if (isUserCertificate.equals("2")) {
            tvAuth.setText("已认证");
        }
    }
}
