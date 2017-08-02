package com.arkui.fz_tools.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.arkui.fz_tools.R;

/**
 * Created by nmliz on 2016/9/5.
 */
public class HistorySearchDividerItem extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = new int[]{
            android.R.attr.listDivider
    };

    public static final int HORIZONTAL_LIST = LinearLayoutManager.HORIZONTAL;

    public static final int VERTICAL_LIST = LinearLayoutManager.VERTICAL;

    private Drawable mDivider;

    private int mOrientation;

    Context mContext;

    public HistorySearchDividerItem(Context context, int orientation) {
       /* final TypedArray a = context.obtainStyledAttributes(ATTRS);
        mDivider = a.getDrawable(0);
        a.recycle();*/
        mContext = context;
        mDivider = ContextCompat.getDrawable(context, R.drawable.divider_recycler_1dp);
        setOrientation(orientation);
    }

    public HistorySearchDividerItem(Context context, int orientation, int drawable) {
        mDivider = ContextCompat.getDrawable(context, drawable);
        setOrientation(orientation);
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
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child
                    .getLayoutParams();
            final int top = child.getBottom() + params.bottomMargin;
            final int bottom = top + mDivider.getIntrinsicHeight();
            /*mDivider.setBounds(dp2px(15), top, right, bottom);
            mDivider.draw(c);*/
            int itemCount = parent.getAdapter().getItemCount();
            if (i ==itemCount - 1)//||i ==itemCount - 2)
             {
                mDivider.setBounds(dp2px(15), top, right, 0);
                mDivider.draw(c);
            } else {
                mDivider.setBounds(dp2px(15), top, right, bottom);
                mDivider.draw(c);
            }
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

            Log.e("TAG", itemPosition + "");
            // Log.e("TAG", parent.getAdapter().getItemCount()+"");
            if (itemPosition == parent.getAdapter().getItemCount()) {
                outRect.set(0, 0, 0, 0);
            } else {

                /*if(itemPosition==0){
                    outRect.set(0,mDivider.getIntrinsicHeight(),  0, mDivider.getIntrinsicHeight());
                }else{
                    outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
                }*/
                outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());

                //outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
            }

        } else {
            outRect.set(0, 0, mDivider.getIntrinsicWidth(), 0);
        }
    }

    /**
     * dp to px
     */
    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }
}
