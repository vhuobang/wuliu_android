package com.arkui.fz_net.utils;

import io.reactivex.observers.DisposableObserver;

/**
 * author: zhu.px
 * e-mail: 1271901673@qq.com
 * time  : 2017/7/24
 * desc  :
 */
public abstract class RxBusDisposable<T> extends DisposableObserver<T> {

    @Override
    public void onNext(T t) {
        try {
            onEvent(t);
        } catch (Exception e) {
            e.printStackTrace();
            onError(e);
        }
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
    }

    protected abstract void onEvent(T t) throws Exception;

}