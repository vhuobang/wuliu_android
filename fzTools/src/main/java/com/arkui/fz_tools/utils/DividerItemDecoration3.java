package com.arkui.fz_tools.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

/**
 * Created by nmliz on 2016/9/5.
 */
public class DividerItemDecoration3 extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable mDivider;

    private int mOrientation;
    private Paint mPaint;

    public DividerItemDecoration3(Context context, int orientation) {
        final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();
        setOrientation(orientation);
        mPaint = new Paint();
        mPaint.setColor(0x99FF0000);

    }
    public DividerItemDecoration3(Context context, int orientation, int drawable) {
        mDivider = ContextCompat.getDrawable(context, drawable);
        setOrientation(orientation);
        mPaint = new Paint();
        mPaint.setColor(0x99FF0000);
    }
    public void setOrientation(int orientation) {
        if (orientation != HORIZONTAL_LIST && orientation != VERTICAL_LIST) {
            throw new IllegalArgumentException("invalid orientation");
        }
        mOrientation = orientation;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent) {
        //Log.v("recyclerview - itemdecoration", "onDraw()");

        if (mOrientation == VERTICAL_LIST) {
            drawVertical(c, parent);
        } else {
            drawHorizontal(c, parent);
        }

    }


    public void drawVertical(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            RecyclerView v = new RecyclerView(parent.getContext());
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    public void drawHorizontal(Canvas c, RecyclerView parent) {
        final int top = parent.getPaddingTop();
        final int bottom = parent.getHeight() - parent.getPaddingBottom();

        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int left = child.getRight() + params.rightMargin;
            final int right = left + mDivider.getIntrinsicHeight();
            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, int itemPosition, RecyclerView parent) {
        if (mOrientation == VERTICAL_LIST) {

            Log.e("TAG",itemPosition+"");
           // Log.e("TAG", parent.getAdapter().getItemCount()+"");
            if (itemPosition == parent.getAdapter().getItemCount()-1) {
                outRect.set(0, 0, 0, 0);
            } else {

                if(itemPosition==0){
                    outRect.set(0,0,  0,0);
                }else{
                    outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
                }
                //outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            }

        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }
/*
    @Override
    public void onDrawOver(Canvas c, RecyclerView parent) {
        final int left = parent.getPaddingLeft()-20;
        final int right = parent.getWidth() - parent.getPaddingRight()-20;
        final int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            params.setMargins(5,5,5,5);
            final int top = child.getBottom() + params.bottomMargin +
                    Math.round(ViewCompat.getTranslationY(child));
            final int bottom = top + 50;
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }*/
}
