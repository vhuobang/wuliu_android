package com.arkui.transportation.activity.my;

import android.app.Activity;
import android.content.Intent;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.arkui.fz_tools._interface.UserEditInterface;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.mvp.BaseMvpActivity;
import com.arkui.fz_tools.mvp.UserEditPresenter;
import com.arkui.transportation.R;
import com.arkui.transportation.base.App;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MyDataActivity extends BaseMvpActivity<UserEditPresenter> implements UserEditInterface, View.OnClickListener {

    @BindView(R.id.et_content)
    EditText mEtContent;
    @BindView(R.id.iv_clean)
    ImageView mIvClean;
    private int type;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_my_data);
        String title = getIntent().getStringExtra("title");
        setTitle(title);
        setRight("确定");

    }

    /**
     * 点击确定按钮
     */
    @Override
    protected void onRightClick() {
        String content = mEtContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            Toast.makeText(MyDataActivity.this, "输入要修改的内容", Toast.LENGTH_SHORT).show();
        }
        if (type == 101) { // qq昵称
            mPresenter.getUserEdit(App.getUserId(), content, App.getUserEntity().getAvatar(), App.getUserEntity().getQq());
        } else {
            mPresenter.getUserEdit(App.getUserId(), App.getUserEntity().getNickname(), App.getUserEntity().getAvatar(), content);
        }

    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mIvClean.setOnClickListener(this);
        type = getIntent().getIntExtra("type", 0);

        if (type == 101) {
            mEtContent.setHint("请输入昵称");
            mEtContent.setInputType(InputType.TYPE_CLASS_TEXT);
        } else {
            mEtContent.setHint("请输入QQ号码");
            mEtContent.setInputType(InputType.TYPE_CLASS_NUMBER);
        }
    }

    public static void openActivity(Activity activity, String title, int type) {
        Intent intent = new Intent(activity, MyDataActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("type", type);
        activity.startActivityForResult(intent, type);
    }

    //修改成功
    @Override
    public void onSuccess(UserEntity userEntity) {
        App.setUserEntity(userEntity);
        Intent intent =new Intent();
        intent.putExtra("userEntity",userEntity);
        setResult(RESULT_OK,intent);
        finish();

    }

    @Override
    public void onFail(String message) {

    }

    @Override
    public void initPresenter() {
        mPresenter.setUserEditInterface(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_clean:
                mEtContent.setText("");
                break;
        }
    }
}
