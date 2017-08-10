package com.arkui.transportation.activity.my;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;
import com.arkui.transportation.base.App;

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
    public void initData() {
      //  Glide.with(this).load(StrUtil.addHttps(App.getInstance().getUserInfoEntity().getAvatar())).bitmapTransform(new CropCircleTransformation(context)).into(mHeaderPic);
    //    Glide.with(this).load(App.getUserEntity().getAvatar()).into(mIvHead).
        UserEntity userEntity = App.getUserEntity();
        mIvNickname.setText(userEntity.getNickname());
    }

    @OnClick({R.id.iv_head, R.id.iv_nickname, R.id.tv_qq})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_head:
                showActivity(MyAvatarActivity.class);
                break;
            case R.id.iv_nickname:
                MyDataActivity.openActivity(mActivity,"昵称设置",101);
                break;
            case R.id.tv_qq:
                MyDataActivity.openActivity(mActivity,"QQ设置",102);
                break;
        }
    }
}
