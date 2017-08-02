package com.arkui.fz_tools.dialog.old;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.arkui.fz_tools.dialog.old.bottom.ActionSheetDialog;


/**
 * Created by hubo on 2016/11/24.
 */
public class SelectDialog extends ActionSheetDialog {

    private OnDialogClickListener onDialogClickListener;

    public SelectDialog(Context context, String[] strs) {
        super(context);
        initView(strs);
    }

    private void initView(String[] strs) {
        setItems(strs);
        isTitleShow(false);
    }

    @Override
    protected void onStart() {
        super.onStart();
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);

        mLlTop.setLayoutParams(layoutParams);
        //在底部显示
        mLlTop.setGravity(Gravity.BOTTOM);
        getWindow().setGravity(Gravity.BOTTOM);
    }

    public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }

    public interface OnDialogClickListener {
        void onDialogClick(int position);
    }
}
