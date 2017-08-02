package com.arkui.fz_tools.dialog.old;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.arkui.fz_tools.R;


/**
 * Created by 任少东 on 2016/08/15 10:30
 */
public abstract class BottomEnsureDialog extends RBottomDialog implements View.OnClickListener {


    private TextView title, cancel, ensure;
    private FrameLayout layout;
    public BottomEnsureDialog(Context context) {
        super(context);
    }

    @Override
    protected View getContentView() {
        View view = LayoutInflater.from(context).inflate(R.layout.dialog_ensure, null);
        title = (TextView) view.findViewById(R.id.ensure_title);
        cancel = (TextView) view.findViewById(R.id.ensure_cancel);
        ensure = (TextView) view.findViewById(R.id.ensure_ensure);
        layout = (FrameLayout) view.findViewById(R.id.ensure_content);
        layout.addView(getContent(context));
        cancel.setOnClickListener(this);
        ensure.setOnClickListener(this);
        //设置按钮颜色
        ensure.setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));
        cancel.setTextColor(ContextCompat.getColor(context,R.color.colorPrimary));
        return view;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.ensure_cancel) {
        } else if (i == R.id.ensure_ensure) {
            onEnsure();

        }
        dismiss();
    }

    protected abstract void onEnsure();

    protected abstract View getContent(Context context);
}
