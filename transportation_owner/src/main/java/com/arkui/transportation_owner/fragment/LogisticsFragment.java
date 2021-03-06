package com.arkui.transportation_owner.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.arkui.fz_net.utils.RxBus;
import com.arkui.fz_tools.entity.LogisticsBusEntity;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.activity.logistics.CityPickerActivity;
import com.arkui.transportation_owner.activity.logistics.CompanyDetailActivity;
import com.arkui.transportation_owner.activity.logistics.PersonageDetailActivity;
import com.arkui.transportation_owner.activity.logistics.SearchLogisticsActivity;
import com.arkui.transportation_owner.adapter.LogisticsAdapter;
import com.arkui.transportation_owner.entity.LogisticalListEntity;
import com.arkui.transportation_owner.mvp.LogisticsPresenter;
import com.arkui.transportation_owner.view.LogisticsView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 物流
 */
public class LogisticsFragment extends BaseFragment implements OnBindViewHolderListener<String>, OnRefreshListener, LogisticsView, AMapLocationListener, BaseQuickAdapter.RequestLoadMoreListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.OnItemClickListener {

    @BindView(R.id.tv_city)
    TextView mTvCity;
    @BindView(R.id.rl_list)
    PullRefreshRecyclerView mRlList;
    private LogisticsAdapter mLogisticsAdapter;
    private LogisticsPresenter mLogisticsPresenter;
    private RxPermissions mRxPermissions;
    //声明AMapLocationClient类对象
    public AMapLocationClient mLocationClient = null;
    //声明AMapLocationClientOption对象
    public AMapLocationClientOption mLocationOption = null;
    private int mPage = 1;
    private String mCity;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_logistics, container, false);
    }

    @Override
    protected void initView(final View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);

        mLogisticsAdapter = new LogisticsAdapter();
        mRlList.setLinearLayoutManager();
        mRlList.setAdapter(mLogisticsAdapter);
        mRlList.setOnRefreshListener(this);
        mRlList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));

        mLogisticsAdapter.setOnItemClickListener(this);
        mLogisticsAdapter.setOnItemChildClickListener(this);
        mLogisticsAdapter.setOnLoadMoreListener(this, mRlList.getRecyclerView());
        //初始化定位
        mLocationClient = new AMapLocationClient(mContext);
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
        //获取一次定位结果：
        //该方法默认为false。
        mLocationOption.setOnceLocation(true);
        //获取最近3s内精度最高的一次定位结果：
        //设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
        mLocationOption.setOnceLocationLatest(true);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);

        //回调收藏状态 改变监听
        Disposable subscribe = RxBus.getDefault().toObservable(LogisticsBusEntity.class).subscribe(new Consumer<LogisticsBusEntity>() {
            @Override
            public void accept(LogisticsBusEntity logisticsBusEntity) throws Exception {
                if(LogisticsBusEntity.LOGISTICS==logisticsBusEntity.getType()){
                    String status = logisticsBusEntity.getStatus();
                    if (TextUtils.isEmpty(status)){
                        return;
                    }
                    mLogisticsAdapter.getItem(logisticsBusEntity.getPosition()).setStatus(status);
                    mLogisticsAdapter.notifyItemChanged(logisticsBusEntity.getPosition());
                }
            }
        });

        mDisposables.add(subscribe);
    }

    @Override
    protected void initData() {
        super.initData();
        mLogisticsPresenter = new LogisticsPresenter(this, getActivity());
        mRxPermissions = new RxPermissions(getActivity());

        if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
           /* //申请WRITE_EXTERNAL_STORAGE权限
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},
                    WRITE_COARSE_LOCATION_REQUEST_CODE);//自定义的code*/
            Disposable subscribe = mRxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION).subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean aBoolean) throws Exception {
                    if (!aBoolean) {
                        Toast.makeText(mContext, "没有权限，无法正常服务，建议你允许", Toast.LENGTH_SHORT).show();
                        mRlList.loadFail("缺少必要定位权限！");
                    } else {
                        //开始定位获取位置
                        //启动定位
                        mLocationClient.startLocation();
                    }
                }
            });

            mDisposables.add(subscribe);
        } else {
            //启动定位
            mLocationClient.startLocation();
        }
    }

    @OnClick({R.id.tv_city, R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_city:
                Intent intent=new Intent(mContext,CityPickerActivity.class);
                startActivityForResult(intent,101);
                break;
            case R.id.tv_search:
                showActivity(SearchLogisticsActivity.class);
                break;
        }

    }

    @Override
    public void convert(BaseViewHolder helper, String item) {
        helper.addOnClickListener(R.id.iv_head);
    }


    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
        if (mCity == null) {
            mRlList.refreshComplete();
            return;
        }
        mPage = 1;
        mLogisticsPresenter.postLogisticsList(mCity, "", mPage);
    }

    @Override
    public void onSucceed(List<LogisticalListEntity> logisticalList) {
        if (mPage == 1) {
            mLogisticsAdapter.setNewData(logisticalList);
            mRlList.refreshComplete();
            mLogisticsAdapter.setEnableLoadMore(logisticalList.size() == 20);
        } else {
            mLogisticsAdapter.addData(logisticalList);
            mLogisticsAdapter.loadMoreComplete();
        }
        mPage += 1;
    }

    @Override
    public void onError() {

        if (mPage == 1) {
            mLogisticsAdapter.setNewData(null);
            mRlList.loadFail();
        } else {
            mLogisticsAdapter.loadMoreEnd();
        }
        mRlList.refreshComplete();
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                //可在其中解析amapLocation获取相应内容。
                //Log.e("fz",aMapLocation.toString());
                mCity = aMapLocation.getCity().replace("市", "");
                mLogisticsPresenter.postLogisticsList(mCity, "", mPage);
                mTvCity.setText(mCity);
            } else {
                Toast.makeText(mContext, "定位失败", Toast.LENGTH_SHORT).show();
                mRlList.loadFail("定位失败");
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
        if (mLogisticsPresenter != null) {
            mLogisticsPresenter.onDestroy();
        }
    }

    @Override
    public void onLoadMoreRequested() {
        mLogisticsPresenter.postLogisticsList(mCity, "", 1);
    }

    @Override
    public void onSucceed(LogisticalListEntity logisticalDetails) {

    }

    @Override
    public void onSucceed(int position) {
        mLogisticsAdapter.getItem(position).setStatus("1".equals(mLogisticsAdapter.getItem(position).getStatus())?"0":"1");
        mLogisticsAdapter.notifyItemChanged(position);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==101){
            if(resultCode== Activity.RESULT_OK){
                mCity=data.getStringExtra(CityPickerActivity.KEY_PICKED_CITY);
                mPage=1;
                mRlList.starLoad();
                mLogisticsPresenter.postLogisticsList(mCity,"",mPage);
                mTvCity.setText(mCity);
            }
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        String id = mLogisticsAdapter.getItem(position).getId();
        mLogisticsPresenter.postCollectionLogistical(id,position);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String id = mLogisticsAdapter.getItem(position).getId();
        String name = mLogisticsAdapter.getItem(position).getName();
        String authTatus = mLogisticsAdapter.getItem(position).getAuthStatus();
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.putExtra("title", name);
        intent.putExtra("position", position);
        intent.putExtra("type", LogisticsBusEntity.LOGISTICS);

        if ("1".equals(authTatus)) {
            //个人
            intent.setClass(mContext, PersonageDetailActivity.class);
            startActivity(intent);
        } else if ("2".equals(authTatus)) {
            //公司
            intent.setClass(mContext, CompanyDetailActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(mContext, "这是一条不规范的数据", Toast.LENGTH_SHORT).show();
        }
    }
}
