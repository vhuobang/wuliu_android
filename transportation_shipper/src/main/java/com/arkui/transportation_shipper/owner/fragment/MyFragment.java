package com.arkui.transportation_shipper.owner.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arkui.fz_tools._interface.UserInterface;
import com.arkui.fz_tools.dialog.ShareDialog;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.model.Constants;
import com.arkui.fz_tools.mvp.UserPresenter;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.fz_tools.utils.SPUtil;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.activity.DriverLoginActivity;
import com.arkui.transportation_shipper.common.base.App;
import com.arkui.transportation_shipper.owner.activity.my.AuthActivity;
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
public class MyFragment extends BaseFragment implements UserInterface {

    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_balance)
    TextView mBalance;
    @BindView(R.id.integral)
    TextView mIntegral;

    private ShareDialog mShareDialog;
    private UserPresenter userPresenter;
    private String isUserCertificate;
    private String isCompanyCertificate;

    @BindView(R.id.tv_auth)
    TextView tvAuth;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        mShareDialog = new ShareDialog();
        userPresenter = new UserPresenter(this, getActivity());
    }

    @OnClick({R.id.ll_balance, R.id.ll_point, R.id.ll_share, R.id.ll_service, R.id.ll_driver_login, R.id.iv_setting, R.id.iv_head, R.id.ll_auth})
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.ll_balance:
             //   bundle.putString("balance",mUserEntity.getBalance());
                showActivity(MyBalanceActivity.class);
                break;
            case R.id.ll_point:
               // bundle.putString("integral",mUserEntity.getIntegral());
                showActivity(MyPointActivity.class);
                break;
            case R.id.ll_share:
                // mShareDialog.show(getChildFragmentManager(), "share");
                showActivity(InviteFriendActivity.class);
                break;
            case R.id.ll_service:
                showActivity(ContactServiceActivity.class);
                break;
            case R.id.ll_driver_login:
                SPUtil.getInstance(mContext).remove(Constants.IS_LOGIN);
                App.getInstance().deleteUserInfo();
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
                if (isUserCertificate.equals("0") && isCompanyCertificate.equals("0")) {
                    showActivity(AuthActivity.class);
                }
                if (isUserCertificate.equals("1") || isCompanyCertificate.equals("1")) {
                    Toast.makeText(getActivity(), "审核中", Toast.LENGTH_SHORT).show();

                }
                if (isUserCertificate.equals("2") || isCompanyCertificate.equals("2")) {
                    Toast.makeText(getActivity(), "已认证", Toast.LENGTH_SHORT).show();
                }

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
   private  UserEntity mUserEntity;
    @Override
    public void loginSucceed(UserEntity userEntity) {
        mUserEntity = userEntity;
        GlideUtils.getInstance().loadRound(getActivity(), userEntity.getAvatar(), ivHead);
        tvName.setText(userEntity.getNickname());
        isUserCertificate = userEntity.getIsUserCertificate();
        isCompanyCertificate = userEntity.getIsCompanyCertificate();
        mIntegral.setText(userEntity.getIntegral());
        mBalance.setText("￥"+userEntity.getBalance());
        if (isUserCertificate.equals("0") && isCompanyCertificate.equals("0")) {
            tvAuth.setText("未认证 立即认证");
        }
        if (isUserCertificate.equals("1") || isCompanyCertificate.equals("1")) {
            tvAuth.setText("审核中");

        }
        if (isUserCertificate.equals("2") || isCompanyCertificate.equals("2")) {
            tvAuth.setText("已认证");
        }
    }


}
