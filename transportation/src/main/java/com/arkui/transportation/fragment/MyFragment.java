package com.arkui.transportation.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arkui.fz_tools._interface.UserInterface;
import com.arkui.fz_tools.dialog.ShareDialog;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.mvp.UserPresenter;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.transportation.R;
import com.arkui.transportation.activity.my.AuthActivity;
import com.arkui.transportation.activity.my.ContactServiceActivity;
import com.arkui.transportation.activity.my.InviteFriendActivity;
import com.arkui.transportation.activity.my.MyBalanceActivity;
import com.arkui.transportation.activity.my.MyInfoFeeActivity;
import com.arkui.transportation.activity.my.MyPointActivity;
import com.arkui.transportation.activity.my.MyProfileActivity;
import com.arkui.transportation.activity.my.SettingActivity;
import com.arkui.transportation.base.App;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 基于基类的Fragment
 */
public class MyFragment extends BaseFragment implements UserInterface {

    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.iv_head)
    ImageView ivHead;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.ll_auth)
    LinearLayout llAuth;
    @BindView(R.id.ll_balance)
    LinearLayout llBalance;
    @BindView(R.id.ll_point)
    LinearLayout llPoint;
    @BindView(R.id.ll_info_fee)
    LinearLayout llInfoFee;
    @BindView(R.id.ll_share)
    LinearLayout llShare;
    @BindView(R.id.ll_service)
    LinearLayout llService;
    @BindView(R.id.ll_driver_login)
    LinearLayout llDriverLogin;

    @BindView(R.id.tv_auth)
    TextView tvAuth;
    @BindView(R.id.balance)
    TextView mBalance;
    @BindView(R.id.integral)
    TextView mIntegral;
    @BindView(R.id.infomation_fee_all)
    TextView mInfomationFeeAll;

    private ShareDialog mShareDialog;
    private UserPresenter userPresenter;
    private String isUserCertificate;
    private String isCompanyCertificate;
   private  UserEntity mUserEntity;
    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_my, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);
        userPresenter = new UserPresenter(this, getActivity());
        mShareDialog = new ShareDialog();
    }

    @OnClick({R.id.ll_balance, R.id.ll_point, R.id.ll_share, R.id.ll_service, R.id.ll_driver_login, R.id.iv_setting, R.id.iv_head, R.id.ll_auth, R.id.ll_info_fee})
    public void onClick(View view) {

      Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.ll_balance:
               // bundle.putString("balance",mUserEntity.getBalance());
                showActivity(MyBalanceActivity.class);
                break;
            case R.id.ll_point:
         //       bundle.putString("points",mUserEntity.getIntegral());
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
              /*  showActivity(DriverLoginActivity.class);
                getActivity().finish();*/
                break;
            case R.id.iv_setting:
                showActivity(SettingActivity.class);
                break;
            case R.id.iv_head:
                showActivity(MyProfileActivity.class);
                break;
            case R.id.ll_auth:

                if (isUserCertificate.equals("0") && isCompanyCertificate.equals("0") ) {
                    showActivity(AuthActivity.class);
                }
                if (isUserCertificate.equals("2") || isCompanyCertificate.equals("2")) {
                    Toast.makeText(getActivity(), "已认证", Toast.LENGTH_SHORT).show();
                }
                if ((isUserCertificate.equals("1") || isCompanyCertificate.equals("1")) &&!(isUserCertificate.equals("2") || isCompanyCertificate.equals("2")) ) {
                    Toast.makeText(getActivity(), "审核中", Toast.LENGTH_SHORT).show();
                }

                if (isUserCertificate.equals("3")|| isCompanyCertificate.equals("3")){
                    showActivity(AuthActivity.class);
                }

                break;
            case R.id.ll_info_fee:
                bundle.putString("informationFee",mUserEntity.getInformationFeeAll());
                showActivity(MyInfoFeeActivity.class,bundle);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        userPresenter.getUserInfo(App.getUserId());
    }

    // 暂不使用
    @Override
    public void onSucceed() {

    }

    // 返回用户信息
    @Override
    public void loginSucceed(UserEntity userEntity) {
        mUserEntity =userEntity;
        GlideUtils.getInstance().loadRound(getActivity(), userEntity.getAvatar(), ivHead);
        tvName.setText(userEntity.getNickname());
        App.setUserEntity(userEntity);
        isUserCertificate = userEntity.getIsUserCertificate();
        isCompanyCertificate = userEntity.getIsCompanyCertificate();
        mBalance.setText("￥"+userEntity.getBalance());
        mIntegral.setText( userEntity.getIntegral());
        mInfomationFeeAll.setText("￥"+userEntity.getInformationFeeAll());
        if (isUserCertificate.equals("0") && isCompanyCertificate.equals("0")) {
            tvAuth.setText("未认证 立即认证");
        }
        if (isUserCertificate.equals("1") || isCompanyCertificate.equals("1")) {
            tvAuth.setText("审核中");
        }
        if (isUserCertificate.equals("2") || isCompanyCertificate.equals("2")) {
            tvAuth.setText("已认证");
        }
        if (isUserCertificate.equals("3")|| isCompanyCertificate.equals("3")){
            tvAuth.setText("重新认证");
        }

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (userPresenter !=null){
            userPresenter.onDestroy();
        }

    }
}
