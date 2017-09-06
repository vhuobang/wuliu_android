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
import com.arkui.transportation_shipper.common.entity.TruckListEntity;
import com.arkui.transportation_shipper.owner.adapter.SelectVehicleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmliz on 2017/8/29.
 * 司机 选择器
 */

public class SelectVehicleDialog extends BaseDialogFragment implements View.OnClickListener {

    private WheelView mWvType;
    private List<TruckListEntity> mTruckListEntity =new ArrayList<>();
    private OnSelectTruckListListener mOnSelectTruckListListener;

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.layout_vehicle_type, container, false);
    }

    @Override
    protected void initView(View mRootView) {
        mWvType = (WheelView) mRootView.findViewById(R.id.wv_type);
        SelectVehicleAdapter selectVehicleAdapter=new SelectVehicleAdapter(getActivity(), mTruckListEntity);
        mWvType.setViewAdapter(selectVehicleAdapter);

        mRootView.findViewById(R.id.tv_cancel).setOnClickListener(this);
        mRootView.findViewById(R.id.tv_confirm).setOnClickListener(this);
        TextView tv_title= (TextView) mRootView.findViewById(R.id.tv_title);
        tv_title.setText("车辆选择");
    }



    public void setTruckList(List<TruckListEntity> truckListEntities) {
        this.mTruckListEntity = truckListEntities;
    }

    public List<TruckListEntity> getTruckList() {
        return mTruckListEntity;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.tv_cancel) {
        } else if (id == R.id.tv_confirm) {
            TruckListEntity truckListEntity = mTruckListEntity.get(mWvType.getCurrentItem());
            if (mOnSelectTruckListListener != null) {
                mOnSelectTruckListListener.selectTruck(truckListEntity.getLicense_plate(),truckListEntity.getId());
            }
        }
        dismiss();
    }

    @Override
    public int getGravity() {
        return Gravity.BOTTOM;
    }

    public void setOnTruckListListener(OnSelectTruckListListener mOnSelectVehicleModelListener) {
        this.mOnSelectTruckListListener = mOnSelectVehicleModelListener;
    }

    public interface OnSelectTruckListListener {
        void selectTruck(String itemText, String itemId);
    }
}
