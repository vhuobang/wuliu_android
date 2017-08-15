package com.arkui.transportation_owner.activity.my;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.GlideUtils;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.base.App;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MyProfileActivity extends BaseActivity {

    @BindView(R.id.iv_head)
    ImageView mIvHead;
    @BindView(R.id.iv_nickname)
    TextView mIvNickname;
    @BindView(R.id.tv_qq)
    TextView mTvQq;
    @BindView(R.id.tv_phone)
    TextView mTvPhone;

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_my_profile);
        setTitle("我的资料");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        UserEntity userEntity = App.getUserEntity();
        GlideUtils.getInstance().loadCircle(this,userEntity.getAvatar(),mIvHead);
        mIvNickname.setText(userEntity.getNickname());
        if (!TextUtils.isEmpty(userEntity.getQq())){
            mTvQq.setText(userEntity.getQq());
        }else {
            mTvQq.setText("未填写");
        }
        mTvPhone.setText(userEntity.getMobile());
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.iv_head, R.id.iv_nickname, R.id.tv_qq})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                Intent intent = new Intent(MyProfileActivity.this, MyAvatarActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_nickname:
                MyDataActivity.openActivity(mActivity,"昵称设置",101);
                break;
            case R.id.tv_qq:
                MyDataActivity.openActivity(mActivity,"QQ设置",102);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            switch (requestCode){
                case 101: // 修改昵称
                    UserEntity userEntity = (UserEntity) data.getSerializableExtra("userEntity");
                    mIvNickname.setText(userEntity.getNickname());
                    break;
                case 102 : // 修改qq
                    UserEntity userEntity1 = (UserEntity) data.getSerializableExtra("userEntity");
                    mTvQq.setText(userEntity1.getQq());
                    break;
            }
        }
    }
}