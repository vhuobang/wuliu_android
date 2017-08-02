package com.arkui.fz_tools.dialog.old;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;

import com.arkui.fz_tools.dialog.old.bottom.ActionSheetDialog;


/**
 * Created by 任少东 on 2016/6/8 0008.15:014
 */
public class SelectPicDialog extends ActionSheetDialog {
   // private OnDialogClickListener onDialogClickListener;


    public SelectPicDialog(Context context) {
        super(context);
        initView(context);
    }

    protected void initView(Context context) {
        String[] stringItems = {"拍照", "从手机相册选择"};
        setItems(stringItems);
        //没有标题
        isTitleShow(false);

        //showAnim(mInnerShowAnim);
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

  /*  public void setOnDialogClickListener(OnDialogClickListener onDialogClickListener) {
        this.onDialogClickListener = onDialogClickListener;
    }

    public interface OnDialogClickListener {
        void onDialogClick(int position);
    }*/
}
