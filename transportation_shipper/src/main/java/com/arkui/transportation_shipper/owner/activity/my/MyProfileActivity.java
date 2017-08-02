package com.arkui.transportation_shipper.owner.activity.my;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation_shipper.R;

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
