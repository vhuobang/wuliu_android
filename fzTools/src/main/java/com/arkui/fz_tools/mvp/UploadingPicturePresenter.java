package com.arkui.fz_tools.mvp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.arkui.fz_net.http.HttpResultFunc;
import com.arkui.fz_net.http.RetrofitFactory;
import com.arkui.fz_net.subscribers.ProgressSubscriber;
import com.arkui.fz_tools._interface.UploadingPictureInterface;
import com.arkui.fz_tools.api.PublicApi;
import com.arkui.fz_tools.entity.UpLoadEntity;
import com.arkui.fz_tools.model.Constants;
import com.arkui.fz_tools.utils.LogUtil;

import java.io.ByteArrayOutputStream;
import java.io.File;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by nmliz on 2017/8/9.
 * 上传图片的功能
 */

public class UploadingPicturePresenter extends BasePresenter {


    UploadingPictureInterface mUploadingPictureInterface;
    private final PublicApi mPublicApi;

    public UploadingPicturePresenter(UploadingPictureInterface mUploadingPictureInterface, Activity activity) {
        this.mUploadingPictureInterface = mUploadingPictureInterface;
        this.mContext = activity;
        mPublicApi = RetrofitFactory.createRetrofit(PublicApi.class);
    }

    //上传图片
    public void upPicture(String path, final String type) {
        if (path == null) {
            Toast.makeText(mContext, "请选择图片", Toast.LENGTH_SHORT).show();
            return;
        }
        Observable.just(path)
                .map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        //调用图片转base64
                        return Base64.encodeToString(getBytes(s), Base64.NO_WRAP);
                    }
                }).flatMap(new Function<String, ObservableSource<UpLoadEntity>>() {
            @Override
            public ObservableSource<UpLoadEntity> apply(String s) throws Exception {
                return mPublicApi.upload(type, s).map(new HttpResultFunc<UpLoadEntity>());
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new ProgressSubscriber<UpLoadEntity>(mContext) {
                    @Override
                    public void onNext(UpLoadEntity value) {
                        //回调给UI
                        mUploadingPictureInterface.onUploadingSuccess(value);
                    }
                });
    }

    private byte[] getBytes(String path) {
        //读取图片 只读边,不读内容
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, newOpts);
        //开始按比例缩放图片
        newOpts.inJustDecodeBounds = false;
        int width = newOpts.outWidth;
        int height = newOpts.outHeight;
        float maxSize = 1200;
        int be = 1;
        if (width >= height && width > maxSize) {//缩放比,用高或者宽其中较大的一个数据进行计算
            be = (int) (newOpts.outWidth / maxSize);
            be++;
        } else if (width < height && height > maxSize) {
            be = (int) (newOpts.outHeight / maxSize);
            be++;
        }
        newOpts.inSampleSize = be;//设置采样率
        newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;//该模式是默认的,可不设
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收
        //下面可是图片压缩
        Bitmap bitmap = BitmapFactory.decodeFile(path, newOpts);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int options = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//质量压缩方法，把压缩后的数据存放到baos中 (100表示不压缩，0表示压缩到最小)
        while (baos.toByteArray().length > 100 * 1024) {//循环判断如果压缩后图片是否大于指定大小,大于继续压缩
            baos.reset();//重置baos即让下一次的写入覆盖之前的内容
            options -= 5;//图片质量每次减少5
            if (options <= 5) options = 5;//如果图片质量小于5，为保证压缩后的图片质量，图片最底压缩质量为5
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//将压缩后的图片保存到baos中
            if (options == 5) break;//如果图片的质量已降到最低则，不再进行压缩
        }
        /*ByteArrayOutputStream bos = new ByteArrayOutputStream();
        Log.i("guoguo","path="+path+",bitmap="+bitmap);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
        LogUtil.i("size=="+bos.toByteArray().length);
        int option = 100;
        while (bos.toByteArray().length > 204800 && option > 10) {
            bos.reset();
            bitmap.compress(Bitmap.CompressFormat.JPEG, option, bos);
            option -= 10;
        }*/
        LogUtil.i("size==" + baos.toByteArray().length);
        return baos.toByteArray();
    }
}
