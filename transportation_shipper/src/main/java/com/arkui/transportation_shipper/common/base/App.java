package com.arkui.transportation_shipper.common.base;

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
    {
        //
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
        PlatformConfig.setWeixin("wx3c39e74c1cfd50ff", "cc9b61cd01e86e82faffd1e63885cf5e");
        PlatformConfig.setQQZone("1106145523", "Z68u61G0Eg1pEO4Y");
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
