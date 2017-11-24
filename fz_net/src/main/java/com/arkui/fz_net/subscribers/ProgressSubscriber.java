package com.arkui.fz_net.subscribers;

import android.app.Activity;
import android.content.Context;
import android.util.MalformedJsonException;
import android.widget.Toast;

import com.arkui.fz_net.http.ApiException;
import com.arkui.fz_net.progress.ProgressCancelListener;
import com.arkui.fz_net.progress.ProgressDialogHandler;
import com.google.gson.JsonSyntaxException;

import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 用于在Http请求开始时，自动显示一个ProgressDialog
 * 在Http请求结束是，关闭ProgressDialog
 * 调用者自己对请求数据进行处理
 * Created by liukun on 16/3/10.
 */
public abstract class ProgressSubscriber<T> implements ProgressCancelListener, SubscriberOnNextListener ,Observer<T> {

    private ProgressDialogHandler mProgressDialogHandler;

    private Context context;

    private boolean mIsDialog;
    private Disposable mDisposable;

    public ProgressSubscriber(Activity activity, boolean isDialog) {
        this.context = activity;
        this.mIsDialog = isDialog;
        mProgressDialogHandler = new ProgressDialogHandler(activity, this, false);
    }


    public ProgressSubscriber(Activity activity) {
        this.context = activity;
        this.mIsDialog = true;
        mProgressDialogHandler = new ProgressDialogHandler(activity, this, false);
    }

    private void showProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    private void dismissProgressDialog() {
        if (mProgressDialogHandler != null) {
            mProgressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            mProgressDialogHandler = null;
        }
    }

    /**
     * 订阅开始时调用
     * 显示ProgressDialog
     *//*
    @Override
    public void onStart() {
        if (mIsDialog) {
            showProgressDialog();
        }
    }

    */

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable=d;
        getDisposable(d);
        if (mIsDialog) {
            showProgressDialog();
        }
    }

    /**
     * 获取disposable 在onDestroy方法中取消订阅disposable.dispose()
     */
    protected abstract void getDisposable(Disposable d);
    /**
     * 完成，隐藏ProgressDialog
     *//*
    @Override
    public void onCompleted() {
        if (mIsDialog) {
            dismissProgressDialog();
        }
    }*/




    @Override
    public void onComplete() {
        if (mIsDialog) {
            dismissProgressDialog();
        }
    }

    /**
     * 对错误进行统一处理
     * 隐藏ProgressDialog
     *
     * @param e
     */
    @Override
    public void onError(Throwable e) {
        if (e instanceof SocketTimeoutException || e instanceof ConnectException || e instanceof UnknownHostException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
            onNetError();
        } else if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            onApiError(apiException);
        } else if (e instanceof MalformedJsonException || e instanceof JsonSyntaxException) {
            Toast.makeText(context, "解析错误", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        dismissProgressDialog();
    }

    /**
     * 将onNext方法中的返回结果交给Activity或Fragment自己处理
     *
     * @param t 创建Subscriber时的泛型类型
     */

   /* @Override
    public void onNext(T t) {
    }*/

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }

    //服务器返回code 0了 这里对应处理
    @Override
    public void onApiError(ApiException e) {
        Toast.makeText(context, e.getMessage().trim(), Toast.LENGTH_SHORT).show();
    }

    //网络连接失败
    public void onNetError() {

    }

}