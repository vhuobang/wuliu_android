package com.arkui.transportation_shipper.owner.utils;

import android.content.Context;

import com.alibaba.fastjson.JSON;
import com.arkui.fz_tools.entity.City;
import com.arkui.fz_tools.utils.FileUtil;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * Created by nmliz on 2017/7/4.
 * 用于初始化 选择器里面的数据
 */

public class LoadCityData {

    public static Disposable initData(final Context context, Consumer<List<City>> onNext) {
        Disposable subscribe = Observable.just("cities.txt").map(new Function<String, List<City>>() {
            @Override
            public List<City> apply(String s) throws Exception {
                //读取asset 里面的数据
                String string = FileUtil.readAssets(context, s);
                return JSON.parseArray(string, City.class);
            }
           /* @Override
            public List<City> call(String name) {
                //读取asset 里面的数据
                String string = FileUtil.readAssets(context, name);
                return JSON.parseArray(string, City.class);
            }*/
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(onNext);
               /* .subscribe(onNext
                        *//*ic_new ProgressSubscriber<List<City>>(getActivity(), false) {
                    @Override
                    public void onNext(List<City> cityList) {
                        mAddressPicker.setCities(cityList);
                    }
                }*//*
                        *//*ic_new Action1<List<City>>() {
                            @Override
                            public void call(List<City> cityList) {

                            }
                        }*//*);*/

                        return subscribe;

    }
}
