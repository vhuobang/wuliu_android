package com.arkui.fz_net.http;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;

public class HttpMethod {

    private static volatile HttpMethod defaultHttpMethod;

    protected HttpMethod() {

    }

    public static HttpMethod getInstance() {
        HttpMethod httpMethod = defaultHttpMethod;
        if (defaultHttpMethod == null) {
            synchronized (HttpMethod.class) {
                httpMethod = defaultHttpMethod;
                if (defaultHttpMethod == null) {
                    httpMethod = new HttpMethod();
                    defaultHttpMethod = httpMethod;
                }
            }
        }
        return httpMethod;
    }

    /**
     * 观察者模式，把observable（被观察者）和subscriber (观察者)通过subscribe实现订阅关系
     * 由于网络请求是耗时任务，所以我们必须添加线程管理
     *
     * @param observable
     * @param subscriber
     * @param <T>
     */
    protected <T> void toSubscribe(Observable<T> observable, Observer<T> subscriber) {
      observable
                /*
                订阅关系发生在IO线程中,Schedulers叫调度器，subsribeOn( )操作符可以指定observable运行的线程
                而我设定的调度器的类型是Schedulers.io()。Schedulers.io()是 I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。
                行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，
                可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。
                 */
                .subscribeOn(Schedulers.io())
                /*
                解除订阅关系也发生在IO线程中
                 */
                .unsubscribeOn(Schedulers.io())
                /*
                指定subscriber (观察者)的回调在主线程中，
                observeOn的作用是指定subscriber（观察者）将会在哪个Scheduler观察这个Observable,
                由于subscriber已经能取到界面所关心的数据了，所以设定指定subscriber的回调在主线程中
                 */
                .observeOn(AndroidSchedulers.mainThread())
                /*
                订阅观察者，subscribe就相当于setOnclickListener()
                 */

                .subscribe(subscriber);
        //subscribeOn影响的是它调用之前的代码（也就是observable），observeOn影响的是它调用之后的代码（也就是subscribe()）
    }

    public <T> void  getNetData(Observable<T> observable, Observer<T> subscriber) {
        //  Observable<List<CartListEntity>> observable = mApiService.getCartList(App.getInstance().getUser_id(),20, page).map();
        toSubscribe(observable, subscriber);
    }


}
