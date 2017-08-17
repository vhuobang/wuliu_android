package com.arkui.fz_tools.dialog;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arkui.fz_tools.R;
import com.arkui.fz_tools.adapter.StringAdapter;
import com.arkui.fz_tools.listener.OnVehicleTypeClickListener;
import com.arkui.fz_tools.view.WheelView;

import java.util.List;

/**
 * Created by nmliz on 2017/6/22.
 */

public class SelectTypePicker extends BaseDialogFragment implements View.OnClickListener {

    private WheelView mWvType;
    private List<String> mStringList;
    private String mItem;
    private OnVehicleTypeClickListener onVehicleTypeClickListener;

    private String mTitle;

    public void setOnTypeClickListener(OnVehicleTypeClickListener onVehicleTypeClickListener) {
        this.onVehicleTypeClickListener = onVehicleTypeClickListener;
    }

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.layout_vehicle_type, container, false);
    }

    @Override
    protected void initView(View mRootView) {
        mWvType = (WheelView) mRootView.findViewById(R.id.wv_type);
        StringAdapter stringAdapter=new StringAdapter(getActivity(),mStringList);
        mWvType.setViewAdapter(stringAdapter);

        TextView mTvTitle= (TextView) mRootView.findViewById(R.id.tv_title);
        mTvTitle.setText(mTitle);
        mRootView.findViewById(R.id.tv_cancel).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_confirm).setOnClickListener(this);
    }

    @Override
    public int getGravity() {
        return Gravity.BOTTOM;
    }

    public SelectTypePicker setData(List<String> stringList) {
        this.mStringList = stringList;
        return this;
    }

    public SelectTypePicker setTitle(String title) {
        mTitle = title;
        return this;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_cancel) {
        } else if (id == R.id.tv_confirm) {
            String selectedItem = mStringList.get(mWvType.getCurrentItem());
            if (onVehicleTypeClickListener != null) {
                onVehicleTypeClickListener.OnVehicleTypeClick(selectedItem,mWvType.getCurrentItem());
            }
        }
        dismiss();
    }
}
