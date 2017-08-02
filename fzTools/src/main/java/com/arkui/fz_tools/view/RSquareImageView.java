package com.arkui.fz_tools.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.arkui.fz_tools.R;


/**
 * Created by 任少东 on 2016/5/30 0030.
 */
public class RSquareImageView extends ImageView {
    private float scale = 1;

    public RSquareImageView(Context context) {
        this(context, null);
    }

    public RSquareImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RSquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RSquareImageView, defStyleAttr, 0);
        scale = array.getFloat(R.styleable.RSquareImageView_hwScale, 1);
        array.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int height = (int) (width * scale);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, mode);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
