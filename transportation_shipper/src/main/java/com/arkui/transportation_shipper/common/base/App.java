package com.arkui.transportation_shipper.common.base;

import android.support.multidex.MultiDexApplication;

import com.alibaba.fastjson.JSON;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.model.Constants;
import com.arkui.fz_tools.net.JsonData;
import com.arkui.fz_tools.utils.SPUtil;
import com.squareup.leakcanary.LeakCanary;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.socialize.PlatformConfig;

import cn.jpush.android.api.JPushInterface;


public class App extends MultiDexApplication {

    private static UserEntity mUserEntity;

    private static String mUser_id;

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
        CrashReport.initCrashReport(getApplicationContext(), "d6fe3a6b65", true);
    }

    public static UserEntity getUserEntity() {
        if (mUserEntity == null) {
            String userObject = SPUtil.getInstance(mApp).read(Constants.USER_OBJECT, null);
            if (userObject != null) {
                return JsonData.getBean(UserEntity.class, userObject);
            }
            //return (UserEntity) FileUtil.read(mApp, Constants.USER_OBJECT);
        }
        return mUserEntity;
    }

    public static void setUserEntity(UserEntity userInfoEntity) {
       /* FileUtil.delete(mApp, Constants.USER_OBJECT);
        FileUtil.save(mApp, Constants.USER_OBJECT, userInfoEntity);
        mUserInfoEntity = userInfoEntity;*/
        SPUtil.getInstance(mApp).remove(Constants.USER_OBJECT);
        String userObject = JSON.toJSONString(userInfoEntity);
        SPUtil.getInstance(mApp).save(Constants.USER_OBJECT,userObject);
        SPUtil.getInstance(mApp).save(Constants.IS_LOGIN,true);
        mUserEntity=userInfoEntity;
    }

    public static String getUserId() {
        if(mUser_id==null){
            if(getUserEntity()==null){
                return "";
            }else{
                mUser_id=getUserEntity().getId();
            }
        }
        return mUser_id;
    }

    public void deleteUserInfo(){
        mUser_id=null;
        mUserEntity=null;
        // FileUtil.delete(mApp, Constants.USER_OBJECT);
        SPUtil.getInstance(mApp).remove(Constants.USER_OBJECT);
        SPUtil.getInstance(mApp).remove(Constants.IS_LOGIN);
    }

}
