package com.arkui.transportation.activity.my;

import android.content.ClipboardManager;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.arkui.fz_tools._interface.ShareCodeInterface;
import com.arkui.fz_tools.entity.ShareCodeEntity;
import com.arkui.fz_tools.mvp.ShareCodePresenter;
import com.arkui.fz_tools.ui.BaseShareActivity;
import com.arkui.transportation.R;
import com.arkui.transportation.base.App;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class InviteFriendActivity extends BaseShareActivity implements ShareCodeInterface {

    @BindView(R.id.tv_share_code)
    TextView tvShareCode;
    @BindView(R.id.tv_copy)
    TextView tvCopy;
    @BindView(R.id.tv_friend_number)
    TextView tvFriendNumber;
    @BindView(R.id.tv_friend_list)
    TextView tvFriendList;
    @BindView(R.id.iv_wx)
    ImageView ivWx;
    @BindView(R.id.iv_qq)
    ImageView ivQq;
    @BindView(R.id.iv_wx_circle)
    ImageView ivWxCircle;
    ShareCodePresenter shareCodePresenter;
    @Override
    public void setRootView() {
        setContentView(R.layout.activity_invite_friend);
        setTitle("邀请好友");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        shareCodePresenter= new ShareCodePresenter(this,this);
    }

    @OnClick({R.id.iv_wx, R.id.iv_qq, R.id.iv_wx_circle,R.id.tv_copy, R.id.tv_friend_list})
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
            case R.id.tv_copy:
                String shareCode = tvShareCode.getText().toString();
                if (!TextUtils.isEmpty(shareCode)){
                    copy(shareCode,InviteFriendActivity.this);
                }
                break;
            case R.id.tv_friend_list:

                break;
        }
    }

    @Override
    public void initData() {
        shareCodePresenter.getShareCode(App.getUser_id());
    }

    public  void copy(String content, Context context) {
           // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context
                .getSystemService(Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
        Toast.makeText(this,"已复制",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(ShareCodeEntity shareCodeEntity) {
        tvShareCode.setText(shareCodeEntity.getRandNum());
        tvFriendNumber.setText(shareCodeEntity.getFriendsNum());
    }

    @Override
    public void onFail(String message) {
        Toast.makeText(InviteFriendActivity.this,message,Toast.LENGTH_SHORT).show();
    }
}
