package com.arkui.fz_tools.dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.arkui.fz_tools.R;
import com.arkui.fz_tools.adapter.CityAdapter;
import com.arkui.fz_tools.entity.City;
import com.arkui.fz_tools.listener.OnWheelChangedListener;
import com.arkui.fz_tools.utils.FileUtil;
import com.arkui.fz_tools.view.WheelView;

import java.util.List;

/**
 * Created by nmliz on 2017/6/23.
 */

public class AddressPicker extends BaseDialogFragment implements OnWheelChangedListener, View.OnClickListener {

    private List<City> cities;
    private WheelView mWvProvince;
    private WheelView mWvCity;
    private OnEnsureClickListener onEnsureClickListener;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.dialog_address, container, false);
    }

    @Override
    protected void initView(View mRootView) {
        mWvProvince = (WheelView) mRootView.findViewById(R.id.wv_province);
        mWvCity = (WheelView) mRootView.findViewById(R.id.wv_city);
      //  wmWvCounty = (WheelView) mRootView.findViewById(R.id.wv_county);

        TextView mtv_cancel= (TextView) mRootView.findViewById(R.id.tv_cancel);
        TextView tv_submit= (TextView) mRootView.findViewById(R.id.tv_submit);
        mtv_cancel.setOnClickListener(this);
        tv_submit.setOnClickListener(this);
        initCity(getActivity());
    }

    private void initCity(Context context) {
       // String string = FileUtil.readAssets(context, "cities.txt");
       // cities = JSON.parseArray(string, City.class);
        CityAdapter provinceAdapter = new CityAdapter(context, cities);
        provinceAdapter.setTextSize(14);
        mWvProvince.setViewAdapter(provinceAdapter);
        mWvCity.setViewAdapter(new CityAdapter(getContext(), cities.get(0).getSub()));
        mWvProvince.addChangingListener(this);
    }

    @Override
    public int getGravity() {
        return Gravity.BOTTOM;
    }

    @Override
    public void onChanged(WheelView wheel, int oldValue, int newValue) {
        mWvCity.setViewAdapter(new CityAdapter(getContext(), cities.get(newValue).getSub()));
        mWvCity.setCurrentItem(0);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.tv_submit){
            if (onEnsureClickListener != null) {
                onEnsureClickListener.onCityClick(getSelect());
                dismiss();
            }
        }
    }

    public interface OnEnsureClickListener {
        void onCityClick(String city);
    }
    public void setOnEnsureClickListener(OnEnsureClickListener onEnsureClickListener) {
        this.onEnsureClickListener = onEnsureClickListener;
    }

    public String getSelect(){
        String[] strings=new String[2];
        int pIndex=mWvProvince.getCurrentItem();
        // strings[0]=cities.get(pIndex).getName();
        strings[0]=cities.get(pIndex).getName();
        if (cities.get(pIndex).getSub()==null){
            strings[1]="";
        } else {
            strings[1] = cities.get(pIndex).getSub().get(mWvCity.getCurrentItem()).getName();
        }
        return strings[0]+"-"+strings[1];
    }


    public void setCities(List<City> cities) {
        this.cities = cities;
    }

}
