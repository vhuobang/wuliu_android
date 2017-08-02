package com.arkui.fz_tools.dialog.old;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.Window;

/**
 * Created by 任少东 on 2016/07/29 14:12
 */
public abstract class RBaseDialog extends Dialog {
    protected Context context;
    public RBaseDialog(Context context) {
        super(context);
        this.context=context;
        init();
    }

    private void init() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setContentView(getContentView());
        int width = getContext().getResources().getDisplayMetrics().widthPixels;
        getWindow().getAttributes().width = (int) (width * getWidthScale());
        getWindow().getAttributes().dimAmount = getDimAmount();
        getWindow().getAttributes().gravity = getGravity();
    }

    protected int getGravity() {
        return Gravity.CENTER;
    }

    protected float getWidthScale() {
        return 0.8f;
    }

    protected float getDimAmount() {
        return 0.3f;
    }

    protected abstract View getContentView();
}
