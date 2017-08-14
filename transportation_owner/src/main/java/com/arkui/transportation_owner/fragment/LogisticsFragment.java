package com.arkui.transportation_owner.fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.arkui.transportation_owner.activity.logistics.CityPickerActivity;
import com.arkui.fz_tools.ui.BaseFragment;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.activity.logistics.CompanyDetailActivity;
import com.arkui.transportation_owner.activity.logistics.PersonageDetailActivity;
import com.arkui.transportation_owner.activity.logistics.SearchLogisticsActivity;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.transportation_owner.adapter.LogisticsAdapter;
import com.arkui.transportation_owner.entity.LogisticalListEntity;
import com.arkui.transportation_owner.mvp.LogisticsPresenter;
import com.arkui.transportation_owner.utils.ListData;
import com.arkui.transportation_owner.view.LogisticsView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.tbruyelle.rxpermissions2.RxPermissionsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.functions.Consumer;

/**
 * 物流
 */
public class LogisticsFragment extends BaseFragment implements OnBindViewHolderListener<String>, OnRefreshListener, LogisticsView, AMapLocationListener {

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
    private int mPage=1;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.fragment_logistics, container, false);
    }

    @Override
    protected void initView(View parentView) {
        super.initView(parentView);
        ButterKnife.bind(this, parentView);

        mLogisticsAdapter = new LogisticsAdapter();
        mRlList.setLinearLayoutManager();
        mRlList.setAdapter(mLogisticsAdapter);
        mRlList.setOnRefreshListener(this);
        mRlList.addItemDecoration(new DividerItemDecoration(mContext, DividerItemDecoration.VERTICAL_LIST));

        mLogisticsAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                showActivity(PersonageDetailActivity.class);
            }
        });

        mLogisticsAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                showActivity(CompanyDetailActivity.class);
            }
        });
        //初始化定位
        mLocationClient = new AMapLocationClient(mContext);
        //设置定位回调监听
        mLocationClient.setLocationListener(this);
        mLocationOption=new AMapLocationClientOption();
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
        //启动定位
        mLocationClient.startLocation();
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
            mRxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION).subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean aBoolean) throws Exception {
                    if (!aBoolean) {
                        Toast.makeText(mContext, "没有权限，无法正常服务，建议你允许", Toast.LENGTH_SHORT).show();
                        mRlList.loadFail("缺少必要定位权限！");
                    } else {
                        //开始定位获取位置

                    }
                }
            });
        }
    }

    @OnClick({R.id.tv_city, R.id.tv_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_city:
                showActivity(CityPickerActivity.class);
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
        mLogisticsPresenter.postLogisticsList("北京", "", 1);
    }

    @Override
    public void onSucceed(List<LogisticalListEntity> logisticalList) {
        if(mPage==1){
            mLogisticsAdapter.setNewData(logisticalList);
            mRlList.refreshComplete();
        }else{
            mLogisticsAdapter.addData(logisticalList);
            mLogisticsAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
             //可在其中解析amapLocation获取相应内容。
                //Log.e("fz",aMapLocation.toString());
                String  city= aMapLocation.getCity().replace("市", "");
                mLogisticsPresenter.postLogisticsList(city, "", mPage);
            }else {
                Toast.makeText(mContext, "定位失败", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }
}
