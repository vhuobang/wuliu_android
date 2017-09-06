package com.arkui.transportation.activity.waybill;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.arkui.fz_tools.ui.BaseActivity;
import com.arkui.transportation.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DriverLocationActivity extends BaseActivity {

    @BindView(R.id.map)
    MapView mMap;

    public static void openActivity(Context mContext ,String log,String lat){
        Intent intent = new Intent(mContext,DriverLocationActivity.class);
        intent.putExtra("log",log);
        intent.putExtra("lat",lat);
        mContext.startActivity(intent);
    }
    @Override
    public void setRootView() {
        setContentView(R.layout.activity_driver_location);
        setTitle("司机位置");
    }

    @Override
    public void initView() {
        super.initView();
        ButterKnife.bind(this);
    }
    @Override
    public void initView(Bundle savedInstanceState) {
        super.initView(savedInstanceState);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMap.onCreate(savedInstanceState);
        double log = Double.parseDouble(getIntent().getStringExtra("log"));
        double lat = Double.parseDouble(getIntent().getStringExtra("lat"));
        LatLng latLng = new LatLng(log,lat);
        AMap map = mMap.getMap();
        UiSettings uiSettings = map.getUiSettings();
        uiSettings.setZoomControlsEnabled(false);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16f));
        final Marker marker = map.addMarker(new MarkerOptions().position(latLng));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMap.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMap.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMap.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMap.onSaveInstanceState(outState);
    }
}
