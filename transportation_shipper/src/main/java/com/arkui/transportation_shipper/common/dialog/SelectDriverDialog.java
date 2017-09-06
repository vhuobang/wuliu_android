package com.arkui.transportation_shipper.common.dialog;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arkui.fz_tools.dialog.BaseDialogFragment;
import com.arkui.fz_tools.view.WheelView;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.entity.DriverListEntity;
import com.arkui.transportation_shipper.common.entity.VehicleModelEntity;
import com.arkui.transportation_shipper.owner.adapter.SelectDriverAdapter;
import com.arkui.transportation_shipper.owner.adapter.VehicleModelAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmliz on 2017/8/29.
 * 司机 选择器
 */

public class SelectDriverDialog extends BaseDialogFragment implements View.OnClickListener {

    private WheelView mWvType;
    private List<DriverListEntity> mDriverListEntity =new ArrayList<>();
    private OnSelectDriverListener mOnSelectDriverListener;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.layout_vehicle_type, container, false);
    }

    @Override
    protected void initView(View mRootView) {
        mWvType = (WheelView) mRootView.findViewById(R.id.wv_type);
        SelectDriverAdapter selectDriverAdapter=new SelectDriverAdapter(getActivity(), mDriverListEntity);
        mWvType.setViewAdapter(selectDriverAdapter);

        mRootView.findViewById(R.id.tv_cancel).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_confirm).setOnClickListener(this);
        TextView tv_title= (TextView) mRootView.findViewById(R.id.tv_title);
        tv_title.setText("司机选择");
    }



    public void setDriverList(List<DriverListEntity> vehicleModelList) {
        this.mDriverListEntity = vehicleModelList;
    }

    public List<DriverListEntity> getDriverList() {
        return mDriverListEntity;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_cancel) {
        } else if (id == R.id.tv_confirm) {
            DriverListEntity driverListEntity = mDriverListEntity.get(mWvType.getCurrentItem());
            if (mOnSelectDriverListener != null) {
                mOnSelectDriverListener.selectDriverModel(driverListEntity.getName(),driverListEntity.getDriver_id());
            }
        }
        dismiss();
    }

    @Override
    public int getGravity() {
        return Gravity.BOTTOM;
    }

    public void setOnDriverListener(OnSelectDriverListener mOnSelectVehicleModelListener) {
        this.mOnSelectDriverListener = mOnSelectVehicleModelListener;
    }

    public interface OnSelectDriverListener {
        void selectDriverModel(String itemText, String itemId);
    }
}
