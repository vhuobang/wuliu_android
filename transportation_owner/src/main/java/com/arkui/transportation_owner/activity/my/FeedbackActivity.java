package com.arkui.transportation_owner.activity.my;

import android.widget.EditText;
import android.widget.Toast;

import com.arkui.fz_tools._interface.PublicInterface;
import com.arkui.fz_tools.mvp.BaseMvpActivity;
import com.arkui.fz_tools.mvp.PublicPresenter;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.base.App;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FeedbackActivity extends BaseMvpActivity<PublicPresenter> implements PublicInterface {


    @BindView(R.id.et_content)
    EditText etContent;
    @BindView(R.id.et_phone)
    EditText etPhone;

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
    public void initData() {


    }

    @Override
    protected void onRightClick() {
        String content = etContent.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        mPresenter.getFaceBack(App.getUserId(),content,phone);

    }

    @Override
    public void onSuccess() {
        Toast.makeText(FeedbackActivity.this,"反馈成功",Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onFail(String message) {
        Toast.makeText(FeedbackActivity.this,message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initPresenter() {
        mPresenter.setPublicInterface(this);
    }

}

