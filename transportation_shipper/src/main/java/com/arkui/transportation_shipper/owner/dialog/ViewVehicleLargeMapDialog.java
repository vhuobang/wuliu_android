package com.arkui.transportation_shipper.owner.dialog;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.arkui.transportation_shipper.R;

/**
 * Created by nmliz on 2017/6/21.
 * 显示大图的 对话框
 */

public class ViewVehicleLargeMapDialog extends DialogFragment {

   /* public ViewVehicleLargeMapDialog(@NonNull Context context) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_view_vehicle_large_map);
        int width = getContext().getResources().getDisplayMetrics().widthPixels;
        getWindow().getAttributes().width = (int) (width);
        getWindow().getAttributes().dimAmount = 0.8f;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));// android:windowBackground
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);// android:backgroundDimEnabled默认是true的
    }*/



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.TransparentAppTheme);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_view_vehicle_large_map, container, false);

    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics dm = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        //设置背景
        getDialog().setCanceledOnTouchOutside(true);
        Window window = getDialog().getWindow();

        window.getAttributes().dimAmount = 0.8f;
        window.setLayout(dm.widthPixels, getDialog().getWindow().getAttributes().height);
        window.getAttributes().gravity = getGravity();
    }

    private int getGravity() {
        return Gravity.CENTER;
    }
}
