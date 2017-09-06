package com.arkui.transportation_shipper.owner.activity.my;

import android.view.View;
import android.widget.TextView;

import com.arkui.fz_tools._interface.UserInterface;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.mvp.UserPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.base.App;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyPointActivity extends BaseActivity implements UserInterface {

    @BindView(R.id.tv_integral)
    TextView mTvIntegral;
    private UserPresenter userPresenter;

    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_my_point);
       /* setTitle("我的积分");
        setRightIcon(R.mipmap.jifen_tixian);*/
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
     //   String integral = getIntent().getStringExtra("integral");
      //  mTvIntegral.setText(integral);
        userPresenter = new UserPresenter(this,this);
    }

    @OnClick({R.id.bt_withdraw, R.id.iv_back,R.id.iv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_withdraw:
                showActivity(PointWithdrawActivity.class);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_right:
                showActivity(WithdrawRecordActivity.class);
                break;
        }

    }

    @Override
    protected void onResume() {
        userPresenter.getUserInfo(App.getUserId());
    }

    @Override
    public void onSucceed() {

    }

    @Override
    public void loginSucceed(UserEntity userEntity) {
        mTvIntegral.setText(userEntity.getIntegral());
    }

   /* @Override
    protected void onRightClick() {
        super.onRightClick();
        showActivity(WithdrawRecordActivity.class);
    }*/
}
