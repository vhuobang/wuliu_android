package com.arkui.transportation.activity.my;

import android.view.View;

import com.arkui.fz_tools.ui.BaseShareActivity;
import com.arkui.transportation.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class InviteFriendActivity extends BaseShareActivity {

    @Override
    public void setRootView() {
        setContentView(R.layout.activity_invite_friend);
        setTitle("邀请好友");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_wx, R.id.iv_qq, R.id.iv_wx_circle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_wx:
                showShare(WX);
                break;
            case R.id.iv_qq:
                showShare(QQ);
                break;
            case R.id.iv_wx_circle:
                showShare(WX_CIRCLE);
                break;
        }
    }
}
