package com.arkui.fz_tools.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

/**
 * Created by Administrator on 2016/5/30 0030.
 */
public class NoTouchScrollerListView extends ListView {
    public NoTouchScrollerListView(Context context) {
        super(context);
    }

    public NoTouchScrollerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoTouchScrollerListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return false;
    }
}
