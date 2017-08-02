package com.arkui.fz_tools.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * Created by nmliz on 2017/2/7.
 */

public class RateTextCircularProgressBar extends FrameLayout {


    private CircularProgressBar mCircularProgressBar;
    private TextView mRateText;

  /*  public RateTextCircularProgressBar(Context mContext) {
        super(mContext);
       // init();
    }*/

    public RateTextCircularProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        mCircularProgressBar = new CircularProgressBar(getContext(),attrs);
        this.addView(mCircularProgressBar);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        lp.gravity = Gravity.CENTER;
        mCircularProgressBar.setLayoutParams(lp);

        mRateText = new TextView(getContext());
        this.addView(mRateText);
        mRateText.setLayoutParams(lp);
        mRateText.setGravity(Gravity.CENTER);
       // mRateText.setTextColor(Color.BLACK);
        mRateText.setTextSize(20);
        mRateText.setText("1");
        //  mCircularProgressBar.setOnProgressChangeListener(this);
    }

  /*  *//**
     * 设置最大值
     *//*
    public void setMax(int max) {
        mCircularProgressBar.setProgress(max);
    }*/

    /**
     * 设置进度
     *
     * @param progress
     */
    public void setProgress(int progress) {
        mCircularProgressBar.setProgress(progress);
    }

    /**
     * 得到 CircularProgressBar 对象，用来设置其他的一些属性
     *
     * @return
     */
    public CircularProgressBar getCircularProgressBar() {
        return mCircularProgressBar;
    }

    /**
     * 设置中间进度百分比文字的尺寸
     *
     * @param size
     */
    public void setTextSize(float size) {
        mRateText.setTextSize(size);
    }

    /**
     * 设置中间进度百分比文字的颜色
     *
     * @param color
     */
    public void setTextColor(int color) {
        mRateText.setTextColor(color);
    }

   /* @Override
    public void onChange(int duration, int progress, float rate) {
        mRateText.setText(String.valueOf( (int)(rate * 100 ) + "%"));
    }*/

}
