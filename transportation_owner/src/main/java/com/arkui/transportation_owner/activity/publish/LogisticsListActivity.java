package com.arkui.transportation_owner.activity.publish;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.arkui.fz_net.utils.RxBus;
import com.arkui.fz_tools.adapter.CommonAdapter;
import com.arkui.fz_tools.entity.LogisticsBusEntity;
import com.arkui.fz_tools.listener.OnBindViewHolderListener;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.fz_tools.utils.DividerItemDecoration;
import com.arkui.fz_tools.utils.LogUtil;
import com.arkui.fz_tools.view.PullRefreshRecyclerView;
import com.arkui.transportation_owner.R;
import com.arkui.transportation_owner.activity.logistics.CityPickerActivity;
import com.arkui.transportation_owner.activity.logistics.CompanyDetailActivity;
import com.arkui.transportation_owner.activity.logistics.PersonageDetailActivity;
import com.arkui.transportation_owner.activity.logistics.SearchLogisticsActivity;
import com.arkui.transportation_owner.adapter.LogisticsAdapter;
import com.arkui.transportation_owner.entity.LogisticalListEntity;
import com.arkui.transportation_owner.entity.RefreshLogistics;
import com.arkui.transportation_owner.mvp.LogisticsPresenter;
import com.arkui.transportation_owner.utils.ListData;
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
 * 未收藏物流公司列表
 */
public class LogisticsListActivity extends BaseActivity implements OnRefreshListener, LogisticsView, BaseQuickAdapter.OnItemClickListener, BaseQuickAdapter.OnItemChildClickListener, BaseQuickAdapter.RequestLoadMoreListener, AMapLocationListener {

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
    private boolean mIsChange=false;
    @Override
    public void setRootView() {
        setContentViewNoTitle(R.layout.activity_logistics_list);
        //setTitle("物流公司列表页");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
        mLogisticsAdapter = new LogisticsAdapter();
        mRlList.setLinearLayoutManager();
        mRlList.setAdapter(mLogisticsAdapter);
        mRlList.setOnRefreshListener(this);
        mRlList.addItemDecoration(new DividerItemDecoration(mActivity, DividerItemDecoration.VERTICAL_LIST));

        mLogisticsAdapter.setOnItemClickListener(this);
        mLogisticsAdapter.setOnItemChildClickListener(this);
        mLogisticsAdapter.setOnLoadMoreListener(this, mRlList.getRecyclerView());

        //初始化定位
        mLocationClient = new AMapLocationClient(mActivity);
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
    }

    @Override
    public void initData() {
        super.initData();
        mLogisticsPresenter = new LogisticsPresenter(this, this);

        if (ContextCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            Disposable subscribe = mRxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION).subscribe(new Consumer<Boolean>() {
                @Override
                public void accept(Boolean aBoolean) throws Exception {
                    if (!aBoolean) {
                        Toast.makeText(mActivity, "没有权限，无法正常服务，建议你允许", Toast.LENGTH_SHORT).show();
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
                startActivityForResult(new Intent(mActivity,CityPickerActivity.class),101);
                break;
            case R.id.tv_search:
                // showActivity(SearchLogisticsActivity.class);
                Intent intent = new Intent(mActivity, SearchLogisticsActivity.class);
                intent.putExtra("isSelect", true);
                startActivity(intent);
                break;
        }

    }

    @OnClick(R.id.tv_next)
    public void onClick() {
       // showActivity(PublishDeclareActivity.class);
        if(mIsChange){
            //发送给下一页 让他执行刷新
            RxBus.getDefault().post(new RefreshLogistics(101));
        }
        finish();
    }

    @Override
    public void onRefresh(RefreshLayout refreshlayout) {
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
            mRlList.loadFail();
        } else {
            mLogisticsAdapter.loadMoreEnd();
        }
        mRlList.refreshComplete();
    }


    @Override
    public void onSucceed(int position) {
        mIsChange=true;
        mLogisticsAdapter.getItem(position).setStatus("1".equals(mLogisticsAdapter.getItem(position).getStatus())?"0":"1");
        mLogisticsAdapter.notifyItemChanged(position);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        String id = mLogisticsAdapter.getItem(position).getId();
        String name = mLogisticsAdapter.getItem(position).getName();
        String authTatus = mLogisticsAdapter.getItem(position).getAuthTatus();
        Intent intent = new Intent();
        intent.putExtra("id", id);
        intent.putExtra("title", name);
        intent.putExtra("position", position);
        intent.putExtra("type", LogisticsBusEntity.LOGISTICS);

        if ("1".equals(authTatus)) {
            //个人
            intent.setClass(mActivity, PersonageDetailActivity.class);
            startActivity(intent);
        } else if ("2".equals(authTatus)) {
            //公司
            intent.setClass(mActivity, CompanyDetailActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(mActivity, "这是一条不规范的数据", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
        String id = mLogisticsAdapter.getItem(position).getId();
        mLogisticsPresenter.postCollectionLogistical(id,position);
    }

    @Override
    public void onLoadMoreRequested() {
        mLogisticsPresenter.postLogisticsList(mCity, "", mPage);
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
                Toast.makeText(mActivity, "定位失败", Toast.LENGTH_SHORT).show();
            }
        }
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
    public void onSucceed(LogisticalListEntity logisticalDetails) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mIsChange){
            //发送给下一页 让他执行刷新
            RxBus.getDefault().post(new RefreshLogistics(101));
        }
        mLogisticsPresenter.onDestroy();
        mLocationClient.stopLocation();
        mLocationClient.onDestroy();
    }
}
