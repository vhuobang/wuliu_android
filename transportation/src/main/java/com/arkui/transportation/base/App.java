package com.arkui.transportation.base;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.umeng.socialize.PlatformConfig;

import cn.jpush.android.api.JPushInterface;


public class App extends Application {

    //private UserInfoEntity mUserInfoEntity;

    private String mUser_id;

    private static App mApp;

    public static App getInstance() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
        JPushInterface.init(this);
       // UMShareAPI.get(this);
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
        PlatformConfig.setWeixin("wx279a4760d6a5d3d6", "daa919cf4e406ce79477c96f4d5f2f44");
        PlatformConfig.setQQZone("1106220644", "HEloPV9ioZEYnKD3");
    }

   /* public UserInfoEntity getUserInfoEntity() {
        if (mUserInfoEntity == null) {
            return (UserInfoEntity) FileUtil.read(mApp, Constants.USER_OBJECT);
        }
        return mUserInfoEntity;
    }*/

   /* public void setUserInfoEntity(UserInfoEntity userInfoEntity) {
        FileUtil.delete(mApp, Constants.USER_OBJECT);
        FileUtil.save(mApp, Constants.USER_OBJECT, userInfoEntity);
        mUserInfoEntity = userInfoEntity;
    }

    public String getUser_id() {
        if(mUser_id==null){
            if(getUserInfoEntity()==null){
                return null;
            }else{
                mUser_id=getUserInfoEntity().getUserId();
            }
        }
        return mUser_id;
    }*/

    /*public void deleteUserInfo(){
        mUser_id=null;
        mUserInfoEntity=null;
        FileUtil.delete(mApp, Constants.USER_OBJECT);
        SPUtil.getInstance(mApp).remove(Constants.IS_LOGIN);
    }*/

}
