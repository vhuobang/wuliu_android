package com.arkui.fz_tools.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.arkui.fz_tools.R;


/**
 * Created by 任少东 on 2016/07/15 14:45
 */
public class RIndicatorView extends View {
    private Paint paint;
    private int num;
    private int itemWidth;
    private int index;
    private int colorNormal;
    private int colorSelect;

    public RIndicatorView(Context context) {
        this(context, null);
    }

    public RIndicatorView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RIndicatorView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RIndicatorView, defStyleAttr, 0);
        num = array.getInt(R.styleable.RIndicatorView_number, 0);
        itemWidth = array.getDimensionPixelSize(R.styleable.RIndicatorView_itemWidth, 30);
        colorNormal = array.getColor(R.styleable.RIndicatorView_colorNormal, 0x88ffffff);
        colorSelect = array.getColor(R.styleable.RIndicatorView_colorSelect, 0xffffffff);
        array.recycle();
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = (num * 2 - 1) * itemWidth;
        setMeasuredDimension(width, itemWidth);
    }

    public void setNum(int num) {
        this.num = num;
        //invalidate();
        requestLayout();
    }

    public void setIndex(int index) {
        this.index = index;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float x;
        float y = itemWidth / 2;
        for (int i = 0; i < num; i++) {
            x = (i * 2 + 0.5f) * itemWidth;
            if (i == index) {
                paint.setColor(colorSelect);
            } else {
                paint.setColor(colorNormal);
            }
            canvas.drawCircle(x, y, itemWidth / 2, paint);
        }
    }
}
