package com.arkui.transportation_owner.base;

import android.support.multidex.MultiDexApplication;

import com.alibaba.fastjson.JSON;
import com.arkui.fz_tools.entity.UserEntity;
import com.arkui.fz_tools.model.Constants;
import com.arkui.fz_tools.net.JsonData;
import com.arkui.fz_tools.utils.SPUtil;
import com.squareup.leakcanary.LeakCanary;
import com.umeng.socialize.PlatformConfig;
/*
 * 这是一条隐藏的注释 因为 我写到import两行中间了 默认折叠你看不到这条注释的
 *  如果你能看到这行注释，说明我已经离职，把代码托(甩)付(锅)给你了，，，
 *  Ps:给你一些忠告
 *  这家公司坑爹的很，日常辞人，卸磨杀驴，什么事都干，锤子福利都没有，扣钱规定
 *  屁事多。
 *  然你入职这家公司了，你就自求多福吧,或者早日跑路吧！
 *  在ps：
 *  我的代码估计只有上帝能看懂了，如果你能看懂我的代码，说明你已经超（屌）神（炸）了（天）
 *  但我感觉这公司不会花那么多钱雇那么屌的人。
 *  原真主与你同在，阿门，保佑你还有这个项目。
 */
import cn.jpush.android.api.JPushInterface;


public class App extends MultiDexApplication {

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
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
        PlatformConfig.setWeixin("wx96bfd7f8eafa5940", "e30acd76f48dcb72946ca0ecf818a778");
        PlatformConfig.setQQZone("1106145377", "Nn7ceRa1ba4Bx95v");
        //  mActivityComponent = Dag.builder().appModule(new AppModule(this)).build();
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
        mUser_id=userInfoEntity.getId();
        SPUtil.getInstance(mApp).remove(Constants.USER_OBJECT);
        String userObject = JSON.toJSONString(userInfoEntity);
        SPUtil.getInstance(mApp).save(Constants.USER_OBJECT, userObject);
        SPUtil.getInstance(mApp).save(Constants.IS_LOGIN, true);
        mUserEntity = userInfoEntity;
    }

    public static String getUserId() {
        if (mUser_id == null) {
            if (getUserEntity() == null) {
                return "";
            } else {
                mUser_id = getUserEntity().getId();
            }
        }
        return mUser_id;
    }

    public void deleteUserInfo() {
        mUser_id = null;
        mUserEntity = null;
        // FileUtil.delete(mApp, Constants.USER_OBJECT);
        SPUtil.getInstance(mApp).remove(Constants.USER_OBJECT);
        SPUtil.getInstance(mApp).remove(Constants.IS_LOGIN);
    }

    public static boolean isLogin() {
        return SPUtil.getInstance(mApp).read(Constants.IS_LOGIN, false);
    }

}
