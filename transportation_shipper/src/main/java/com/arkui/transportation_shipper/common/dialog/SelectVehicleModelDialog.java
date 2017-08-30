package com.arkui.transportation_shipper.common.dialog;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arkui.fz_tools.dialog.BaseDialogFragment;
import com.arkui.fz_tools.view.WheelView;
import com.arkui.transportation_shipper.R;
import com.arkui.transportation_shipper.common.entity.VehicleModelEntity;
import com.arkui.transportation_shipper.owner.adapter.VehicleModelAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmliz on 2017/8/29.
 * 车型 选择器
 */

public class SelectVehicleModelDialog extends BaseDialogFragment implements View.OnClickListener {

    private WheelView mWvType;
    private List<VehicleModelEntity> mVehicleModelList=new ArrayList<>();
    private OnSelectVehicleModelListener mOnSelectVehicleModelListener;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.layout_vehicle_type, container, false);
    }

    @Override
    protected void initView(View mRootView) {
        mWvType = (WheelView) mRootView.findViewById(R.id.wv_type);
        VehicleModelAdapter vehicleModelAdapter=new VehicleModelAdapter(getActivity(),mVehicleModelList);
        mWvType.setViewAdapter(vehicleModelAdapter);

        mRootView.findViewById(R.id.tv_cancel).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_confirm).setOnClickListener(this);
    }



    public void setVehicleModelList(List<VehicleModelEntity> vehicleModelList) {
        this.mVehicleModelList = vehicleModelList;
    }

    public List<VehicleModelEntity> getVehicleModelList() {
        return mVehicleModelList;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_cancel) {
        } else if (id == R.id.tv_confirm) {
            //String selectedItem = mStringList.get(mWvType.getCurrentItem());
            VehicleModelEntity vehicleModelEntity = mVehicleModelList.get(mWvType.getCurrentItem());
            if (mOnSelectVehicleModelListener != null) {
                mOnSelectVehicleModelListener.selectVehicleModel(vehicleModelEntity.getTruck_type(),vehicleModelEntity.getId());
            }
        }
        dismiss();
    }

    @Override
    public int getGravity() {
        return Gravity.BOTTOM;
    }

    public void setOnSelectVehicleModelListener(OnSelectVehicleModelListener mOnSelectVehicleModelListener) {
        this.mOnSelectVehicleModelListener = mOnSelectVehicleModelListener;
    }

    public interface OnSelectVehicleModelListener{
        void selectVehicleModel(String itemText,String itemId);
    }
}
