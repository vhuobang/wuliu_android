/* * Copyright (c) 2015, 张涛. * * Licensed under the Apache License, Version 2.0 (the "License"); * you may not use this file except in compliance with the License. * You may obtain a copy of the License at * *      http://www.apache.org/licenses/LICENSE-2.0 * * Unless required by applicable law or agreed to in writing, software * distributed under the License is distributed on an "AS IS" BASIS, * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. * See the License for the specific language governing permissions and * limitations under the License. */package com.arkui.fz_tools.ui;import android.app.Activity;import android.content.Intent;import android.os.Bundle;import android.support.v4.app.Fragment;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;/** * 兼容v4包的Fragment * * @author kymjs (http://www.kymjs.com/) . */public abstract class BaseFragment extends Fragment {    protected View fragmentRootView;    protected Activity mContext;    protected abstract View inflaterView(LayoutInflater inflater,                                         ViewGroup container, Bundle bundle);    /**     * initialization widget, you should look like parentView.findviewbyid(id);     * call method     *     * @param parentView     */    protected void initView(View parentView) {    }    /**     * initialization data     */    protected void initData() {    }    /**     * 当通过changeFragment()显示时会被调用(类似于onResume)     */    public void onChange() {    }    @Override    public View onCreateView(LayoutInflater inflater, ViewGroup container,                             Bundle savedInstanceState) {        mContext = getActivity();        fragmentRootView = inflaterView(inflater, container, savedInstanceState);        initView(fragmentRootView);        initMvp();        initData();        return fragmentRootView;    }    /**     * 初始化 mvp 参数     */    protected void initMvp() {    }    public void showActivity(Class<?> cls) {        Intent intent = new Intent();        intent.setClass(mContext, cls);        startActivity(intent);    }    public void showActivity(Intent it) {        startActivity(it);    }    public void showActivity(Class<?> cls, Bundle extras) {        Intent intent = new Intent();        intent.putExtras(extras);        intent.setClass(mContext, cls);        startActivity(intent);    }}