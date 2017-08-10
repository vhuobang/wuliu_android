package com.arkui.transportation.base;

import android.app.Application;

import com.alibaba.fastjson.JSON;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.model.Constants;
import com.arkui.fz_tools.net.JsonData;
import com.arkui.fz_tools.utils.SPUtil;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.socialize.PlatformConfig;

import cn.jpush.android.api.JPushInterface;


public class App extends Application {


    private static UserEntity mUserEntity;

    private static String mUser_id;

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

    public static UserEntity getUserEntity() {

            String userObject = SPUtil.getInstance(mApp).read(Constants.USER_OBJECT, null);
            if (userObject != null) {
                mUserEntity= JsonData.getBean(UserEntity.class, userObject);
            }
            //return (UserEntity) FileUtil.read(mApp, Constants.USER_OBJECT);
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
                return null;
            }else{
                mUser_id=getUserEntity().getId();
            }
        }
        return mUser_id;
    }

    public  void deleteUserInfo(){
        mUser_id=null;
        mUserEntity=null;
        // FileUtil.delete(mApp, Constants.USER_OBJECT);
        SPUtil.getInstance(mApp).remove(Constants.USER_OBJECT);
        SPUtil.getInstance(mApp).remove(Constants.IS_LOGIN);
    }


}
