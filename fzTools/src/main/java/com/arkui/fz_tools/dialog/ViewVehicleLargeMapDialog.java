package com.arkui.fz_tools.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

import com.arkui.fz_tools.R;
import com.arkui.fz_tools.utils.GlideUtils;


/**
 * Created by nmliz on 2017/6/21.
 * 显示大图的 对话框
 */

public class ViewVehicleLargeMapDialog extends BaseDialogFragment {

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

    String mImgUrl;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_FRAME, R.style.TransparentAppTheme);
    }

    @Override
    protected View inflaterView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {
        return inflater.inflate(R.layout.activity_view_vehicle_large_map, container, false);
    }

    /* @Nullable
     @Override
     public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
         return inflater.inflate(R.layout.activity_view_vehicle_large_map, container, false);

     }
 */
    @Override
    protected void initView(View mRootView) {
        ImageView mIvImg = (ImageView) mRootView.findViewById(R.id.iv_img);
        GlideUtils.getInstance().load(getActivity(), mImgUrl, mIvImg);
    }

    public ViewVehicleLargeMapDialog setImgUrl(String url) {
        mImgUrl = url;
        return this;
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

    @Override
    public int getGravity() {
        return Gravity.CENTER;
    }
}
