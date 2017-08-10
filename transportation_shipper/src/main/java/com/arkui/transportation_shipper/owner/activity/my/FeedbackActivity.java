package com.arkui.transportation_shipper.owner.activity.my;

import android.widget.EditText;

import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools.mvp.BaseMvpActivity;
import com.arkui.fz_tools.mvp.PublicPresenter;
import com.arkui.fz_tools.utils.ToastUtil;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.base.App;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FeedbackActivity extends BaseMvpActivity<PublicPresenter> implements PublicInterface {


    @BindView(R.id.et_face_back)
    EditText etFaceBack;
    @BindView(R.id.et_phone_number)
    EditText etPhoneNumber;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_feedback);
        setTitle("意见反馈");
        setRight("提交");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);

    }

    @Override
    protected void onRightClick() {
        String content = etFaceBack.getText().toString().trim();
        String phoneNumber = etPhoneNumber.getText().toString().trim();
        mPresenter.getFaceBack(App.getUserId(),content,phoneNumber);
    }


    @Override
    public void onSuccess() {
        ToastUtil.showToast(FeedbackActivity.this,"反馈成功");
        finish();
    }

    @Override
    public void onFail(String message) {
        ToastUtil.showToast(FeedbackActivity.this,message);
    }

    @Override
    public void initPresenter() {
      mPresenter.setPublicInterface(this);
    }
}
