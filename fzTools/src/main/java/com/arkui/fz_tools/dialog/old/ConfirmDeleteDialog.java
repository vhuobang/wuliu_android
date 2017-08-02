package com.arkui.fz_tools.dialog.old;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.arkui.fz_tools.R;
import com.arkui.fz_tools.dialog.old.listener.OnBtnClickL;

/**
 * Created by nmliz on 2017/2/16.
 */

public class ConfirmDeleteDialog extends RBaseDialog implements View.OnClickListener {

    public void setOnBtnClickL(OnBtnClickL onBtnClickL) {
        this.onBtnClickL = onBtnClickL;
    }

    OnBtnClickL onBtnClickL;

    public ConfirmDeleteDialog(Context context) {
        super(context);
    }

    @Override
    protected View getContentView() {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_confirm_delete, null);

        TextView mTvConfirm = (TextView) view.findViewById(R.id.tv_confirm);
        TextView mTvCancel = (TextView) view.findViewById(R.id.tv_cancel);
        mTvConfirm.setOnClickListener(this);
        mTvCancel.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_confirm) {
            if (onBtnClickL != null) {
                onBtnClickL.onBtnClick();
                dismiss();
            }
        } else if (v.getId()== R.id.tv_cancel){
            dismiss();
        }
    }
}
