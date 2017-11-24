package com.arkui.transportation_owner.activity.my;

import android.text.TextUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools.api.PayApi;
import com.arkui.fz_tools.mvp.WithDrawPresenter;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.view.ShapeButton;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.base.App;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PointWithdrawActivity extends BaseActivity implements PublicInterface {

    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.iv_clean)
    ImageView mIvClean;
    @BindView(R.id.bt_withdraw)
    ShapeButton mBtWithdraw;
    private PayApi mPayApi;
    private WithDrawPresenter withDrawPresenter;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_point_withdraw);
        setTitle("申请提现");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        withDrawPresenter = new WithDrawPresenter(this, this);

    }

    @OnClick(R.id.bt_withdraw)
    public void onViewClicked() {
        String trim = mEtContent.getText().toString().trim();
        if (TextUtils.isEmpty(trim)){
            Toast.makeText(PointWithdrawActivity.this,"不能为空",Toast.LENGTH_SHORT).show();
            return;
        }

    //    mPayApi.getWithDraw()
        withDrawPresenter.getIntegral(App.getUserId(),"2",trim);

    }

    @Override
    public void onSuccess() {
        Toast.makeText(PointWithdrawActivity.this,"提现成功",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onFail(String message) {
        Toast.makeText(PointWithdrawActivity.this,message,Toast.LENGTH_SHORT).show();
    }
}
