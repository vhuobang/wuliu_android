package com.arkui.fz_tools.ui;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.widget.Toast;

import com.arkui.fz_tools.R;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.umeng.socialize.utils.SocializeUtils;

import io.reactivex.functions.Consumer;

/**
 * Created by nmliz on 2017/8/3.
 */

public abstract class BaseShareActivity extends BaseActivity{


    private ProgressDialog mDialog;

    public final int QQ=1;
    public final int WX=2;
    public final int WX_CIRCLE=3;
    private UMWeb mWeb;
    private RxPermissions mRxPermissions;

    @Override
    public void initView() {
        super.initView();
        mRxPermissions=new RxPermissions(mActivity);
        mDialog = new ProgressDialog(this);

        // TODO 没权限 不能分享QQ 也不知道为什么哦
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mRxPermissions.request(Manifest.permission.WRITE_EXTERNAL_STORAGE).subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean aBoolean) throws Exception {
                    if(!aBoolean){
                        Toast.makeText(mActivity, "没有存储权限，QQ无法正常分享哦，建议你允许！", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    public void showShare(int type,String url){

        mWeb = new UMWeb(url);
        mWeb.setTitle("危货帮");
        mWeb.setThumb(new UMImage(this, R.mipmap.about_logo));
        mWeb.setDescription("运危货，就找危货帮！");
        switch (type){
            case QQ:
                new ShareAction(mActivity)
                        .withText("this is title")
                        .withMedia(mWeb)
                        .setPlatform(SHARE_MEDIA.QQ)
                        .setCallback(mShareListener).share();
                break;
            case WX:
                new ShareAction(mActivity)
                        .withText("this is title")
                        .withMedia(mWeb)
                        .setPlatform(SHARE_MEDIA.WEIXIN)
                        .setCallback(mShareListener).share();
                break;
            case WX_CIRCLE:
                new ShareAction(mActivity)
                        .withText("this is title")
                        .withMedia(mWeb)
                        .setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE)
                        .setCallback(mShareListener).share();
                break;
        }
    }

    private UMShareListener mShareListener = new UMShareListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            SocializeUtils.safeShowDialog(mDialog);
        }
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(mActivity, "成功了", Toast.LENGTH_LONG).show();
            SocializeUtils.safeCloseDialog(mDialog);
        }
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            SocializeUtils.safeCloseDialog(mDialog);
            Toast.makeText(mActivity, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            SocializeUtils.safeCloseDialog(mDialog);
            Toast.makeText(mActivity, "取消了", Toast.LENGTH_LONG).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }
}
